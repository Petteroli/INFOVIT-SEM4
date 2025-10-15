package no.uib.inf101.rpg.model.entity;

import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.model.grid.GridDimension;

/**
 * Represents a camera in the game, which defines the visible area of the game world.
 * The camera has a position and dimensions (rows and columns), and it is responsible 
 * for determining which portion of the game world is displayed to the player.
 * This class implements the GridDimension interface to provide the grid dimensions for the camera's viewport.
 */
public class Camera implements GridDimension {

    private int rows, cols;
    private CellPosition cameraposition;


    /**
     * Constructs a new Camera with the specified position, number of rows, and number of columns.
     * The camera defines a viewport of the game world that the player can see.
     *
     * @param pos   The position of the camera in the game world.
     * @param rows  The number of rows in the camera's viewport.
     * @param cols  The number of columns in the camera's viewport.
     */
    public Camera(CellPosition pos, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cameraposition = pos;
    }

    /**
     * Gets the number of rows in the camera's viewport.
     *
     * @return The number of rows in the camera's viewport.
     */
    @Override
    public int rows() {
        return rows;
    }

    /**
     * Gets the number of columns in the camera's viewport.
     *
     * @return The number of columns in the camera's viewport.
     */
    @Override
    public int cols() {
        return cols;
    }
    
    /**
     * Gets the current position of the camera in the game world.
     *
     * @return The position of the camera as a CellPosition.
     */
    public CellPosition getPosition() {
        return cameraposition;
    }
}
