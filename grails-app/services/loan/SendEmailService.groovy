package loan

import grails.gorm.transactions.Transactional

@Transactional
class SendEmailService {

    def mailService

    def sendEmail(String email, String subjectOfEmail, String message){
        mailService.sendMail {
            to email
            subject subjectOfEmail
            text message
        }
    }
}
