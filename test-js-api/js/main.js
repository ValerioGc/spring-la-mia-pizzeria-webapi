
let app = document.getElementById("container");


fetch('http://localhost:8080/api/pizzas/index')
  .then((response) => response.json())
  .then((data) =>  {

    console.log(data);

    for (let i = 0; i < data.length; i++) {
      app.innerHTML += 
      ` <div class="card p-4 text-center">
          <h4>Pizza:</h4>
          <ul class="list-group w-50 m-auto">
            <li class="w-25 m-auto"> ${data[i].name} </li>
            <li class="w-25 m-auto"> ${data[i].description} </li>
          </ul>
        </div> `;
        
      console.log(data[i])
    }
  });
  




