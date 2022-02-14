import java.util.Scanner;

public class SciCalculator
{
    public static void main(String[] args)
    {
        double firstOperand = 1.0;
        double secondOperand = 1.0;
        double numResult = 0.0;
        int menuSelection;
        double resultSum = 0.0; // Tracks sum of each result
        int numCalcs = 0; // Tracks number of calculations
        Scanner scnr = new Scanner(System.in);
        int runOpSelection; // Enables prompt looping without reprinting menu

        while (true)
        {
            runOpSelection = 1;
            System.out.println("Current Result: " + numResult + "\n");
            System.out.println("Calculator Menu");
            System.out.println("---------------");
            System.out.println("0. Exit Program");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exponentiation");
            System.out.println("6. Logarithm");
            System.out.println("7. Display Average\n");

            while (runOpSelection == 1)
            {
                runOpSelection = 0; // ensures that the loop reverts back to top unless otherwise stated

                System.out.print("Enter Menu Selection: ");
                menuSelection = scnr.nextInt();

                if (menuSelection < 0 || menuSelection > 7)
                {           // accounts for any values entered that are not a menu opton
                    System.out.println("\nError: Invalid selection!\n");
                    runOpSelection = 1; // prompts the loop back so that the user has the option of selecting operands
                    continue;
                }

                if (menuSelection == 0)
                {
                    /* Catch and exit */
                    System.out.print("\nThanks for using this calculator. Goodbye!");
                    System.exit(0);
                }

                if (menuSelection != 7) // Only take operands if selection is not 7
                {
                    System.out.print("Enter first operand: ");
                    if (scnr.hasNext("RESULT"))
                    {
                        firstOperand = numResult;
                        scnr.next(); // Dump scanner input
                    } else
                    {
                        firstOperand = scnr.nextDouble();
                    }

                    System.out.print("Enter second operand: ");
                    if (scnr.hasNext("RESULT"))
                    {
                        secondOperand = numResult;
                        scnr.next();
                    } else
                    {
                        secondOperand = scnr.nextDouble();
                    }
                    System.out.print("\n");
                }

                switch (menuSelection)
                {
                    case 1:
                        numResult = firstOperand + secondOperand;
                        break;
                    case 2:
                        numResult = firstOperand - secondOperand;
                        break;
                    case 3:
                        numResult = firstOperand * secondOperand;
                        break;
                    case 4:
                        numResult = firstOperand / secondOperand;
                        break;
                    case 5:
                        numResult = Math.pow(firstOperand, secondOperand);
                        break;
                    case 6:
                        numResult = Math.log(secondOperand) / Math.log(firstOperand); // Alt. formula for log with base
                        break;
                    case 7:
                        if (numCalcs == 0)
                        {
                            System.out.println("\nError: No calculations yet to average!\n");
                        } else
                        {
                            System.out.println("\nSum of calculations: " + resultSum);
                            System.out.println("Number of calculations: " + numCalcs);
                            System.out.println("Average of calculations: " + (Math.round(((resultSum / numCalcs) * 100d)) / 100d) + "\n");
                            // rounding inspired by https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                        }
                        runOpSelection = 1;
                }

                if (menuSelection != 7)
                {
                    numCalcs++;
                    resultSum += numResult;
                }
            }
        }
    }
}
