

function nextQuestion() {

    let form = document.getElementsByName('personalinfo')
    if(document.getElementById('smoker_yes').checked){
        window.location.replace("/qaddictions.html", "_self")
        return true;
    }else{
        form.action='/qfamily.html';
        return true;
    }
}