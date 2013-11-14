import net.kaleidos.directmessagesmailsample.User
import net.kaleidos.directmessagesmailsample.Role
import net.kaleidos.directmessagesmailsample.UserRole
import net.kaleidos.directmessages.Message
import java.util.Random

class BootStrap {
    def mailMessagingService

    def init = { servletContext ->
        //Sample data
        new User(name:"Admin", password:"admin", username:"admin@example.com", enabled:true).save()

        def roleBF = new Role(authority: 'ROLE_BF', description: 'BF role').save()
        def roleWFC = new Role(authority: 'ROLE_WFC', description: 'WFC role').save()

        def paul = new User(name:"Paul Smith", password:"paul", username:"paul@example.com", enabled:true).save()
        UserRole.create(paul, roleBF, true)



        def bob = new User(name:"Bob Davis", password:"bob", username:"bob@example.com", enabled:true).save()
        UserRole.create(bob, roleWFC, true)
        def sam = new User(name:"Sam Brown", password:"sam", username:"sam@example.com", enabled:true).save()
        UserRole.create(sam, roleWFC, true)
        def alice = new User(name:"Alice Williams", password:"alice", username:"alice@example.com", enabled:true).save()
        UserRole.create(alice, roleWFC, true)




        //Messages for pagination
        def users = [alice, bob, sam]
        def random = new Random()
        def user
        90.times{
            user = users[random.nextInt(users.size())]
            mailMessagingService.sendMessage(user, paul, "Message number ${it}", "Subject ${it}")
        }


        90.times{
            user = users[random.nextInt(users.size())]
            mailMessagingService.sendMessage(paul, user, "Message number ${90+it}", "Subject ${90+it}")
        }

        Message.list().each {
            it.readed = true
            it.save()
        }


        def message
        //Messages between Paul and Bob
        message = mailMessagingService.sendMessage(paul, bob, "Hi buddy", "Hello")
        message.readed = true
        message.save()
        message = mailMessagingService.sendMessage(bob, paul, "Hello, my friend", "Hello")
        message.readed = true
        message.save()
        message = mailMessagingService.sendMessage(paul, bob, "How are you?", "Hello")

        message = mailMessagingService.sendMessage(bob, paul, "Did you seen last night movie?", "Movie")

        message = mailMessagingService.sendMessage(paul, bob, "Are you free on friday?", "Friday")
        message.readed = true
        message.save()
        message = mailMessagingService.sendMessage(bob, paul, "yes, why?", "Friday")


        //Messages between Paul and Alice
        message = mailMessagingService.sendMessage(paul, alice, "Hi Alice", "How are you?")
        message.readed = true
        message.save()
        message = mailMessagingService.sendMessage(alice, paul, "Hi Paul", "How are you?")
        message.readed = true
        message.save()
        message = mailMessagingService.sendMessage(paul, alice, "Are you ok?", "How are you?")




    }
    def destroy = {
    }
}
