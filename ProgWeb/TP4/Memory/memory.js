
function MemoryGame(images, blank) {
  this.images = images;
  this.blank = blank;
}

MemoryGame.prototype.build = function build(div) {
  var cards = shuffleCards(this.images.length);
  var nodes = cards.map((c) => {
    var node = document.createElement("img");
    node.src = "blank.png";
    node.alt = "blank";
    node.onclick = function(ev) {
      node.src = "lego" + (c+1) + ".png";
      node.alt = "lego" + (c+1);
    };
    return node;
  });

  nodes.forEach( function(n) {
    div.appendChild(n);
  });
}
// '<img src="lego' + (c+1) + '.png" alt="lego' + (c+1) +'"/>'
function shuffleCards(length) {
  var cards = [];
  for(var i = 0; i < length; i++) {
    cards.push(i);
    cards.push(i);
  }
  return cards;
}
