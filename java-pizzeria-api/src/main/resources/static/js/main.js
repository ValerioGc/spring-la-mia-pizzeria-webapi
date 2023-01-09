let pizzaDrop = document.getElementById('pizza-dropdown');
let drinkDrop = document.getElementById('drink-dropdown');
	
pizzaDrop.addEventListener("click", displayPizzasActions);
drinkDrop.addEventListener("click", displayDrinksActions);
	
	
let pzDrop = false;
let drDrop = false;
 	

let pizzaElAct = document.getElementsByClassName('nav-el');
let drinkElAct = document.getElementsByClassName('nav-el');


function displayPizzasActions() {
	
	if (drDrop == true) {
		document.getElementById("drink-actions").classList.toggle("d-none");
	}
	if (pzDrop == true) {
		pzDrop = false ;	
	} else {
		pzDrop = true;
	}
	document.getElementById("pizza-actions").classList.toggle("d-none");
	
}

function displayDrinksActions() {
	if (drDrop == true) {
		document.getElementById("pizza-actions").classList.toggle("d-none");
	}
	if (drDrop == true) {
		drDrop = false ;	
	} else {
		drDrop = true;
	}
	
	document.getElementById("drink-actions").classList.toggle("d-none");
}

// Controllo elementi attivi navbar ------------------------------------
for (let i = 0; i < pizzaElAct.length;i++) {
	if (pizzaElAct[i].classList.contains("active")) {
		document.getElementById("pizza-actions").classList.toggle("d-none");

	}
}


for (let i = 0; i < drinkElAct.length;i++) {
	if (drinkElAct[i].classList.contains("active")) {

		document.getElementById("drink-actions").classList.toggle("d-none");
	}
}
