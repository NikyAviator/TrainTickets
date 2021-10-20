import java.util.ArrayList;

public class Stations {

    //a new instance of the Class Filehandler called filehandler
    Filehandler filehandler = new Filehandler();
    //an ArrayList for our stations
    private ArrayList<String> stations;

    // a simple constructor that sets field variable station via a call to filehandler.getStation()
    public Stations() {
        this.stations = filehandler.getStations();
    }

    //a for-loop that loops through the station list and adds the numbers for the stations,
    //making it easier to chose from our stations
    public void printStations() {
        for (int i = 0; i < stations.size(); i++) {
            //added a 'plus 1' so that our stations start from nr 1 (and not 0).
            System.out.println((i + 1) + " " + stations.get(i));
        }
    }
    //a method that returns the station we chose from the numbers of the stations
    public String getStation(int stationIndex) {
        for (int i = 0; i < stations.size(); i++) {
            //we add a 'plus 1' so the digit next to station and the actual station is the same
            if ((i + 1) == stationIndex) {
                return stations.get(i);
            }
        }
        System.out.println("Could not find station! Try from the beginning.");
        return null;
    }
}
