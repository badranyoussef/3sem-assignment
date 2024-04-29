{/* I java arbejder vi med klasser i jsx arbejder vi med moduler */ }

import React,{ useState } from 'react'
import './App.css'
import PersonViewer from './components/PersonViewer'
import TodoList from './components/TodoList'
import TodoForm from './components/TodoForm'

function App() {
  const [todo, setTodo] = useState({title:'', userId:""});
  const [todos, setTodos] = useState([])

  return (
    <>
      <h1>Hello from APP</h1>
      <TodoForm todo={todo} setTodo={setTodo} setTodos={setTodos} todos={todos}/>
      <TodoList todos={todos} setTodos={setTodos} />
      <hr /> {/* horisontalt linje */}
      <PersonViewer name="Dorthe Danielson" age={20} />
    </>
  )
}

export default App