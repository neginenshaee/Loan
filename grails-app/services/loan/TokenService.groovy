package loan

import auth.User
import grails.gorm.transactions.Transactional

@Transactional
class TokenService {

    def createVerificationToken(User user, String token) {
        VerificationToken newUserToken = new VerificationToken()
        newUserToken.setUser(user)
        newUserToken.setToken(token)
        newUserToken.save();
    }

    def retrieveVerificationToken(String token){
        VerificationToken.findByToken(token);
    }
}
