import java.util.Scanner;

// Card object to call in-game
class Card
{
    public int realValue; // Value 1-13 of the card

    public int effectiveValue() // Value that card counts for in Blackjack
    {
        switch (this.realValue)
        {
            case 11: return 10;
            case 12: return 10;
            case 13: return 10;
            default: return this.realValue;
        }
    }

    public String faceValue() // How to print the value of the card
    {
        switch (this.realValue)
        {
            case 1: return "ACE";
            case 11: return "JACK";
            case 12: return "QUEEN";
            case 13: return "KING";
            default: return String.valueOf(realValue);
        }
    }
}

public class Blackjack
{

    private static int playerTurn(P1Random numGen, int wins, int losses, int draws, int played, Scanner prompt)
    {
        int playerTotal = 0;
        int playerInput = 1;
        Card drawnCard = new Card();

        while (true)
        {
            switch (playerInput)
            {
                case 1: // Draw a card and decide whether to continue, blackjack, or bust
                    drawnCard.realValue = numGen.nextInt(13) + 1;
                    playerTotal += drawnCard.effectiveValue();

                    System.out.print("Your card is a " + drawnCard.faceValue() + "!\n" +
                            "Your hand is: " + playerTotal + "\n" +
                            "\n");

                    if (playerTotal > 21)
                    {
                        System.out.print("You exceeded 21! You lose.\n" +
                                "\n");
                        return 22;
                    } else if (playerTotal == 21)
                    {
                        System.out.print("BLACKJACK! You win!\n" +
                                "\n");
                        return 21;
                    } else
                    {
                        break;
                    }
                case 2: // Hold hand and keep current score
                    return playerTotal;
                case 3: // Print statistics
                    float percent = (float) (100 * wins / played);
                    String formatPercent = String.format("%.1f", percent);
                    System.out.print("Number of Player wins: " + wins + "\n" +
                            "Number of Dealer wins: " + losses + "\n" +
                            "Number of tie games: " + draws + "\n" +
                            "Total # of games played is: " + played + "\n" +
                            "Percentage of Player wins: " + formatPercent + "%\n" +
                            "\n");
                    break;
                case 4: // This 0 will tell the program to exit before deciding to continue to the dealer's turn
                    return 0;
            }

            while (true)
            {
                // Default menu selection
                System.out.print("1.  Get another card\n" +
                        "2.  Hold hand\n" +
                        "3.  Print statistics\n" +
                        "4.  Exit\n" +
                        "\n" +
                        "Choose an option: ");

                playerInput = prompt.nextInt();

                if (playerInput == 1 || playerInput == 2 || playerInput == 3 || playerInput == 4)
                {
                    break;
                }
                else
                {
                     System.out.print("\n" +
                         "Invalid input!\n" +
                         "Please enter an integer value between 1 and 4.\n" +
                         "\n");
                }
            }

            System.out.print("\n");
        }
    }

    private static int dealerTurn(P1Random numGen)
    {
        return numGen.nextInt(11) + 16; // Generate a random integer 16 - 26
    }

    public static void main(String[] args)
    {
        // Initialize global variables that persist between games
        int gameCount = 0; // Game counter
        int winCount = 0;
        int lossCount = 0;
        int drawCount = 0;
        P1Random numGen = new P1Random();
        Scanner prompt = new Scanner(System.in);

        // Game loop start here
        while (true)
        {
            gameCount++;
            System.out.print("START GAME #" + gameCount + "\n\n");

            // This is the interactive player's turn
            int playerScore;
            playerScore = playerTurn(numGen, winCount, lossCount, drawCount, gameCount-1, prompt);

            switch (playerScore) // Decide whether to continue the game
            {
                case 0: //Quit the program
                    return;
                case 21: // Blackjack, restart the game and count the win
                    winCount++;
                    continue; // Restart game loop
                case 22: // Bust, restart the game and count the loss
                    lossCount++;
                    continue;
            }

            // Get dealer's score
            int dealerScore;
            dealerScore = dealerTurn(numGen);

            System.out.print("Dealer's hand: " + dealerScore + "\n" +
                    "Your hand is: " + playerScore + "\n" +
                    "\n");

            if (dealerScore == playerScore)
            {
                System.out.print("It's a tie! No one wins!\n" +
                        "\n");
                drawCount++;
            }
            else if (dealerScore > 22 || dealerScore < playerScore) // Dealer busted if score = 22
            {
                System.out.print("You win!\n" +
                        "\n");
                winCount++;
            }
            else
            {
                System.out.print("Dealer wins!\n" +
                        "\n");
                lossCount++;
            }
        }
    }
}
                                   