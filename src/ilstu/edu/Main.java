package ilstu.edu;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Driver class for overall program */
public class Main {

    /** global fields/objects */
    private static final int ADMIT_PATIENT = 1, PRINT_PATIENT_INFORMATION = 2, SUBMIT_PCR_RESULT = 3, DO_ROUNDS = 4, DISCHARGE_PATIENT = 5, EXIT = 6;
    private static Scanner keyboard = new Scanner(System.in);
    private static ArrayList<Patient> allPatients = new ArrayList<Patient>();

    /** main method */
    public static void main(String [] args) {

        int menuSelection = -1; // arbitrary initial value for menu selection
        displayWelcomeSign();
        while (menuSelection != EXIT) {
            displayMenu();
            menuSelection = askForIntBetween("Your selection (1-6): ", 1, 6);
            if ( menuSelection == ADMIT_PATIENT )
                admitPatient();
            if ( menuSelection == PRINT_PATIENT_INFORMATION ) {
                askForIntBetween("")
                printPatientInformation();
            }
        }

    }

    /** methods */
    /**
     * adds a covid patient to the allPatients ArrayList
     * @param temperature the patient's temperature
     */
    private static void admitCovidPatient(double temperature) {
        int id = askForIntBetween("Please enter the patient's id number: ", 0, 999999);
        String fName = askForStringWithinLength("Please enter the patient's first name: ", 50);
        String lName = askForStringWithinLength("Please enter the patient's last name: ", 50);
        int age = askForIntBetween("Please enter the patient's age (0-120): ", 0, 120);
        Covid19Patient newCovid19Patient = new Covid19Patient(id, fName, lName, age, temperature);
        allPatients.add(newCovid19Patient);
    }

    /**
     * Admits a patient
     */
    private static void admitPatient() {
        boolean hasCovid = askForPCRTestResult();
        if ( hasCovid ) {
            double temperature = askForDoubleBetween(0, 200);
            admitCovidPatient(temperature);
        }
        if ( ! hasCovid ) {
            String mainSymptom = askForStringWithinLength("Please enter the patient's main symptom: ", 30);
            admitRegularPatient(mainSymptom);
        }
    }

    /**
     * adds a regular patient to the allPatients ArrayList
     * @param mainSymptom the patient's main symptom
     */
    private static void admitRegularPatient(String mainSymptom) {
        int id = askForIntBetween("Please enter the patient's id number: ", 0, 999999);
        String fName = askForStringWithinLength("Please enter the patient's first name: ", 50);
        String lName = askForStringWithinLength("Please enter the patient's last name: ", 50);
        int age = askForIntBetween("Please enter the patient's age (0-120): ", 0, 120);
        RegularPatient newRegularPatient = new RegularPatient(id, fName, lName, age, mainSymptom);
        allPatients.add(newRegularPatient);
    }

    /**
     * Asks for a double between or equal to lower and upper bound values
     * @param num1 The lower bound
     * @param num2 The upper bound
     * @return A double between or equal to the upper bound and lower bound
     */
    private static double askForDoubleBetween(double num1, double num2) {
        double input = -9999;
        boolean isDouble = false;
        boolean isBetweenNum1AndNum2 = false;
        while (isDouble == false || isBetweenNum1AndNum2 == false) {
            isDouble = false;
            isBetweenNum1AndNum2 = false;
            try {
                System.out.print("Your input (" + num1 + " - " + num2 + "): ");
                input = keyboard.nextDouble();
                if (input >= num1 & input <= num2)
                    isBetweenNum1AndNum2 = true;
                if (input < num1 || input > num2)
                    System.out.println("input is outside of acceptable range");
                isDouble = true;
            } catch (InputMismatchException e) {
                keyboard.nextLine();
                System.out.println("Incorrect input: not a double");
            }
        }
        return input;
    }

    /**
     * Asks for an int between or equal to lower and upper bound values
     * @param num1 The lower bound
     * @param num2 The upper bound
     * @return A number between or equal to the upper bound and lower bound
     */
    private static int askForIntBetween(String informationRequestMessage, int num1, int num2) {
        int input = -9999;
        boolean isInt = false;
        boolean isBetweenNum1AndNum2 = false;
        while (isInt == false || isBetweenNum1AndNum2 == false) {
            isInt = false;
            isBetweenNum1AndNum2 = false;
            try {
                System.out.print();
                input = keyboard.nextInt();
                if (input >= num1 & input <= num2)
                    isBetweenNum1AndNum2 = true;
                if (input < num1 || input > num2)
                    System.out.println("input outside of acceptable range");
                isInt = true;
            } catch (InputMismatchException e) {
                keyboard.nextLine();
                System.out.println("Incorrect input: not an integer");
            }
        }
        return input;
    }

    /**
     * asks for the results from a PCR Test
     * @return true if test was positive, false if test was negative
     */
    private static boolean askForPCRTestResult() {
        String informationRequestMessage = """
                What was the result of the PCR Test?
                1. Positive
                2. Negative""";
        int selection = askForIntBetween(informationRequestMessage, 1, 2);
        boolean hasCovid = true;
        if ( selection == 1)
            hasCovid = true;
        if ( selection == 2)
            hasCovid = false;
        return hasCovid;
    }

    /**
     * Asks for a String that is within a certain length
     * @param informationRequestMessage The request message
     * @param lengthUpperBound The upper bound for length
     * @return A String within a length determined by the upper bound
     */
    private static String askForStringWithinLength(String informationRequestMessage, int lengthUpperBound) {
        keyboard.nextLine();
        boolean isWithinCorrectLength = false;
        String output = "";
        while (isWithinCorrectLength == false) {
            System.out.print(informationRequestMessage);
            output = keyboard.nextLine();
            if (output.length() <= lengthUpperBound)
                isWithinCorrectLength = true;
            if (output.length() > lengthUpperBound)
                System.out.println("input not of valid length " + "(" + lengthUpperBound + ")");
        }
        return output;
    }

    /**
     * Displays the menu
     */
    private static void displayMenu() {
        System.out.println("""
                1. Admit a Patient
                2. Print Patient Information
                3. Submit a PCR Test Result
                4. Do Rounds
                5. Discharge Patient
                6. Exit Database""");
    }

    /**
     * Displays the program's welcome sign
     */
    private static void displayWelcomeSign() {
        System.out.println("""
                ****************************************
                Welcome to the Hospital Patient Database
                ****************************************""");
    }


}
