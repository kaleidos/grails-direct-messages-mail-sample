import net.kaleidos.directmessagesmailsample.User
import net.kaleidos.directmessages.Message
import net.kaleidos.directmessages.DirectMessageService
import java.util.Random

class BootStrap {
    def directMessageService

    def init = { servletContext ->

        //Sample data

        def paul = new User(username:"paul", password:"paul", enabled:true).save()
        def alice = new User(username:"alice", password:"alice", enabled:true).save()
        def bob = new User(username:"bob", password:"bob", enabled:true).save()
        def sam = new User(username:"sam", password:"sam", enabled:true).save()





        //Messages between Paul and Bob
        directMessageService.sendMessage(paul.id, bob.id, "Hi buddy", "Hello")
        directMessageService.sendMessage(bob.id, paul.id, "Hello, my friend", "Hello")
        directMessageService.sendMessage(paul.id, bob.id, "How are you?", "Hello")

        directMessageService.sendMessage(bob.id, paul.id, "Did you seen last night movie?", "Movie")

        directMessageService.sendMessage(paul.id, bob.id, "Are you free on friday?", "Friday")
        directMessageService.sendMessage(bob.id, paul.id, "yes, why?", "Friday")


        //Messages for pagination
        def users = [alice, bob, sam]
        def random = new Random()
        90.times{
            def user = users[random.nextInt(users.size())]
            directMessageService.sendMessage(user.id, paul.id, "Message number ${it}", "Subject ${it}")
        }


        90.times{
            def user = users[random.nextInt(users.size())]
            directMessageService.sendMessage(paul.id, user.id, "Message number ${90+it}", "Subject ${90+it}")
        }






    }
    def destroy = {
    }
}
