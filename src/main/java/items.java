public class items {
    private String ID;
    private String title;
    private String type;
    private int loan;
    private int numOfCopies;
    private double price;
    private Boolean RentalStatus;

    public items(String ID, String title, String type, int loan, int numOfCopies, double price, Boolean rentalStatus) {
        this.ID = ID;
        this.title = title;
        this.type = type;
        this.loan = loan;
        this.numOfCopies = numOfCopies;
        this.price = price;
        RentalStatus = rentalStatus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        if (ID.matches("^I\\d{3}-\\d{4}$")) {
            this.ID = ID;
        } else {
            System.out.println("ID format is wrong ");
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(type.equalsIgnoreCase("Record")||type.equalsIgnoreCase("DVD")||type.equalsIgnoreCase("Game"))
         this.type = type;
        else
            System.out.println("please enter valid type");


    }

    public String getLoan() {
        if (loan==1)
         return loan+"week ";
        if (loan==2)
            return loan+"days";
        return null;
    }

    public void setLoan(int loan) {
        if (loan==1||loan==2)
            this.loan = loan;
        else
            System.out.println("wrong input ");
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getRentalStatus() {
        return RentalStatus;
    }

    public void setRentalStatus(Boolean rentalStatus) {
        RentalStatus = rentalStatus;
    }
}