// EX 1.1.1
let names = ["Lars", "Peter", "Jan", "Bo"];

function myFilter(array, callback){
    let arrResult = [];
    for (let i = 0; i < array.length; i++) {
      if(callback(array[i])){
      arrResult.push(array[i])
      }
  }
  return arrResult;
}

// testring my function
let shortNames = myFilter(names, navn => navn.length <= 3);
console.log("Short names:", shortNames);


function callbackFunc(arry){
    return arry.length <= 3;
}

let shortNames2 = myFilter(names, callbackFunc);
console.log("Short names:", shortNames2);


// 1.1.3 Opret en funktion kaldet myMap
function myMap(array, tilbagekald) {
  let resultat = [];
  for (let i = 0; i < array.length; i++) {
      resultat.push(tilbagekald(array[i]));
  }
  return resultat;
}

let upperCase = myMap(names, name => name.toUpperCase());
console.log("Names Uppercase:", upperCase);

function callbackFunc2(arry){
  return arry.toUpperCase();
}

let upperCase2 = myMap(names, callbackFunc2);
console.log("Names Uppercase:", upperCase2);

//EX 1.2

// Fjerner array argument således at vi tilføjer funktionen til Array-Prototypen
function myFilter2(callback) {
  let arrResult = [];
  for (let i = 0; i < this.length; i++) {
    if(callback(this[i])){
      arrResult.push(this[i]);
    }
}
return arrResult;
}

// Fjerner array argument således at vi tilføjer funktionen til Array-Prototypen
function myMap2(callback) {
  let arrResult = [];
  for (let i = 0; i < this.length; i++) {
      arrResult.push(callback(this[i]));
  }
  return arrResult;
}

// Tilføjer funktionerne til Array-Prototypen
Array.prototype.myFilter2 = myFilter2;
Array.prototype.myMap2 = myMap2;

// Nu kan metoder benyttes direkte på enhver array

console.log(names.myFilter2(name => name.toLowerCase() === "lars"));

console.log(names.myMap2(name => name.toLowerCase()))