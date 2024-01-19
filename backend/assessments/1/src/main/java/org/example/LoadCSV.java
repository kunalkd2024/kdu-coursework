package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadCSV {
    public List<Player> loadCSV(String file)
    {
        List<Player> players= new ArrayList<>();
        Path filePath = Paths.get(file);

        try(BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII))
        {
            String eachPlayer = br.readLine();
            eachPlayer = br.readLine();

            while(eachPlayer != null)
            {
                String[] playerDetails = eachPlayer.split(",");

                Player player = createPlayers(playerDetails);
                players.add(player);
                eachPlayer = br.readLine();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return players;
    }
    private static Player createPlayers(String[] playerInfo)
    {
        String name = playerInfo[0];
        String team = playerInfo[1];
        String role = playerInfo[2];
        int matches= Integer.parseInt(playerInfo[3]);
        int runs = Integer.parseInt(playerInfo[4]);
        double average = Double.parseDouble(playerInfo[5]);
        double strikeRate = Double.parseDouble(playerInfo[6]);
        int wickets = Integer.parseInt(playerInfo[7]);
        return new Player(name, team, role, matches, runs, average, strikeRate, wickets);
    }

}