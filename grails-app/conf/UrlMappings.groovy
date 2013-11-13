class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        name inbox: "/" {controller = 'message'; action = 'inbox'}
        name deleteMessages: "/delete" {controller = 'message'; action = [POST:'deleteMessages']}
        name sent: "/sent" {controller = 'message'; action = 'sent'}
        name view: "/view" {controller = 'message'; action = 'view'}
        name newMessage: "/newMessage" {controller = 'message'; action = [POST:'saveNewMessage', GET:'newMessage']}
        name userList: "/users" {controller = 'message'; action = 'userList'}
        name reportUser: "/report" {controller = 'message'; action = 'reportUser'}
        name blockUser: "/block" {controller = 'message'; action = 'blockUser'}
        "500"(view:'/error')
    }
}
