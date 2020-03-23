package loan

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/user/password"(controller: 'user', action: 'password')
        "/user/edit"(controller: 'user', action: 'edit')
        "/user/confirm/$token"(controller: 'user', action: 'confirm')
        "/user/save"(controller: 'user', action: 'save')
        "/user/index"(controller: 'adminUser', action: 'index')
        "/loan/index"(controller: 'loanRequest', action: 'index')
        "/loan/show/$id"(controller: 'loanRequest', action: 'show')
        "/loan/approve/$id"(controller: 'adminLoan', action: 'approve')
        "/loan/cancel/$id"(controller: 'loanRequest', action: 'cancel')
//        "/user/create"(controller: 'user', action: 'save')
    }
}
