package no.uib.inf101.rpg.model;

import java.util.Iterator;

import no.uib.inf101.rpg.model.animation.Animation;
import no.uib.inf101.rpg.model.entity.Entity;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.model.grid.GridCell;
import no.uib.inf101.rpg.model.grid.GridDimension;
import no.uib.inf101.rpg.view.Drawable;
import no.uib.inf101.rpg.view.ViewableEntity;

/**
 * {@code WorldIterator} iterates over a portion of the {@link World},
 * typically based on the player's position and camera view.
 * 
 * It returns {@link GridCell} objects containing {@link ViewableEntity} instances,
 * filling in "out of bounds" positions with placeholder entities.
 */
public class WorldIterator implements Iterator<GridCell<ViewableEntity>> {

    private World world;
    private GridDimension camera;
    private int startRow, startCol;
    private int row, col;

    /**
     * Constructs a {@code WorldIterator} for a specific section of the world grid.
     *
     * @param world      the {@link World} to iterate over
     * @param playerpos  the player's {@link CellPosition}
     * @param camera     the camera view size ({@link GridDimension})
     * @param camerapos  the player's position within the camera view
     */
    public WorldIterator(World world, CellPosition playerpos, GridDimension camera, CellPosition camerapos) {
        this.world = world;
        this.camera = camera;
        this.startRow = playerpos.row() - camera.rows() + camerapos.row() + 1;
        this.startCol = playerpos.col() - camera.cols() + camerapos.col() + 1;

        this.row = 0;
        this.col = 0;
    }

    /**
     * Creates a placeholder {@link Entity} when a requested position is out of bounds.
     *
     * @param pos the {@link CellPosition} that was out of bounds
     * @return a dummy {@link Entity} representing an empty or invalid space
     */
    private Entity getOutOfBounds(CellPosition pos) {
        Animation<Drawable> nothing = null;
        return new Entity(pos, nothing, '-', null);
    }

    /**
     * Checks if there are more grid cells to iterate over.
     *
     * @return {@code true} if more cells are available, otherwise {@code false}
     */
    @Override
    public boolean hasNext() {
        if (row >= camera.rows()) {
            return false;
        }
        return true;
    }

    /**
     * Returns the next {@link GridCell} in the camera view.
     * 
     * If the position is outside the world boundaries, returns a placeholder entity.
     *
     * @return the next {@link GridCell} containing a {@link ViewableEntity}
     */
    @Override
    public GridCell<ViewableEntity> next() {
        CellPosition pos = new CellPosition(startRow + row, startCol + col);
        if (world.positionIsOnWorld(pos)) {
            GridCell<ViewableEntity> cell = new GridCell<>(new CellPosition(row, col), world.get(pos));
            increment();
            return cell;
        } else {
            GridCell<ViewableEntity> cell = new GridCell<>(new CellPosition(row, col), getOutOfBounds(pos));
            increment();
            return cell;
        }
    }

    /**
     * Advances the iterator to the next cell position.
     * Moves to the next row when the end of a row is reached.
     */
    private void increment() {
        col++;
        if (col >= camera.cols()) {
            col = 0;
            row++;
        }
    }
}
