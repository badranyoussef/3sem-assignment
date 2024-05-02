
import { useEffect, useState } from 'react'
import { PersonForm } from './components/PersonForm'
import { PersonList } from './components/PersonList'
import './styles/App.css'
import { fetchData } from './util/persistence'


//tomt objekt vi skal bruge
const blankPerson = {id:'', age: '', name: '', email: '', gender: ''}

function App() {
  const [persons, setPerons] = useState([])
  const [personToEdit, setPersonToEdit] = useState(blankPerson);

  const APIURL = "http://localhost:3000/api"

  function getPersons(callback) {
    //fetch data
    fetchData(APIURL, callback)
  }

  function editPerson (person){
    setPersonToEdit(person)

  }

  function mutatePerson(person){
    if (person.id != ""){
      //put
      updatePerson(person)
    }else{
      //POST
      createPerson(person)
    }
  }

  function updatePerson(person){
    fetchData(`${APIURL}/${person.id}`,
    setPerons(persons.map(p => p.id == person.id ? {...person}: p)),
    "PUT",
    person)
  }

  function createPerson(person){
    fetchData(`${APIURL}`, (person) => setPerons([...persons], person) ,"POST", person)

  }

  function deletePersonById(personId){
    //Fjern person fra jasonServer via API
    fetchData(`${APIURL}/${personId}`, ()=>{},"DELETE") //callback er tom da der ikke skal foregå noget. person skal blot slettes
    //Fjern fra persons array via setPersons
    setPerons([...persons.filter(p => p.id != personId)]);
  }

  useEffect(() => {
    // get all persons
    getPersons((data) => setPerons(data));
  }, [])

  return (
    <>
      <h1>Person DB</h1>
      <PersonForm blankPerson={blankPerson} personToEdit={personToEdit} mutatePerson={mutatePerson}/>
      <PersonList persons={persons} setPerons={setPerons} deletePersonById={deletePersonById} editPerson={editPerson}/>
    </>
  )
}

export default App
