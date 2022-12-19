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
                    <li>Ingredienti:</li>
                </ul>
                <button>Dettagli</button>
                <button>Modifica</button>
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
                apiUrl:"http://localhost:8080/api/pizzas",
                pizzasArray: [],

                // new pizza
                new_pizza_form: false,
                new_pizza: {},
                msg:"",
            }
        },
        mounted() {
            axios.get(this.apiUrl + "/index")
            .then( response => {
                
                const pizzasList = response.data;
                
                if (pizzasList == null) return;
                
                this.pizzasArray = pizzasList;
            })

        },
        methods: {
            createPizza(e) {
                e.preventDefault();
                axios.post(this.apiUrl + "/store", this.new_pizza)
                    .then(response => {
                        const pizza = response.data;
                        if (pizza == null) return;
                        this.pizzasArray.push(pizza);
                        this.new_pizza_form = false;
                        this.msg = "Pizza creata con successo";
                    });
            },
        }
    })
</script>


<style lang="sass" scoped>

</style>