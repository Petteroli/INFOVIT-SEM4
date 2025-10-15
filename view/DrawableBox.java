package no.uib.inf101.rpg.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import no.uib.inf101.rpg.model.grid.CellPosition;

/**
 * The DrawableBox class implements the Drawable interface and represents a simple colored box that can be drawn at a specified position on the screen. The box is drawn as a filled rectangle with a user-defined color.
 */
public class DrawableBox implements Drawable {
    private Color color;

    /**
     * Constructs a DrawableBox with a specified color.
     *
     * @param color The color of the box.
     */
    public DrawableBox(Color color) {
        this.color = color;
    }
    
    /**
     * Draws the box at a specific grid cell position, using the provided graphics context and a converter to map the grid position to pixel coordinates.
     *
     * @param g2 The Graphics2D object used to render the box.
     * @param pos The cell position of the box in the grid.
     * @param converterModel The converter used to translate the grid cell position into pixel coordinates.
     */
    @Override
    public void drawSelf(Graphics2D g2, CellPosition pos, CellPositionToPixelConverter converterModel) {
        Rectangle2D tile = converterModel.getBoundsForCell(pos);
        g2.setColor(color);
        g2.fill(tile);

    }


    /**
     * This method does not implement any drawing logic, as it is not used for drawing a box at specific pixel coordinates. It is provided to fulfill the Drawable interface.
     *
     * @param g2 The Graphics2D object used to render the box (not used in this method).
     * @param posx The x-coordinate in pixels (not used in this method).
     * @param posy The y-coordinate in pixels (not used in this method).
     */
    @Override
    public void drawSelf(Graphics2D g2, double posx, double posy) {
        // Method intentionally left empty as it is not used in this class.
    }
}
