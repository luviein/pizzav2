## TASK ONE 

1. Set up Springboot application with relevant dependencies
2. Set up HTML files provided in ZIP folder. Standard HTML -> Template / Angular -> Static

### CONFIG CLASS

1. Set up Redis Config 
2. Add necessary properties in application properties


## TASK TWO

### MODEL CLASS (PIZZA)
1. Set up Pizza model class based on fields in HTML
2. Add validations 


### SERVICE CLASS
1. Create final array of pizza names and pizza sizes.
2. Compare if selected pizza is in the final pizza name
    - EG: Curry chicken is not in the final list = format an error message
3. Create constructor that generates Hash Set of final pizza names and pizza size


## TASK THREE

### DELIVERY MODEL
1. Add in properties that will be found in form
2. Add in validation as per requirements


### DELIVERY FORM HTML
1. Create delivery form
2. Add in validation for errors

### CONTROLLER CLASS
1. Add in Post Mapping for /pizza/orders for when the form is being submitted
2. Display binding result errors
3. Get Pizza object from "pizza" session previously
4. Save Order using previous session Pizza object and validated Delivery object
5. Add order to model attribute to be displayed in Order.html

### ORDER MODEL
1. To combine both Pizza and Delivery details + random ID + calculated total cost
2. Set get pizza cost to be displayed in HTML (if isRush = true, -2, else retain total cost)
3. Build from Java Object to JSON to be stored in Redis

### SERVICE CLASS
1. Create a method to generate UUID of 8 numbers
2. Create a method to calculate the cost of the pizzas
3. Create save method by autowiring from Repo class > generate UUID > calculate total cost

### REPOSITORY CLASS
1. Create a save method to store ID(key) and values of Order in JSON format