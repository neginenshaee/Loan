package loan

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class MainController {

    def index() {
        def username = "hamidfarmani"
        [user:username]
    }
}
