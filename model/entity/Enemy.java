package no.uib.inf101.rpg.model.entity;

import java.util.HashMap;

import no.uib.inf101.rpg.model.animation.Animation;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.view.Drawable;

/**
 * Represents an enemy entity in the game. The enemy has attributes such as name, health, 
 * attack, defense, and a set of animations. This class extends from the Entity class and 
 * provides methods for interaction with the enemy's stats and animations.
 */
public class Enemy extends Entity {

    private String name;
    private int attack;
    private int health;
    private HashMap<Character, Animation<Drawable>> animations;

    /**
     * Constructs a new Enemy entity with the specified attributes, including its name, 
     * position, health, visual character, attack value, and animation map.
     *
     * @param name           The name of the enemy.
     * @param position       The position of the enemy on the game grid.
     * @param health         The health of the enemy.
     * @param visual         The visual character representing the enemy.
     * @param attack         The attack value of the enemy.
     * @param mapAnimations  A map of animations associated with different characters.
     */
    public Enemy(String name, CellPosition position, int health, char visual, int attack, HashMap<Character, Animation<Drawable>> mapAnimations) {
        super(position, mapAnimations.get(visual), EntityType.ENEMY, mapAnimations);; 
        this.name = name;
        this.health = health;
        this.attack = attack;
    }
    
    /**
     * Gets the name of the enemy.
     *
     * @return The name of the enemy.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the current health of the enemy.
     *
     * @return The health of the enemy.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the attack value of the enemy.
     *
     * @return The attack value of the enemy.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Reduces the enemy's health by the specified amount, ensuring that health does not go below 0.
     *
     * @param amount The amount of damage to apply to the enemy's health.
     */
    public void takeDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }

    /**
     * Gets the map of animations associated with the enemy.
     *
     * @return The map of animations for the enemy.
     */
    public HashMap<Character, Animation<Drawable>> getAnimations() {
        return animations;
    }
}
