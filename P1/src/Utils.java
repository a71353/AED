

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Utils {
    public static List<Championship> readChampionshipsFromCSV(String filename){
        // Cria uma lista para armazenar os campeonatos lidos do ficheiro
        List<Championship> championships = new ArrayList<>();
        try {

            Scanner reader = new Scanner(new File(filename));

            //para ignorar a primeira linha
            reader.nextLine();

            // Lê o resto do ficheiro, duas linhas de cada vez

            while (reader.hasNextLine()) {
                //linha 1
                List<String> line1 = Arrays.asList(reader.nextLine().split(","));
                //linha2
                List<String> line2 = Arrays.asList(reader.nextLine().split(","));

                // Cria um novo campeonato com as informações da primeira linha
                Championship championship = new Championship(
                        Integer.parseInt(line1.get(1)),// ano do campeonato
                        line1.get(0), // nome do piloto
                        line1.get(2), //categoria
                        line1.get(3), //mota
                        Integer.parseInt(line1.get(25).substring(0, line1.get(25).length() -2)),//posição
                        Integer.parseInt(line1.get(26))//pontos
                );
                championships.add(championship);
                // Adiciona as informações das corridas ao campeonato
                for (int i = 0; i < 21; i++) {
                    int position = 0;
                        try{//para quando há valores vazios ou não validos o codigo continuar a correr
                            if(i < line2.size()-4) {//para quando há valores vazios não verificar o i nas colunas que não são da posição das corridas
                                position = Integer.parseInt(line2.get(i + 4));//a corrida só começa na coluna 4(nome da corrida da linha 1 so na pos 4)
                                }
                            }
                        catch (NumberFormatException e){
                        }
                    Race race = new Race(line1.get(i+4), championship.getRacer(), championship.getYear(), position, position != 0);
                    championship.addRace(i + 1, race);
                }
            }
            reader.close();
        } catch (IOException exception) {
        }
        return championships;
    }

    public static void sortChampionships(List<Championship> champs){
        champs.sort(Championship::compareTo);//ordenar a lista de campionatos com a função da classe championship
    }

    public static void printChampionships(List<Championship> champs){
        for (Championship champ : champs) {
            System.out.println(champ);}
    }


    public static List<Championship> filterByRacer(List<Championship> champs,String racer){
        List<Championship> list = new ArrayList<>();
        for (Championship champ : champs) {
            if (champ.getRacer().equals(racer)) {
                list.add(champ);
            }
        }
        return list;
    }

    static List<Championship> filterByTeam(List<Championship> champs,String teamName,String category){

        return champs;
    }

    public static void printRacerStats(List<Championship> champs, String racer){
        return;
    }

    public static void printRacerCircuitRanking(List<Championship> champs, String racer){
        return;
    }

    public static List<Race> getBestRaces(List<Championship> champs, String racer){
        return null;
    }

    public static List<String> getTeams(List<Championship> champs, String Class){
        return null;
    }
}