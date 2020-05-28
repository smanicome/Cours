"use strict";

var ajax = new XMLHttpRequest();
ajax.onreadystatechange = function() {
	if (ajax.readyState !== 4) {
		return;
	}
	if(ajax.status !== 200) {
		console.log("error " + ajax.status);
	} else {
		console.log(ajax.responseText);
	}
};
ajax.open("GET", "fruits.json", true);
ajax.overrideMimeType("application/json");
ajax.send();