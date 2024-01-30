package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rows;
        int columns;
        int difficulty = 0;
        String difficultyReply;
        int revealRow = 0;
        int revealCol = 0;
        int flagRow = 0;
        int flagCol = 0;
        String flagReply;
        String unflagReply = "";
        int unflagRow = 0;
        int unflagCol = 0;
        Scanner reader = new Scanner(System.in);
        while (true) {
            // Prompt for rows
            System.out.print("How many rows would you like? (between 5-25 inclusive) ");
            String input = reader.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty.");
                continue;
            }
            if (!input.matches("\\d+")) { //check if input is made of only digits
                System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                continue; // Restart the loop for new input
            }

            rows = Integer.parseInt(input);
            if (rows < 5 || rows > 25) {
                if (rows < 5) {
                    System.out.println("Error: Number of rows must be at least 5."); // Error message for rows less than 5
                } else {
                    System.out.println("Error: Number of rows cannot exceed 25."); // Error message for rows greater than 25
                }
                continue; // Restart the loop for new input
            }
            break;
        }
        while (true){
            // Prompt for columns
            System.out.print("How many columns would you like? (between 5-25 inclusive) ");
            String input = reader.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty.");
                continue;
            }
            if (!input.matches("\\d+")) { //check if input is made of only digits
                System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                continue; // Restart the loop for new input
            }

            columns = Integer.parseInt(input); //converts string value into integer
            if (columns < 5 || columns > 25) {
                if (columns < 5) {
                    System.out.println("Error: Number of columns must be at least 5."); // Error message for columns less than 5
                } else {
                    System.out.println("Error: Number of columns cannot exceed 25."); // Error message for columns greater than 25
                }
                continue; // Restart the loop for new input
            }

            // If both rows and columns are valid, exit the loop
            break;
        }

        Board minesweeperBoard = new Board(rows,columns); //create the board with given inputs
        int numberOfCells = rows*columns; //calculates total number of cells for difficulty

        while (true) {
            System.out.print("What difficulty would you like? (easy, medium, hard) ");
            difficultyReply = reader.next();
            difficultyReply = difficultyReply.toLowerCase(); //lowercase all input
            //checks if input is a word thats not easy, medium or hard
            if (difficultyReply.matches("[a-zA-Z]+") && !difficultyReply.equals("easy") && !difficultyReply.equals("medium") && !difficultyReply.equals("hard")) {
                System.out.println("Error: Please only enter easy, medium or hard. ");
                continue;
                //sets difficulty for given input
            } else if (difficultyReply.equals("easy")) {
                difficulty = (int) Math.ceil((double) numberOfCells/4);
                break;
            } else if (difficultyReply.equals("medium")) {
                difficulty = (int) Math.ceil((double) numberOfCells/3);
                break;
            } else if (difficultyReply.equals("hard")) {
                difficulty = (int) Math.ceil((double) numberOfCells/2);
                break;
            } else {
                System.out.println("Invalid input: Please enter a valid word");
                continue;
            }
        }
        reader.nextLine(); //clears Scanner input, as error was occuring when asking for following input
        //difficulty = 0;
        minesweeperBoard.placeMines(difficulty); //mines are randomly placed based on chosen difficulty
        minesweeperBoard.calculateNeighbouringMines(); //neighbouring mines are calculated for each cell
        while (!minesweeperBoard.isGameOver()) { //loop continues until the game is over
            minesweeperBoard.displayBoard(); //displays board
            while (true) {
                // Prompt for rows
                System.out.print("What row would you like to choose? (between 1 and " + rows + ") ");
                String input = reader.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Error: Input cannot be empty.");
                    continue;
                }
                if (!input.matches("\\d+")) { //check if input is made of only digits
                    System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                    continue; // Restart the loop for new input
                }

                revealRow = Integer.parseInt(input);
                if (revealRow < 1 || revealRow > rows) {
                    if (revealRow < 5) {
                        System.out.println("Error: Row must be at least 1."); // Error message for rows less than 5
                    } else {
                        System.out.println("Error: Row can't exceed " + rows + "."); // Error message for rows greater than 25
                    }
                    continue; // Restart the loop for new input
                }
                break;
            }
            while (true) {
                System.out.print("What column would you like to choose? (between 1 and " + columns + ") ");
                String input = reader.nextLine().trim();
                if (input.isEmpty()) { //checks is input is empty
                    System.out.println("Error: Input cannot be empty.");
                    continue;
                }
                if (!input.matches("\\d+")) { //check if input is made of only digits
                    System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                    continue; // Restart the loop for new input
                }

                revealCol = Integer.parseInt(input);
                if (revealCol < 1 || revealCol > columns) {
                    if (revealCol < 1) {
                        System.out.println("Error: Number of columns must be at least 1."); // Error message for columns less than 5
                    } else {
                        System.out.println("Error: Columns can't exceed " + columns + "."); // Error message for columns greater than 25
                    }
                    continue; // Restart the loop for new input
                }

                // If both rows and columns are valid, exit the loop
                break;
            }
            if (minesweeperBoard.cellRevealedStatus(revealRow, revealCol)) {  //if chosen cell has already been revealed, restarts loop
                System.out.println("Error: cell has already been revealed.");
                continue;
            }
            minesweeperBoard.revealCell(revealRow, revealCol); //Reveal the cell
            if (minesweeperBoard.checkWin()) {
                System.out.println("YOU WIN");
                minesweeperBoard.setGameOver(true);
                break;
            }
            if (!minesweeperBoard.isGameOver()) { //If the game isn't over, display the board
                minesweeperBoard.displayBoard();
            } else { //If they've chosen the mine breaks loop to end game
                break;
            }

            while (true) {
                System.out.print("Would you like to flag a cell? (yes or no) ");
                flagReply = reader.next();
                flagReply = flagReply.toLowerCase();
                if (flagReply.matches("[a-zA-Z]+") && !flagReply.equals("yes") && !flagReply.equals("no")) {
                    System.out.println("Error: Please only enter yes or no");
                    reader.next();
                    continue;
                } else if (flagReply.equals("yes")) {
                    reader.nextLine();
                    while (true) {
                        // Prompt for rows
                        System.out.print("What row would you like to choose? (between 1 and " + rows + ") ");
                        String input = reader.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Error: Input cannot be empty.");
                            continue;
                        }
                        if (!input.matches("\\d+")) { //check if input is made of only digits
                            System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                            continue; // Restart the loop for new input
                        }

                        flagRow = Integer.parseInt(input);
                        if (flagRow < 1 || flagRow > rows) {
                            if (flagRow < 5) {
                                System.out.println("Error: Row must be at least 1."); // Error message for rows less than 5
                            } else {
                                System.out.println("Error: Row can't exceed " + rows + "."); // Error message for rows greater than 25
                            }
                            continue; // Restart the loop for new input
                        }
                        break;
                    }
                    while (true) {
                        System.out.print("What column would you like to choose? (between 1 and " + columns + ") ");
                        String input = reader.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Error: Input cannot be empty.");
                            continue;
                        }
                        if (!input.matches("\\d+")) { //check if input is made of only digits
                            System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                            continue; // Restart the loop for new input
                        }

                        flagCol = Integer.parseInt(input);
                        if (flagCol < 1 || flagCol > columns) {
                            if (flagCol < 1) {
                                System.out.println("Error: Number of columns must be at least 1."); // Error message for columns less than 5
                            } else {
                                System.out.println("Error: Columns can't exceed " + columns + "."); // Error message for columns greater than 25
                            }
                            continue; // Restart the loop for new input
                        }

                        // If both rows and columns are valid, exit the loop
                        break;
                    }
                    if (minesweeperBoard.cellRevealedStatus(flagRow,flagCol)) { //Gives error if chosen cell to flag has been revealed
                        System.out.println("Error: Cell has already been revealed.");
                        continue;
                    } else if (minesweeperBoard.cellFlaggedStatus(flagRow, flagCol)) { //Gives error if chosen cell to flag has already been flagged
                        System.out.println("Error: Cell has already been flagged.");
                        continue;
                    }
                    minesweeperBoard.flagCell(flagRow,flagCol);
                } else if (flagReply.equals("no")) { //if user doesn't want to flag a cell, exits loop
                    reader.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input: Please enter a valid word (yes or no)"); //if user inputs a word buts its not yes/no, gives error
                    continue;
                } break;
            }

            while (minesweeperBoard.cellFlagChecker()) { //loop continues while at least 1 cell is flagged
                System.out.print("Would you like to unflag a cell? (yes or no) ");
                unflagReply = reader.next();
                unflagReply = unflagReply.toLowerCase();
                if (unflagReply.matches("[a-zA-Z]+") && !unflagReply.equals("yes") && !unflagReply.equals("no")) {
                    System.out.println("Error: Please only enter yes or no");
                    reader.next();
                    continue;
                } else if (unflagReply.equals("yes")) {
                    reader.nextLine();
                    while (true) {
                        // Prompt for rows
                        System.out.print("What row would you like to choose? (between 1 and " + rows + ") ");
                        String input = reader.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Error: Input cannot be empty.");
                            continue;
                        }
                        if (!input.matches("\\d+")) { //check if input is made of only digits
                            System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                            continue; // Restart the loop for new input
                        }

                        unflagRow = Integer.parseInt(input);
                        if (unflagRow < 1 || unflagRow > rows) {
                            if (unflagRow < 5) {
                                System.out.println("Error: Row must be at least 1."); // Error message for rows less than 5
                            } else {
                                System.out.println("Error: Row can't exceed " + rows + "."); // Error message for rows greater than 25
                            }
                            continue; // Restart the loop for new input
                        }
                        break;
                    }
                    while (true) {
                        System.out.print("What column would you like to choose? (between 1 and " + columns + ") ");
                        String input = reader.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Error: Input cannot be empty.");
                            continue;
                        }
                        if (!input.matches("\\d+")) { //check if input is made of only digits
                            System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                            continue; // Restart the loop for new input
                        }

                        unflagCol = Integer.parseInt(input);
                        if (unflagCol < 1 || unflagCol > columns) {
                            if (unflagCol < 1) {
                                System.out.println("Error: Number of columns must be at least 1."); // Error message for columns less than 5
                            } else {
                                System.out.println("Error: Columns can't exceed " + columns + "."); // Error message for columns greater than 25
                            }
                            continue; // Restart the loop for new input
                        }

                        // If both rows and columns are valid, exit the loop
                        break;
                    }
                    if (minesweeperBoard.cellRevealedStatus(unflagRow,unflagCol)) { //Throws error is user attempts to unflag a cell that has been revealed
                        System.out.println("Error: Cell has already been revealed.");
                        continue;
                    } else if (!minesweeperBoard.cellFlaggedStatus(unflagRow, unflagCol)) { //Throws error if user attempts to unflag a cell that hasn't been flagged
                        System.out.println("Error: Cell hasn't been flagged.");
                        continue;
                    }
                    minesweeperBoard.unflagCell(unflagRow, unflagCol); //unflags chosen cell
                } else if (unflagReply.equals("no")) { //leaves loop if input is no
                    reader.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input: Please enter a valid word (yes or no)");
                    continue;
                } break;
            }
        }
        minesweeperBoard.displayBoard();
    }
}

/*


 */