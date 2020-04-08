package loan

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import org.springframework.scheduling.annotation.Scheduled

import java.text.SimpleDateFormat

class DailyEmailJobService {

    static lazyInit = false

    def sendEmailService
    def shadowPaymentService


    @Scheduled(cron = "0 30 8 1/1 * ?")
    void sendNotifications() {

        sendEmailService.sendEmail('hamidfarmani1@gmail.com', 'Test Message','testing')
    }

}
