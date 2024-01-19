package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class Main {
    static List<Player> players = new ArrayList<>();
    protected static Map<String, Player> teamToPlayer = new HashMap<>();
    public static void main(String[] args) {
        String file = "src/main/resources/IPL_2021-data.csv";
        LoadCSV read = new LoadCSV();
        players = read.loadCSV(file);

        for(Player p : players){
            System.out.println(p);
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("Enter the choice :");
            System.out.println("1.Enter team's name to return all the bowlers who have taken at least 40 wickets.");
            System.out.println("2.Enter the team's name to display the details of the highest wicket-taker and highest run-scorer.");
            System.out.println("3.The top 3 run-scorer and top 3 wicket-takers of the season.");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case '1':
                    System.out.println("Enter the name of the team : ");
                    String team1 = scanner.next();
                    List<String> bowlers = Choice.getBowlers40(players, team1);
                    System.out.println(bowlers);
                    break;
                case '2':
                    System.out.println("Enter the name of the team : ");
                    String team2 = scanner.next();
                    Choice.highest(players, team2);
                    break;
                case '3':

            }
        }



    }
}