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
