package loan

import enums.Status
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('ROLE_USER')
class LoanRequestController {

    LoanRequestService loanRequestService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond loanRequestService.list(params), model:[loanRequestCount: loanRequestService.count()]
    }

    def show(Long id) {
        respond loanRequestService.get(id)
    }

    def create() {
        respond new LoanRequest(params)
    }



    def save(LoanRequest loanRequest) {
        if (loanRequest == null) {
            notFound()
            return
        }
        Long amount = params?.amount
        Date deadline = params?.deadline
        String description = params?.desc
        def user = springSecurityService.currentUser

        loanRequest.setAmount(amount)
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

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        loanRequestService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loanRequest.label', default: 'LoanRequest'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
