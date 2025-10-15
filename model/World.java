package no.uib.inf101.rpg.model;

import no.uib.inf101.rpg.model.entity.Entity;
import no.uib.inf101.rpg.model.entity.Player;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.model.grid.Grid;
import no.uib.inf101.rpg.model.grid.GridCell;
import no.uib.inf101.rpg.model.grid.GridDimension;
import no.uib.inf101.rpg.model.grid.IGrid;
import no.uib.inf101.rpg.view.ViewableEntity;

/**
 * The {@code World} class represents the game world containing entities and a player,
 * organized on a grid. It provides methods to interact with and modify the world state.
 */
public class World implements Iterable<GridCell<ViewableEntity>> {

    private static final Entity defaultValue = null;
    private final IGrid<Entity> grid;
    private Player player;
    
    /**
     * Constructs a new {@code World} with a grid of size 15x20.
     */
    public World() {
        grid = new Grid<Entity>(15, 20, defaultValue);
    }

    /**
     * Returns the dimensions of the world grid.
     *
     * @return the {@link GridDimension} representing the size of the world
     */
    public GridDimension getDimension() {
        return grid;
    }

    /**
     * Returns the current camera view of the world.
     * If the player is not set, returns the entire world dimension.
     *
     * @return the {@link GridDimension} for the camera
     */
    public GridDimension getCamera() {
        if (player == null) {
            return getDimension();
        }
        return player.getCamera();
    }

    /**
     * Returns an iterable over all the grid cells containing entities.
     *
     * @return an {@link Iterable} of {@link GridCell} with {@link Entity} contents
     */
    public Iterable<GridCell<Entity>> getIterable() {
        return grid;
    }

    /**
     * Returns an iterator over the world cells, based on the camera view.
     *
     * @return a {@link WorldIterator} for iterating over visible cells
     */
    @Override
    public WorldIterator iterator() {
        if (player == null) {
            return new WorldIterator(this, new CellPosition(0, 0), grid, new CellPosition(grid.rows(), grid.cols()));
        } else {
            return new WorldIterator(this, player.getPosition(), player.getCamera(), player.getCameraPosition());
        }
    }

    /**
     * Sets an entity at the given position in the world grid.
     * Updates the entity's internal position to match.
     *
     * @param pos    the {@link CellPosition} where the entity should be placed
     * @param entity the {@link Entity} to place (can be {@code null})
     */
    public void set(CellPosition pos, Entity entity) {
        if (entity != null) {
            entity.setPosition(pos);
        }
        grid.set(pos, entity);
    }

    /**
     * Sets an entity in the world grid based on the entity's internal position.
     *
     * @param entity the {@link Entity} to place
     */
    public void set(Entity entity) {
        grid.set(entity.getPosition(), entity);
    }

    /**
     * Gets the entity at the given position.
     *
     * @param pos the {@link CellPosition} to check
     * @return the {@link Entity} at that position, or {@code null} if none
     */
    public Entity get(CellPosition pos) {
        return grid.get(pos);
    }

    /**
     * Checks if the given position is inside the world grid boundaries.
     *
     * @param pos the {@link CellPosition} to check
     * @return {@code true} if position is within bounds, {@code false} otherwise
     */
    public boolean positionIsOnWorld(CellPosition pos) {
        return grid.positionIsOnGrid(pos);
    }

    /**
     * Checks if the position is legal for placement or movement.
     * Currently checks only that it is within grid boundaries.
     *
     * @param pos the {@link CellPosition} to check
     * @return {@code true} if legal, {@code false} otherwise
     */
    public boolean isPositionLegal(CellPosition pos) {
        if (!grid.positionIsOnGrid(pos)) {
            return false;
        }
        return true;
    }

    /**
     * Sets the player in the world.
     *
     * @param newPlayer the {@link Player} to assign
     */
    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    /**
     * Removes any entity at the given position (sets it to {@code null}).
     *
     * @param pos the {@link CellPosition} to clear
     */
    public void remove(CellPosition pos) {
        set(pos, null);
    }
}
