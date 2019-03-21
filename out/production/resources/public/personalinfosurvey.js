

function personalInfoNext() {
    document.getElementById('personalInformationSurvey').hidden = true;

    if(document.getElementById('smoker_yes').checked){
        document.getElementById("frequencyQ" ).hidden=false;
    }else if(document.getElementById('smoker_no').checked){
        document.getElementById("reasonsQ" ).hidden=false;

    }else if(document.getElementById('smoker_ex').checked){
        document.getElementById("reasonsQ").hidden=false;

    }
}

function reasonsNext() {

    document.getElementById("reasonsQ").hidden=true;

    if(document.getElementById("smoker_no").checked){
        document.getElementById("familyQ").hidden=false;
    }else if(document.getElementById("smoker_ex").checked){
        document.getElementById("frequencyQ").hidden=false;

    }
    
}

function frequencyNext() {
    document.getElementById("frequencyQ").hidden=true;
    document.getElementById("situationsQ").hidden=false;
}

function situationsNext() {
    document.getElementById('situationsQ').hidden=true;
    document.getElementById("familyQ").hidden=false;
}

function familyNext() {
    document.getElementById("familyQ").hidden=true;
    document.getElementById("friendsQ").hidden=false;
}

function friendsNext() {
    document.getElementById("friendsQ").hidden=true;
    document.getElementById("otherAddictionsQ").hidden=false;
    document.getElementById("submit").hidden=false;
}