public class Tank extends Enemy {

    private int cooldown = 0;

    public Tank(int hp, int atk, int def, int eva, int luck) {
        super(hp, atk, def, eva, luck);
    }

    @Override
    public void special(Character target) {

        int damage = this.attack * 4;

        target.currentHP -= damage;
        cooldown = 3;
    }

    @Override
    public void takeTurn(Character player) {

        if (cooldown > 0) {
            cooldown--;
            attack(player);
            return;
        }

        if (Math.random() < 0.3) {
            special(player);
        } else {
            attack(player);
        }
    }
}