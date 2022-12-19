<template>
    <main>
        <p v-if="msg != ''">
            {{msg }}
        </p>
        <div class="form-new-pizza py-3 form">
            
            <button @click="new_pizza_form = true">Crea nuova pizza</button>

            <form v-if="new_pizza_form == true" @submit="createPizza">
                
                <label>Crea una nuova pizza:</label>
                
                <label for="name">Inserisci il nome della pizza:</label>
                <input type="text" name="name" id="name" v-model="new_pizza.name"/>

                <label for="name">Inserisci la descrizione della pizza</label>
                <input type="text" name="description" id="description" v-model="new_pizza.description"/>

                <label for="name">Inserisci il prezzo della pizza</label>
                <input type="number" name="price" id="price" v-model="new_pizza.price"/>

                <input type="submit" value="Crea"  />
            </form>
        </div>


        <div v-if="pizzasArray.length > 0" class="card-container" >
            <div v-for="(pizza, index) in pizzasArray" :key="index" class="card">
                <ul >
                    <li>Nome: {{pizza.name}}</li>
                    <li>Descrizione: {{pizza.description}}</li>
                    <li>Prezzo: {{pizza.price}}</li>

                    <li v-if="pizza.ingredients != null">
                        Ingredienti: 
                        <span v-for="(ingredients, indexIng) in pizza.ingredients" :key="indexIng"> {{ingredients.name}}, </span>
                    </li>
                    <li v-else>
                        <span>Ingredienti non disponibili </span>
                    </li>
                </ul>


                <form :id="'editPizza-' + pizza.id" @submit="editPizza" class="d-none">
                
                    <label>Modifica pizza:</label>
                
                    <label for="name">Inserisci il nome della pizza:</label>
                    <input type="text" name="name" id="name" v-model="new_pizza.name" />
                
                    <label for="name">Inserisci la descrizione della pizza</label>
                    <input type="text" name="description" id="description" v-model="new_pizza.description" />
                
                    <label for="name">Inserisci il prezzo della pizza</label>
                    <input type="number" name="price" id="price" v-model="new_pizza.price" />
                
                    <input type="submit" value="Modifica" />
                    <button @click="editPizza(PIZZE_EDIT_ID_DEFAULT)">CANCEL</button>
                </form>


                <button>Dettagli</button>
                <button @click="showEditForm(pizza.id)">Modifica</button>

                <button>Elimina</button>
            </div>
        </div>
        <div v-else>
            <h3>Nessuna Pizza presente!</h3>
        </div>
    </main>
</template>

<script>

    import axios from 'axios';

    export default ({
        name:"homePizza",
        data () {
            return {
                apiUrl:"http://localhost:8080/api",
                msg:"",
                
                // ---- Pizze ----
                pizzasArray: [],

                // ---- new pizza ----
                new_pizza_form: false,
                new_pizza: {},
                // --------------------
            }
        },
        mounted() {
            axios.get(this.apiUrl + "/pizzas/index")
            .then( response => {
                const pizzasList = response.data;
                if (pizzasList == null) return;

                this.pizzasArray = pizzasList;

                for (let i = 0; i < pizzasList.length;  i++) {
                    this.getIngredients(i);
                }
            })  
        },
        methods: {
        //  Mostra menu edit pizza
            showEditForm(id) {
                document.getElementById("editPizza-" + id).classList.toggle("d-none");
            },
        //  Trova Id pizza
            getPizzaIndexById(id) {
                for (let i = 0; i < this.pizzasArray.length; i++) {
                    const pizza = this.pizzasArray[i];
                    
                    if (pizza.id == id) {
                        return i;
                    }
                }
                return -1;
            },
        //  Trova pizza
            getPizzaById(id) {
                return this.pizzasArray[this.getPizzaIndexById(id)];
            },
        //  Ingredienti
            getIngredients(id) {
                axios.get(this.apiUrl + "/ingredients/pizza/" + id)
                    .then(response => {
                        const ingr = response.data;
                        if (ingr == null) return;

                        const indx = this.getPizzaIndexById(id);
                        this.pizzasArray[indx].ingredients = ingr;
                    });
            },
        //  Crea pizza
            createPizza(e) {
                e.preventDefault();
                axios.post(this.apiUrl + "/pizzas/store", this.new_pizza)
                    .then(response => {
                        const pizza = response.data;
                        if (pizza == null) return;

                        this.pizzasArray.push(pizza);
                        this.new_pizza_form = false;
                        this.msg = "Pizza creata con successo";
                    });
            },
        //  Modifica pizza
            editPizza(id) {

            }
        }
    })
</script>


<style lang="scss" scoped>
.d-none { 
    display: none;
}
</style>