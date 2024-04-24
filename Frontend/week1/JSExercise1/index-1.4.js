// Funktion til håndtering af klik på div1
function handleClickDiv1() {
  console.log("Hi from div1");
}

// Funktion til håndtering af klik på div2
function handleClickDiv2() {
  console.log("Hi from div2");
}

//EX 1.4.5
// Funktion til håndtering af klik på den ydre div
function handleClickOuter(event) {
  console.log("Hi from outer div");
  console.log("Event target id:", event.target.id);

  //EX 1.4.6 - Udskriver nedentående i <p> tagget
  document.getElementById("paragraph").innerHTML = `Event target id: ${event.target.id}`

}

// Hent div1 og tilføj klikhåndterer
var div1 = document.getElementById("div1");
div1.addEventListener("click", handleClickDiv1);

// Hent div2 og tilføj klikhåndterer
var div2 = document.getElementById("div2");
div2.addEventListener("click", handleClickDiv2);

// Hent outer div og tilføjer klikhåndtering
let outerDiv = document.getElementById("outer")


outerDiv.addEventListener("click", handleClickOuter)