import { useState, useEffect } from 'react';

function PersonForm({ blankPerson, personToEdit, updatePerson, mutatePerson }) {
  const [person, setPerson] = useState({ ...personToEdit });

  useEffect(() => {
    setPerson(personToEdit);
  }, [personToEdit]);

  function handleChange(event) {
    const value = event.target.value;
    const name = event.target.id;
    setPerson({ ...person, [name]: value });
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log('submit', person);
    mutatePerson(person);
    // callback function fra App.jsx, som enten indsætter en ny person (hvis id er tom)
    // eller opdaterer (hvis id != "")
  }

  return (
    <div>
      <h1>Add/Edit Person</h1>
      {JSON.stringify(person)}
      <form onSubmit={handleSubmit}>
        <label htmlFor="id">Id</label>
        <input
          id="id"
          type="number"
          readOnly
          placeholder="id"
          value={person.id}
        />
        <label htmlFor="name">Name</label>
        <input
          id="name"
          type="text"
          placeholder="name"
          value={person.name}
          onChange={handleChange}
        />
        <label htmlFor="age">Age</label>
        <input
          id="age"
          type="number"
          min="1"
          max="120"
          placeholder="age"
          value={person.age}
          onChange={handleChange}
        />
        <label htmlFor="email">Email</label>
        <input
          id="email"
          type="email"
          placeholder="email"
          value={person.email}
          onChange={handleChange}
        />
        <label htmlFor="gender">Gender</label>
        <select id="gender" value={person.gender} onChange={handleChange}>
          <option defaultChecked>Select Gender</option>
          <option value="male">Male</option>
          <option value="female">Female</option>
          <option value="other">Other</option>
        </select>

        <button className="m-2 btn btn-outline-success btn-sm">Update</button>
        <button
          className="btn btn-outline-danger btn-sm"
          onClick={() => setPerson(blankPerson)}
        >
          Reset
        </button>
      </form>
    </div>
  );
}

export default PersonForm;


// import { useEffect, useState } from 'react'

// export const PersonForm = ({ blankPerson, personToEdit, mutatePerson }) => {
//   const [person, setPerson] = useState({ ...personToEdit })

//   useEffect(() => {
//     setPerson(personToEdit)
//   }, [personToEdit]);

//   function handleChange(event) {
//     const value = event.target.value; // her hiver vi value ud fra input feltet. 
//     const name = event.target.id // her hiver vi name ud fra input feltet. name i dette tilfælde er navnet på property i json
//     setPerson({ ...person, [name]: value });
//   }

//   function handleSubmit(event) {
//     event.preventDefault(); //forhindre bobling. der er flere knapper og vi ønsker KUN at der sker en handling ved den knap vi klikker på.
//     // callback function fra App.jsx, som enten indsatter en ny person (hvis id er tom)
//     // eller opdaterer (hvis id !="")
//     mutatePerson(person)
//   }


//   return (
//     <div>
//       <form onSubmit={handleSubmit}>
//         <label htmlFor="id">Id</label>
//         <input id="id" type="number" readOnly placeholder="id" value={person.id} />
//         <label htmlFor="name" >Name</label>
//         <input id="name" type="text" placeholder="name" value={person.name} onChange={handleChange} />
//         <label htmlFor="age">Age</label>
//         <input id="age" type="number" min="1" max="120" placeholder="age" value={person.age} onChange={handleChange} />
//         <label htmlFor="email">Email</label>
//         <input id="email" type="email" placeholder="email" value={person.email} onChange={handleChange} />
//         <label htmlFor="gender">Gender</label>
//         <select id="gender" value={person.gender} onChange={handleChange}>
//           <option defaultChecked>Select Gender</option>
//           <option value="male">Male</option>
//           <option value="female">Female</option>
//           <option value="other">Other</option>
//         </select>
//         <button>Update</button>
//         <button onClick={() => setPerson(blankPerson)}>Reset</button>
//       </form>
//     </div>
//   )
// }