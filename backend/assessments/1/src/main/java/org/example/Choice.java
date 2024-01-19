package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Choice {
    public static List<String> getBowlers40(List<Player> players, String team){
        return players.stream()
                .filter(p -> p.getTeam().equalsIgnoreCase(team))
                .filter(p -> p.getWickets() >= 40)
                .map(Player::getName)
                .toList();
    }

    public static void highest(List<Player> players, String team){
        String wicketTaker= String.valueOf(highestWicket(players, team));
        String runMaker= String.valueOf(highestRun(players, team));
        System.out.println("Highest wicket taker is " + wicketTaker);
        System.out.println("Highest run maker is " + runMaker);
    }

    private static Optional<String> highestWicket(List<Player> players, String team){
        return players.stream()
                .filter(p -> p.getTeam().equalsIgnoreCase(team))
                .max((p1, p2) -> p1.getWickets() - p2.getWickets())
                .map(Player::getName);
    }

    private static Optional<String> highestRun(List<Player> players, String team){
        return players.stream()
                .filter(p -> p.getTeam().equalsIgnoreCase(team))
                .max((p1, p2) -> p1.getRuns() - p2.getRuns())
                .map(Player::getName);
    }

    public static List<String> highestThree(List<Player> players, String team){
        String wicketTaker= String.valueOf(highestWicketThree(players, team));
        String runMaker= String.valueOf(highestRunThree(players, team));
        System.out.println("Highest wicket taker is " + wicketTaker);
        System.out.println("Highest run maker is " + runMaker);
        return players.stream()
                .filter(p -> p.getTeam().equalsIgnoreCase(team))
                .filter(p -> p.getWickets() >= 40)
                .map(Player::getName)
                .toList();
    }

    private static List<String> highestWicketThree(List<Player> players, String team){
        return players.stream()
                .sorted(Comparator.comparing(player -> -player.getWickets()))
                .map(Player::getName)
                .toList();
    }


}
