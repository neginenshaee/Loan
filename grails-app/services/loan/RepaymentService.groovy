package loan

import grails.gorm.transactions.Transactional

import java.math.RoundingMode
import java.text.DecimalFormat

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

    private static DecimalFormat df = new DecimalFormat("0.00");


    def getRepaymentsByLoanRequest(LoanRequest loanRequest){
        List<Repayment> repayments = list()


        List<Map<String, String>> res = new ArrayList<HashMap<String, String>>()

        for (Repayment r : repayments){
            Map result = new HashMap()
            result.put("dailyPenalty",r.getDailyPenalty())
            result.put("period",r.getPeriod())
            result.put("interest",r.getInterest())
            def all = (loanRequest.getAmount() * r.getInterest() / 100) + loanRequest.getAmount()
            df.setRoundingMode(RoundingMode.UP)
            def monthly = all / r.getPeriod()
            result.put("monthlyShare", df.format(monthly))
            result.put("all", all)
            res.add(result)
        }
        res
    }
}
