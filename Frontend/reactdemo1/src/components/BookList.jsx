import React, {useState, useEffect} from 'react'

export default ({updated, setUpdated, setBook, setEditing}) => {
  const [books, setBooks] = useState([]);
  //const [updated, setUpdated] = useState(false) // denne opløftes til parent så vi kan sende data mellem child - UPLIFTED...


    useEffect(() => {
        fetch('http://localhost:3001/books')
            .then(res => res.json())
            .then(data => {
              setBooks(data);
            })
    }, [updated])

    const handleDelete = (e) => {
      console.log("clicked delete", e.target.id)
      fetch(`http://localhost:3001/books/${e.target.id}`, {method: 'DELETE'})
        .then(() => {
          setUpdated(!updated) // vi ændre status på updated for at trigger use effect useeffect
        })
    }

    const handleEdit = (e) => {
      console.log("clicked edit", e.target.id)
      fetch(`http://localhost:3001/books/${e.target.id}`, {method: 'GET'})
        .then(res => res.json())
        .then(data => {
          setBook(data)
          setEditing(true)
          console.log(data)
        })
    }
  return (
    <>
      <h1>Book List</h1>
      <table>
        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Rating</th>
            <th>Year</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.title}</td>
              <td>{book.author}</td>
              <td>{book.rating}</td>
              <td>{book.year_published}</td>
              <td>
                <button id={book.id} onClick={handleDelete}>Delete</button>
                <button id={book.id} onClick={handleEdit}>Edit</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  )
}