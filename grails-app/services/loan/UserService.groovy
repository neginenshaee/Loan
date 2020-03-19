package loan

import auth.Role
import auth.User
import auth.UserRole
import enums.Status
import enums.UserStatus
import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    def springSecurityService
    def tokenService

    def get(Long id){
        User.findById(id)
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

    def delete(id){
        User.get(id).delete()
    }

    def save(User user){
        user.setStatus(UserStatus.CREATED)
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')

        User savedUser = user.save()
        if (!savedUser.authorities.contains(userRole)) {
            UserRole.create(savedUser, userRole, true)
        }
        tokenService.sendVerificationEmail(savedUser)
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
