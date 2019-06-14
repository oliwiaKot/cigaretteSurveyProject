function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

function getMessage() {
    var classifiedas = getQueryVariable("classifiedas");
    var is = getQueryVariable("is");

    if(classifiedas == "yes" && is == "yes"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie również deklaruje się jako osoby palące. Pamiętaj - palenie szkodzi Tobie i osobom w Twoim otoczeniu";
    }else  if(classifiedas == "yes" && is == "no"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie, w przeciwienstwie do Ciebie deklaruje się jako osoby palące. Gratulujemy!";
    }else  if(classifiedas == "yes" && is == "ex"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie deklaruje się jako osoby palące. Gratulujemy wygranej z nałogiem!";
    }else  if(classifiedas == "no" && is == "yes"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie deklaruje się jako osoby niepalące. Pamiętaj - palenie szkodzi Tobie i osobom w Twoim otoczeniu";
    }else  if(classifiedas == "no" & is == "no"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie również deklaruje się jako osoby niepalące. Gratulujemy!"
    }else if(classifiedas == "no" & is == "ex"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie  deklaruje się jako osoby niepalące. Gratulujemy wygranej z nałogiem!"
    }else if(classifiedas == "ex" & is == "yes"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie  deklaruje się jako byli palacze. Pamiętaj - palenie szkodzi Tobie i osobom w Twoim otoczeniu"
    }else if(classifiedas == "ex" & is == "ex"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie również deklaruje się jako byli palacze. Gratulujemy wygranej z nałogiem!"
    }else if(classifiedas == "ex" & is == "no"){
        document.getElementById("message").innerHTML = "Większość osób odpowiadających podobnie do Ciebie deklaruje się jako byli palacze. Gratulujemy!"
    }
}

