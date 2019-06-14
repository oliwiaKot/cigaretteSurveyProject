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
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie rowniez deklaruje sie jako osoby palace.Pamietaj - palenie szkodzi Tobie i osobom w Twoim otoczeniu";
    }else  if(classifiedas == "yes" && is == "no"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie, w przeciwienstwie do Ciebie deklaruje sie jako osoby palace. Gratulujemy!";
    }else  if(classifiedas == "yes" && is == "ex"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie deklaruje sie jako osoby palace. Gratulujemy wygranej z nalogiem!";
    }else  if(classifiedas == "no" && is == "yes"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie deklaruje sie jako osoby niepalace. Pamietaj - palenie szkodzi Tobie i osobom w Twoim otoczeniu";
    }else  if(classifiedas == "no" & is == "no"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie rowniez deklaruje sie jako osoby niepalace. Gratulujemy!"
    }else if(classifiedas == "no" & is == "ex"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie  deklaruje sie jako osoby niepalace. Gratulujemy wygranej z nalogiem!"
    }else if(classifiedas == "ex" & is == "yes"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie  deklaruje sie jako byli palacze. Pamietaj - palenie szkodzi Tobie i osobom w Twoim otoczeniu"
    }else if(classifiedas == "ex" & is == "ex"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie rowniez deklaruje sie jako byli palacze. Gratulujemy wygranej z nalogiem!"
    }else if(classifiedas == "ex" & is == "no"){
        document.getElementById("message").innerHTML = "Wiekszosc osob odpowiadajacych podobnie do Ciebie  deklaruje sie jako byli palacze. Gratulujemy!"
    }
}

