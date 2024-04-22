// Array of names
let names = [];

// Function to generate the name list
function generateNameList() {
    // Clear the previous list
    document.getElementById("nameList").innerHTML = "";

    // Generate the new list
    names.map(name => {
      let listItem = document.createElement("li");
      listItem.textContent = name;
      document.getElementById("nameList").appendChild(listItem);
  });
}

// Function to handle form submission
function handleSubmit(event) {
    event.preventDefault(); // Prevent form submission

    // Get the input value
    let newName = document.getElementById("nameInput").value;

    // Add the new name to the array
    if(newName.trim() !== "" && isNaN(newName)){
    names.push(newName);

    // Generate the updated name list
    generateNameList();
    }else{
      document.getElementById("errorMessage").innerHTML = "You did not type a valid name. try again"
    }



    // Clear the input field
    document.getElementById("nameInput").value = "";
}

// Function to remove the first name from the list
function removeFirst() {
    names.shift(); // Remove the first name
    generateNameList(); // Generate the updated name list
}

// Function to remove the last name from the list
function removeLast() {
    names.pop(); // Remove the last name
    generateNameList(); // Generate the updated name list
}

// Add event listener to the form
document.getElementById("nameForm").addEventListener("submit", handleSubmit);

// Initial generation of the name list
generateNameList();