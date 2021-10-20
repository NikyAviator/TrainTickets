import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    //creating an instance of class Filehandler called filehandler.
    Filehandler filehandler = new Filehandler();
    //creating an instance of class Stations called stations.
    Stations stations = new Stations();
    //creating an instance of class Scanner called input.
    Scanner input = new Scanner(System.in);
    //setting our default menuChoice to '0'
    int menuChoice = 0;

    //main menu for our program
    void menu() {
        System.out.println("|1. Sök resa |2. Visa mina biljetter |3. Biljettpriser | 4. Avsluta ");
        menuChoice = input.nextInt();
        switch (menuChoice) {
            case 1:
                //We create an instance of class Ticket called ticket that is returned by searchTrip()
                //searchTrip() method returns a ticket
                Ticket ticket = searchTrip();
                // if we mess up the ticket, by for example searching for a station out of bounds, we set the ticket
                // to null and call main().
                if (ticket == null) {
                    menu();
                    break;
                }
                //we also call userAddTraveler and send ticket as an argument.
                userAddTraveler(ticket);
                System.out.println("Tips på avgångar: ");
                //Making an Arraylist that prints line by line from avgangar file, so that it is easier to read.
                // We loop through the list, line by line then printing them.
                ArrayList<String> avgangarEzRead = filehandler.getAvgangar();
                for (int i = 0; i < avgangarEzRead.size(); i++) {
                    System.out.println(avgangarEzRead.get(i));
                }
                //calling the payment method (ticket is passed as an argument)
                payment(ticket);
                //we call the menu() after we have paid
                menu();
                break;
            case 2:
                //we call the method that reads the ticket that has been saved to the file.
                filehandler.deserializeTicket();
                //we call the menu() after we have looked at our ticket
                menu();
                break;
            case 3:
                /*A new instance of Ticket called ticket1 used to call method that displays the costs-
                of traveling */
                Ticket ticket1 = new Ticket();
                ticket1.ticketCost();
                //we call menu() after the display of ticket costs
                menu();
                break;
            case 4:
                //Just a plain text and no method calls after, that just terminates the program
                System.out.println("Stänger av programmet");
                break;
            default:
                //default choice, ie wrong input, a message and menu() call.
                System.out.println("Fel intryck!");
                menu();
                break;
        }

    }

    /**
    A method that we can choose our departure and arrival from.
    We create a new ticket (sending departureString and destinationString as parameters) and we return our ticket,
    where we are departing and arriving.
     */

    // We create a method called searchTrip() that returns instance of class Ticket.
    public Ticket searchTrip() {
        System.out.println("Välj avgång (ange siffra och tryck ENTER): ");
        //we print our stations.
        stations.printStations();
        // we save our nextInt input as 'departure'
        int departure = input.nextInt();
        // we create a and save a string that is compared to the number we send as an argument to the getStation method
        String departureString = stations.getStation(departure);
        // if our depatureString is out of bounds we return null to our ticket object;
        if (departureString == null) {
            return null;
        }
        System.out.println(departureString);
        System.out.println("Välj destination (ange siffra och tryck ENTER): ");
        stations.printStations();
        int destination = input.nextInt();
        String destinationString = stations.getStation(destination);
        if (destinationString == null) {
            return null;
        }
        System.out.println(destinationString);
        // we call the first constructor and pass our departure and destination as a String. (a new instance of class
        // Ticket called ticket).
        Ticket ticket = new Ticket(departureString, destinationString);
        // we return the ticket.
        return ticket;
    }

    /**
        a method that adds user that wants to travel.
        First it keeps looping depending on how many travelers we input in the console.
        A switch sends the value of 'vuxen', 'barn' etc and return the cost for those types of travelers.
        We receive the new ticket as a parameter, where we want to save our travelers.
     */
    public void userAddTraveler(Ticket ticket) {
        System.out.println("Hur många skall resa?");
        int numberOfTravelers = input.nextInt();
        for (int i = 0; i < numberOfTravelers; i++) {
            System.out.println(
                    "Resenär nr " + (i + 1) + ":\n" +
                            "1. Barn \n" +
                            "2. Vuxen \n" +
                            "3. Pensionär");

            int typeOfTraveler = input.nextInt();

            switch (typeOfTraveler) {
                case 1:
                    ticket.setTotalPrice("barn");
                    break;
                case 2:
                    ticket.setTotalPrice("vuxen");
                    break;
                case 3:
                    ticket.setTotalPrice("pension");
                    break;
                default:
                    System.out.println("Fel intryck av resenär, tar tillbaka dig till menyn:");
                    menu();
                    break;
            }
        }

    }

    /**
        this method handles the payment. we put in our money through moneyInput.
        The if-logic handles, if we put in more money than the cost, then we get back the difference.
        If we put in too little money we get a prompt that we put in too little money, the user gets back
        his/hers money, and we get another chance to retry (we call the payment method again).
     */
    public void payment(Ticket ticket) {
        System.out.println("För in pengar i automaten för betalning: ");
        int moneyInput = input.nextInt();
        System.out.println("Ditt saldo är: " + moneyInput + " kr.");
        System.out.println("Priset för biljetterna är: " + ticket.getTotalPrice() + " kr.");
        //we serialize the ticket after a successful payment
        boolean b = filehandler.serializeTicket(ticket);
        if (moneyInput >= (ticket.getTotalPrice())) {
            System.out.println("Pengar tillbaka: " + (moneyInput - ticket.getTotalPrice()) + " kr.");
        } else {
            System.out.println("Otillräckligt med pengar på saldot...");
            System.out.println("Returnerar dina pengar, försök igen!");
            payment(ticket);
        }
    }
}
