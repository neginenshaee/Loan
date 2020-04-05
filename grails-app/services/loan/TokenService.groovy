package loan

import auth.User
import grails.gorm.transactions.Transactional

@Transactional
class TokenService {

    def sendEmailService
    def generalService
    def grailsApplication

    def createVerificationToken(User user, String token) {
        VerificationToken newUserToken = new VerificationToken()
        newUserToken.setUser(user)
        newUserToken.setToken(token)
        newUserToken.save();
    }

    def retrieveVerificationToken(String token){
        VerificationToken.findByToken(token)
    }

    def sendVerificationEmail(User user){
        String token = UUID.randomUUID().toString()
        createVerificationToken(user,token)
        String url =  "/confirm/" + token
        String t ="http://localhost:" + grailsApplication.config."server.port" +"/user" + url
        sendEmailService.sendEmail(
                user.getEmail(),
                "Registration Confirmation",
                generalService.getMessage("user.registration.message")+"\n"+ t)
    }
}
