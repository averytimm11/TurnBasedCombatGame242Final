public abstract class Enemy extends Character {

    public Enemy(int maxHP, int attack, int defense, int evasion, int luck) {
        super(maxHP, attack, defense, evasion, luck);
    }

    public abstract void takeTurn(Character player);
}