package loan

import auth.User
import grails.gorm.transactions.Transactional

@Transactional
class TokenService {

    def mailService

    def createVerificationToken(User user, String token) {
        VerificationToken newUserToken = new VerificationToken()
        newUserToken.setUser(user)
        newUserToken.setToken(token)
        newUserToken.save();
    }

    def retrieveVerificationToken(String token){
        VerificationToken.findByToken(token);
    }

    def sendVerificationEmail(User user){
        String token = UUID.randomUUID().toString()
        createVerificationToken(user,token)
        String url =  "/confirm/" + token
        String t ="http://localhost:1580/user" + url
        mailService.sendMail {
            to user.getEmail()
            subject "Registration Confirmation"
            text ("User created!"+"\n"+ t)
        }
    }
}
