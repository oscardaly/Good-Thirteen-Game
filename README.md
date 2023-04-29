# Good Thirteen CLI Card Game

This is a Java command-line interface (CLI) implementation of the popular card game "Good Thirteen". It allows you to play against the computer, and supports multiple rounds of play.

## How to Play

To play the game, you will need to have Java 8 or higher installed on your computer. Once you have Java installed, follow these steps:

1. Clone the repository to your local machine using `git clone https://github.com/oscardaly/Good-Thirteen-Game.git`.
2. Navigate to the project root directory in your terminal.
3. Run the command `mvn clean package` to compile and package the code.
4. Run the game by typing `java -jar target/good-thirteen-cli-1.0-SNAPSHOT.jar` in your terminal.

Once the game is running, you will be prompted to choose between user mode, where the user chooses the moves, and demonstration mode, where the computer decides the moves. The game will then begin, with the computer shuffling the 54 cards and then dealing 10 cards face up.

On each turn, you will be prompted to select one or 2 cards from your hand that add up to 13 and if you can't think of a move, by clicking the 'H' key, you are able to view a hint of an available move. The game will continue until either the player or the computer has run out of cards, or until there are no moves available.

## Testing

The project uses the JUnit 5 testing framework for unit testing. To run the tests locally, run the command `mvn test`. Automated testing is set up using Github Actions, which runs the tests on each push to the repository.

## Github Actions

This project is set up with Github Actions to automate the build and testing process. Whenever a commit is pushed to the repository, the actions will trigger a build and run the tests. If the tests pass, the code will be deployed to the `main` branch.