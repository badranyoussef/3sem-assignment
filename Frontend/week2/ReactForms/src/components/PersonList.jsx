function PersonList({ persons, deletePersonById, editPerson }) {
  return (
    <div>
      <h3>List of persons</h3>
      {/* uses bootstrap css. has been inserted in index.html (header) */}
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Age</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {persons.map((person) => (
            <tr key={crypto.randomUUID()}>
              <td>{person.id}</td>
              <td>{person.name}</td>
              <td>{person.age}</td>
              <td>{person.email}</td>
              <td>{person.gender}</td>
              <td>
                <button onClick={() => editPerson(person)}>Edit</button>
                <button onClick={() => deletePersonById(person.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default PersonList;