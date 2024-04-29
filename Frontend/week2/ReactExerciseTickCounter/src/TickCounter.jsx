import React, { useState, useEffect } from 'react';

function TickCounter() {
  // State to manage minutes, seconds, and running status
  const [minutes, setMinutes] = useState(0);
  const [seconds, setSeconds] = useState(0);
  const [isRunning, setIsRunning] = useState(false);

  // Function to start or pause the timer
  const toggleTimer = () => {
    setIsRunning((prevState) => !prevState); // Toggles between true and false
  };

  // Function to reset the timer
  const resetTimer = () => {
    setIsRunning(false); // Pause the timer
    setMinutes(0); // Reset minutes to 0
    setSeconds(0); // Reset seconds to 0
  };

  // Function to handle preset timer durations
  const handlePresetTimer = (presetMinutes) => {
    setIsRunning(true); // Start the timer
    setMinutes(presetMinutes); // Set minutes to the preset value
    setSeconds(0); // Reset seconds to 0
  };

  // Function to format time values (add leading zero if single digit)
  const formatTime = (value) => {
    return value < 10 ? `0${value}` : value;
  };

  // Effect to handle the timer logic
  useEffect(() => {
    let intervalId;

    // Check if the timer is running
    if (isRunning) {
      intervalId = setInterval(() => {
        // Update seconds
        if (seconds === 0) {
          if (minutes === 0) {
            // Timer has reached 0, stop the timer
            clearInterval(intervalId);
            setIsRunning(false);
          } else {
            // Decrease minutes and reset seconds
            setMinutes((prevMinutes) => prevMinutes - 1);
            setSeconds(59);
          }
        } else {
          // Decrease seconds
          setSeconds((prevSeconds) => prevSeconds - 1);
        }
      }, 1000); // Timer interval: 1000ms (1 second)
    } else {
      // Pause the timer (clear the interval)
      clearInterval(intervalId);
    }

    // Clean up function to clear the interval when the component unmounts or when the timer is paused
    return () => clearInterval(intervalId);
  }, [isRunning, minutes, seconds]); // Dependencies: isRunning, minutes, seconds

  return (
    <div>
      {/* Input fields for minutes and seconds */}
      <div>
        <input
          type="number"
          placeholder="Minutes"
          value={minutes}
          onChange={(e) => setMinutes(parseInt(e.target.value))}
        />
        <input
          type="number"
          placeholder="Seconds"
          value={seconds}
          onChange={(e) => setSeconds(parseInt(e.target.value))}
        />
        {/* Button to start/pause the timer */}
        <button onClick={toggleTimer}>{isRunning ? 'Pause' : 'Start'}</button>
        {/* Button to reset the timer */}
        <button onClick={resetTimer}>Reset</button>
      </div>
      {/* Preset timer buttons */}
      <div>
        <button onClick={() => handlePresetTimer(5)}>5 min</button>
        <button onClick={() => handlePresetTimer(10)}>10 min</button>
        <button onClick={() => handlePresetTimer(15)}>15 min</button>
      </div>
      {/* Display remaining time */}
      <div>Time Remaining: {formatTime(minutes)}:{formatTime(seconds)}</div>
    </div>
  );
}

export default TickCounter;
