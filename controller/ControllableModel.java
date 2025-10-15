package no.uib.inf101.rpg.controller;

/**
 * The ControllableModel interface defines the methods required for a model that can be controlled in the game. 
 * It provides functionality for player movement, game state updates (via clock ticks), battle actions (such as attacking and running away), and checking the battle state.
 */
public interface ControllableModel {
    
    /**
     * Moves the player by the specified number of rows and columns.
     * 
     * @param deltaRows The number of rows to move the player (positive for downward movement, negative for upward).
     * @param deltaCols The number of columns to move the player (positive for rightward movement, negative for leftward).
     * @return True if the move was successful, false otherwise.
     */
    public boolean movePlayer(int deltaRows, int deltaCols);

    /**
     * Returns the number of milliseconds between each game tick, typically used for controlling the frequency of updates in a timer.
     * 
     * @return The time in milliseconds between clock ticks.
     */
    public int milliseconds();

    /**
     * Updates the game state at regular intervals, typically called by a timer.
     * This method should handle game logic such as enemy actions or other periodic updates.
     */
    public void clockTick();

    /**
     * Executes the player's attack action in battle. This method is expected to handle the mechanics of attacking, such as calculating damage and updating battle status.
     */
    public void attack();
    
    /**
     * Executes the player's action to run away from the battle. 
     */
    public void runAway();

    /**
     * Checks whether the player is currently in a battle.
     * 
     * @return True if the player is in a battle, false otherwise.
     */
    public boolean inBattle();
}
