package ilstu.edu;

/** A class that represents a patient who has COVID-19 */
public class Covid19Patient extends Patient {

    /** fields */
    private double temperature;

    /** constructors */
    public Covid19Patient(int id, String fName, String lName, int age, double temperature) {
        super(id, fName, lName, age);
        this.temperature = temperature;
    }

    /** methods */
    /**
     * gets the temperature of the covid-19 patient
     * @return temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * sets the temperature of the covid-19 patient
     * @param temperature temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * determines which type of treatment the patient should receive based on age and temperature
     * @return type of treatment
     */
    public String treat() {
        String output = "";
        if ( (super.getAge() > 65) & (Math.abs(this.temperature - 37.5) < 0.01) )
            output = "Remdesivir";
        else
            output = "Fluids and Tylenol";
        if ( (Math.abs(this.temperature - 40.0) < 0.01) )
            output += " and Dexamethasone";
        return output;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTemperature: " + this.temperature +
                "\nTreatment: " + treat();
    }

}
