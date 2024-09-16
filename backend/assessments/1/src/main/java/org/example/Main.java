package org.example;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    static List<Player> players = new ArrayList<>();
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
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
            logger.info("Enter the choice :");
            logger.info("1.Enter team's name to return all the bowlers who have taken at least 40 wickets.");
            logger.info("2.Enter the team's name to display the details of the highest wicket-taker and highest run-scorer.");
            logger.info("3.The top 3 run-scorer and top 3 wicket-takers of the season.");
            logger.info("4.Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case '1':
                    logger.info("Enter the name of the team : ");
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
                    Choice.highestThree(players);
                    break;
                case '4':
                    logger.info("You are exiting.");
                    running = false;
                default:
                    logger.info("Invalid input. Try again!");
            }
        }
    }
}