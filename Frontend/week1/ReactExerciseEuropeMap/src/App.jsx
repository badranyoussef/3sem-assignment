import React, { useState, useEffect } from 'react';
import SvgComponent from './SvgComponent';

function App() {
  const [selectedCountry, setSelectedCountry] = useState(null); //sætter værdien til null ved opstart at program.
  const [countryData, setCountryData] = useState(null); //sætter værdien til null ved opstart at program.

  function clickHandler(event) {
    setSelectedCountry(event.target)
  }

  useEffect(() => {
    if (selectedCountry != null) { // da useEffect kaldes første gang når programmet kører er selectedCountry null... Derfor må der ikke fetches før brugeren klikker på et land.
      fetch('https://restcountries.com/v3.1/alpha/' + selectedCountry.id) //således får vi tilføjet lande ID til api'et
        .then(response => response.json())
        .then((data) => {
          const countryObject = {
            name: data[0].name.common,
            capital: data[0].capital,
            population: data[0].population
          };
          setCountryData(countryObject);
        })
        .catch(error => console.error('Error fetching joke:', error));
    };
  }, [selectedCountry]); // Jeg sætter selectedCountry ind i Array (dependecie) for at sikre at useEffect kaldes hver gang selectedCountry ændres
  // dvs hvis Arrayet (som er en dependency) er tom eller fjernes, vil useEffect kun kaldes én gang

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
      <div onClick={clickHandler}> {/* sætter en onClick på den ydre div for at fange den enkelte div der klikkes på inde i SVG filen. */}
        <SvgComponent />
      </div>
    </div>
  );
}

export default App;
