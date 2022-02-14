/*
 * @Author(Ryan Odum, Hunter Schwartz)
 * @Name(Numeric Conversion)
 * @Version (1.0.0)
 */

import java.util.Scanner; // take in input
import java.util.InputMismatchException;

// NumericConversion class begins
public class NumericConversion {

    // display the menu options
    public static void printMenu() {
        System.out.println("Decoding Menu");
        System.out.println("-------------");
        System.out.println("1. Decode hexadecimal");
        System.out.println("2. Decode binary");
        System.out.println("3. Convert binary to hexadecimal");
        System.out.println("4. Quit");
        System.out.println();
    }

    // reverse the array
    public static char[] reverseArray(char[] hexArr) {
        char[] output = new char[hexArr.length];
        int counter = hexArr.length - 1;
        for (int i = 0; i < hexArr.length; i++) {
            output[i] = hexArr[counter - i];
        }
        return output;
    }

    // turn a hex string into a decimal number
    public static long hexStringDecode(String hex) {
        long output = 0;

        // trim 0x if it exits
        if (hex.substring(0, 2).equalsIgnoreCase("0x")) {
            hex = hex.substring(2);
        }

        char[] hexArr = hex.toCharArray();
        hexArr = reverseArray(hexArr);

        // sum each place multiplied by its corresponding power of 16
        for (int i = 0; i < hexArr.length; i++) {
            output += (hexCharDecode(hexArr[i]) * Math.pow(16, i));
        }

        return output;
    }

    // Convert hex character to decimal value
    public static short hexCharDecode(char digit) {
        short value = 0;

        // Run through options to find match and convert to short integer
        switch (digit) {
            case '0': value += 0;
                break;
            case '1': value += 1;
                break;
            case '2': value += 2;
                break;
            case '3': value += 3;
                break;
            case '4': value += 4;
                break;
            case '5': value += 5;
                break;
            case '6': value += 6;
                break;
            case '7': value += 7;
                break;
            case '8': value += 8;
                break;
            case '9': value += 9;
                break;
            case 'A': // Fall through
            case 'a': value += 10;
                break;
            case 'B':
            case 'b': value += 11;
                break;
            case 'C':
            case 'c': value += 12;
                break;
            case 'D':
            case 'd': value += 13;
                break;
            case 'E':
            case 'e': value += 14;
                break;
            case 'F':
            case 'f': value += 15;
                break;
        }

        return value;
    }

    // turn a binary string into a decimal number
    public static short binaryStringDecode(String binary) {
        short output = 0;

        // trim 0b if it exists
        if (binary.substring(0, 2).equalsIgnoreCase("0b")) {
            binary = binary.substring(2);
        }

        char[] binArr = binary.toCharArray();
        binArr = reverseArray(binArr);

        // multiply each place by its corresponding power of 2
        for (int i = 0; i < binArr.length; i++) {
            output += (hexCharDecode(binArr[i]) * Math.pow(2, i));
        }

        return output;
    }

    // convert binary string to hexadecimal string
    public static String binaryToHex(String binary) {
        int decimal = binaryStringDecode(binary);
        String hex = "";

        while (decimal != 0) {
            // get the remainder of division by 16 and tack it onto the least significant digit's place
            switch(decimal % 16)
            {
                case 0:
                    hex = 0 + hex;
                    break;
                case 1:
                    hex = 1 + hex;
                    decimal -= 1;
                    break;
                case 2:
                    hex = 2 + hex;
                    decimal -= 2;
                    break;
                case 3:
                    hex = 3 + hex;
                    decimal -= 3;
                    break;
                case 4:
                    hex = 4 + hex;
                    decimal -= 4;
                    break;
                case 5:
                    hex = 5 + hex;
                    decimal -= 5;
                    break;
                case 6:
                    hex = 6 + hex;
                    decimal -= 6;
                    break;
                case 7:
                    hex = 7 + hex;
                    decimal -= 7;
                    break;
                case 8:
                    hex = 8 + hex;
                    decimal -= 8;
                    break;
                case 9:
                    hex = 9 + hex;
                    decimal -= 9;
                    break;
                case 10:
                    hex = "A" + hex;
                    decimal -= 10;
                    break;
                case 11:
                    hex = "B" + hex;
                    decimal -= 11;
                    break;
                case 12:
                    hex = "C" + hex;
                    decimal -= 12;
                    break;
                case 13:
                    hex = "D" + hex;
                    decimal -= 13;
                    break;
                case 14:
                    hex = "E" + hex;
                    decimal -= 14;
                    break;
                case 15:
                    hex = "F" + hex;
                    decimal -= 15;
                    break;
            }

            // divide by 16 to 'trim the 0' and restart
            decimal = decimal / 16;
        }

        return hex;
    }


    // main method begins
    public static void main (String[] args) {
        int option = 0;
        String input = "";
        boolean running = true;
        Scanner scan = new Scanner(System.in); // collect user input
        // runs the program
        while (running) {
            printMenu();
            try {
                // picks an option
                System.out.print("Please enter an option: ");
                option = scan.nextInt();
            }
            catch (InputMismatchException e) {
                // prevent errors
                scan.next();
            }
            // decode hexadecimal
            if (option == 1) {
                try {
                    // picks an option
                    System.out.print("Please enter the numeric string to convert: ");
                    input = scan.next();
                    System.out.println("Result: " + hexStringDecode(input));
                    System.out.println();
                }
                catch (InputMismatchException e) {
                    // prevent errors
                    scan.next();
                }
            }
            // decode binary
            else if (option == 2) {
                try {
                    // picks an option
                    System.out.print("Please enter the numeric string to convert: ");
                    input = scan.next();
                    System.out.println("Result: " + binaryStringDecode(input));
                    System.out.println();
                }
                catch (InputMismatchException e) {
                    // prevent errors
                    scan.next();
                }
            }
            // turn binary into hexadecimal
            else if (option == 3) {
                try {
                    // picks an option
                    System.out.print("Please enter the numeric string to convert: ");
                    input = scan.next();
                    System.out.println("Result: " + binaryToHex(input));
                    System.out.println();
                }
                catch (InputMismatchException e) {
                    // prevent errors
                    scan.next();
                }
            }
            // quits the program
            else if (option == 4) {
                System.out.println("Goodbye!");
                running = false;
                scan.close();
            }
        } // while loop ends
    } // main method ends

} //  NumericConversion class ends
