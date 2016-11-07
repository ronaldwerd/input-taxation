## Taxation Coding Exercise

### Overview
This is a coding exercise to demonstrate console UI order entry in the following format:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Output:
1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

### Improvisations
I made my own improvisations and business rules and additions which demonstrates a near fully working application
which can be properly expanded.

### Command Line interface parser
#### This is an application which actually runs and accepts commands to add, view and remove your order
#### has following features:
- A functional order entry parser which detects a specific line format
- Uses wordnet API and english library to ensure nouns are present and both singular and plural nouns are real words.
- Looks up a nouns lexical file information to determine if the item is a book item or food item.
- Uses Lambdas and Java8 to assist in creating order summaries.
- Uses Maven for acquiring library dependencies and building.
