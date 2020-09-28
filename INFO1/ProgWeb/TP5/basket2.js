"use strict";

var fruitResponseParsed;
var priceResponseParsed;
var reducer = (accumulator, currentValue) => accumulator + currentValue;

window.onload = loadDatas;

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

	fruitResponseParsed.forEach(element => {
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
	var quantities = fruitResponseParsed.map(element => element.quantity);
	quantityTag.innerHTML = quantities.reduce(reducer);
}

function changePrice() {
	var priceTag = document.getElementById("price");
	var prices = fruitResponseParsed.map(element => element.quantity * priceResponseParsed[element.name])
	priceTag.innerHTML = prices.reduce(reducer);
}

function loadDatas() {
	loadData("fruits.json").then((value) => {
		fruitResponseParsed = value;
		changeTable();
		changeQuantity();
	});

	loadData("prices.json").then((value) => {
		priceResponseParsed = value;
		changePrice();
	});
}

/* function loadData(file, update) {
	var ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function () {
		if (ajax.readyState !== 4) {
			return;
		}
		if (ajax.status !== 200) {
			console.log("error " + ajax.status);
		} else {
			let response;
			response = JSON.parse(ajax.responseText);
			console.log(response);
			update(response);
		}
	};
	ajax.open("GET", file, true);
	ajax.overrideMimeType("application/json");
	ajax.send();
}  */

function loadData(file) {
	return new Promise((resolve, reject) => {
		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function () {
			if (ajax.readyState !== 4) {
				return;
			}
			if (ajax.status !== 200) {
				console.log("error " + ajax.status);
			} else {
				let response;
				response = JSON.parse(ajax.responseText);
				console.log(response);
				resolve(response);
			}
		};
		ajax.open("GET", file, true);
		ajax.overrideMimeType("application/json");
		ajax.send();
	});
} 