public class Fighter extends Enemy {

    public Fighter(int hp, int atk, int def, int eva, int luck) {
        super(hp, atk, def, eva, luck);
    }

    @Override
    public void special(Character target) {
        int damage = this.attack * 2;
        target.currentHP -= damage;
    }

    @Override
    public void takeTurn(Character player) {

        if (Math.random() < 0.6) {
            attack(player);
        } else {
            special(player);
        }
    }
}