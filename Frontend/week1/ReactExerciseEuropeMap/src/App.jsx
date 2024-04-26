import React, { useState } from 'react';
import SvgComponent from './SvgComponent';

function App() {
  const [selectedCountry, setSelectedCountry] = useState(null);
  const [countryData, setCountryData] = useState(null);

  const handleCountryClick = (event) => {
    const countryCode = event.target.getAttribute('id');
    setSelectedCountry(countryCode);

    fetch(`https://restcountries.com/v3.1/alpha/${countryCode}`)
      .then(response => response.json())
      .then(data => setCountryData(data))
      .catch(error => console.error('Error fetching country data:', error));
  };

  return (
    <div>
      <h1>Europe Map</h1>
      <SvgComponent handleCountryClick={handleCountryClick} />
      {selectedCountry && countryData && (
        <div>
          <h2>{countryData.name.common}</h2>
          <p>Capital: {countryData.capital}</p>
          <p>Population: {countryData.population}</p>
          <p>Region: {countryData.region}</p>
          {/* Add more details as needed */}
        </div>
      )}
    </div>
  );
}

export default App;
