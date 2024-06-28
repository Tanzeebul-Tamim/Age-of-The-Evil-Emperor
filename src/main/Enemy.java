package main;

public class Enemy extends GameCharacter {
    private int playerXp;

    // Enemy specific constructor
    public Enemy(String name, int playerXp) {
        super(name, calculateMaxHp(calculateXp(playerXp)), calculateXp(playerXp));
        this.playerXp = playerXp;
    }

    // Calculate maxHp based on playerXp
    private static int calculateMaxHp(int xp) {
        return (int) (Math.random() * xp + xp / 3 + 5);
    }

    // Calculate xp based on playerXp
    private static int calculateXp(int playerXp) {
        return (int) (Math.random() * (playerXp / 4 + 2) + 1);
    }

    // Enemy specific methods
    @Override
    public int attack() {
        return (int) (Math.random() * (playerXp / 4 + 1) + xp / 4 + 3);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (playerXp / 4 + 1) + xp / 4 + 3);
    }
}