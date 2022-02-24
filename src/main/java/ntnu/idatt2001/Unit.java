package ntnu.idatt2001;

public abstract class Unit {
    private String name;
    private int health, attack, armor;

    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    public void attack(Unit opponent){
        opponent.health = opponent.health - (this.attack + this.getAttackbonus()) + (opponent.armor + opponent.getResistBonus());
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract int getAttackbonus();

    public abstract int getResistBonus();

    public String toString(){
        return "\n" + name + ", Health: " + this.health + ", Attack: " + this.attack + ", Armor: " + this.armor;
    }
}
