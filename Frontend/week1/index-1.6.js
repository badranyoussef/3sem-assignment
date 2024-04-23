var cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

function generateTable(data) {
  // Bygger HTML String til table
  let html = '<table border="1">';
  
  // Tilføjer header
  html += '<tr>';
  for (let key in data[0]) {
      html += '<th>' + key + '</th>';
  }
  html += '</tr>';
  
  // tilføjer rækker/row pr objekt
  data.map(item => { 
      html += '<tr>';
      for (let key in item) {
          html += '<td>' + item[key] + '</td>';
      }
      html += '</tr>';
  });
  // lukker tabellen
  html += '</table>';

  console.log(html);

  return html;
}

// Generer tabellen og indsæt i HTML-dokumentet
let tableHTML = generateTable(cars);
document.getElementById('tableContainer').innerHTML = tableHTML;

// Function to filter cars based on price
function filterCarsByPrice(maxPrice) {
  if (maxPrice === '' || isNaN(maxPrice)) {
    alert('Please enter a valid price.');  
    return cars;
  } else {
      return cars.filter(car => car.price <= maxPrice);
  }
}

// Function to handle filter button click
document.getElementById('filterButton').addEventListener('click', function() {
  let maxPrice = document.getElementById('filterInput').value.trim();
  let filteredCars = filterCarsByPrice(maxPrice);
  let filteredTableHTML = generateTable(filteredCars);
  document.getElementById('tableContainer').innerHTML = filteredTableHTML;
});

// Function to handle filter button click
document.getElementById('filterButton').addEventListener('click', function() {
  let keyword = document.getElementById('filterInput').value;
  let filteredCars = filterCars(keyword);
  let filteredTableHTML = generateTable(filteredCars);
  document.getElementById('tableContainer').innerHTML = filteredTableHTML;
});