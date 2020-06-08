"use strict";

var responseParsed;
window.onload = loadData;

function setTableHeader() {
	var table = document.getElementById("basket");

	let header = document.createElement("th");
	let header1 = document.createElement("td");
	let header2 = document.createElement("td");

	header1.innerHTML = "Fruits";
	header2.innerHTML = "Quantité";

	header.appendChild(header1);
	header.appendChild(header2);
	table.appendChild(header);
}

function setTableContent() {
	var table = document.getElementById("basket");

	responseParsed.forEach(element => {
		let row = document.createElement("tr");

		let fruit = document.createElement("td");
		fruit.innerHTML = element.name;
		let quantity = document.createElement("td");
		quantity.innerHTML = element.quantity;

		row.appendChild(fruit);
		row.appendChild(quantity);

		table.appendChild(row);
	});
}

function changeTable() {
	setTableHeader();
	setTableContent();
}

function changeQuantity() {
	var quantityTag = document.getElementById("quantity");
	var quantities = responseParsed.map(element => element.quantity);
	quantityTag.innerHTML = quantities.reduce((accumulator, currentValue) => accumulator + currentValue);
}

function loadData() {
	var ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function () {
		if (ajax.readyState !== 4) {
			return;
		}
		if (ajax.status !== 200) {
			console.log("error " + ajax.status);
		} else {
			console.log(ajax.responseText);
			responseParsed = JSON.parse(ajax.responseText);
			console.log(responseParsed);

			changeTable();
			changeQuantity();
		}
	};
	ajax.open("GET", "fruits.json", true);
	ajax.overrideMimeType("application/json");
	ajax.send();
}