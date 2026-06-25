public class Rogue extends Enemy {

    public Rogue(int hp, int atk, int def, int eva, int luck) {
        super(hp, atk, def, eva, luck);
    }

    @Override
    public void special(Character target) {

        int damage = this.attack;

        // ignores block
        target.currentHP -= damage;
    }

    @Override
    public void takeTurn(Character player) {

        if (Math.random() < 0.5) {
            attack(player);
        } else {
            special(player);
        }
    }
}