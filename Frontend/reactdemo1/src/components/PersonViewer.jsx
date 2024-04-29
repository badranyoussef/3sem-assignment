import React, { useState, useEffect } from 'react' // useState og useEffect kaldes for HOOKS
// import as useStat bruges til at kunne ændre props. I dette tilfælde bruges det til at ændre nævnet

function PersonViewer({ name, age }) {
  {/* name og age er props som ikke er mutable */ }

  const [nameSate, setNameState] = useState(name) // initial value from props
  const [users, setUsers] = useState([{ id:1, name: 'Dorthe Danielsen', email: 'dorthe@d.com' }]);

  useEffect(() => {
    const myPromise = fetch("https://jsonplaceholder.typicode.com/users"); //fetch returnerer et Promise. den fanger vi i en const
    const mySecondPromise = myPromise.then((response) => response.json()); //returnere det andet promise via then()
    mySecondPromise.then((data) => {
      setUsers(data) // on mount -> læs op
    })
  }, []); // [] er et dependency array. Det er ikke obligatorisk men en god idé at have det med så det kun køres én gang med mindre man sætter værdi i som den skal lytte på.

  return (
    //<> {/* tomme tags kaldes for fragments og benyttes når der skal returneres flere div's. man kan nemlig kun sende én div */}
    //<ShowName name = {nameSate}/> {/* ShowName er functionen, name er key som får en værdi {name} som kommer fra parent længere oppe */}
    //<button onClick={() => setNameState('Dorthe')}>Set name</button>
    //<div>age: {age}</div>
    //</>
    <>
      {
        users.map((user) => (
          <div key={user.id}> {/* Altid vigtigt at tilknytte en id når der gennemløbes en liste */}
            <ShowName name={user.name} />
            <div>email: {user.email}</div>
          </div>
        ))
      }
    </>
  )
}

const ShowName = ({ name }) => <h3>Name: {name}</h3>

export default PersonViewer;