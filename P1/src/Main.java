import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int year = Integer.parseInt(sc.nextLine());

        String racer = sc.nextLine();

        String category = sc.nextLine();

        String bike = sc.nextLine();

        int position = Integer.parseInt(sc.nextLine());

        int points = Integer.parseInt(sc.nextLine());

        Championship championship = new Championship(year, racer, category, bike, position, points);

        int raceNumber = Integer.parseInt(sc.nextLine());

        String circuit = sc.nextLine();
        String racer2 = sc.nextLine();
        int year2 = Integer.parseInt(sc.nextLine());
        int position2 = Integer.parseInt(sc.nextLine());
        boolean finished = sc.nextBoolean();

        Race r = new Race(circuit, racer2, year2, position, finished);

        championship.addRace(raceNumber, r);

        System.out.println(championship.toString());
    }
}