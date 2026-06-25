public class Player extends Character {

    private int specialCooldown = 0;

    public Player(int maxHP, int attack, int defense, int evasion, int luck) {
        super(maxHP, attack, defense, evasion, luck);
    }

    @Override
    public void special(Character target) {

        if (specialCooldown > 0) {
            System.out.println("Special on cooldown!");
            return;
        }

        int damage = this.attack * 2;

        target.currentHP -= damage;
        if (target.currentHP < 0) target.currentHP = 0;

        specialCooldown = 2;
    }

    public void reduceCooldown() {
        if (specialCooldown > 0) specialCooldown--;
    }
}