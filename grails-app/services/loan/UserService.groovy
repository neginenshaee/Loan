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
        List<User> users
        def authorities = springSecurityService.currentUser.authorities
        if(authorities.contains(Role.findByAuthority("ROLE_USER"))) {
            users = User.findAll()
        }else{
            users = User.findAll()
        }
        users
    }

    def count(){
        User.count()
    }

    def static delete(id){
        User.get(id).delete()
    }

    User save(UserCommand command){

        user.setStatus(UserStatus.CREATED)
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')

        User savedUser = user.save()
        if (!savedUser.authorities.contains(userRole)) {
            UserRole.create(savedUser, userRole, true)
        }
        tokenService.sendVerificationEmail(savedUser)
        user
    }

    def confirmUser(String token){
        VerificationToken retrieved = tokenService.retrieveVerificationToken(token)
        User user = retrieved.getUser()
        user.setStatus(UserStatus.CONFIRMED)
        user.save()
    }

    def updatePassword(String old, String newpass, String confirm){

        User user = springSecurityService.currentUser
//        println springSecurityService.encodePassword(old)
//        if (springSecurityService.passwordEncoder.isPasswordValid(user.getPassword(),old,null)){
        if(newpass==confirm){
            println 'the same!'
            user.setPassword(newpass)
//            }
        }
        println user.getPassword()
        user.save()
    }

    def getCurrentUser(){
        springSecurityService.currentUser
    }

}
