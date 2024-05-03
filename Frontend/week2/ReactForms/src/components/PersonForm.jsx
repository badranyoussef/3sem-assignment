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
        // Validering: Kontroller om alle felter er udfyldt
        if (!person.age || !person.fullname || !person.gender || !person.email) {
          alert("Alle felter skal udfyldes pånær ID!");
          return;
      }

    event.preventDefault();
    console.log('submit', person);
    mutatePerson(person);
    // callback function fra App.jsx, som enten indsætter en ny person (hvis id er tom)
    // eller opdaterer (hvis id != "")
  }

  function handleReset(event) {
    event.preventDefault();
    setPerson({ ...blankPerson });
  }

  return (
    <div>
      <h3>Add/Edit Person</h3>
    
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
          onClick={handleReset}
        >
          Reset
        </button>
      </form>
    </div>
  );
}

export default PersonForm;