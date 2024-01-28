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
            if (!reader.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                reader.next();
                continue; // Restart the loop for new input
            }
            rows = reader.nextInt();
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
            if (!reader.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer value."); // Error message for non-integer input
                reader.next();
                continue; // Restart the loop for new input
            }
            columns = reader.nextInt();
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
            difficultyReply = difficultyReply.toLowerCase();
            //checks if input is a word thats not easy, medium or hard
            if (difficultyReply.matches("[a-zA-Z]+") && !difficultyReply.equals("easy") && !difficultyReply.equals("medium") && !difficultyReply.equals("hard")) {
                System.out.print("Error: please only enter easy, medium or hard.");
                reader.next();
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

        minesweeperBoard.placeMines(difficulty);
        minesweeperBoard.calculateNeighbouringMines();
        while (!minesweeperBoard.isGameOver()) {
            minesweeperBoard.displayBoard();
            while (true) {
                // Prompt for rows
                System.out.print("What row would you like to choose? (between 1 and " + rows + ") ");
                if (!reader.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer value."); // Error message for non-integer input
                    reader.next();
                    continue; // Restart the loop for new input
                }
                revealRow = reader.nextInt();
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
                if (!reader.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer value."); // Error message for non-integer input
                    reader.next();
                    continue; // Restart the loop for new input
                }
                revealCol = reader.nextInt();
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

            while (true) {
                System.out.print("Would you like to flag a cell? (yes or no) ");
                flagReply = reader.next();
                flagReply = flagReply.toLowerCase();
                if (flagReply.matches("[a-zA-Z]+") && !flagReply.equals("yes") && !flagReply.equals("no")) {
                    System.out.println("Error: Please only enter yes or no");
                    reader.next();
                    continue;
                } else if (flagReply.equals("yes")) {
                    while (true) {
                        // Prompt for rows
                        System.out.print("What row would you like to choose? (between 1 and " + rows + ")");
                        if (!reader.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid integer value."); // Error message for non-integer input
                            reader.next();
                            continue; // Restart the loop for new input
                        }
                        flagRow = reader.nextInt();
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
                        System.out.print("What column would you like to choose? (between 1 and " + columns + ")");
                        if (!reader.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid integer value."); // Error message for non-integer input
                            reader.next();
                            continue; // Restart the loop for new input
                        }
                        flagCol = reader.nextInt();
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
                } else if (flagReply.equals("no")) {
                    break;
                } else {
                    System.out.println("Invalid input: Please enter a valid word (yes or no)");
                    continue;
                } break;
            }
            if (flagReply.equals("yes")) {
                minesweeperBoard.flagCell(flagRow, flagCol);
            }

            while (minesweeperBoard.cellFlagChecker()) {
                System.out.print("Would you like to unflag a cell? (yes or no) ");
                unflagReply = reader.next();
                unflagReply = unflagReply.toLowerCase();
                if (unflagReply.matches("[a-zA-Z]+") && !unflagReply.equals("yes") && !unflagReply.equals("no")) {
                    System.out.println("Error: Please only enter yes or no");
                    reader.next();
                    continue;
                } else if (unflagReply.equals("yes")) {
                    while (true) {
                        // Prompt for rows
                        System.out.print("What row would you like to choose? (between 1 and " + rows + ")");
                        if (!reader.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid integer value."); // Error message for non-integer input
                            reader.next();
                            continue; // Restart the loop for new input
                        }
                        unflagRow = reader.nextInt();
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
                        System.out.print("What column would you like to choose? (between 1 and " + columns + ")");
                        if (!reader.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid integer value."); // Error message for non-integer input
                            reader.next();
                            continue; // Restart the loop for new input
                        }
                        unflagCol = reader.nextInt();
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
                } else if (unflagReply.equals("no")) {
                    break;
                } else {
                    System.out.println("Invalid input: Please enter a valid word (yes or no)");
                    continue;
                } break;
            }
            if (unflagReply.equals("yes")) {
                minesweeperBoard.unflagCell(unflagRow, unflagCol);
            }


            minesweeperBoard.revealCell(revealRow, revealCol);
            if (minesweeperBoard.checkWin()) { //checks if player has won
                minesweeperBoard.setGameOver(true); //sets gameOver to be true to end loop
                System.out.println("You WIN!!!"); //prints winning message to screen
            }
        }
        minesweeperBoard.displayBoard();
    }
}

/*

If cell is already flagged or revealed, it should show error and reask

 */