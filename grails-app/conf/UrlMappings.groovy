class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        name inbox: "/" {controller = 'message'; action = 'inbox'}
        name sent: "/sent" {controller = 'message'; action = 'sent'}
        name view: "/view" {controller = 'message'; action = 'view'}
        name view: "/newMessage" {controller = 'message'; action = [POST:'newMessage']}
        "500"(view:'/error')
    }
}
