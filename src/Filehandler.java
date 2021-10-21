import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Filehandler {
    private ArrayList<String> stations;
    private ArrayList<String> avgangar;

    /**
     * First we create two ArrayLists for our 'stations' + 'avgangar'.
     * We create two Bufferreaders (reader) that will read from our two text files: avgangar and stationer
     * We embrace both readers with try-catch.
     * We also close the readers when their job is done.
     */
    public Filehandler() {
        stations = new ArrayList<>();
        avgangar = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/stationer"));
            String line;
            while ((line = reader.readLine()) != null) {
                stations.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/avgangar"));
            String line;
            while ((line = reader.readLine()) != null) {
                avgangar.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method that writes our ticket to a file (our ticket that we created from the Ticket class)
     * a boolean returns true if no problem, false if there are problems.
     */

    public boolean writeTicket(Ticket ticket) {
        try {
            Path path = Paths.get("src/ticket.txt");
            Files.writeString(path, new String(ticket.toString()));
            return true;
        } catch (java.io.IOException ioe) {
            return false;
        }

    }

    /**
     * A method that reads the ticket.txt file into the console.
     * Also the method returns a ticket.
     */

    public Ticket readTicket() {
        try {
            byte[] bytes = Files.readAllBytes(Path.of("src/ticket.txt"));
            String ticketData = new String(bytes, StandardCharsets.UTF_8);
            Ticket ticket = new Ticket();
            System.out.println(ticketData);
            return ticket;
        } catch (java.io.IOException ioe) {
            return null;
        }
    }

    // a getter for the stations
    public ArrayList<String> getStations() {
        return stations;
    }

    // a getter for the avgångar
    public ArrayList<String> getAvgangar() {
        return avgangar;
    }
}



