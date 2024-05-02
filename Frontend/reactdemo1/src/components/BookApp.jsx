import React, {useState} from 'react'
import BookForm from './BookForm.jsx'
import BookList from './BookList.jsx'
import '../App.jsx'

export default () => {
  const [updated, setUpdated] = useState(false);
  const [editing, setEditing] = useState(false);
  const [book, setBook] = useState({
    title: '',
    author: '',
    rating: '',
    year_published: ''
  });
  return (
    <>
      <BookForm updated={updated} setUpdated={setUpdated} book={book} setBook={setBook} editing={editing} setEditing={setEditing} />
      <BookList updated={updated} setUpdated={setUpdated} setBook={setBook} setEditing={setEditing} />
    </>
  )
}