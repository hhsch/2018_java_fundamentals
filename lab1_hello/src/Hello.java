import java.util.Scanner;

public class Hello
{
    public static void main(String[] args)
    {
        Scanner user = new Scanner(System.in);
        String response;

        System.out.print("Hello. What is your name? ");
        response = user.next();

        System.out.print("It's nice to meet you, " + response + ". How old are you? ");
        response = user.next();

        System.out.print("I see that you are still quite young at only " + response + ".\n" +
                "Where do you live? ");
        response = user.next();

        System.out.print("Wow! I've always wanted to go to " + response + ". Thanks for chatting with me. Bye!");
    }
}
