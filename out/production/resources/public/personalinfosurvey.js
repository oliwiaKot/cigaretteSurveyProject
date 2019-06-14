

function personalInfoNext() {
    document.getElementById('personalInformationSurvey').hidden = true;

    document.getElementById("questionNumber").hidden = false;
    if(document.getElementById('smoker_yes').checked){
        document.getElementById("frequencyQ" ).hidden=false;
        document.getElementById("questionNumber").innerHTML = "Pytanie 1/5";
    }else if(document.getElementById('smoker_no').checked){
        document.getElementById("reasonsQ" ).hidden=false;
        document.getElementById("questionNumber").innerHTML = "Pytanie 1/4";


    }else if(document.getElementById('smoker_ex').checked){
        document.getElementById("reasonsQ").hidden=false;
        document.getElementById("questionNumber").innerHTML = "Pytanie 1/6";


    }
}

function reasonsNext() {

    document.getElementById("reasonsQ").hidden=true;

    if(document.getElementById("smoker_no").checked){
        document.getElementById("familyQ").hidden=false;
        document.getElementById("questionNumber").innerHTML = "Pytanie 2/4";

    }else if(document.getElementById("smoker_ex").checked){
        document.getElementById("frequencyQ").hidden=false;
        document.getElementById("questionNumber").innerHTML = "Pytanie 2/6";

    }
    
}

function frequencyNext() {
    document.getElementById("frequencyQ").hidden=true;
    document.getElementById("situationsQ").hidden=false;

    if(document.getElementById('smoker_yes').checked) {

        document.getElementById("questionNumber").innerHTML = "Pytanie 2/5";
    } else if(document.getElementById('smoker_ex').checked){

        document.getElementById("questionNumber").innerHTML = "Pytanie 3/6";


    }
}

function situationsNext() {
    document.getElementById('situationsQ').hidden=true;
    document.getElementById("familyQ").hidden=false;

    if(document.getElementById('smoker_yes').checked) {

        document.getElementById("questionNumber").innerHTML = "Pytanie 3/5";
    } else if(document.getElementById('smoker_ex').checked){

        document.getElementById("questionNumber").innerHTML = "Pytanie 4/6";


    }
}

function familyNext() {
    document.getElementById("familyQ").hidden=true;
    document.getElementById("friendsQ").hidden=false;

    if(document.getElementById('smoker_yes').checked) {

        document.getElementById("questionNumber").innerHTML = "Pytanie 4/5";
    } else if(document.getElementById('smoker_ex').checked){

        document.getElementById("questionNumber").innerHTML = "Pytanie 5/6";


    }else if(document.getElementById('smoker_no').checked){

        document.getElementById("questionNumber").innerHTML = "Pytanie 3/4";


    }
}

function friendsNext() {
    document.getElementById("friendsQ").hidden=true;
    document.getElementById("otherAddictionsQ").hidden=false;
    document.getElementById("submit").hidden=false;

    if(document.getElementById('smoker_yes').checked) {

        document.getElementById("questionNumber").innerHTML = "Pytanie 5/5";
    } else if(document.getElementById('smoker_ex').checked){

        document.getElementById("questionNumber").innerHTML = "Pytanie 6/6";


    }else if(document.getElementById('smoker_no').checked){

        document.getElementById("questionNumber").innerHTML = "Pytanie 4/4";


    }
}