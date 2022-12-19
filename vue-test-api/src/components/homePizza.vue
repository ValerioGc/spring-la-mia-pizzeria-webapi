<template>
    <main>
        <div v-if="pizzasArray.length > 0" class="card-container" >
            <div class="card">
                <ul v-for="(pizza, index) in pizzasArray" :key="index">
                    <li>Noem: {{pizza.name}}</li>
                    <li>Descrizione: {{pizza.description}}</li>
                    <li>Prezzo: {{pizza.price}}</li>
                </ul>
                <button>Modifica</button>
                <button>Elimina</button>
            </div>
        </div>
        <div v-else>
            <h3>Nessuna Pizza presente</h3>
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
                pizzasArray: []
            }
        },
        mounted() {
            axios.get(this.apiUrl + "/index")
            .then( response => {
                
                const pizzasList = response.data;
                
                if (pizzasList == null) return;
                
                this.pizzasArray = pizzasList;
            })
        }
    })
</script>


<style lang="sass" scoped>

</style>