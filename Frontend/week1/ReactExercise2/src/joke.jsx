import React, { useState, useEffect } from 'react';

function JokeComponent() {
    // State to store the joke
    const [joke, setJoke] = useState('');

      // Function to fetch a joke
    const fetchJoke = () => {
      fetch('https://api.chucknorris.io/jokes/random')
          .then(response => response.json())
          .then(data => setJoke(data.value))
          .catch(error => console.error('Error fetching joke:', error));
  };

      // useEffect to fetch the joke once on mount
      useEffect(() => {
        fetchJoke();
    }, []); // Empty dependency array means this runs once on mount

    // Render the joke
    return (
        <div>
            <p>{joke}</p>
            <button onClick={fetchJoke}>Get new joke</button>
        </div>
    );
}

export default JokeComponent;
