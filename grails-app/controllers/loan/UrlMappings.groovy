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
        "/loan/index"(controller: 'loanRequest', action: 'index')
        "/loan/show/$id"(controller: 'loanRequest', action: 'show')

    }
}
