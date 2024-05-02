// import React, {useState}from 'react'

// export default ({setUpdated, updated, book, setBook}) => {
// // const[book, setBook] = useState({title:"", author:"", rating:"", year_published:""});

// const handleChange = (e) => {
//   setBook({...book, [e.target.id]: e.target.value});
// }

// // const handleSubmit = (e) =>{
// //   e.preventDefault();
// //   console.log(book);

// //   fetch('http://localhost:3001/books', {
// //     method:"POST",
// //     header: {
// //       'Content-Type': 'application/json'
// //     },
// //     body: JSON.stringify(book),
// //   })
// //   .then(res => {
// //     res.json()
// //   }).then(() => {
// //     setUpdated(!updated)
// //     setBook({title:"", author:"", rating:"", year_published:""})
// //   })
// // }

// // const handleSubmitEdit = (e) =>{
// //   e.preventDefault();
  
// //   console.log(book);
  
// //   fetch(`http://localhost:3001/books/${book.id}`, {
// //     method:"PUT",
// //     body: JSON.stringify(book),
// //     header: {
// //       'Content-Type': 'application/json'
// //     },
// //   })
// //   .then((res) => {
// //     res.json()
// //   }).then(() => {
// //     setUpdated(!updated)
// //     setBook({title:"", author:"", rating:"", year_published:""})
// //   });
// // }

// const handleSubmit = (e) => {
//   e.preventDefault();
//   console.log(book)
//   const httpMethod = editing ? 'PUT' : 'POST'
//   const url = editing ? `http://localhost:3001/books/${book.id}` : `http://localhost:3001/books`
//   fetch(url, {
//       method: httpMethod,
//       headers: {
//           'Content-Type': 'application/json'
//       },
//       body: JSON.stringify(book)
//   })
//   .then(res => {
//       res.json()
//   })
//   .then(data => {
//       console.log(data)
//       setUpdated(!updated)
//       setEditing(false)
//       setBook(initialBook)
//   })
// }

//   return (
//     <>
//     <form onSubmit={handleSubmit}>
      
//       <input id = "title" value={book.title} type="text" placeholder='Enter book title' onChange={handleChange}/>
//       <input id = "author" value={book.author} type="text" placeholder='Enter book author' onChange={handleChange}/>
//       <input id = "rating" value={book.rating} type="number" placeholder='Enter book rating' onChange={handleChange}/>
//       <input id = "year_published" value={book.year_published} type="number" placeholder='Enter year publishing' onChange={handleChange}/>
//       <button type='submit'>Submit</button>
//       <button onClick={handleSubmit}>edit Submit</button>
//     </form>
    
//     </>
//   )
// }


import React, { useState } from 'react'

export default ({updated, setUpdated, book, setBook, editing, setEditing}) => {
    const initialBook = {id:'', title:'', author:'', rating:'', year_published:''};
    //const [book, setBook] = useState({})

    const handleChange = (e) => {
        setBook({...book, [e.target.id]: e.target.value})
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(book)
        const httpMethod = editing ? 'PUT' : 'POST'
        const url = editing ? `http://localhost:3001/books/${book.id}` : `http://localhost:3001/books`
        fetch(url, {
            method: httpMethod,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
        .then(res => {
            res.json()
        })
        .then(data => {
            console.log(data)
            setUpdated(!updated)
            setEditing(false)
            setBook(initialBook)
        })
    }

  return (
    <>
      <form onSubmit={handleSubmit}>
        {/* ID has to be exactly the same af they are written in tj json file */}
        <input id="title" value={book.title} type="text" placeholder="Enter book title" onChange={handleChange} />
        <input id="author" value={book.author} type="text" placeholder='Enter author name' onChange={handleChange} />
        <input id="rating" value={book.rating} type="number" placeholder='Enter rating' onChange={handleChange} />
        <input id="year_published" value={book.year_published} type="number" placeholder='Enter year published' onChange={handleChange} />
        <button type="submit">{editing ? "Update book" : "Add book"}</button>
      </form>
    </>
  )
}