import java.util.Scanner;
/* Simple One-Run Calculator  */
public class Calculator
{
    public static void main(String[] args)
    {
        /* Initialize variables */
        Scanner scanner = new Scanner(System.in);
        double operand1;
        double operand2;
        int selection;
        double response = 0;

        /* Ask for numbers to operate on */
        System.out.print("Enter first operand: ");
        operand1 = scanner.nextDouble();
        System.out.print("Enter second operand: ");
        operand2 = scanner.nextDouble();

        /* Print calculator menu and make selection */
        System.out.print("\n" +
                "Calculator Menu \n" +
                "--------------- \n" +
                "1. Addition \n" +
                "2. Subtraction \n" +
                "3. Multiplication \n" +
                "4. Division \n" +
                "\n" +
                "Which operation do you want to perform? ");

        selection = scanner.nextInt();

        /* Decide what operation should be performed */
        switch(selection)
        {
            case 1: response = operand1 + operand2;
                break;
                /* Performs addition */
            case 2: response = operand1 - operand2;
                break;
                /* Performs subtraction */
            case 3: response = operand1 * operand2;
                break;
                /* Performs multiplication */
            case 4: response = operand1 / operand2;
                break;
                /* Performs division */
            default:
                System.out.println("\nError: Invalid selection! Terminating program.");
                System.exit(0);
                /* Invalid input and quit program immediately */
        }

        System.out.println("\nThe result of the operation is " + response + ". Goodbye!");
    }
}
