package loan

import auth.Role
import auth.User
import auth.UserRole
import commands.UserCommand
import enums.Status
import enums.UserStatus
import exceptions.UserNotFoundException
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class UserService {
    def springSecurityService
    def tokenService
    def generalService


    static User get(Long id){
        User.get(id)
    }

    def list(params){
        def users = User.createCriteria().list(max: params.max, offset: params.offset) {
            projections {
                count()
            }
        }
        users
    }

    def static count(){
        User.count()
    }

    def delete(id){
        User user = get(id)
        if(user == null) {
            log.warn(generalService.getMessage("log.user.not.found.message", id))
            throw new UserNotFoundException(String.valueOf(id), generalService.getMessage("user.not.found.message"))
        }else {
            user.delete()
        }
    }

    User save(UserCommand command){
        User user = bindValues(new User(), command)
        User savedUser = user.save()
        log.info(generalService.getMessage('user.created.message',savedUser.getId()))
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')
        if (!savedUser.authorities.contains(userRole)) {
            UserRole.create(savedUser, userRole, true)
            log.info(generalService.getMessage('role.assigned.message', userRole.toString(),savedUser.toString()))
        }
        tokenService.sendVerificationEmail(savedUser)
        log.info(generalService.getMessage('verification.sent.message', userRole.toString(),savedUser.toString()))
        savedUser
    }

    User update(UserCommand command){
        User user = get(command.id)
        user.setFirstName(command.firstName)
        user.setLastName(command.lastName)
        user.setCountry(command.country)
        user.setAddress(command.address)
        if(command.image.size()>0) {
            user.setImage(command.image)
        }
        user.save()
        user
    }

    def updateActivation(UserCommand command){
        User user = get(command.id)
        user.setEnabled(command.enabled)
        user.save()
        user
    }

    def confirmUser(String token){
        VerificationToken retrieved = tokenService.retrieveVerificationToken(token)
        User user
        if(retrieved!=null) {
            user = retrieved.getUser()
            user.setStatus(UserStatus.CONFIRMED)
            user.setEnabled(true)
            user.save()
            log.info(generalService.getMessage('user.verified.message',user,token))
            springSecurityService.reauthenticate(user.username,user.password)
        }
        user
    }

    def updatePassword(UserCommand command){
        User user = springSecurityService.currentUser
        user.setPassword(command.password)
        user.save()
    }

    def resetPassword(String email){
        User user = User.findByEmail(email)
        if(user!=null) {
            tokenService.sendResetPasswordEmail(user)
        }else{
            return null
        }
    }

    def checkResetToken(token){
        VerificationToken retrieved = tokenService.retrieveVerificationToken(token)
        if(retrieved!=null) {
            return retrieved.getUser()
        }
        return null
    }


    def reset(Long id, String password){
        User user = get(id)
        if(user!=null) {
            user.setPassword(password)
            user.save()
            springSecurityService.reauthenticate(user.username,user.password)
        }
        user
    }

    def getCurrentUser(){
        springSecurityService.currentUser
    }

    User bindValues(User user, UserCommand u){
        user.setFirstName(u.firstName)
        user.setLastName(u.lastName)
        user.setUsername(u.username)
        user.setPassword(u.password)
        user.setEmail(u.email)
        user.setCountry(u.country)
        user.setAddress(u.address)
        user.setStatus(u.status)
        user.setEnabled(u.enabled)
        user.setAccountExpired(u.accountExpired)
        user.setAccountLocked(u.accountLocked)
        user.setPasswordExpired(u.passwordExpired)
        user.setDateCreated(u.dateCreated)
        user.setLastUpdated(u.lastUpdated)
        user
    }

    def search(params){
        def result = User.createCriteria().list(max: params.max, offset: params.offset){
            projections {
                count()
            }
            like('username', '%' + params.searchusername + '%')
            like('firstName', '%' + params.searchfirst + '%')
            like('lastName', '%' + params.searchlast + '%')
            like('email', '%' + params.searchemail + '%')
            if(params.searchstatus!='') {
                eq('status', UserStatus.valueOf(params.searchstatus))
            }
            order("username", "asc")
        }

        result
    }

}
