package no.uib.inf101.rpg.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.model.grid.GridDimension;

/**
 * A utility class for converting a `CellPosition` (row and column in a grid) to pixel coordinates
 * on a given `Rectangle2D` box. The conversion takes into account the grid dimensions and margins
 * between the cells to calculate the appropriate pixel position and size for each cell.
 */
public class CellPositionToPixelConverter {

    private final Rectangle2D box;
    private final GridDimension gd;
    private final double margin;

    /**
     * Constructs a new `CellPositionToPixelConverter` with the specified box, grid dimensions, and margin.
     *
     * @param box The `Rectangle2D` box that represents the area where the grid will be rendered.
     * @param gd The grid dimensions, used to determine the number of rows and columns in the grid.
     * @param margin The margin to be applied between cells.
     */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /**
     * Converts the given `CellPosition` (row and column) to pixel coordinates in the given box.
     * The size of each cell is calculated based on the box size and the grid dimensions, taking into account the margin between cells.
     *
     * @param cellPosition The position of the cell in the grid to convert.
     * @return A `Rectangle2D` representing the pixel bounds of the specified cell.
     */
    public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
        double cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
        double cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
        double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
        double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
        return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
    }

}
