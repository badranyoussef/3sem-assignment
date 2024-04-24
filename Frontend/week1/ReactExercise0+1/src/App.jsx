import './App.css'
import upper, { text1, text2, text3 } from './file1';
import person from './file2'
import { males, females } from './file2'
import { MultiWelcome } from "./file3";

function App() {

  const { firstName, email } = person;


  //EX 2.2
  return (
    <>
      <h2>Ex 1</h2>
      <p>{text1}</p>
      <p>{text2}</p>
      <p>{text3}</p>
      <p>{upper("please uppercase me")}</p>
      <p>{upper(text3)}</p>
      <h2>Ex 2</h2>
      <p>Firstname: {firstName} ----- Email: {email}</p>
      <p>Showing all names:<DisplayNames /></p>
      <h2>EX 3</h2>
      <MultiWelcome />
    </>

  )
}



const DisplayNames = () => {

  const allNames = [...males, 'Kurt', 'Helle', ...females, 'Sarah', 'Tina']; // "..." spreder elementerne fra males og females ud i det nye array.
  
  console.log(allNames);
  console.log(personV2)

  return (
    <>
      <p>Males: {males.join(', ')}</p>
      <p>Females: {females.join(', ')}</p>
      <p>All names: {allNames.join(', ')}</p>
    </>
  );
}

const personV2 = {
  ...person, // Spread syntax... Denne bruger vi her til at hive al data ud af person Objektet og kopiere dem til det nye object
  phone: 123456,
  friends: [...males, ...females]
};

export default App
