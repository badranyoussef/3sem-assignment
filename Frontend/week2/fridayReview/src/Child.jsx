// Child.jsx
import React from 'react';

function Child({ count, setCount }) {
  const handleIncrement = () => {
    setCount(count + 1);
  };

  return (
    <div>
      <h2>Child Component</h2>
      <p>Count: {count}</p>
      <button onClick={handleIncrement}>Increment</button>
    </div>
  );
}

export default Child;