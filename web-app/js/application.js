function checkNewMessage(){
    var subject = document.getElementById('subject');
    var text = document.getElementById('text');
    if (!subject.value) {
        alert ("The subject is mandatory");
        return false;
    }

    if (!text.value) {
        alert ("The text is mandatory");
        return false;
    } else if (text.value.lenght > 5000) {
        alert ("The maximun size of a message is 5,000 chars");
        return false;
    }
    return true;
}

function showDeletedMessages() {
    var elements = document.getElementsByClassName("thread_container");
    var i = 0;
    for (i=0;i<elements.length;i++){
        elements[i].className = "thread_container";
    }
    document.getElementById("show_deleted_message").className = "hidden";
    return false;
}
