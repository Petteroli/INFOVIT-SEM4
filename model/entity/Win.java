package no.uib.inf101.rpg.model.entity;

import java.util.HashMap;

import no.uib.inf101.rpg.model.animation.Animation;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.view.Drawable;

/**
 * Represents a "Win" entity in the game, typically marking the win condition. 
 * This entity extends from the Entity class and has a specific position on the game grid.
 */
public class Win extends Entity{

    private CellPosition position;

    /**
     * Constructs a new Win entity at the specified position, with the given animation, 
     * type, and animation map. The Win entity represents the win condition in the game.
     *
     * @param position      The position of the Win entity on the game grid.
     * @param animation     The animation to be applied to the Win entity.
     * @param type          The type of the entity (which is set to WIN).
     * @param animationmap  A map of animations associated with different characters.
     */
    public Win(CellPosition position, Animation<Drawable> animation, EntityType type, HashMap<Character, Animation<Drawable>> animationmap) {
        super(position, animation, EntityType.WIN);
        this.position = position;
    }
    
    /**
     * Gets the current position of the Win entity.
     *
     * @return The position of the Win entity.
     */
    public CellPosition getPosition() {
        return position;
    }





}
