package no.uib.inf101.rpg.view;

import no.uib.inf101.rpg.model.entity.Enemy;
import no.uib.inf101.rpg.model.entity.Player;
import no.uib.inf101.rpg.model.grid.GridCell;
import no.uib.inf101.rpg.model.grid.GridDimension;

/**
 * Interface representing a viewable game model in the RPG.
 * This interface exposes methods to retrieve the current game state, including the dimensions of the grid, the player, the enemies, and the game board.
 */
public interface ViewableGameModel {

    /**
     * Gets the dimensions of the game board, i.e., the number of rows and columns.
     * This is used for defining the grid that the game is played on.
     *
     * @return A {@link GridDimension} object representing the board's dimensions.
     */
    GridDimension getDimension();
    
    /**
     * Gets the current camera dimensions, which may be different from the full game board dimensions.
     * This is used to represent the portion of the game world currently visible to the player.
     *
     * @return A {@link GridDimension} object representing the camera's view dimensions.
     */
    GridDimension getCamera();

    /**
     * Gets an iterable of all the tiles on the board, including their positions and corresponding values.
     * Each tile is represented by a {@link GridCell} that contains the position and value of the tile.
     *
     * @return An iterable collection of {@link GridCell} objects containing positions and entity values.
     */
    Iterable<GridCell<ViewableEntity>> getTilesOnBoard();

    /**
     * Gets the player in the game.
     * The player is the central entity controlled by the user and interacts with the game world.
     *
     * @return The {@link Player} object representing the player.
     */
    Player getPlayer();

    /**
     * Checks whether the game is currently in a battle state.
     * This method is used to determine if the player is engaged in combat with an enemy.
     *
     * @return A boolean indicating whether the player is in battle.
     */
    boolean inBattle();

    /**
     * Gets the current enemy that the player is facing in battle.
     * This will return the enemy entity that the player is currently interacting with.
     *
     * @return The {@link Enemy} object representing the current enemy.
     */
    Enemy getCurrentEnemy();

    /**
     * Checks if the player has won the maze.
     * This method is used to determine if the player has successfully completed the game or a specific level.
     *
     * @return A boolean indicating whether the player has won the maze.
     */
    boolean winMaze();
}
