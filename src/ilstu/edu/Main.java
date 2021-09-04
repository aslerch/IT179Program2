package ilstu.edu;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Driver class for overall program */
public class Main {

    /** global fields/objects */
    private static final int ADMIT_PATIENT = 1, PRINT_PATIENT_INFORMATION = 2, SUBMIT_PCR_RESULT = 3,
            DO_ROUNDS = 4, DISCHARGE_PATIENT = 5, EXIT = 6, INVALID = -1;
    private static final double LOWEST_LIVABLE_TEMPERATURE = 24, HIGHEST_LIVABLE_TEMPERATURE = 42;
    private static final int ID_LOWER_BOUND = 0, ID_UPPER_BOUND = 999999;
    private static Scanner keyboard = new Scanner(System.in);
    private static ArrayList<Patient> allPatients = new ArrayList<Patient>();

    /** main method */
    public static void main(String [] args) {

        int menuSelection = -1; // arbitrary initial value for menu selection
        displayWelcomeSign();
        while (menuSelection != EXIT) {
            displayMenu();
            menuSelection = askForIntBetween("Your selection (1-6): ", 1, 6);
            if (menuSelection == ADMIT_PATIENT) {
                admitPatient();
                System.out.println("****************************************\n");
            }
            if (menuSelection == PRINT_PATIENT_INFORMATION) {
                printPatientInformation();
                System.out.println("****************************************\n");
            }
        }

    }

    /** methods */
    /**
     * adds a covid patient to the allPatients ArrayList
     * @param temperature the patient's temperature
     */
    private static void admitCovidPatient(double temperature) {
        int id = askForPatientIdNumber();
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
            double temperature = askForDoubleBetween("Please enter the patient's temperature in celsius (24-42): ",
                    LOWEST_LIVABLE_TEMPERATURE, HIGHEST_LIVABLE_TEMPERATURE);
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
        int id = askForPatientIdNumber();
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
    private static double askForDoubleBetween(String informationRequestMessage, double num1, double num2) {
        double input = -9999;
        boolean isDouble = false;
        boolean isBetweenNum1AndNum2 = false;
        while (isDouble == false || isBetweenNum1AndNum2 == false) {
            isDouble = false;
            isBetweenNum1AndNum2 = false;
            try {
                System.out.print(informationRequestMessage);
                input = keyboard.nextDouble();
                if (input >= num1 & input <= num2)
                    isBetweenNum1AndNum2 = true;
                if (input < num1 || input > num2)
                    System.out.println("input outside of acceptable range");
                isDouble = true;
            } catch (InputMismatchException e) {
                keyboard.nextLine();
                System.out.println("Incorrect input: not a double value");
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
                System.out.print(informationRequestMessage);
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
        keyboard.nextLine();
        return input;
    }

    /**
     * asks the user for a patient id number
     * @return patient id number if the patient exists, -1(INVALID) if the patient does not exist
     */
    private static int askForPatientIdNumber() {
        int id = INVALID;
        while (id == INVALID) {
            id = askForIntBetween("Please enter an id number for the patient: ", ID_LOWER_BOUND, ID_UPPER_BOUND);
            if ( doesPatientExist(id) == true ) {
                System.out.println("That id number is already taken");
                id = INVALID;
            }
        }
        return id;
    }

    /**
     * asks for the results from a PCR Test
     * @return true if test was positive, false if test was negative
     */
    private static boolean askForPCRTestResult() {
        String informationRequestMessage = """
                What was the result of the PCR Test?
                1. Positive
                2. Negative
                Your selection (1-2):  """;
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

    /**
     * checks to see if the patient exists in the allPatients ArrayList
     * @param id identification number
     * @return true if patient exists, false if patient does not exist
     */
    private static boolean doesPatientExist(int id) {
        boolean doesExist = false;
        if ( searchAllPatientsFor(id) != null)
            doesExist = true;
        return doesExist;
    }

    /**
     * prints the patient's information if they exist
     */
    private static void printPatientInformation() {
        int returnId = INVALID;
        int id = askForIntBetween("Please enter the patient's id number: ",
                ID_LOWER_BOUND, ID_UPPER_BOUND);
        boolean patientExists = doesPatientExist(id);
        if (patientExists)
            returnId = id;
        if (returnId != INVALID)
            System.out.println(searchAllPatientsFor(id).toString());
        if (returnId == INVALID)
            System.out.println("no patient has that id number");
    }

    /**
     * searches the allPatients ArrayList for a patient based on their id number
     * @param id identification number
     * @return the patient if they exist, null if they do not exist
     */
    private static Patient searchAllPatientsFor(int id) {
        Patient returnPatient = null;
        for (Patient patient : allPatients) {
            if (patient.getId() == id)
                returnPatient = patient;
        }
        return returnPatient;
    }


}
