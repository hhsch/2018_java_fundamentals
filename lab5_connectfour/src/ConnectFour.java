import java.util.Scanner;

public class ConnectFour
{
    /* Print the board in its current state */
    public static void printBoard(char[][] array)
    {
        /* Iterate through each row of the array (starting at the 'end' and working back to 0) */
        for (int i = array.length - 1; i >= 0; i--)
        {
            /* Iterate along the row */
            for (int j = 0; j < array[i].length; j++)
            {
                System.out.print(array[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /* Start the board in a known state of hyphens */
    public static void initializeBoard(char[][] array)
    {
        /* Iterate through each square and fill with a hyphen */
        for (int i = array.length - 1; i >= 0; i--)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                array[i][j] = '-';
            }
        }
    }

    /* Drop a chip into the given column and return the row it landed in */
    public static int insertChip(char[][] array, int col, char chipType)
    {
        for (int i = 0; i < array.length; i++)
        {
            if(array[i][col] == '-') // Find the spot closest to the 'bottom' that is not yet full
            {
                array[i][col] = chipType;
                return i;
            }
        }

        return -1; // Returns to make IntelliJ happy, theoretically in case there was no space
    }

    /* See if there is a four-in-a-row in either a row or a column of one chip-type, where the recent chip was placed */
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        int winCounter = 0; // Counter to keep track of how many chips in a row

        /* Checks column for a win */
        for (int i = row; i >= 0; i--)
        {
            if (array[i][col] == chipType)
            {
                winCounter += 1;
                if (winCounter == 4)
                    return true;
            }
            else
            {
                winCounter = 0;
            }
        }

        /* Reset winCounter just in case, and then check row */
        winCounter = 0;
        for (int j = 0; j < array[row].length; j++)
        {
            if (array[row][j] == chipType)
            {
                winCounter += 1;
                if (winCounter == 4)
                    return true;
            }
            else
            {
                winCounter = 0;
            }
        }

        /* If not returned true, return false (no winner) */
        return false;
    }

    /* Entrance to game */
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        int height; // Height of the board
        int length; // Length of the board
        int col;    // Column of the most recent move
        int row;    // Row of the most recent move

        /* Ask user for the board dimensions */
        System.out.print("What would you like the height of the board to be? ");
        height = userInput.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        length = userInput.nextInt();

        /* Set up/display game board */
        char[][] board = new char[height][length];
        initializeBoard(board);
        printBoard(board);

        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println();

        /* Run as many game rounds as there are spaces in the board */
        for (int round = 0; round < (length * height); round++)
        {
            /* Even rounds are Player 1's turns */
            if (round % 2 == 0)
            {
                System.out.print("Player 1: Which column would you like to choose? ");
                col = userInput.nextInt();
                row = insertChip(board, col, 'x');
                printBoard(board);
                if (checkIfWinner(board, col, row, 'x'))
                {
                    System.out.print("Player 1 won the game!");
                    System.exit(0);
                }
            }
            /* Odd rounds are Player 2's turns */
            else
            {
                System.out.print("Player 2: Which column would you like to choose? ");
                col = userInput.nextInt();
                row = insertChip(board, col, 'o');
                printBoard(board);
                if (checkIfWinner(board, col, row, 'o'))
                {
                    System.out.print("Player 2 won the game!");
                    System.exit(0);
                }
            }
        }

        /* Only displays if there is no win after all rounds (the board is full) */
        System.out.print("Draw. Nobody wins.");
    }
}
