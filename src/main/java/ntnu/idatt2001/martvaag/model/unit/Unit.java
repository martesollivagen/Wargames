package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;

/**
 * abstract class which represent a unit
 *
 * @version 2022-05-22
 * @author martvaag
 */
public abstract class Unit{
    private final String name;
    private int health, attack, armor;

    /**
     * constructor for a unit with relevant parameters
     *
     * @param name   a short descriptive name, for example "Swordsman" or "Archer"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor  a defence-value, which protects the unit during an attack
     */
    public Unit(String name, int health, int attack, int armor) {
        if (health<0) throw new IllegalArgumentException("Unit's health cannot be below 0");
        if (name.isEmpty()) throw new IllegalArgumentException("Unit's name cannot be empty");
        if (attack<0) throw new IllegalArgumentException("Unit's attack cannot be below 0");
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    /**
     * attack which hurts the opponent
     * the result of the method will be the opponent's health after the attack
     *
     * @param opponent a unit "opponent"
     * @param terrain  terrain
     */
    public void attack(Unit opponent, Terrain terrain){
        opponent.health = opponent.health - (this.attack + this.getAttackBonus(terrain)) + (opponent.armor + opponent.getResistBonus(terrain));
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
        if (health<0) throw new IllegalArgumentException("Unit's health cannot be below 0");
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
     * @param terrain terrain
     * @return attack-bonus
     */
    public abstract int getAttackBonus(Terrain terrain);

    /**
     * gives bonus when a unit defends itself, which gets added to the defence-value
     * @param terrain terrain
     * @return defence-value
     */
    public abstract int getResistBonus(Terrain terrain);

    /**
     * adds health value to another unit
     *
     * @param unitOne unit one
     * @param unitTwo unit two
     */
    public abstract void heal(Unit unitOne, Unit unitTwo);

    /**
     * get a reasonable textual representation of a unit
     * @return textual representation of a unit
     */
    public String toString(){
        return getClass().getSimpleName() + "," + name + "," + this.health;
    }
}
