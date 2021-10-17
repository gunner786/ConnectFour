

$(document).ready(function () {
	$('#enter').click(function (){
		    var name;	    
			var xhttp = new XMLHttpRequest();
			var url = "/startGame/start?player="+$("#name").val();
		    xhttp.open("GET", url);
		    xhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
		    xhttp.send();
		    xhttp.onload = () => {
		    	var obj = JSON.parse(xhttp.response);
		    	displayMessage(obj);		    
		    };
		
	});
	
	$('#playGame').click( function (){
		
		var xhttp = new XMLHttpRequest();
		var url = "/startGame/play?player="+$("#name").val()+"&move="+$("#makeMove").val();
	    xhttp.open("GET", url);
	    xhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	    xhttp.send();
	    xhttp.onload = () => {
	    	var obj = JSON.parse(xhttp.response);
	    	displayMessage(obj);		    
	    };
		
	});
	
    $('#disconnect').click( function (){
		
		var xhttp = new XMLHttpRequest();
		var url = "/startGame/end?player="+$("#name").val();
	    xhttp.open("GET", url);
	    xhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	    xhttp.send();
	    xhttp.onload = () => {
	    	var obj = JSON.parse(xhttp.response);
	    	displayMessage(obj);		    
	    };
		
	});
	
	
	
    }); 
    

function displayMessage(message) {

    console.log("showGameMessage:");
    console.log(message);

    $("#gameModel").empty().append("<tr><td>" + message.gameMessage + "</td></tr>");

    if(message.gameMessage.includes("already exists") || message.gameMessage == "Plese enter player name") {
        $("#namePanel").show();
        $("#movePanel").hide();
    }
    if(message.gameMessage.includes("forfeited")) {
    	$("#namePanel").hide();
        $("#movePanel").hide();
    }
    else {
        $("#movePanel").show();
    }

    $("#conversation").show();

}