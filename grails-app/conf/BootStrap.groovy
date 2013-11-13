import net.kaleidos.directmessagesmailsample.User
import net.kaleidos.directmessagesmailsample.Role
import net.kaleidos.directmessagesmailsample.UserRole
import net.kaleidos.directmessages.Message
import net.kaleidos.directmessages.ThreadMessageService
import java.util.Random

class BootStrap {
    def threadMessageService

    def init = { servletContext ->



        //Sample data

        new User(username:"admin", password:"admin", email:"admin@example.com", enabled:true).save()



        def roleBF = new Role(authority: 'ROLE_BF', description: 'BF role').save()
        def roleWFC = new Role(authority: 'ROLE_WFC', description: 'WFC role').save()

        def paul = new User(username:"paul", password:"paul", email:"paul@example.com", enabled:true).save()
        UserRole.create(paul, roleBF, true)


        def alice = new User(username:"alice", password:"alice", email:"alice@example.com", enabled:true).save()
        UserRole.create(alice, roleWFC, true)
        def bob = new User(username:"bob", password:"bob", email:"bob@example.com", enabled:true).save()
        UserRole.create(bob, roleWFC, true)
        def sam = new User(username:"sam", password:"sam", email:"sam@example.com", enabled:true).save()
        UserRole.create(sam, roleWFC, true)




        //Messages for pagination
        def users = [alice, bob, sam]
        def random = new Random()
        def user
        90.times{
            user = users[random.nextInt(users.size())]
            threadMessageService.sendThreadMessage(user.id, paul.id, "Message number ${it}", "Subject ${it}")
        }


        90.times{
            user = users[random.nextInt(users.size())]
            threadMessageService.sendThreadMessage(paul.id, user.id, "Message number ${90+it}", "Subject ${90+it}")
        }

        Message.list().each {
            it.readed = true
            it.save()
        }


        def message
        //Messages between Paul and Bob
        message = threadMessageService.sendThreadMessage(paul.id, bob.id, "Hi buddy", "Hello")
        message.readed = true
        message.save()
        message = threadMessageService.sendThreadMessage(bob.id, paul.id, "Hello, my friend", "Hello")
        message.readed = true
        message.save()
        message = threadMessageService.sendThreadMessage(paul.id, bob.id, "How are you?", "Hello")



        message = threadMessageService.sendThreadMessage(bob.id, paul.id, "Did you seen last night movie?", "Movie")



        message = threadMessageService.sendThreadMessage(paul.id, bob.id, "Are you free on friday?", "Friday")
        message.readed = true
        message.save()
        message = threadMessageService.sendThreadMessage(bob.id, paul.id, "yes, why?", "Friday")


        //Messages between Paul and Alice
        message = threadMessageService.sendThreadMessage(paul.id, alice.id, "Hi Alice", "How are you?")
        message.readed = true
        message.save()
        message = threadMessageService.sendThreadMessage(alice.id, paul.id, "Hi Paul", "How are you?")
        message.readed = true
        message.save()
        message = threadMessageService.sendThreadMessage(paul.id, alice.id, "Are you ok?", "How are you?")




    }
    def destroy = {
    }
}
