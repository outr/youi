var guesses;
var solution;

var message = document.getElementById('message');
var input = document.getElementById('input');
var guessButton = document.getElementById('guess');
var resetButton = document.getElementById('reset');

reset();

function reset() {
    guesses = 0;
    solution = generateSolution();
    setMessage('Guess a number between 0 and 100.');
    input.value = '';
    guessButton.disabled = false;
    resetButton.disabled = false;
}

function attempt() {
    try {
        var guess = parseInt(input.value);
        guesses++;
        if (guess < solution) {
            setMessage('You guessed too low. Try a higher number.');
        } else if (guess > solution) {
            setMessage('You guess too high. Try a lower number.');
        } else {
            setMessage('You guessed it right in ' + guesses + ' tries.');
            guessButton.disabled = true;
        }
    } catch(err) {
        setMessage('Please enter a valid number between 0 and 100!');
    }
}

function generateSolution() {
    return Math.floor((Math.random() * 100.0) + 1.0);
}

function setMessage(value) {
    message.innerHTML = value;
}