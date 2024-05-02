import React, {useState, useEffect} from 'react'

export default ({updated, setUpdated, setBook, setEditing}) => {
  const [books, setBooks] = useState([]);
    useEffect(() => {
      //setUpdated(false)
        fetch('http://localhost:3001/books')
            .then(res => res.json())
            .then(data => {
              setBooks(data);
            })
    }, [updated])

    const handleDelete = (e) => {
      console.log("clicked delete", e.target.id)
      //setUpdated(!updated)
      fetch(`http://localhost:3001/books/${e.target.id}`, {method: 'DELETE'})
        .then(() => {
          setUpdated(!updated)
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


// import React, { useEffect, useState } from 'react'

// export default ({setUpdated, updated, setBook}) => {
//   const [books, setBooks] = useState([])
//   //const [updated, setUpdated] = useState(false) // denne opløftes til parent så vi kan sende data mellem child - UPLIFTED...


//   useEffect(() => {

//     fetch("http://localhost:3001/books").then(res => res.json()).then(data => setBooks(data))
//   }, [updated])

  
//   const handleDelete = (e) => {
//     console.log("delete button clicked", e.target.id)
    
//     fetch(`http://localhost:3001/books/${e.target.id}`, {
//       method: 'DELETE'
//     })
//       .then(() => setUpdated(!updated)) // vi ændre status på updated for at trigger use effect useeffect
//   }

//   // const handleEdit = (e) => {
//   //   console.log("edit button clicked", e.target.id);
//   //   const bookId = String(e.target.id); // Konverter ID'et til en streng
//   //   const selectedBook = books.find(book => String(book.id) === bookId); // Find den valgte bog i books-arrayet
//   //   console.log("Selected Book:", selectedBook); // Udskriv den valgte bog til konsollen
//   //   setBook(selectedBook); // Opdater bogtilstanden med den valgte bog
//   // };

//   const handleEdit = (e) => {
//     console.log("clicked edit", e.target.id)
//     fetch(`http://localhost:3001/books/${e.target.id}`, {method: 'GET'})
//       .then(res => res.json())
//       .then(data => {
//         setBook(data)
//         setEditing(true)
//         console.log(data)
//       })
//   }
  



//   return (
//     <div>
//       <h1>BookList</h1>
//       <>
//         <table>
//           <thead>
//             <tr>
//               <th>ID</th>
//               <th>Title</th>
//               <th>Author</th>
//               <th>Rating</th>
//               <th>Year Published</th>
//               <th>Action</th>
//             </tr>
//           </thead>
//           <tbody>
//             {books.map((book) => (
//               <tr key={book.id}>
//                 <td>{book.id}</td>
//                 <td>{book.title}</td>
//                 <td>{book.author}</td>
//                 <td>{book.rating}</td>
//                 <td>{book.year_published}</td>
//                 <td><button id={book.id} onClick={handleDelete}>Delete</button></td>
//                 <td><button value={book} id={book.id} onClick={handleEdit}>Edit</button></td>
//               </tr>
//             ))}
//           </tbody>
//         </table>
//       </>
//     </div>
//   )
// }
