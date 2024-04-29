import React, { useState, useEffect } from 'react';
import SvgComponent from './SvgComponent';

function App() {
  const [selectedCountry, setSelectedCountry] = useState(null); //sætter værdien til null ved opstart at program. Vi gemmer det ID'et i denne variabel
  const [countryData, setCountryData] = useState(null); //sætter værdien til null ved opstart at program. Her gemmer vi al information omkring landet

  function clickHandler(event) {

    if (selectedCountry != null) { // Vi laver dette tjek for at sikre programmet kan køre. Ved opstart er selectedCountry null hvorfor der vi lopstå en null pointer.
      selectedCountry.style.fill = "#c0c0c0"; //dernæst skal valgte land markeres rødt. (linje 15). Når et andet land markeres køres denne linje så landet markeres gråt igen og derefter markeres det nye land med rødt
    }
    
    setSelectedCountry(event.target);
    event.target.style.fill = "red";
  }



  useEffect(() => {
    if (selectedCountry != null) { // da useEffect kaldes første gang når programmet kører er selectedCountry null... Derfor må der ikke fetches før brugeren klikker på et land.
      fetch('https://restcountries.com/v3.1/alpha/' + selectedCountry.id) //således får vi tilføjet lande ID til api'et
        .then(response => response.json())
        .then((data) => {
          const countryObject = {     // vi bruger destructing således at vi kan hive de ønskede data.
            name: data[0].name.common,
            capital: data[0].capital,
            population: data[0].population
          };
          setCountryData(countryObject);
        })
        .catch(error => console.error('Error country Data: ', error));
    };
  }, [selectedCountry]); // Jeg sætter selectedCountry ind i Array (dependecie) for at sikre at useEffect kaldes hver gang selectedCountry ændres
  // dvs hvis Arrayet (som er en dependency) er tom eller fjernes, vil useEffect kun kaldes én gang og det er når et land vælges første gang.

  return (
    <div>
      <h1>Europe Map</h1>
      {/* da countryData er null når programmet starter er vi nædt til at sætte && operatøren ind som i JS i dette tilfælde betyder at hvis countryData er null/falsy så skal countryData.id ikke vises */}
      {countryData && (
        <div> {/* Da vi har flere elementer skal alle elementer omslyttes en div eller fragment (tomt tag) --> Kræves af JSX */}
          <p> Country ID: {selectedCountry.id} </p>
          <p> Country name: {countryData.name} </p>
          <p> Capital: {countryData.capital} </p>
          <p> Population: {countryData.population} </p>
        </div>
      )}
      <div onClick={clickHandler}> {/* sætter en ClickHandler på den ydre div for at fange den enkelte div der klikkes på inde i SVG filen. - event bubbling */}
        <SvgComponent />
      </div>
    </div>
  );
}

export default App;
