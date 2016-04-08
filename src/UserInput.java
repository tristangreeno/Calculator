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

    public static void doCalculations(){
        while(true) {
            int input = welcome();
            double userNumber = scanNumber();

            boolean invalidInput = false;

            do {
                switch (input) {
                    case 1:
                        currentValue = add(currentValue, userNumber);
                        break;
                    case 2:
                        currentValue = subtract(currentValue, userNumber);
                        break;
                    case 3:
                        currentValue = multiply(currentValue, userNumber);
                        break;
                    case 4:
                        currentValue = divide(currentValue, userNumber);
                        break;
                    case 5:
                        currentValue = exponent(currentValue, userNumber);
                        break;
                    case 6:
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

    private static double clearCalculator() {
        System.out.println("Current value set to 0.\n");
        currentValue = 0.0;
        return currentValue;
    }

    private static double logarithm(double currentValue, double input){
        double log = logOfBase(currentValue, input);
        System.out.printf("Current value is now %s.\n", log);
        return log;
    }

    private static double exponent(double currentValue, double input){
        double pow = Math.pow(currentValue, input);
        System.out.printf("Current value is now %s.\n", pow);
        return pow;
    }

    private static double divide(double currentValue, double input) {

        if(input == 0){
            System.out.println("Error: can't divide by 0.");
            return currentValue;
        }

        currentValue /= input;

        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue;
    }

    private static double multiply(double currentValue, double input) {
        currentValue *= input;
        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue;
    }

    private static double subtract(double currentValue, double input) {
        currentValue -= input;
        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue;
    }

    private static double add(double currentValue, double input) {
        currentValue += input;
        System.out.printf("Current value is now %s.\n", currentValue);
        return currentValue ;
    }

    // Taken from online, not my original function but is modified to fit my needs
    private static double logOfBase(double base, double num) {
        return Math.log(num) / Math.log(base);
    }

}
