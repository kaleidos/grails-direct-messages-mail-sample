class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        name inbox: "/" {controller = 'message'; action = 'inbox'}
        name sent: "/sent" {controller = 'message'; action = 'sent'}
        "500"(view:'/error')
    }
}
