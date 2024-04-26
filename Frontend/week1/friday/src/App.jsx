import React, {useState}from 'react'
import './App.css'

function App({ firstName, lastName, initialAge }) { // Modtager parametre fra komponentens props
  
  
  // initialiserer useState med initialAge som argument da det er denne vi ønsker at ændre
  const [age, setAge] = useState(initialAge); // Bruger useState til at ændre alderen
  // Deconstructoer returværdien fra useState... den returnerer en variable og en metode.
  
  
  // initialiserer en person objekt
  const person = {
    firstName: firstName, // Bruger parametrer til at definere personobjektet
    lastName: lastName,
    age: age
  };

  function older () {
    // funktionen incrementerer alderen
    setAge(previousAge => previousAge + 1);
  };

  function younger () {
    // funktionen decrementerer alderen
    setAge(previousAge => previousAge - 1);
  };

  return (
    // JSX format
    <div>
      <h1>Hello, {person.firstName} {person.lastName}!</h1>
      <p>You are {person.age} years old.</p>
      <button onClick={older}>Set older</button> {/* Knap til at ændre alderen */}
      <button onClick={younger}>Set younger</button> {/* Knap til at ændre alderen */}
    </div>
  );
}

export default App
