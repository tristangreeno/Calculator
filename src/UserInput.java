import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Welcomes the user, and then prompts the user to enter input to perform a certain calculation.
 */
public class UserInput {
    private static double currentValue = 0;

    public static int welcome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the calculator.");
        System.out.printf("The current calculated value is %s.\n", currentValue);
        System.out.println("Enter [1] for addition.");
        System.out.println("Enter [2] for subtraction.");
        System.out.println("Enter [3] for multiplication.");
        System.out.println("Enter [4] for division.");
        System.out.println("Enter [5] for exponentiation.");
        System.out.println("Enter [6] for logarithms of a custom base.");
        System.out.println("Enter [7] to clear the calculator.");

        return Integer.parseInt(scanner.nextLine());
    }

    private static double scanNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number to do your selected operation with.");
        double userDouble = scanner.nextDouble();

        try{
            return userDouble;
        }catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter only numbers.");
            return 0.0;
        }
    }

    public static void doCalculations() throws IOException{
        while(true) {
            int input = welcome();


            boolean invalidInput = false;

            do {
                switch (input) {
                    case 1:
                        double userNumber = scanNumber();
                        currentValue = add(currentValue, userNumber);
                        break;
                    case 2:
                        userNumber = scanNumber();
                        currentValue = subtract(currentValue, userNumber);
                        break;
                    case 3:
                        userNumber = scanNumber();
                        currentValue = multiply(currentValue, userNumber);
                        break;
                    case 4:
                        userNumber = scanNumber();
                        currentValue = divide(currentValue, userNumber);
                        break;
                    case 5:
                        userNumber = scanNumber();
                        currentValue = exponent(currentValue, userNumber);
                        break;
                    case 6:
                        userNumber = scanNumber();
                        currentValue = logarithm(currentValue, userNumber);
                        break;
                    case 7:
                        currentValue = clearCalculator();
                        break;
                    default:
                        System.out.println("Invalid input.");
                        invalidInput = true;
                        welcome();
                }
            } while (invalidInput);
        }
    }

    private static double clearCalculator() throws IOException {
        System.out.println("Current value set to 0, and previous operations have been written to a text file.\n");
        CalculatorFile.writeToFile();
        currentValue = 0.0;
        return currentValue;
    }

    private static double logarithm(double currentValue, double input) throws IOException{
        double log = logOfBase(currentValue, input);
        CalculatorFile.addToFile(currentValue, log, "logarithm");
        System.out.printf("Current value is now %s.\n", log);
        return log;
    }

    private static double exponent(double currentValue, double input) throws IOException{
        double pow = Math.pow(currentValue, input);
        CalculatorFile.addToFile(currentValue, pow, "exponentiation");
        System.out.printf("Current value is now %s.\n", pow);
        return pow;
    }

    private static double divide(double currentValue, double input) throws IOException{

        if(input == 0){
            System.out.println("Error: can't divide by 0.");
            return currentValue;
        }

        double valueStorage = currentValue;
        currentValue /= input;
        CalculatorFile.addToFile(valueStorage, currentValue, "division");

        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue;
    }

    private static double multiply(double currentValue, double input) throws IOException{
        double valueStorage = currentValue;
        currentValue *= input;
        CalculatorFile.addToFile(valueStorage, currentValue, "multiplication");
        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue;
    }

    private static double subtract(double currentValue, double input) throws IOException{
        double valueStorage = currentValue;
        currentValue -= input;
        CalculatorFile.addToFile(valueStorage, currentValue, "subtraction");
        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue;
    }

    private static double add(double currentValue, double input) throws IOException{
        double valueStorage = currentValue;
        currentValue += input;
        CalculatorFile.addToFile(valueStorage, currentValue, "addition");
        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue ;
    }

    // Taken from online, not my original function but is modified to fit my needs
    private static double logOfBase(double base, double num) {
        return Math.log(num) / Math.log(base);
    }

}
