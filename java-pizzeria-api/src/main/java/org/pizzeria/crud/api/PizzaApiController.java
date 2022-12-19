package org.pizzeria.crud.api;

import java.util.List;

import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin("*")
public class PizzaApiController {

	@Autowired
	PizzaService pizzaServ;

//  Index
	@RequestMapping("/index") 
	public List<Pizza> getPizzasList() {
		
		List<Pizza> pizzasList = pizzaServ.findAll();
		
		return pizzasList;
	}
	
	
//  Store
	@PostMapping("/store")
	public Pizza storePizza(@Valid @RequestBody Pizza pizza) {
		Pizza newP = null;
		try {			
			System.out.println("Dati pizza: " + pizza);

			newP = pizzaServ.save(pizza);
			
		} catch (Exception err) {
			System.err.println("Errore: \n" + err);

		}
		return newP;
	}
	
	
//  Edit
	@PostMapping("/update/{id}")
	public Pizza updatePizza(@PathVariable("id") int id, @Valid @RequestBody Pizza pizza) {
		Pizza newPizza = null;
		try {
			Pizza old = pizzaServ.findPizzaById(id).get();
			
			pizza.setIngredients(old.getIngredients());
			newPizza = pizzaServ.save(pizza);
			
		} catch (Exception err) {
			
			System.err.println("Errore: \n" + err);
		
		}
		return newPizza;
	}
	
	
//  Delete 
	@PostMapping("/delete/{id}")
	public void deletePizza(@PathVariable("id") int id) {
		pizzaServ.deletePizzaById(id);
	}
}
 