"use strict";

const LEFT = 0;
const RIGHT = 1;
const UP = 2;
const DOWN = 3;
const SPACE = 4;

let canvas;
let player;
let aliens = [];
let bullets = [];

function drawTriangle(x, y, color) {
	let context = canvas.getContext('2d');

	context.fillStyle = color;

	context.beginPath();
	context.moveTo(x + 3,y + 3);
	context.lineTo(x,y - 3);
	context.lineTo(x - 3,y + 3);
	context.closePath();

	context.fill();
}

function drawCircle(x, y, color) {
	let context = canvas.getContext('2d');

	context.fillStyle = color;

	context.beginPath();
	context.arc(x, y, 1, 0, 2 * Math.PI);
	context.closePath();

	context.fill();
}

function drawText(text, color) {
	let context = canvas.getContext('2d');
	context.beginPath();
	context.font = "50px Arial"
	context.fillStyle = color;
	context.fillText(text, canvas.width / 3, canvas.height / 2, canvas.width);
}

class Bullet {
	constructor(x, y) {
		this.x = x;
		this.y = y - 10;
		this.start = this.y;
		this.speed = 5;
	}

	reduceSpeed() {
		this.speed = this.speed * 0.99;
	}

	move() {
		this.y = this.y - this.speed;
	}

	dies() {
		return ( ((this.start - this.y) >= 2 * canvas.height / 3) || this.y <= 0 )
	}

	draw() {
		drawCircle(this.x, this.y, "tomato");
	}
}

class Player {
	constructor() {
		this.x = Math.round(canvas.width / 2);
		this.y = canvas.height - 20;
		this.actions = {
			LEFT: false,
			RIGHT: false,
			UP: false,
			DOWN: false,
			SPACE: false
		}

		let that = this;
		window.addEventListener('keydown', function(event) {
			switch(event.key) {
				case "ArrowDown":
					that.actions.DOWN = true;
					break;
				case "ArrowUp":
					that.actions.UP = true;
					break;
				case "ArrowLeft":
					that.actions.LEFT = true;
					break;
				case "ArrowRight":
					that.actions.RIGHT = true;
					break;
				case " ":
					that.actions.SPACE = true;
			}
		});

		window.addEventListener('keyup', function(event) {
			switch(event.key) {
				case "ArrowDown":
					that.actions.DOWN = false;
					break;
				case "ArrowUp":
					that.actions.UP = false;
					break;
				case "ArrowLeft":
					that.actions.LEFT = false;
					break;
				case "ArrowRight":
					that.actions.RIGHT = false;
					break;
				case " ":
					that.actions.SPACE = false;
			}
		});
	}

	shoot() {
		bullets.push(new Bullet(this.x, this.y));
	}

	draw() {
		drawTriangle(this.x, this.y, "red");
	}

	move() {
		if (this.actions.DOWN && this.y < canvas.height) {
			this.y += 5;
		}
		if (this.actions.UP && this.y > 0) {
			this.y -= 5;
		}
		if (this.actions.LEFT && this.x > 0) {
			this.x -= 5;
		}
		if (this.actions.RIGHT && this.x < canvas.width ) {
			this.x += 5;
		}
		if (this.actions.SPACE) {
			this.shoot();
		}
	}

	alienCollision() {
		for(let i = 0; i < aliens.length; i++) {
			let alien = aliens[i];
			if ( (alien.x+1 >= this.x -1) && (alien.x-1 <= this.x +1) && (alien.y+1 >= this.y -1) && (alien.y-1 <= this.y +1) ) {
				return true;
			}
		}

		return false;
	}
}

class Alien {
	constructor() {
		let rnj = Math.round((Math.random() * canvas.width));
		this.x = rnj;
		this.y = rnj % 50;
		this.speed = 1;

		if (rnj%2 === 0) {
			//this.x = 0;
			this.horizontalDirection = RIGHT;
		} else {
			//this.x = canvas.width;
			this.horizontalDirection = LEFT;
		}

		this.verticalDirection = DOWN;
	}

	increaseSpeed() {
		this.speed = this.speed * 1.08;
	}

	move() {
		if (this.verticalDirection === DOWN) {
			if (this.y >= canvas.height) {
				this.verticalDirection = UP;
				this.increaseSpeed();
				this.y -= this.speed;
			} else {
				this.y += this.speed;
			}
		} else {
			if (this.y <= 0) {
				this.verticalDirection = DOWN;
				this.increaseSpeed();
				this.y += this.speed;
			} else {
				this.y -= this.speed;
			}
		}

		if (this.horizontalDirection === RIGHT) {
			if (this.x >= canvas.width) {
				this.horizontalDirection = LEFT;
				this.x -= this.speed;
			} else {
				this.x += this.speed;
			}
		} else {
			if (this.x <= 0) {
				this.horizontalDirection = RIGHT;
				this.x += this.speed;
			} else {
				this.x -= this.speed;
			}
		}
	}

	draw() {
		drawTriangle(this.x, this.y, "yellow");
	}

	bulletCollision() {
		for(let i = 0; i < bullets.length; i++) {
			let bullet = bullets[i];
			if ( (bullet.x >= this.x -1) && (bullet.x <= this.x +1) && (bullet.y >= this.y -1) && (bullet.y <= this.y +1) ) {
				bullets = bullets.filter((bullet) => bullet !== bullets[i]);
				return true;
			}
		}

		return false;
	}
}

function clearCanvas() {
	let context = canvas.getContext('2d');
	context.fillStyle = "black";
	context.fillRect(0, 0, canvas.width, canvas.height);
}

function drawAll() {
	clearCanvas();

	for (let i = 0; i < bullets.length; i++) {
		bullets[i].move();
		bullets[i].reduceSpeed();
		bullets[i].draw();
		if(bullets[i].dies()) {
			bullets = bullets.filter((bullet) => bullet !== bullets[i]);
		}
	}

	for (let i = 0; i < aliens.length; i++) {
		aliens[i].move();
		aliens[i].draw();
		if(aliens[i].bulletCollision()) {
			aliens = aliens.filter((alien) => alien !== aliens[i]);
		}
	}

	player.move();
	player.draw();

	if(player.alienCollision()) {
		drawText("You died...", "red");
		return;
	}

	if(aliens.length === 0) {
		drawText("You win !", "green");
		return;
	}

	window.requestAnimationFrame(drawAll);
}

window.onload = function() {
	canvas = document.getElementById("game_area");
	player = new Player();

	for (let i = 0; i < 250; i++) {
		aliens.push(new Alien());
	}

	drawAll();
};
