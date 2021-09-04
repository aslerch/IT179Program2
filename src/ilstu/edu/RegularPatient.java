package ilstu.edu;

/** A class that represents a regular patient */
public class RegularPatient extends Patient {

    /** fields */
    private String mainSymptom;

    /** constructors */
    public RegularPatient(int id, String fName, String lName, int age, String mainSymptom) {
        super(id, fName, lName, age);
        this.mainSymptom = mainSymptom;
        super.setPcr(false);
    }

    /** methods */
    /**
     * determines which type of treatment that a patient should receive based on their main symptom
     * @return treatment type
     */
    public String treat() {
        String output = "";
        if ( mainSymptom.equalsIgnoreCase("coughing") ||
                mainSymptom.equalsIgnoreCase("runny nose") ||
                mainSymptom.equalsIgnoreCase("stuffy nose") )
            output = "Amoxicillin";
        if ( mainSymptom.equalsIgnoreCase("hypertension") )
            output = "ACE inhibitors";
        else
            output = "IV fluids";
        return output;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nMain Symptom: " + this.mainSymptom +
                "\nTreatment: " + treat();
    }

}
