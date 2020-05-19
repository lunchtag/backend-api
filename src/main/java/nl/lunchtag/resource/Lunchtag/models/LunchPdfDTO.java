package nl.lunchtag.resource.Lunchtag.models;

public class LunchPdfDTO {
    private String firstname;

    private String lastname;

    private int count;

    public LunchPdfDTO(String firstname, String lastname, int count) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.count = count;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
