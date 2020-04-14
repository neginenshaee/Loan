package loan

import grails.testing.gorm.DomainUnitTest
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Loan)
class LoanSpec extends Specification implements DomainUnitTest<Loan> {

    Loan loan = new Loan()

    void 'default Loan is valid' (){
        expect:
        loan.validate()


    }

}
