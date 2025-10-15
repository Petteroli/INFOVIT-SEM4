package no.uib.inf101.rpg.model.grid;

/**
 * A generic record class that represents a single cell in a grid. 
 * It stores the position of the cell and a value associated with the cell.
 * 
 * @param <T> The type of the value stored in the cell.
 */
public record GridCell<T>(CellPosition pos, T value) {}


