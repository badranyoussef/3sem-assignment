
import { useState, useEffect } from 'react'
import './App.css'

function Counter({ initialCount }) {
  const [count, setCount] = useState(() => { //Passing a function that gets the saved count in the local storage
    const storedCount = localStorage.getItem('count'); // retrieving the stored count from browser
    return storedCount !== null ? parseInt(storedCount, 10) : initialCount; //returning the value of the saved count. If no saved value then return the value of the passed prop
  });

  useEffect(() => {
    localStorage.setItem('count', count);
  }, [count]);



  function increment() {
    setCount(prevCount => prevCount + initialCount)
  }

  function decrement() {
    setCount(prevCount => prevCount - initialCount)
  }

  return (
    <div>
      <h1>This is a counter</h1>
      <h3>{count}</h3>
      <button onClick={increment}>increment by One</button>
      <button onClick={decrement}>decrement by One</button>
    </div>
  )
}

export default Counter
