import java.io.Serializable;
//Ticket class handles the costs, types of people traveling, departure and destination.
public class Ticket implements Serializable{
    private double adultPrice;
    private double kidPrice;
    private double seniorPrice;
    private double cphPrice;
    private String avgang;
    private String destination;
    private double totalPrice;
    private int barn = 0;
    private int vuxen = 0;
    private int pension = 0;

    //this is a constructor that creates instance of class Ticket. It receives two Strings, 'avgang' and 'destination'.
    public Ticket(String avgang, String destination) {
        this.adultPrice = 50;
        this.kidPrice = 30;
        this.seniorPrice = 20;
        this.cphPrice = 10;
        this.avgang = avgang;
        this.destination = destination;
        this.totalPrice = 0;
        this.barn = 0;
        this.vuxen = 0;
        this.pension = 0;
    }

    //constructor that is used to get the ticket prices
    public Ticket() {
        this.adultPrice = 50;
        this.kidPrice = 30;
        this.seniorPrice = 20;
        this.cphPrice = 10;

    }

    //a getter to getTotalPrice of tickets
    public double getTotalPrice() {
        return totalPrice;
    }

    //a simple method that displays costs for traveling
    public void ticketCost() {
        System.out.println("Priserna är för Skåne, för CPH tillkommer ett tillägg!");
        System.out.println("Vuxen pris: " + adultPrice + " kr.");
        System.out.println("Barn pris: " + kidPrice + " kr.");
        System.out.println("Pensionär pris: " + seniorPrice + " kr.");
        System.out.println("Tillägg för CPH, pris: " + cphPrice + " kr.");
    }

    //a method that sets price depending on what we chose of being, ie: "vuxen", "barn" etc.
    //We also write an if-logic that checks if either our departure or destination is CPH,
    // then we add an extraPrice (10kr).
    //It prints the total cost for our trip
    public void setTotalPrice(String travelerType) {
        double extraPrice = 0;
        if (avgang.equals("Köpenhamn C") || destination.equals("Köpenhamn C")) {
            extraPrice += cphPrice;
        }
        if (travelerType.equals("barn")) {
            totalPrice += kidPrice + extraPrice;
            barn++;
        } else if (travelerType.equals("vuxen")) {
            totalPrice += adultPrice + extraPrice;
            vuxen++;
        } else if (travelerType.equals("pension")) {
            totalPrice += seniorPrice + extraPrice;
            pension++;
        }
        System.out.println("Total priset är: " + totalPrice + " kr.");
    }

    //Returns string representation from the ticket object. (This is our receipt)
    @Override
    public String toString() {
        return "KVITTO: {" +
                " Avgång = '" + avgang + '\'' +
                ", Destination = '" + destination + '\'' +
                ", TOTAL PRIS= " + totalPrice +" kr" +
                ". Antal barn: " + barn + ". Antal vuxna: " + vuxen +
                ". Antal pensionärer: " + pension +
                '}';
    }
}
