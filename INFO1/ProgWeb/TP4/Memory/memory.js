
function MemoryGame(images, blank) {
	this.images = images;
	this.blank = blank;
}

MemoryGame.prototype.build = function build(div) {
	var cards = shuffleCards(this.images.length);
	var discovered = cards.map(c => false);
	var flipped = 0;
	var flippedCards = [];


	var nodes = cards.map((c, index) => {
		var node = document.createElement("img");
		node.src = "blank.png";
		node.alt = "blank";
		return node;
	});

	// separated from above to modify nodes on click
	for (var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		node.onclick = (function(i){
			return function() {
				if (discovered[i]) return;

				console.log(i);
				nodes[i].src = "lego" + (cards[i]+1) + ".png";
				nodes[i].alt = "lego" + (cards[i]+1);

				discovered[i] = true
				flippedCards.push(cards[i]);
				flipped++;


				if (flipped === 2) {
					if (flippedCards.some((card) => card !== cards[i])) {
						setTimeout((flippedCards) => {
							flippedCards.forEach( function(card) {
								var idx = cards.indexOf(card);
								discovered[idx] = false;
								nodes[idx].src = "blank.png";
								nodes[idx].alt = "blank"

								idx = cards.lastIndexOf(card);
								discovered[idx] = false;
								nodes[idx].src = "blank.png";
								nodes[idx].alt = "blank";
							});

						}, 500, flippedCards);
					}
					flipped = 0;
					flippedCards = [];
				}
			};
		})(i);

		div.appendChild(node);
	}
}

function shuffleCards(length) {
	var cards = [];
	for(let i = 0; i < length; i++) {
		cards.push(i);
		cards.push(i);
	}

	var j, x;
	for (let i = cards.length - 1; i > 0; i--) {
		j = Math.floor(Math.random() * (i + 1));
		x = cards[i];
		cards[i] = cards[j];
		cards[j] = x;
	}

	return cards;
}
