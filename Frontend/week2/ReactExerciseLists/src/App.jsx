import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListDemo from './ListDemo'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <ListDemo />
    </>
  )
}

export default App
