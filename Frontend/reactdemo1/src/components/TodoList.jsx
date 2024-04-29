import React, { useEffect, useState } from 'react'

export default ({ todos, setTodos }) => {



  useEffect(
    () => {
      fetch("https://jsonplaceholder.typicode.com/todos")
        .then((response) => response.json()) //det første Then bruges til at exportere kun body i json. ellers kommer alt med som header og andet data
        .then((todos) => {
          console.log(todos)
          setTodos(todos)
        })
    }, []
  );

  return (
    <>
      <div>TodoList</div>
      <hr /> {/* horisontalt linje */}
      {
        todos.map(
          (todo) => (
            <div key={todo.id}>
              <div style={{ width: '100%', display: 'flex', justifyContent: 'space-between' }}>
                <div>TODO ID: {todo.id}</div> <div>TODO: {todo.title} </div><div> Completed: <input type="checkbox" checked={todo.completed} readOnly /></div>
              </div>
            </div>
          )
        )
      }
    </>
  )
}