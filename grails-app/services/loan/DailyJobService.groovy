//package loan
//
//import groovy.util.logging.Slf4j
//import org.springframework.scheduling.annotation.Scheduled
//
//@Slf4j
//class DailyJobService {
//
//
//    def sendEmailService
//    def shadowPaymentService
//    def generalService
//
//    static triggers = {
//        simple name: 'simpleTrigger', startDelay: 1000, repeatInterval: 3000, repeatCount: 10
////        cron name:   'cronTrigger',   cronExpression: '0 35 14 1/1 * ?'
//    }
//
//    void execute(){
//        println 'here'
//    }
///*
//    void execute() {
//        List<Object[]> result = shadowPaymentService.getEmailsUsingNativeQuery()
//        log.info result.toString()
//        for(Object[] r: result){
//            //r[0] = user.email
//            //r[1] = loan.monthly_payment
//            //r[2] = payment_date
//            //r[3] = balance
////            print r[0] + '\t' + r[1] +'\t'+ r[2]
//            sendEmailService.sendEmail(
//                    r[0],
//                    generalService.getMessage("payment.notification.subject"),
//                    generalService.getMessage("payment.notification.message", r[2],r[1],r[3])
//            )
//
//
//        }
//
//    }
//
//    */
//}
