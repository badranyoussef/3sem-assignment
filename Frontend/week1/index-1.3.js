// Funktion til at ændre farven på de tre divs
function changeColor() {
  var divs = document.getElementsByTagName("div"); // Hent alle divs på siden
  var colors = ["red", "green", "blue", "yellow", "brown", "black"];
  

  for (var i = 0; i < divs.length; i++) {
    var randomIndex = Math.floor(Math.random() * colors.length); // Generer et tilfældigt indeks
    divs[i].style.backgroundColor = colors[randomIndex]; //sætter farve
  }


  // man kunne også gør det simplere således:
  //Array.from(divs).map(element => element.backgroundColor(colors[randomIndex])) //men så 


}

// Tilføj klikhåndterer til knappen
document.getElementById("button").addEventListener("click", changeColor);
