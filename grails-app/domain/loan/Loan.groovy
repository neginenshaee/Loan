package loan

class Loan {
    Long amount
    int months
    Double interest
    Double monthlyPayment
    static belongsTo = [loanRequest: LoanRequest]
    Date dateCreated
    Date lastUpdated
    static constraints = {
        amount()
        months()
        interest()
        monthlyPayment()
    }
}
