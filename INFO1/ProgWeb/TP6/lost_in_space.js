"use strict";

const LEFT = 0;
const RIGHT = 1;
const UP = 2;
const DOWN = 3;
const SPACE = 4;
const SPEED = 5;

function drawTriangle(x, y, color, context) {
	context.fillStyle = color;

	context.beginPath();
	context.moveTo(x + 3,y + 3);
	context.lineTo(x,y - 3);
	context.lineTo(x - 3,y + 3);
	context.closePath();

	context.fill();
}

function drawCircle(x, y, color, context) {
	context.fillStyle = color;

	context.beginPath();
	context.arc(x, y, 1, 0, 2 * Math.PI);
	context.closePath();

	context.fill();
}

function drawText(text, color, context, canvas) {
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
		this.speed = SPEED;
	}

	reduceSpeed() {
		this.speed = this.speed * 0.99;
	}

	move() {
		this.y = this.y - this.speed;
	}

	dies(canvas) {
		return ( ((this.start - this.y) >= 2 * canvas.height / 3) || this.y <= 0 )
	}

	draw(context) {
		drawCircle(this.x, this.y, "tomato", context);
	}
}

class Player {
	constructor(canvas) {
		this.x = Math.round(canvas.width / 2);
		this.y = canvas.height - 20;
		this.bullets = [];

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
		this.bullets.push(new Bullet(this.x, this.y));
	}

	draw(context) {
		drawTriangle(this.x, this.y, "red", context);
	}

	move(canvas) {
		if (this.actions.DOWN && this.y < canvas.height) {
			this.y += SPEED;
		}
		if (this.actions.UP && this.y > 0) {
			this.y -= SPEED;
		}
		if (this.actions.LEFT && this.x > 0) {
			this.x -= SPEED;
		}
		if (this.actions.RIGHT && this.x < canvas.width ) {
			this.x += SPEED;
		}
		if (this.actions.SPACE) {
			this.shoot();
		}
	}

	bulletCollision(bullet) {
		return (bullet.x >= this.x -1)
			&& (bullet.x <= this.x +1)
			&& (bullet.y >= this.y -1)
			&& (bullet.y <= this.y +1);
	}

	alienCollision(alien) {
		return (alien.x+1 >= this.x -1)
			&& (alien.x-1 <= this.x +1)
			&& (alien.y+1 >= this.y -1)
			&& (alien.y-1 <= this.y +1);
	}
}

class Alien {
	constructor(canvas) {
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

	move(canvas) {
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

	draw(context) {
		drawTriangle(this.x, this.y, "yellow", context);
	}

	bulletCollision(bullet) {
		return (bullet.x >= this.x -1)
			&& (bullet.x <= this.x +1)
			&& (bullet.y >= this.y -1)
			&& (bullet.y <= this.y +1)
	}
}

function clearCanvas(canvas, context) {
	context.fillStyle = "black";
	context.fillRect(0, 0, canvas.width, canvas.height);
}

function moveAll(canvas, player, aliens, context) {
	player.move(canvas);
	for (let i = 0; i < player.bullets.length; i++) {
		let bullet = player.bullets[i];
		bullet.move();
		bullet.reduceSpeed();

		if(bullet.dies(canvas)) {
			player.bullets = player.bullets.filter((b) => b !== bullet);
		}
	}

	for (let i = 0; i < aliens.length; i++) {
		let alien = aliens[i];
		alien.move(canvas);
	}

	return player;
}

function drawAll(player, aliens, context) {
	player.draw(context);

	for (let i = 0; i < player.bullets.length; i++) {
		let bullet = player.bullets[i];
		bullet.draw(context);
	}

	for (let i = 0; i < aliens.length; i++) {
		let alien = aliens[i];
		alien.draw(context);
	}
}

function countLoop(count, aliens, player) {
	count = (count + 1) % 10;
	if (count === 9) {
		for (let i=0; i< aliens.length / 5; i++) {
			let alien = aliens[Math.floor(Math.random() * 260)];

			if (alien === undefined) continue;

			if (alien.x < player.x) {
				alien.horizontalDirection = RIGHT;
			} else {
				alien.horizontalDirection = LEFT;
			}

			if (alien.y < player.y) {
				alien.verticalDirection = DOWN;
			} else {
				alien.verticalDirection = UP;
			}
		}
	}

	return count;
}

function detectPlayerCollision(player, aliens) {
	for (let i = 0; i < aliens.length; i++) {
		let alien = aliens[i];
		if (player.alienCollision(alien)) {
			return true;
		}
	}

	for (let i = 0; i < player.bullets.length; i++) {
		let bullet = player.bullets[i];
		if (player.bulletCollision(bullet)) {
			return true;
		}
	}

	return false
}

function detectAlienCollision(player, aliens) {
	for (let i = 0; i < aliens.length; i++) {
		let alien = aliens[i];

		for (let j = 0; j < player.bullets.length; j++) {
			let bullet = player.bullets[j];
			if (alien.bulletCollision(bullet)) {
				console.log("OK")
				player.bullets = player.bullets.filter((b) => b !== bullet);
				aliens = aliens.filter((a) => a !== alien);
			}
		}
	}

	return {
		"player": player,
		"aliens": aliens
	};
}

function updateAll(count, canvas, player, aliens, context) {
	clearCanvas(canvas, context);

	player = moveAll(canvas, player, aliens, context)
	drawAll(player, aliens, context);

	if(detectPlayerCollision(player, aliens)) {
		drawText("You died...", "red", context, canvas);
		return;
	}
	let result = detectAlienCollision(player, aliens);
	player = result.player;
	aliens = result.aliens;

	if(aliens.length === 0) {
		drawText("You win !", "green", context, canvas);
		return;
	}

	count = countLoop(count, aliens, player);

	window.requestAnimationFrame(function() {
		updateAll(count, canvas, player, aliens, context)
	});
}

window.onload = function() {
	let canvas;
	let player;
	let aliens = [];
	canvas = document.getElementById("game_area");
	let context = canvas.getContext('2d');
	player = new Player(canvas);

	for (let i = 0; i < 250; i++) {
		aliens.push(new Alien(canvas));
	}

	updateAll(0, canvas, player, aliens, context);
};
