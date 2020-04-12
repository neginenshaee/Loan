package loan

import enums.Status
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import org.springframework.scheduling.annotation.Scheduled

import java.text.SimpleDateFormat

class DailyEmailJobService {

    static lazyInit = false

    def sendEmailService
    def shadowPaymentService
    def generalService


    @Scheduled(cron = "0 8 21 1/1 * ?")
    void sendNotifications() {
        List<Object[]> result = shadowPaymentService.getEmailsUsingNativeQuery();
        for(Object[] r: result){
            //r[0] = user.email
            //r[1] = loan.monthly_payment
            //r[2] = payment_date
            //r[3] = balance
//            print r[0] + '\t' + r[1] +'\t'+ r[2]
            sendEmailService.sendEmail(
                    r[0],
                    generalService.getMessage("payment.notification.subject"),
                    generalService.getMessage("payment.notification.message", r[2],r[1],r[3])
            )


        }

    }

//    @Scheduled(fixedDelay = 10000L)
//    def abc(){
//        shadowPaymentService.getEmailsUsingNativeQuery()
//        shadowPaymentService.getEmailsUsingHQL()
//        shadowPaymentService.getEmailsUsingCriteria()
//        shadowPaymentService.getEmailsUsingView()
//    }

}
