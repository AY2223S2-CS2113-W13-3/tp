# Developer Guide

+ [Acknowledgement](#acknowledgement)
+ [Design](#design)
  + [Architecture Design Diagram](#architecture-design-diagram)
  + [UML Sequence Diagram](#uml-sequence-diagram)
+ [Implementation](#implementation)
  + [Add feature](#add-feature)
  + [Remove feature](#remove-feature)
  + [Find feature](#find-feature)
  + [Update feature](#update-feature)
  + [List feature](#list-feature)
  + [Clear feature](#clear-feature)
  + [Help feature](#help-feature)
  + [Exit feature](#exit-feature)
  + [Storage](#storage)
+ [Appendix: Requirements](#appendix--requirements)
  + [Product Scope](#product-scope)
  + [User Stories](#user-stories)

## Acknowledgement

The documentation and organisation of our project follows the recommended format in [SE-Education](http://se-education.org/addressbook-level3/DeveloperGuide.html).
Part of the code is inspired by the [addressbook-level3](https://github.com/se-edu/addressbook-level3)

## Design
### Architecture Design Diagram

![Diagram](images/Architecture_Diagram.png)

Main components of the architecture

`FoodSupplyTracker` is the entry point of the application. It is responsible for, 
+ At app launch: Initializes the components in the correct sequence, and connects them up with each other.
+ At shut down: Shuts down the components

The rest of the App consists of five components:
+ Ui: The UI of the App. This part displays the messages to the user.
+ Parse: Parse user input.
+ Command: The command executor.
+ CommandResult: The command result display, displaying the command result.
+ Storage: Reads data from, and writes data to, the hard disk.
<<<<<<< HEAD
+ Parser: The command parser. Identify the various command type.
=======
+ CommandResult: Prints response to users.

### UML Sequence Diagram
![SequenceDiagram](images/SequenceDiagram-Food_Supply_Tracker.png)
---

## Implementation

### Add feature

The add feature is implemented using a `AddCommand` class extending from `Command` Class. 
`AddCommand` contains an `execute` methods, which utilises the internal `spiltDetails` methods to extract the food details
and to construct new `food` object .
It is also facilitated by `Foodlist` Class, using `addFood` method of `FoodList` Class.

The add feature implementation is as follows:
1. New AddCommand object is created by passing in a String containing food details
2. `splitDetails` is called to split the string using different flags `-n`,`-e`,`-c`,`-q`,`-u`;
3. After obtaining an array of String, it will check the correctness of each parameter. It will specially check the date 
and the quantity. If the `date` is not a valid date (e.g. 02/15/2024)or it is before the current date
(meaning expired food), it will return a `CommandResult` object, and `FoodSupplyTracker` will continue print the command result.
4. If there is no error, it will create a new `Food` object, based on the parameter number contained in the array.
5. `addFood` is called to add the new food into the food list
6. Return a `CommandResult` to show the successful message to the user


**Class Diagram**

![ClassDiagram](images/AddCommandClassDiagram.png)


**Sequence Diagram**

![ClassDiagram](images/AddCommandSequenceDiagram.png)

### Remove feature
The remove command is implemented using a `RemoveCommand` class which
utilizes `removeFood` method of the FoodList.

The `removeFood` method implementation is as follows:
1. New `RemoveCommand` object is created by passing in a String containing arguments from `Parser`.
2. The `index` of the item to be deleted will be taken as its arguments.
3. The method `execute` of `Command` class will be called from `FoodSupplyTracker` with the `index`.
4. This method will determine if there exist a food item with that `index` in the `FoodList`.
5. If it exists, the food item will be deleted and feedback to the user that it has been successfully removed.
6. Otherwise, the feedback will be an error message
7. This method will return an object called `CommandResult` and pass `feedbackToUser` as its argument.
8. `FoodSupplyTracker` will subsequently call `printResult` method from `CommandResult`.

**Class Diagram**

![ClassDiagram](images/RemoveCommandClassDiagram.png)

**Object Diagram**

![ClassDiagram](images/RemoveCommandSequenceDiagram.png)

### Find feature
The `find` command is implemented using a `FindCommand` class and its `execute` method.

The `FindCommand` method implementation is as follows:
1. Inside the constructor, split provided arguments into the term and flags
2. Start `execute` method by initializing `FoodList` result variable
3. If no term or flag is provided, throw the corresponding error to the user
4. Start a loop iterating over each foodItem 
5. Do word processing on a term and check if the foodItem name includes it
6. Enter a loop with switch statement for each component of the `flags` argument 
7. If some flag is not satisfied, continue the loop without adding foodItem to the result
8. If the flags loop is over, the flags conditions were satisfied, so add the foodItem to the result

**Class Diagram** <br>
![ClassDiagram](images/FindCommandClassDiagram.png)


**Sequence Diagram** <br>
![SequenceDiagram](images/FindCommandSequenceDiagram.png)

### Update feature
The `update` command is implemented using an `UpdateCommand` class and its `execute` method.

The `UpdateCommand` implementation is as follows:
1. Inside the constructor, split provided arguments into the index and flags
2. Start `execute` method by parsing index to integer and catching the exception if
   it's in the invalid format
3. Get the current food item by calling `foodList.getFood(index)`
4. Enter the loop iterating through each flag
5. Inside the loop, split flag into its name and value
6. Call `updateFoodAttribute` with the `currentFood`, `flagName`, `flagValue`, 
   and all `flags` provided (important for some interdependent attributes like unit and quantity)
7. The `updateFoodAttribute` enters switch statement for the `flagName`,
   calling corresponding attribute setters for the `flagValue` after its validation. If no
   `flagName` is matched, an `InvalidFlagException(flagName)` is thrown

**Class Diagram**<br>
![ClassDiagram](images/UpdateCommandClassDiagram.png)


**Sequence Diagram**<br>
![SequenceDiagram](images/UpdateCommandSequenceDiagram.png)


### List feature
The list command is implemented using a `ListCommand` class which utilizes `COMMAND_WORD`
attributes of all other commands within the `commands` package.

The list command is implemented as follows:
1. New `ListCommand` object created will call the method `execute` of `Command` class.
2. The method `execute` decides what to append to the string `printToUser` according to `foodList`. 
3. `foodList` is an object of type `FoodList` that is passed as argument to `execute`, it consists of the list of food 
that has been added by user.
4. `foodList` contains of `Food` that has undergoes the `toString` method.
5. `execute` will check if `numberOfFood` is greater than 0, in which `numberOfFood` is obtained from the `FoodList` 
function `getNumberOfFood`.
6. If `numberOfFood` is greater than 0,  the foodList in String will be appended to `printToUser`. If it is not, then 
the empty `foodList` will not be appended to `printToUser`.
7. After appending the `foodList`, the method `execute` will return an object called `CommandResult` and pass          
`printToUser` as its argument.
8. `FoodSupplyTracker` will then call `printResult` method from `CommandResult` which will print the food list for the user.

**Class Diagram** <br>
![ClassDiagram](images/ListCommandClassDiagram.png)

**Object Diagram** <br>
![ClassDiagram](images/ListCommandSequenceDiagram.png)

### Clear feature
The clear command is implemented using a `ClearCommand` class which utilizes `COMMAND_WORD` attributes to all other 
commands within the `commands` package.

The clear command is implemented as follows:
1. New `ClearCommand` object created will call the method `execute` of `Command` class.
2. The method `execute` decides what to append to the string `printToUser`.
3. The method `execute` calls the method `clearFoodList` in FoodList to remove all the objects (Food) in FoodList.
4. After the method `clearFoodList` has finished executing, `execute` will return an acknowledgement message to user.

**Class Diagram** <br>
![ClassDiagram](images/ClearCommandClassDiagram.png)

**Sequence Diagram** <br>
![SequenceDiagram](images/ClearCommandSequenceDiagram.png)

### Help feature
The help command is implemented using a `HelpCommand` class which utilizes `COMMAND_WORD` 
attributes of all other commands within the `commands` package. 

The help command is implemented as follows:
1. When `FoodSupplyTracker` reads the user input, it calls the method `parse` from the `Parser` class.
2. The `parse` method process which command word is being queried and calls the constructor class based 
on the command word.
3. In the case where `help` is the command word, a new `HelpCommand` object is created with the rest of the user input 
being the arguments for the constructor.
4. The constructor `HelpCommand` will split the arguments based on the `--` regex and store them in an array of
strings called `filters`.
5. The method `execute` of `Command` class will then be called all the way from `FoodSupplyTracker` which will be overriden by the 
same method in `HelpCommand`.
6. This method calls the private method `appendMessage` which decides what to append to the string `printToUser` as 
specified by `filter`. 
7. After looping through all the `filter`, this method will return an object called `CommandResult` and pass
`printToUser` as its argument.
8. `FoodSupplyTracker` will then call `printResult` method from `CommandResult` which will print the necessary message.

**Class Diagram** <br>
![ClassDiagram](images/HelpCommandClassDiagram.png)


**Sequence Diagram** <br>
![ClassDiagram](images/HelpCommandSequenceDiagram.png)

### Exit feature
The exit command is implemented using a `ExitCommand` class which utilizes `COMMAND_WORD` attributes of all other 
commands within the `commands` package.

The exit command is implemented as follows
1. When `FoodSupplyTracker` reads the user input, it calls the method `parse` from the `Parser` class.
2. The `parse` method process which command word is being queried and calls the constructor class based
   on the command word.
3. In the case where `exit` is the command word, a new `ExitCommand` object is created with the rest of the user input
   being the arguments for the constructor.
4. The method `execute` of `Command` class will then be called all the way from `FoodSupplyTracker` which will be overridden by the
   same method in `ExitCommand`.
5. This method append the exiting message to the string `printToUser`, and return an object called `CommandResult` and 
pass `printToUser` as its argument
6. `FoodSupplyTracker` will then call `printResult` method from `CommandResult` which will print the necessary message.
7. `FoodSupplyTracker` will further call `isExit` method from `ExitCommand` which will then change `isExit` from `false` to `true`,
which will then exits the program.

**Class Diagram** <br>
![ClassDiagram](images/ExitCommandClassDiagram.png)

**Sequence Diagram** <br>
![ClassDiagram](images/ExitCommandSequenceDiagram.png)

### Storage
This class deals with the reading and writing of the foodTrackerList textfile. This serves as a memory that remembers 
the latest food list inputted by the user. Upon starting the program, the text file will load the food in the foodlist,
and right before the program close, the text file will be written with the updated list.

**Class Diagram** <br>
![ClassDiagram](images/StorageClassDiagram.png)

**Sequence Diagram** <br>
![ClassDiagram](images/StorageSequenceDiagram.png)
---
## Appendix: Requirements
# Product Scope
**Project Direction**
- Food Supplies Tracker: Main function is to track expiry dates 
and storage area of foods as to minimise food shortage.

- **Target user profile:**
- Kitchen worker, Homemaker
- has a need to manage food
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition:**
1) Manage food supply faster than a typical mouse / GUI driven app
2) Check the expiry date of a particular food quickly
3) Check the foods in the most recent expiry dates quickly
4) Get suggestions and alert to consume foods that are going to expire soon

Currently, the following functionality was implemented:

`add --n PRODUCT_NAME --e EXPIRY_DATE `

`add --n PRODUCT_NAME --e EXPIRY_DATE --c CATEGORY `

`add --n PRODUCT_NAME --e EXPIRY_DATE --p QUANTITY `

`add --n PRODUCT_NAME --e EXPIRY_DATE --c CATEGORY --p QUANTITY`

`add --n PRODUCT_NAME --e EXPIRY_DATE --p QUANTITY --u UNIT`

`add --n PRODUCT_NAME --e EXPIRY_DATE --c CATEGORY --p QUANTITY --u UNIT`

`list`

`clear`

`find {PRODUCT_NAME}`

`find {PRODUCT_NAME} --fresh`

`find {PRODUCT_NAME} --expired`

`update INDEX {flags}`
+ Allowed flags to update the food item attributes:
  * name: `--n {string}`
  * expiry date: `--e {dd/mm/yyyy}`
  * unit: `--u {UNIT}`
  * quantity: `--q {number}`
  * category: `--c {CATEGORY}`

`help {--COMMAND_WORD}`

# User stories

| Priority | As a...             | I want to ...                                                         | So that I can...                                                | Current Feature Available |
|----------|---------------------|-----------------------------------------------------------------------|-----------------------------------------------------------------|---------------------------|
| `* * *`  | Forgetful cook      | be reminded of what is left in my fridge                              | use the food wisely and avoid repetitive purchasing.            | `list`                    |
| `*`      | Forgetful cook      | get all meals for the next `x` days with their ingredients            | plan my grocery trips accordingly.                              | nil                       |
| `* *`    | Lazy cook           | get recommended recipes based on what I have left in the <br/>fridge  | can spend less time considering what to cook today.             | nil                       |
| `* * *`  | Lazy cook           | have an efficient method of updating what leftover ingredients I have | spend less time using the software.                             | `add --n`                 |
| `* * *`  | Lazy cook           | check the lists of food fast                                          | waste less effort digging through the fridge / kitchen.         | `list`                    |
| `* * *`  | Busy cook           | get reminded on the products which will expire soon                   | prepare a meal based on it.                                     |                           |
| `* * *`  | Busy cook           | get reminded on the products which will expire soon                   | plan my grocery purchases accordingly.                          |                           |
| `* * *`  | Busy cook           | check the availability of certain food fast                           | waste little time digging through the fridge / kitchen.         | `find`                    |
| `* * *`  | Prudent cook        | check the expiry dates of food in fridge / kitchen                    | don't waste food due to food expiring and spoiling.             | `find`                    |
| `* *`    | Disorganised person | organise food by categories at least in a program (fruits,veggies...) | don't spend time looking for the food scattered in the kitchen. | nil                       |
| `* *`    | Prudent cook        | track the amount of different products I have                         | know how many portions I can cook.                              | nil                       |
| `*`      | Creative cook       | replace some products with others                                     | add potential replacement for the product.                      | nil                       |     
| `*`      | Inexperienced cook  | know how much to cook based on the number of people eating            | prepare the correct amount of food.                             | nil                       |  
| `* * *`  | Cook                | add product (name, expiry date, category etc)                         | keep my program updated.                                        | `add --n --e --c --q --u` |
| `* * *`  | Cook                | remove product                                                        | keep my program updated.                                        | `remove`                  |

# Non-Functional Requirements
1) Should work on any mainstream OS as long as it has Java 11 or above installed.
2) Should be able to hold up to 9999 food products without a noticeable sluggishness in performance for typical usage.
3) A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.