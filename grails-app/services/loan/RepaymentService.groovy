package loan

import grails.gorm.transactions.Transactional

@Transactional
class RepaymentService {

    static Repayment get(Long id){
        Repayment.get(id)
    }

    def list(){
        List<Repayment> repayments = Repayment.findAll()
        repayments
    }

    def static count(){
        Repayment.count()
    }

    def save(Repayment repayment){
        repayment.save()
    }
}
