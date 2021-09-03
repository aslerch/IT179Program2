package ilstu.edu;

/** A class that represents a patient */
public abstract class Patient {

    /** fields */
    private int id;
    private String fName;
    private String lName;
    private int age;
    private boolean pcr;

    /** constructors */
    public Patient(int id, String fName, String lName, int age) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.age = age;
    }

    /** methods */
    /**
     * gets the identification number
     * @return identification number
     */
    public int getId() {
        return id;
    }

    /**
     * sets the identification number
     * @param id identification number
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the first name
     * @return first name
     */
    public String getfName() {
        return fName;
    }

    /**
     * sets the first name
     * @param fName first name
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * gets the last name
     * @return last name
     */
    public String getlName() {
        return lName;
    }

    /**
     * sets the last name
     * @param lName last name
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * gets the age (in years)
     * @return age (in years)
     */
    public int getAge() {
        return age;
    }

    /**
     * sets the age (in years)
     * @param age age (in years)
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * gets the pcr test status of the patient
     * @return pcr test status
     */
    public boolean getPcr() {
        return pcr;
    }

    /**
     * sets the pcr test status of the patient
     * @param pcr pcr test status
     */
    public void setPcr(boolean pcr) {
        this.pcr = pcr;
    }
}
