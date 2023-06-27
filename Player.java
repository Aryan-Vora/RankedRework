public class Player {
    private int elo;
    private String name;
    private int prevElo;
    private double multiplier;
    public Player(String name, int elo) {
        this.elo = elo;
        this.name = name;
    }
   
    public void addGame(int obj, int kills, int enemyElo, int teamElo, int teamScore, int enemyScore, int teamKills,
        int enemyKills, int teamObj, int enemyObj) {
        multiplier = 1.0;
        int eloChange = 25;

        // Calculating game impact variables
        double killContribution = ((double) kills) / teamKills;
        double objContribution = ((double) obj) / teamObj;

        // getting game impact mulitplier
        multiplier = multiplier * ((killContribution * 2) + (objContribution * 2));
        multiplier = multiplier * (double) enemyElo / teamElo;
        
        // changing elo
        if (teamScore > enemyScore) {
            this.prevElo = this.elo;
            this.elo += Math.round((eloChange * multiplier));
        } else {
            this.prevElo = this.elo;
            this.elo += Math.round((eloChange * multiplier));
            this.elo -= 50;
        }
    }
    public double getMultiplier(){
        return this.multiplier;
    }
    public int getElo() {
        return this.elo;
    }public String getName() {
        return this.name;
    }
    public int getPreviousElo(){
        return prevElo;
    }

}