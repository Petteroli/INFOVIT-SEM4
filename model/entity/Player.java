package no.uib.inf101.rpg.model.entity;

import java.util.HashMap;

import no.uib.inf101.rpg.model.animation.Animation;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.model.grid.GridDimension;
import no.uib.inf101.rpg.view.Drawable;

/**
 * Represents the player entity in the game. The player has position, health, 
 * a camera for the game view, and the ability to move in different directions.
 * This class extends the Entity class and provides additional player-specific functionality.
 */
public class Player extends Entity {
    
    private CellPosition position;
    private int health;
    public Direction facingDirection;
    private Camera camera;

    /**
     * Constructs a new Player with specified position, animation, health, and animation map.
     * Also initializes the player's camera.
     *
     * @param position      The position of the player in the game world.
     * @param animation     The animation to be applied to the player.
     * @param health        The health of the player.
     * @param animationmap  A map of animations associated with different characters.
     */
    public Player(CellPosition position, Animation<Drawable> animation, int health, HashMap<Character, Animation<Drawable>> animationmap) {
        super(animation, 'I', EntityType.PLAYER, animationmap);
        this.position = position;
        this.health = health;

        this.camera = new Camera(new CellPosition(2, 2), 5, 5);
    }

    /**
     * Gets the current position of the player.
     *
     * @return The current position of the player.
     */
    public CellPosition getPosition() {
        return position;
    }

    /**
     * Gets the position of the camera associated with the player.
     *
     * @return The position of the camera.
     */
    public CellPosition getCameraPosition() {
        return camera.getPosition();
    }

    /**
     * Gets the current health of the player.
     *
     * @return The health of the player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets a new position for the player.
     *
     * @param pos The new position of the player.
     */
    public void setPosition(CellPosition pos) {
        position = pos;
    }

    /**
     * Sets a new health value for the player.
     *
     * @param newHealth The new health value for the player.
     */
    public void setPlayerHealth(int newHealth) {
        health = newHealth;
    }

    /**
     * Gets the camera associated with the player.
     *
     * @return The camera of the player.
     */
    public GridDimension getCamera() {
        return camera;
    }

    /**
     * Sets a new camera for the player.
     *
     * @param cam The new camera to be set for the player.
     */
    public void setCamera(Camera cam) {
        this.camera = cam;
    }

    /**
     * Reduces the player's health by the specified amount, ensuring that health does not go below 0.
     *
     * @param amount The amount of damage to apply to the player's health.
     */
    public void takeDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }

    /**
     * Moves the player by the specified deltas in the x and y directions. 
     * The player's facing direction is updated based on the movement.
     *
     * @param dx The change in the x-direction (horizontal movement).
     * @param dy The change in the y-direction (vertical movement).
     */
    public void moveBy(int dx, int dy) {
        CellPosition currentPos = getPosition();
        
        if (dx > 0) {
            facingDirection = Direction.RIGHT;
            setPosition(new CellPosition(currentPos.row() + dx, currentPos.col()));
            setVisual('R');
        } else if (dx < 0) {
            facingDirection = Direction.LEFT;
            setPosition(new CellPosition(currentPos.row() + dx, currentPos.col()));
            setVisual('L');
        } else if (dy > 0) {
            facingDirection = Direction.DOWN;
            setPosition(new CellPosition(currentPos.row(), currentPos.col() + dy));
            setVisual('D');
        } else if (dy < 0) {
            facingDirection = Direction.UP;
            setPosition(new CellPosition(currentPos.row(), currentPos.col() + dy));
            setVisual('U');
        }

        setAnimation(animationmap.get(getVisual()));
    }

    /**
     * Enum representing the possible directions the player can face or move.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
