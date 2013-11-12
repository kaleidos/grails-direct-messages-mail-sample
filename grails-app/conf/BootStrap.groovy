import net.kaleidos.directmessagesmailsample.User
import net.kaleidos.directmessagesmailsample.Role
import net.kaleidos.directmessagesmailsample.UserRole
import net.kaleidos.directmessages.Message
import net.kaleidos.directmessages.DirectMessageService
import java.util.Random

class BootStrap {
    def directMessageService

    def init = { servletContext ->

        //Sample data

        def roleBF = new Role(authority: 'ROLE_BF', description: 'BF role').save()
        def roleWFC = new Role(authority: 'ROLE_WFC', description: 'WFC role').save()

        def paul = new User(username:"paul", password:"paul", enabled:true).save()
        UserRole.create(paul, roleBF, true)


        def alice = new User(username:"alice", password:"alice", enabled:true).save()
        UserRole.create(alice, roleWFC, true)
        def bob = new User(username:"bob", password:"bob", enabled:true).save()
        UserRole.create(bob, roleWFC, true)
        def sam = new User(username:"sam", password:"sam", enabled:true).save()
        UserRole.create(sam, roleWFC, true)



        //Messages between Paul and Bob
        directMessageService.sendMessage(paul.id, bob.id, "Hi buddy", "Hello")
        directMessageService.sendMessage(bob.id, paul.id, "Hello, my friend", "Hello")
        directMessageService.sendMessage(paul.id, bob.id, "How are you?", "Hello")

        directMessageService.sendMessage(bob.id, paul.id, "Did you seen last night movie?", "Movie")

        directMessageService.sendMessage(paul.id, bob.id, "Are you free on friday?", "Friday")
        directMessageService.sendMessage(bob.id, paul.id, "yes, why?", "Friday")

        //Messages between Paul and Alice
        directMessageService.sendMessage(paul.id, alice.id, "Hi Alice", "How are you?")
        directMessageService.sendMessage(alice.id, paul.id, "Hi Paul", "How are you?")
        directMessageService.sendMessage(paul.id, alice.id, "Are you ok?", "How are you?")



        //Messages for pagination
        def users = [alice, bob, sam]
        def random = new Random()
        def user
        90.times{
            user = users[random.nextInt(users.size())]
            directMessageService.sendMessage(user.id, paul.id, "Message number ${it}", "Subject ${it}")
        }


        90.times{
            user = users[random.nextInt(users.size())]
            directMessageService.sendMessage(paul.id, user.id, "Message number ${90+it}", "Subject ${90+it}")
        }






    }
    def destroy = {
    }
}
