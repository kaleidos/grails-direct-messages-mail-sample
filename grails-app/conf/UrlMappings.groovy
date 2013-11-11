class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        name index: "/" {controller = 'message'; action = 'inbox'}
        "500"(view:'/error')
    }
}
