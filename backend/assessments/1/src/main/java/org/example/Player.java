package org.example;

public class Player {
    private String name;
    private String team;
    private String role;
    private int matches;
    private int runs;
    private double avg;
    private double sr;
    private int wickets;

    Player(String name, String team, String role, int matches, int runs, double avg, double sr, int wickets){
        this.name = name;
        this.team = team;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.avg = avg;
        this.sr = sr;
        this.wickets = wickets;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setTeam(String team){
        this.team = team;
    }

    public String getTeam(){
        return team;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getMatches() {
        return matches;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getRuns() {
        return runs;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getAvg() {
        return avg;
    }

    public void setSr(double sr) {
        this.sr = sr;
    }

    public double getSr() {
        return sr;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getWickets() {
        return wickets;
    }

    public String toString(){
        return "Name : " + name + ", Team : " + team + ", Role : " + role + ", Matches : " + matches +
                ", Runs : " + runs + ", Avg : " + avg + ", Strike Rate : " + sr + ", Wickets : " + wickets;
    }
}
