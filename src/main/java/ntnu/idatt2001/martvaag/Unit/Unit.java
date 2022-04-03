package ntnu.idatt2001.martvaag.Unit;

/**
 * abstract class which represent a unit
 * @version 2022-04-03
 * @author martvaag
 */
public abstract class Unit {
    private String name;
    private int health, attack, armor;

    /**
     * constructor for a unit with relevant parameters
     * @param name a short descriptive name, for example "Swordsman" or "Archer"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor a defence-value, which protects the unit during an attack
     */
    public Unit(String name, int health, int attack, int armor) {
        if (health<0) throw new IllegalArgumentException("Unit's health cannot be below 0");
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    /**
     * attack which hurts the opponent
     * the result of the method will be the opponent´s health after the attack
     * @param opponent a unit "opponent"
     */
    public void attack(Unit opponent){
        opponent.health = opponent.health - (this.attack + this.getAttackBonus()) + (opponent.armor + opponent.getResistBonus());
    }

    /**
     * get the descriptive name of unit
     * @return name of unit
     */
    public String getName() {
        return name;
    }

    /**
     * get the health-value of the unit
     * @return health-value of unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * get the attack-value of the unit
     * @return attack-value of unit
     */
    public int getAttack() {
        return attack;
    }

    /**
     * get the defence-value of the unit
     * @return defence-value of unit
     */
    public int getArmor() {
        return armor;
    }

    /**
     * set a new health-value of the unit
     * @param health new health-value
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * checks if a unit´s health is above 0
     * @return {@code true} if the unit´s health equals to or is below 0, {@code false} if not
     */
    public boolean isDead(){
        return getHealth() <= 0;
    }

    /**
     * gives bonus when a unit attacks, which gets added to the attack-value
     * @return attack-bonus
     */
    public abstract int getAttackBonus();

    /**
     * gives bonus when a unit defends itself, which gets added to the defence-value
     * @return defence-value
     */
    public abstract int getResistBonus();

    /**
     * get a reasonable textual representation of a unit
     * @return textual representation of a unit
     */
    public String toString(){
        return "\n" + name + ", Health: " + this.health;
    }
}
