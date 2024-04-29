import React, {useState} from "react";

const TodoForm = ({todo, setTodo, setTodos, todos}) => {

const handleSubmit = (event) => {
  event.preventDefault(); //prevent from reloading
  todo.id= Math.floor(Math.random()*10000)
  
  setTodos([...todos, todo]) // "...todos" generer et nyt array. derefter tilføjer vi "todo" til arrayet
}

const changeTodo = (event) => {
  setTodo({
    ...todo, 
    [event.target.id] : event.target.value // henter id og giver det værdien som indtastes i interfacet. bruges ved onChange længere nede
  })
}

//Lifting state up


  return (
    <>
    <form onSubmit={handleSubmit}>
      Title <input id='title' type="text" value ={todo.title} placeholder='write a new todo' onChange={changeTodo} /><br />
      User ID <input id ='userId'type="text" placeholder='enter userID' value ={todo.userId} onChange={changeTodo} /><br />
      <input type="submit" value="Add Todo" />
      </form>
    </>
  )

}

export default TodoForm;