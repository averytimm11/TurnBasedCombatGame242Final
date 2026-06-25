public abstract class Character {

    protected int maxHP;
    protected int currentHP;
    protected int attack;
    protected int defense;
    protected int evasion;
    protected int luck;

    protected boolean blocking;

    public Character(int maxHP, int attack, int defense, int evasion, int luck) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.evasion = evasion;
        this.luck = luck;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public void resetHP() {
        currentHP = maxHP;
    }

    public void block() {
        blocking = true;
    }

    public void endTurnReset() {
        blocking = false;
    }

    public abstract void special(Character target);

    public void attack(Character target) {

        int hitChance = 100 - target.evasion + this.evasion;

        if (Math.random() * 100 > hitChance) {
            System.out.println("Miss!");
            return;
        }

        int damage = this.attack - target.defense;
        if (damage < 1) damage = 1;

        if (Math.random() * 100 < this.luck) {
            damage *= 2;
            System.out.println("Critical hit!");
        }

        if (target.blocking) {
            damage *= 0.75;
        }

        target.currentHP -= damage;

        if (target.currentHP < 0) target.currentHP = 0;
    }
}