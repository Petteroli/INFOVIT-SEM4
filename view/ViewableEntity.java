package no.uib.inf101.rpg.view;

import no.uib.inf101.rpg.model.grid.CellPosition;

/**
 * Interface representing an entity that can be viewed in the game world.
 * A ViewableEntity has visual representation, position, health, and a drawable associated with it.
 */
public interface ViewableEntity {

    /**
     * Gets the visual representation of the entity as a character.
     * This character is used to display the entity in the game world.
     *
     * @return The character representing the entity's visual appearance.
     */
    char getVisual();

    /**
     * Gets the position of the entity in the game world.
     * The position is represented by a {@link CellPosition} indicating the entity's row and column.
     *
     * @return The {@link CellPosition} of the entity.
     */
    CellPosition getPosition();

    /**
     * Gets the drawable associated with the entity.
     * The drawable is responsible for rendering the entity's graphical representation.
     *
     * @return The {@link Drawable} associated with the entity.
     */
    Drawable getDrawable();

    /**
     * Gets the name of the entity.
     * This can be used for identifying or labeling the entity in the game.
     *
     * @return The name of the entity.
     */
    String getName();

    /**
     * Gets the health of the entity.
     * This is used to track the entity's vitality and can influence gameplay actions (e.g., battle).
     *
     * @return The health value of the entity.
     */
    int getHealth();
}
