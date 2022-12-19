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
public class ApiController {

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
			
		} catch (Exception e) {
			System.err.println("Errore: \n" + e);

		}
		return newP;
	}
	
	
//  Edit
	@PostMapping("/update/{id}")
	public void updatePizza( @PathVariable("id") int id) {
		
	}
	
	
//  Delete 
	@PostMapping("/delete/{id}")
	public void deletePizza(@PathVariable("id") int id) {
		pizzaServ.deletePizzaById(id);
	}
}
 