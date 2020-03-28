package loan

class Repayment {

    int period
    double interest
    double dailyPenalty

    static constraints = {
        period nullable: false
        interest nullable: false
        dailyPenalty nullable: false
    }
}
