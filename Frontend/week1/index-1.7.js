// Tilføj en enkelt klikhåndterer til knapperne
document.getElementById('buttons').addEventListener('click', getValueOfDisplay);


function getValueOfDisplay(event) {

  let buttonText = event.target.textContent;

  if (buttonText !== '=') {
    if (!isNaN(parseInt(buttonText))) {
      // Nulstil displayet, hvis der tidligere blev vist en fejl
      if (document.getElementById('display').textContent === 'Error') {
        document.getElementById('display').textContent = '';
      }
    }
    document.getElementById('display').textContent += buttonText;
  }
}

// Tilføj en ny klikhåndterer til "=" (calculate) knappen
document.getElementById('calculate').addEventListener('click', calculate);

function calculate(event) {
  // Hent indholdet af displayet
  let expression = document.getElementById('display').textContent;

  // Tjek om displayet indeholder en gyldig matematisk operation
  if (expression.includes('+') || expression.includes('-') || expression.includes('*') || expression.includes('/')) {
    // Udfører beregning ved hjælp af eval og viser resultatet på displayet
    document.getElementById('display').textContent = eval(expression);
  } else {
    // Hvis displayet ikke indeholder en gyldig operation, vises fejlmeddelelse
    document.getElementById('display').textContent = 'Error';
  }
}