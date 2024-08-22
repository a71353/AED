import java.util.ArrayList;
import java.util.List;

public class Championship {

    private int year;

    private String circuit;
    private String racer;
    private String category;

    private String bike;
    private int position;

    private int points;

    private boolean finished;

    public Championship(int year, String racer, String category, String bike, int position, int points) {
        for(int i = 0; i < 21; i++){
            raceL.add(i, null);
        }
        this.year = year;
        this.racer = racer;
        this.category = category;
        this.bike = bike;
        this.position = position;
        this.points = points;
    }

    ArrayList<Race> raceL = new ArrayList<Race>();

    public int getYear() {
        return year;
    }

    public String getRacer() {
        return racer;
    }

    public String getCategory() {
        return category;
    }

    public String getBike() {
        return bike;
    }

    public int getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public void addRace(int raceNumber, Race r) {
        if (raceNumber < 1 || raceNumber > 21) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        if (r.getRacer().equals(this.racer)  && r.getYear() == (this.year) ) {
            raceL.set(raceNumber-1, r);
        } else {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
    }

    public Race getRace(int raceNumber) {
        Race race;
        if (raceNumber < 1 || raceNumber > 21) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        try {
            race = raceL.get(raceNumber-1);
        } catch (Exception e) {
            return null;
        }
        return race;
    }

    public String toString() {
        Race raceN;
        String result = (racer + "/" + year + "/" + category + "/" + bike + "\n");
        for (int i = 1; i < 22; i++) {
            raceN = getRace(i);
            if(raceN == null){
                result += " /";
            }
            else{
                result += raceN.getCircuit();
                int pos = raceN.getPosition();
                if(pos == 0){
                    result += " /";
                }
                else{
                    result += " " + Integer.toString(pos) + "/";
                }
            }
        }
        return result;
    }

    public int compareTo(Championship c2){
        int result = this.racer.compareTo(c2.racer);
        if(result == 0){
            result = (c2.year - this.year);
        }
        return result;
    }
}