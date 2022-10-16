
//============================================================================
function createMessage(out_mess){
	document.getElementById("massageField").remove();
	var mess = document.createElement("div");
	mess.setAttribute("id", "massageField");
	mess.setAttribute("align", "center");
	mess.innerHTML = out_mess;
	document.body.appendChild(mess);
}

//=======================стилизованные параграфы каталога=====================

