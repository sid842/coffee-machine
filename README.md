# Coffee Machine

##  Requirements
* Design coffee machine system that is able to serve N beverages concurrently.
* Keep track of ingredients present in the coffee machine and reject requests for beverages in case ingredients 
are unavailable.
* Requests for beverages can be processed in any order

## Key Classes
1. **CoffeeMachineService** <br/>
This class is provided to the clients to access the coffee machine services. It encapsulates
the N outlets that serve the beverages concurrently. Also it maintains an instance of _IngredientsService_ class which
helps in keeping track of the various ingredients.
2. **IngredientsService** <br />
This class keeps track of all the ingredients present in the coffee machine. When preparing a beverage, this class is used
for deciding whether we have sufficient ingredients or not.
3. **FetchBeverageTask** <br />
This class encapsulates the task of actually serving a beverage request. 


