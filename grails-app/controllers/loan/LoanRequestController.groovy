package loan

import auth.Role
import enums.Status
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_USER','ROLE_ADMIN'])
class LoanRequestController {

    LoanRequestService loanRequestService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(){
        List<LoanRequest> loans
        def authorities = springSecurityService.currentUser.authorities
        if(authorities.contains(Role.findByAuthority("ROLE_USER"))) {
            loans = LoanRequest.findAllByUser(springSecurityService.currentUser)
        }else{
            loans = LoanRequest.findAll()
        }
        render(view: '/loan/index', model: [loans: loans])
    }

    def show(Long id) {
        render(view: '/loan/show', model: [loanRequest: loanRequestService.get(id)])
    }

    def create() {
        render(view: '/loan/request')
    }



    def save(LoanRequest loanRequest) {
        if (loanRequest == null) {
            notFound()
            return
        }
        String amount = params?.amount
        String deadlineDate = params?.deadline
        println deadlineDate
        Date deadline = new Date()
        String description = params?.desc
        def user = springSecurityService.currentUser

        loanRequest.setAmount(Long.valueOf(amount))
        loanRequest.setDeadline(deadline)
        loanRequest.setStatus(Status.REQUESTED)
        loanRequest.setUser(user)
        loanRequest.setDescription(description)
        loanRequest.setActionDate(new Date())

        try {
            loanRequestService.save(loanRequest)
        } catch (ValidationException e) {
            respond loanRequest.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'loanRequest.label', default: 'LoanRequest'), loanRequest.id])
                redirect loanRequest
            }
            '*' { respond loanRequest, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond loanRequestService.get(id)
    }

    def update(LoanRequest loanRequest) {
        if (loanRequest == null) {
            notFound()
            return
        }

        try {
            loanRequestService.save(loanRequest)
        } catch (ValidationException e) {
            respond loanRequest.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loanRequest.label', default: 'LoanRequest'), loanRequest.id])
                redirect loanRequest
            }
            '*'{ respond loanRequest, [status: OK] }
        }
    }



    @Transactional
    def cancel() {
        Long id = Long.valueOf(params.loanRequest)
        LoanRequest loanRequest = LoanRequest.findById(id)
        loanRequest.setStatus(Status.CANCELLED)
        loanRequest.save()
        redirect(view: '/loan/index')
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'loanRequest.label', default: 'LoanRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
