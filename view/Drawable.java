package no.uib.inf101.rpg.view;

import java.awt.Graphics2D;

import no.uib.inf101.rpg.model.grid.CellPosition;

/**
 * The Drawable interface defines the methods for objects that can be drawn to the screen.
 * Implementing classes should provide the logic for rendering themselves on a graphical canvas at specific positions.
 */
public interface Drawable {
    
    /**
     * Draws the object at a specified cell position using a provided graphics context and a converter to translate the cell position into pixel coordinates.
     *
     * @param g2 The Graphics2D object used to render the object.
     * @param pos The cell position of the object in the grid.
     * @param converterModel The converter used to translate the grid cell position into pixel coordinates.
     */
    void drawSelf(Graphics2D g2, CellPosition pos, CellPositionToPixelConverter converterModel);

    /**
     * Draws the object at specified pixel coordinates on the screen using a provided graphics context.
     *
     * @param g2 The Graphics2D object used to render the object.
     * @param posx The x-coordinate (in pixels) where the object should be drawn.
     * @param posy The y-coordinate (in pixels) where the object should be drawn.
     */
    void drawSelf(Graphics2D g2, double posx, double posy);
}
