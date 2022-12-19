package org.pizzeria.crud.api;

import java.util.List;
import java.util.Set;

import org.pizzeria.crud.pojo.Ingredient;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.serv.IngredientService;
import org.pizzeria.crud.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin("*")
public class IngredientsApiController {
	
	
	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	IngredientService ingredientService;
	
//  Index
	@RequestMapping("/all") 
	public List<Ingredient> getIngredients() {
		
		List<Ingredient> ingredientsList = ingredientService.findAll();
		
		return ingredientsList;
	}
	
//  Pizzas Ingredients
	@RequestMapping("/pizza/{id}") 
	public Set<Ingredient> getPizzaIngredients(@PathVariable("id") int id) {
		
		Pizza pizza = pizzaService.findPizzaById(id).get();

		return pizza.getIngredients();
	}
}
