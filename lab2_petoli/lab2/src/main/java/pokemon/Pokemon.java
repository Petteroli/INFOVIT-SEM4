package pokemon;

import java.util.Random;

public class Pokemon {
    ////// Oppgave 1a
    // Create field variables here:
    String name;
    int healthPoints;
    int maxHealthPoints;
    int strength;


    ///// Oppgave 1b
    // Create a constructor here:
    Pokemon(String name, int healthPoints, int strength) {
        this.maxHealthPoints = healthPoints;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.name = name;

    }
        

    ///// Oppgave 2

    /**
     * Get name of the pokémon
     *
     * @return name of pokémon
     */
    String getName() {
        return this.name;
    }

    /**
     * Get strength of the pokémon
     *
     * @return strength of pokémon
     */
    int getStrength() {
        return this.strength;
    }

    /**
     * Get current health points of pokémon
     *
     * @return current HP of pokémon
     */
    int getCurrentHP() {
        return this.healthPoints;
    }

    /**
     * Get maximum health points of pokémon
     *
     * @return max HP of pokémon
     */
    int getMaxHP() {
        return this.maxHealthPoints;
    }

    /**
     * Check if the pokémon is alive.
     * A pokemon is alive if current HP is higher than 0
     *
     * @return true if current HP > 0, false if not
     */
    boolean isAlive() {
        if (healthPoints > 0) {
            return true;
        }
        else {
            return false;
        }
    }


    ///// Oppgave 4

    /**
     * The Pokémon takes a given amount of damage. This method reduces the number of
     * health points the pokémon has by <code>damageTaken</code>.
     * If <code>damageTaken</code> is higher than the number of current
     * health points then set current HP to 0.
     * <p>
     * It should not be possible to deal negative damage, i.e. increase the number of health points.
     * <p>
     * The method should print how much HP the Pokémon is left with.
     *
     * @param damageTaken the amount to reduce the Pokémon's HP by
     */
    void takeDamage(int damageTaken) {
        if (damageTaken > 0) {
            if (damageTaken >= healthPoints) {
                healthPoints = 0;
            }
            else{
                healthPoints -= damageTaken;
            }
        }
        System.out.println(name+" takes "+damageTaken+" and is left with " + healthPoints+"/"+maxHealthPoints+" HP");
    }

    ///// Oppgave 5

    /**
     * Attack another pokémon. The method conducts an attack by <code>this</code>
     * on <code>target</code>. Calculate the damage using the pokémons strength
     * and a random element. Reduce <code>target</code>s health.
     * <p>
     * If <code>target</code> has 0 HP then print that it was defeated.
     *
     * @param target pokémon that is being attacked
     */
    void attack(Pokemon target) {
        Random rand = new Random();
        int damageInflincted = (int) (rand.nextInt(this.strength+1));
        System.out.println(this.name+" attacks "+target);
        target.takeDamage(damageInflincted);
        if (target.healthPoints == 0) {
            System.out.println(target+" is defeated by "+this.name);
        }


    }

    ///// Oppgave 3
    @Override
    public String toString() {
        return ("Mew HP: "+"("+healthPoints+"/"+maxHealthPoints+")"+" STR: "+strength);
    }

}
