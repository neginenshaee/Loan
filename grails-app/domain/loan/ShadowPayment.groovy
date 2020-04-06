package loan

class ShadowPayment {

    Date paymentDate
    double principal
    double interest
    double balance
    static belongsTo = [loan: Loan]
    Date dateCreated
    Date lastUpdated

    static constraints = {
        paymentDate()
        principal()
        interest()
        balance()
        loan()
    }
}
