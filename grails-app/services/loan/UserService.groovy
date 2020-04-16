package loan

import auth.Role
import auth.User
import auth.UserRole
import commands.UserCommand
import enums.Status
import enums.UserStatus
import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    def springSecurityService
    def tokenService


    static User get(Long id){
        User.get(id)
    }

    def list(){
        List<User> users = User.findAll()
        users
    }

    def static count(){
        User.count()
    }

    def static delete(id){
        User.get(id).delete()
    }

    User save(UserCommand command){
        User user = bindValues(new User(), command)
        User savedUser = user.save()
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')
        if (!savedUser.authorities.contains(userRole)) {
            UserRole.create(savedUser, userRole, true)
        }
        tokenService.sendVerificationEmail(savedUser)
        savedUser
    }

    User update(UserCommand command){
        User user = get(command.id)
        user.setFirstName(command.getFirstName())
        user.setLastName(command.getLastName())
        user.setCountry(command.getCountry())
        user.setAddress(command.getAddress())
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

}
