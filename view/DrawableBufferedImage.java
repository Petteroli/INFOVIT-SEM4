package no.uib.inf101.rpg.view;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import no.uib.inf101.rpg.model.grid.CellPosition;

/**
 * The DrawableBufferedImage class implements the Drawable interface and represents a drawable image that can be rendered onto a Graphics2D context. 
 * The image is drawn either at a specified grid position or at specific pixel coordinates.
 */
public class DrawableBufferedImage implements Drawable {

    BufferedImage bufferedImage;

    /**
     * Constructs a DrawableBufferedImage with the specified BufferedImage.
     *
     * @param bufferedImage The image to be drawn.
     */
    public DrawableBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
    
    /**
     * Draws the buffered image at the given grid position using the provided graphics context and a converter to map the grid position to pixel coordinates.
     *
     * @param g2 The Graphics2D object used to render the image.
     * @param pos The cell position of the image in the grid.
     * @param converterModel The converter used to translate the grid cell position into pixel coordinates.
     */
    @Override
    public void drawSelf(Graphics2D g2, CellPosition pos, CellPositionToPixelConverter converterModel) {
        Rectangle2D tile = converterModel.getBoundsForCell(pos);

        double centerX = tile.getCenterX();
        double centerY = tile.getCenterY();

        int imgWidth = bufferedImage.getWidth();
        int imgHeight = bufferedImage.getHeight();

        int drawX = (int) (centerX - imgWidth / 2.0);
        int drawY = (int) (centerY - imgHeight / 2.0);

        g2.drawImage(bufferedImage, drawX, drawY, null);
    }

    /**
     * Draws the buffered image at the specified pixel coordinates, with the image centered
     * on those coordinates.
     *
     * @param g2 The Graphics2D object used to render the image.
     * @param posx The x-coordinate in pixels where the image should be centered.
     * @param posy The y-coordinate in pixels where the image should be centered.
     */
    @Override
    public void drawSelf(Graphics2D g2, double posx, double posy) {
        int imgWidth = bufferedImage.getWidth();
        int imgHeight = bufferedImage.getHeight();

        int drawX = (int) (posx - imgWidth / 2.0);
        int drawY = (int) (posy - imgHeight / 2.0);

        g2.drawImage(bufferedImage, drawX, drawY, null);
    }
}
