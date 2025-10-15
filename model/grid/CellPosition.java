package no.uib.inf101.rpg.model.grid;

/**
 * A record representing a position in a grid, defined by a row and column.
 * This class provides methods to access the row and column values, as well as a utility method to translate (move) the position by a specified amount.
 */
public record CellPosition(int row, int col) {
    
    /**
     * Translates the position by the specified amounts in the row and column directions.
     * This method returns a new CellPosition that represents the translated position.
     * 
     * @param deltaRow The amount to move in the row direction.
     * @param deltaCol The amount to move in the column direction.
     * @return A new CellPosition with the translated position.
     */
    public CellPosition translateBy(int deltaRow, int deltaCol) {
        return new CellPosition(this.row + deltaRow, this.col + deltaCol);
    }
}