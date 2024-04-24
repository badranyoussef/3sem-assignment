import React from "react";
import { persons } from "./file2";

function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

export function MultiWelcome() {
  return (
    <div>
      <Welcome name="Sara" />
      <Welcome name="Cahal" />
      <Welcome name="Edith" />
      <WelcomePerson person={persons[0]} />
      <WelcomePerson person={persons[1]} />
      <WelcomePerson person={persons[2]} />
      <p>Now same as above but with map()</p>
      <p>{persons.map((person, index) => <WelcomePerson key={index} person={person} />)}</p>
      <p>
        {persons.map((person, index) => {
          return <WelcomePerson key={index} person={person} />;
        })}
      </p>

    </div>
  );
}

export function WelcomePerson(props) {
  const { firstName, lastName, email } = props.person; // Destrukturer person objektet... i MultiWelcome definerer vi key til at være "person"

  return (
    <div>
      <p>Name: {firstName} {lastName}</p>
      {/* <p>Email: {email}</p>  <-- man kunne også gør det direkte via props.person.email */}
      <p>Email: {props.person.email}</p>
    </div>
  );

}