import net.kaleidos.directmessagesmailsample.User
import net.kaleidos.directmessages.Message
class BootStrap {

    def init = { servletContext ->

        //Sample data

        def paul = new User(username:'paul', password:'paul', enabled:true).save()
        def alice = new User(username:'alice', password:'alice', enabled:true).save()
        def bob = new User(username:'bob', password:'bob', enabled:true).save()
        def sam = new User(username:'sam', password:'sam', enabled:true).save()





        //Message from Paul to Bob
        new Message(fromId:paul.id, toId:bob.id, text:'Hi, buddy', last:false, readed: true).save()
        new Message(fromId:bob.id, toId:paul.id, text:'Hello, Paul', last:true, readed: false).save()

        //Message from Paul to Alice
        new Message(fromId:paul.id, toId:alice.id, text:'Hi, Alice', last:true, readed: true).save()


        //Message from Paul to Sam
        new Message(fromId:paul.id, toId:sam.id, text:'Hello', last:false, readed: true).save()
        new Message(fromId:sam.id, toId:paul.id, text:'Hi. Did you see the direct messages plugin for grails?', last:false, readed: true).save()
        new Message(fromId:paul.id, toId:sam.id, text:'Yea, it is cool', last:true, readed: true).save()



    }
    def destroy = {
    }
}
