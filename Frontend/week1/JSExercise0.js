// EX 01
// initializing an array with names
const arrOfNames = ["Lars", "Jan", "Peter", "Bo", "Frederik", "Youssef", "Hanni", "Lasse", "Ahmad"];

// benytter filter metoden for at returnere en array med navne som har en længde på <= 3
const newArray = arrOfNames.filter(names => names.length <= 3);

//using foreach to print the elements
newArray.forEach(element => {
  console.log(element)
});

// EX 02
const arrUppercase = arrOfNames.map(names => names.toUpperCase());

arrUppercase.forEach(element =>{
  console.log(element)
})

//EX03

function generateNameList(navne) {
  // convert every name to <li> element
  let listeElementer = navne.map(navn => `<li>${navn}</li>`);

  // using join to create a string 
  let listeStreng = listeElementer.join('');

return `<ul>${listeElementer}</ul>`;
}

console.log(generateNameList(arrOfNames));

// eller således for at få det efterspurgte print hvor hver elemt i listen er på en linje for sig. Men i opgaven ønskes ovenstående output

function generateNameList2(navne) {
  // Bruger map til at konvertere elementerne til følgende:
  let listeElementer = navne.map(navn => `<li>${navn}</li>`);

  // Start HTML-strengen med <ul> tag bruger \n for at skabe en ny linje
  let htmlStreng = "<ul>\n";

  // Gennemløb alle <li>-elementer og tilføj dem til HTML-strengen
  listeElementer.forEach(element => {
      htmlStreng += `  ${element}\n`;
  });

  // Slut HTML-strengen med </ul> tag
  htmlStreng += "</ul>";

  // Returner den færdige HTML-streng
  return htmlStreng;
}

console.log(generateNameList2(arrOfNames));


//EX  04
let cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

let carsNewerThan1999 = cars.filter(car => car.year >1999);
console.log(carsNewerThan1999);

let volvoCars = cars.filter(car => car.make == "Volvo");
console.log(volvoCars);

let carsPriceBelow5000 = cars.filter(car => car.price < 5000);
console.log(carsPriceBelow5000);


function generateSQL(biler) {
  // Bruger map til at konvertere hvert bilobjekt til en SQL-statement. Ved index lægger jeg 1 til da array er nul indekseret. og ID for første bil skal starte med 1
  let sqlSætninger = biler.map((bil, index) => {
      return `INSERT INTO cars (id,year,make,model,price) VALUES ( ${index + 1}, ${bil.year}, '${bil.make}','${bil.model}', ${bil.price} );`;
  });

  // Bruger join til at kombinere statements til en samlet streng
  let sqlStreng = sqlSætninger.join('\n');

  // Returner den samlede SQL-streng
  return sqlStreng;
}

console.log(generateSQL(cars));
