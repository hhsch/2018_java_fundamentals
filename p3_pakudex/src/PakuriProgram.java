import java.util.Scanner;

public class PakuriProgram
{
    private static int getMenuSelection(Scanner userInput)
    {
        while (true)
        {
            System.out.print("\n" +
                    "Pakudex Main Menu\n" +
                    "-----------------\n" +
                    "1. List Pakuri\n" +
                    "2. Show Pakuri\n" +
                    "3. Add Pakuri\n" +
                    "4. Evolve Pakuri\n" +
                    "5. Sort Pakuri\n" +
                    "6. Exit\n" +
                    "\n" +
                    "What would you like to do? ");

            if (userInput.hasNextInt())
            {
                int menuSelection = userInput.nextInt();
                if (0 <= menuSelection && menuSelection <= 7)
                    return menuSelection;
            }
            else
            {
                /* Flush scanner */
                userInput.next();
            }

            System.out.print("Unrecognized menu selection!\n");
        }
    }

    /* Main starts here */
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        Pakudex pakudex;

        /* Make introductions */
        System.out.print("Welcome to Pakudex: Tracker Extraordinaire!\n");

        /* Get valid pakudex size from user */
        while (true)
        {
            System.out.print("Enter max capacity of the Pakudex: ");
            if (userInput.hasNextInt())
            {
                /* Initialize the pakudex */
                int dexSize = userInput.nextInt();
                pakudex = new Pakudex(dexSize);
                System.out.print("The Pakudex can hold " + dexSize + " species of Pakuri.\n");
                break;
            }

            /* Signal an error and flush the scanner */
            System.out.print("Please enter a valid size.\n");
            userInput.next();
        }

        /* Loop until program quits */
        for (int userSelection; true;)
        {
            userSelection = getMenuSelection(userInput);

            switch (userSelection)
            {
                case 1: /* List pakuri */
                    String[] pakuriSpecies = pakudex.getSpeciesArray();
                    if (pakuriSpecies == null)
                    {
                        System.out.print("No Pakuri in Pakudex yet!\n");
                    }
                    else
                    {
                        System.out.print("Pakuri In Pakudex:\n");
                        for (int pakuriNo = 0; pakuriNo < pakuriSpecies.length; pakuriNo++)
                        {
                            System.out.print((pakuriNo + 1) + ". " + pakuriSpecies[pakuriNo] + "\n");
                        }
                    }
                    break;

                case 2: /* Show pakuri species stats */
                    System.out.print("Enter the name of the species to display: ");
                    String statSpecies = userInput.next();
                    int[] stats = pakudex.getStats(statSpecies);
                    if (stats == null)
                    {
                        System.out.print("Error: No such Pakuri!\n");
                    }
                    else
                    {
                        System.out.print("\n" +
                                "Species: " + statSpecies + "\n" +
                                "Attack: " + stats[0] + "\n" +
                                "Defense: " + stats[1] + "\n" +
                                "Speed: " + stats[2] + "\n");
                    }
                    break;

                case 3: /* Add pakuri */
                    if (pakudex.getSize() == pakudex.getCapacity())
                    {
                        System.out.print("Error: Pakudex is full!\n");
                    }
                    else
                    {
                        System.out.print("Enter the name of the species to add: ");
                        String addSpecies = userInput.next();
                        boolean addSuccess = pakudex.addPakuri(addSpecies);
                        if (addSuccess)
                        {
                            System.out.print("Pakuri species " + addSpecies + " successfully added!\n");
                        }
                        else
                        {
                            System.out.print("Error: Pakudex already contains this species!\n");
                        }
                    }
                    break;

                case 4: /* Evolve pakuri */
                    System.out.print("Enter the name of the species to evolve: ");
                    String evolveSpecies = userInput.next();
                    boolean evolveSuccess = pakudex.evolveSpecies(evolveSpecies);
                    if (evolveSuccess)
                    {
                        System.out.print(evolveSpecies + " has evolved!\n");
                    }
                    else
                    {
                        System.out.print("Error: No such Pakuri!\n");
                    }
                    break;

                case 5: /* Sort pakuri */
                    pakudex.sortPakuri();
                    System.out.print("Pakuri have been sorted!\n");
                    break;

                case 6: /* Exit */
                    System.out.print("Thanks for using Pakudex! Bye!\n");
                    System.exit(0);
            }
        }
    }
}
