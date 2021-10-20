import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Filehandler {
    private ArrayList<String> stations;
    private ArrayList<String> avgangar;

    /**
    First we create two ArrayLists for our 'stations' + 'avgangar'.
    We create two Bufferreaders (reader) that will read from our two text files: avgangar and stationer
    We embrace both readers with try-catch.
    We also close the readers when their job is done.

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

    /** method that writes our ticket to a file (our ticket that we created from the Ticket class)
    a boolean returns true if no problem, false if there are problems.
    Output stream is used to write the data to a file*/

    public boolean serializeTicket(Ticket ticket) {
        try {
            Path path = Paths.get("src/serialized_tickets.txt");
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
            //writes an object instead of only pure text
            out.writeObject(ticket);
            out.close();

            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    /** method that decodes the text file into a ticket object, this is our 'kvitto' (receipt)
        Input stream is used to read the data from the source file
        I used the slides for FileInputStream and ObjectoutputStream and wrapped them in a try catch.
     */
    public Ticket deserializeTicket() {
        try {
            FileInputStream fileIn = new FileInputStream("src/serialized_tickets.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //we read the object ticket and further down we 'sout' it to a 'toString'
            Ticket ticket = (Ticket) in.readObject();
            in.close();
            fileIn.close();
            //We print the content of the ticket object.
            System.out.println(ticket.toString());
            return ticket;
        } catch (FileNotFoundException fne) {
            return null;
        } catch (IOException ioe) {
            ioe.printStackTrace();

            return null;
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Ticket class not found");
            cnfe.printStackTrace();
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



