// Tilføj en enkelt klikhåndterer til knapperne
document.getElementById('buttons').addEventListener('click', getValueOfButton);

function getValueOfButton(button) {
  // Få teksten i den klikkede knap via event target
  let buttonText = button.target.textContent;

  // Hvis knappen ikke er "=" (calculate), tilføj værdien til displayet
  if (buttonText !== '=') {
      document.getElementById('display').textContent += buttonText;
  }
}


// Tilføj en ny klikhåndterer til "=" (calculate) knappen
document.getElementById('calculate').addEventListener('click', function(event) {
  // Forhindre hændelsen i at boble op til containerens klikhåndterer
  event.stopPropagation();

  // Hent indholdet af displayet
  let expression = document.getElementById('display').textContent;

  // Tjek om displayet indeholder en gyldig matematisk operation
  if (expression.includes('+') || expression.includes('-') || expression.includes('*') || expression.includes('/')) {
      // Udfør beregning ved hjælp af eval og vis resultatet på displayet
      document.getElementById('display').textContent = eval(expression);
  } else {
      // Hvis displayet ikke indeholder en gyldig operation, vis fejlmeddelelse
      document.getElementById('display').textContent = 'Error';
  }
});


