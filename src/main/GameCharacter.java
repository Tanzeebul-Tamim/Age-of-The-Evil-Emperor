package main;

public abstract class GameCharacter {
    // Variables / Attributes all characters have
    public String name;
    public int maxHp, hp, xp;

    // Constructor for character
    public GameCharacter(String name, int maxHp, int xp) {
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
        this.hp = maxHp;
    }

    // Get player name
    public String getName() {
        return name;
    }

    // Methods every character has
    public abstract int attack();

    public abstract int defend();
}
