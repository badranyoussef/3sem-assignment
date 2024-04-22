//console.log benyttes til at udskirve data til konsollen.. Ligesom sout i java... bruges til at debugge
console.log("Hello World!"); //  Som der ses på Hello World linjerne kan man bruges '' og "" til at definere at det er en String og samtidig benytte '' eller "" i selve string værdien
console.log('"Hello World!"');
console.log("'Hello World!'");

//data typer (modsat java som er statist baseret satatyper, så er det ved JS dynamisk)
//dette eksemepel viser at datatyper er dynamisk. Starter med at deklereres som string derefter int.
let fullName = "John Doe";
fullName = 12;

//fere datatyper
let isBoolean = true;
// const er et keyword man benytter når en variable IKKE må laves om.
// const obj = {};  // <-- key: value
// const arr = [];  // <-- 0 indekseret arrayliste
const mother = {name: "Lone", age:42};
const obj = {name: "Jane Doe", age: 25, isStudent: true, friends: ["Jane","Jack","John"], mother:mother};
obj.name = "Youssef"; // <-- ændre value for property name

console.log(obj.name +" "+ obj.age);
console.log(obj);


const arr = [1, "john", true, {name:"lasse", age: 15}];
arr.push("John");
console.log(arr);
const popped = arr.pop();
console.log("popped: ", popped) // <-- Print til konsol sker anderledes end JAVA.... Flere objekter kna udskrives ved hjælp af ","

const subarray = arr.slice(0,2) // <-- "slicer" arrayet/returnerer et nyt array med data det første index der gives til det andet index der gives
console.log(subarray);

const myValue = null; console.log(myValue); // <-- null intentional
let abs; console.log(abs); // <-- undefined fordi man ikke har initializeret

function myFunction(){
  console.log("Hello from myFunction")
}

myFunction();

function myFunctionWithArg(name, age){
  console.log("Hello from myFunction", name, age)
}

myFunctionWithArg(obj.name,obj.age);

function myFunctionWithCondition(name, age){
  if(typeof name === "string"){
    console.log("Hello from myFunction", name, age)
  }else{
    console.log("the entered name was not a String")
  }
}

myFunctionWithCondition(obj.name,obj.age);
myFunctionWithCondition(obj.age,obj.age);

// når man benytter 3 x = så sammenligner man noget... 

const var1 = 10;
const var2 = "10";

console.log(var1 == var2); // true ----- her sammenlignes kun værdi
console.log(var1 === var2); // false ----- Her sammen lignes både værdi og type

const myString = "Hello World";
console.log(myString.length);
console.log(myString.substring(0,5));
console.log(myString.search("World")); // <-- hvis ordet findes får jeg index for start bogstavet retur
console.log(myString.replace("Hello", "Hi"));
console.log(myString.indexOf("World"));

const array = ["john", "jane", "jack", "jill"]
  for(let i = 0 ; i < array.length ; i++){
      console.log(i)
  }

  array.forEach(function (name){
    console.log(name);
  });

  array.forEach((name) => {
    console.log(name);
  });

  array.forEach((name, index) => {   // <-- det er prædefineret at det andet argument som kan sendes med er indexet for elementet
    console.log(name, index);
  });

  const newArray = arr.map((name)=> { // <-- map i dette tilfælde returnere et nyt Array. Det som foregår i Map kaldes i dette tilfælde for et CallBack da vi sender en function som et argument.
    return name + " Doe";
  })

  console.log(newArray)


// for at illusterere callback se her:

const addDoe = (name) => {
  return name + " doe";
};

// eller også kan det laves som en function:

function addDoe2(name){
  return name + " doe";
};

// nu kan vi bruge en af ovenstående til map functionen

const newArray2 = arr.map(addDoe2);
console.log(newArray2);

for (let key in obj) {
  console.log (key, obj[key])
}

for(let prop in obj){
  console.log(prop, obj.prop)
}


//Desctructoring

const {name : fullname, age} = obj;
console.log(fullName, age);

const [first, second, ...rest] = array;
console.log(first,second,rest);

const obj2 = {...obj, name:"Hans Doe", age: 30} //<-- ... betyder at vi pakker noget ind eller ud.
console.log(obj2)