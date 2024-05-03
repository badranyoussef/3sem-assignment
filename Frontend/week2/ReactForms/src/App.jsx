import './styles/App.css';
import PersonList from './components/PersonList';
import PersonForm from './components/PersonForm';
import { useState, useEffect } from 'react';
import { fetchData } from './util/persistence';

const blankPerson = { id: '', age: '', name: '', email: '', gender: '' };

function App() {
  const [persons, setPersons] = useState([]);
  const [personToEdit, setPersonToEdit] = useState(blankPerson);

  const APIURL = 'http://localhost:3000/api';

  function editPerson(person) {
    setPersonToEdit(person);
  }

  function mutatePerson(person) {
    if (person.id != '') {
      // PUT
      updatePerson(person);
    } else {
      // POST
      createPerson(person);
    }
  }

  function updatePerson(person) {

    console.log('update');
    fetchData(
      `${APIURL}/${person.id}`,
      (person) => {
        setPersons(
          persons.map((p) => (p.id === person.id ? { ...person } : p))
        );
      },
      'PUT',
      person
    );
  }

  function createPerson(person) {
    console.log('create');
    fetchData(
      APIURL,
      (person) => setPersons([...persons, person]),
      'POST',
      person
    );
  }

  function getPersons(callback) {
    // Fetch data
    fetchData(APIURL, callback);
  }

  function deletePersonById(personId) {
    // Fjern via API - JSONServer
    fetchData(`${APIURL}/${personId}`, () => {}, 'DELETE');
    // Fjern fra persons array via setPesons()
    setPersons([...persons.filter((p) => p.id != personId)]);


  //   fetch("http://localhost:3000/api/", {
  //     method: "DELETE"
  // }).then(() => {
  //         setPersons([...persons.filter((p) => p.id != personId)]);
  // })
  }

  useEffect(() => {
    // get all persons
    getPersons((data) => setPersons(data));
  }, []);

  return (
    <div>
      <h1>Person DB FORM</h1>
      <PersonForm
        blankPerson={blankPerson}
        personToEdit={personToEdit}
        mutatePerson={mutatePerson}
      />

      <PersonList
        persons={persons}
        deletePersonById={deletePersonById}
        editPerson={editPerson}
      />
    </div>
  );
}

export default App;