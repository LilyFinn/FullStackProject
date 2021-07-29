"use strict"; 

const baseURL = "http://localhost:8080" 



axios.get(`${baseURL}/`)
    .then(res => { // handle response with callback
        console.log(res);
        console.log("DATA: ", res.data);
    }).catch(err => console.log(err));

    console.log("Have we got a response yet?");

    const getAllOutput = document.querySelector("#getAllOutput");
    const getByIdOutput = document.querySelector("#getByIdOutput");
    
    const animalId = document.querySelector("#animalId");
    
    const getAllSection = document.querySelector("#getAllSection");
    
    const getAllAnimals = () => {
    axios.get(`${baseURL}/animal/all`)
        .then(res => {
            const animal = res.data;
            getAllSection.innerHTML = ""; 
    
            animal.forEach(animal => renderAnimal(animal, getAllSection));
        }).catch(err => console.log(err));
    }



    // THIS MAKES IT LOOKS NICE 
const renderAnimal = (animal, outputDiv) => {
    const newAnimal = document.createElement('div'); 

    const animalSpecies = document.createElement('h3');
    animalSpecies.innerText = animal.species; 
    newAnimal.appendChild(animalSpecies)

    const animalID = document.createElement("h4");
    animalID.innerText = `ID: ${animal.id}`;
    newAnimal.appendChild(animalID);

    const animalLifeSpan = document.createElement("p"); 
    animalLifeSpan.innerText = `lifeSpan: ${animal.lifeSpan}`;
    newAnimal.appendChild(animalLifeSpan); 

    const animalColouring = document.createElement("p");
    animalColouring.innerText = `colouring: ${animal.colouring}`;
    newAnimal.appendChild(animalColouring);

    const animalDepthFound = document.createElement("p");
    animalDepthFound.innerText = `depthFound: ${animal.depthFound}`; 
    newAnimal.appendChild(animalDepthFound); 

    const deleteButton = document.createElement('button');
    deleteButton.innerText = "Delete";

    deleteButton.addEventListener('click', () => deleteAnimal(animal.id));

    newAnimal.appendChild(deleteButton);
     
   outputDiv.appendChild(newAnimal);

}


// THIS DELETES AN ANIMAL 
const deleteAnimal = id => {
    axios.delete(`${baseURL}/deleteAnimal/${id}`)
        .then(res => {
            console.log(res);
            getAllAnimals();
        }).catch(err => console.log(err));
}

//THIS FINDS AN ANIMAL BY ID 

const getByIdSection = document.querySelector("#getByIDSection");
const animalID = document.querySelector('#animalID');

const getAnimalById = () =>  {

    axios.get(`${baseURL}/getAnimal/${animalID.value}`)
    .then(res => {
        const animal = res.data;

        renderAnimal(animal, getByIdSection);

    }).catch(err => console.log(err));

}


//CREATING A NEW SEA ANIMAL

document.querySelector("section#postSection > form").addEventListener('submit', (e) => {
    e.preventDefault(); 

    const form = e.target;

const data = {
    species: form.species.value,
    colouring: form.colouring.value,
    lifeSpan: form.lifeSpan.value,
    depthFound: form.depthFound.value
}

console.log("DATA: ", data);

axios.post(`${baseURL}/createAnimal`, data)
.then((res) => {
    console.log(res);
    getAllAnimals();


    form.reset(); 
    //form.name.focus(); 

    alert("thank you for logging your sightings");

}).catch(err => console.log(err));
});


// UPDATE ANIMAL 

document.querySelector("section#updateSection > form#formSection").addEventListener('submit', (e) => {
    e.preventDefault(); 

const id = document.querySelector('#newAnimalID');
const form = e.target;

const data = {
    id: form.id.value,
    species: form.species.value,
    colouring: form.colouring.value,
    lifeSpan: form.lifeSpan.value,
    depthFound: form.depthFound.value
}

axios.put(`${baseURL}/replaceAnimal/${id.value}`, data)
.then((res) => {
    console.log(res);
    getAllAnimals();

    form.reset();
    form.id.focus();
    alert("Thank you for updated the information!")
}).catch(err => console.log(err));
});

getAllAnimals(); 

