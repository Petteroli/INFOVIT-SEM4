package no.uib.inf101.rpg.model.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class representing a 2D grid of generic type elements. The grid has a specified number of rows and columns, 
 * and each cell in the grid can hold a value of type T. This class provides methods to access and modify the cells,
 * check if a position is within the grid, and iterate over the cells.
 * 
 * @param <T> The type of the value stored in each cell of the grid.
 */
public class Grid<T> implements IGrid<T> {

    private int rows;
    private int cols;

    private List<List<T>> cells;

    /**
     * Constructs a new grid with the specified number of rows and columns.
     * Each cell in the grid is initialized with the provided default value.
     * 
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     * @param defaultValue The default value to initialize each cell in the grid.
     */
    public Grid(int rows, int cols, T defaultValue) {
        this.rows = rows;
        this.cols = cols;

        cells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<T> innerList = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                innerList.add(defaultValue);
            }
            cells.add(innerList);
        }
    }

    /**
     * Returns the number of rows in the grid.
     * 
     * @return The number of rows in the grid.
     */
    @Override
    public int rows() {
        return this.rows;
    }


    /**
     * Returns the number of columns in the grid.
     * 
     * @return The number of columns in the grid.
     */
    @Override
    public int cols() {
        return this.cols;
    }

    /**
     * Returns an iterator for iterating over the grid's cells.
     * Each cell is represented by a {@link GridCell} containing the position and value of the cell.
     * 
     * @return An iterator over the grid's cells.
     */
    @Override
    public Iterator<GridCell<T>> iterator() {
        List<GridCell<T>> gridCells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CellPosition pos = new CellPosition(i, j);
                GridCell<T> cell = new GridCell<>(pos, get(pos));
                gridCells.add(cell);
            }
        }
        return gridCells.iterator();
    }

    /**
     * Sets the value at the specified position in the grid.
     * 
     * @param pos The position of the cell to modify.
     * @param symbol The value to set in the specified cell.
     * @throws IndexOutOfBoundsException If the position is outside the bounds of the grid.
     */
    @Override
    public void set(CellPosition pos, T symbol) {
        if (!positionIsOnGrid(pos))
            throw new IndexOutOfBoundsException("Given position is not within grid: (" + pos.row() + ", " + pos.col() + ")");

        int row = pos.row();
        int col = pos.col();
        cells.get(row).set(col, symbol);
    }

    /**
     * Gets the value at the specified position in the grid.
     * 
     * @param pos The position of the cell to retrieve.
     * @return The value stored at the specified position.
     * @throws IndexOutOfBoundsException If the position is outside the bounds of the grid.
     */
    @Override
    public T get(CellPosition pos) {
        if (!positionIsOnGrid(pos))
            throw new IndexOutOfBoundsException("Given position is not within grid: (" + pos.row() + ", " + pos.col() + ")");

        int row = pos.row();
        int col = pos.col();
        return cells.get(row).get(col);
    }


    /**
     * Checks whether the specified position is within the bounds of the grid.
     * 
     * @param pos The position to check.
     * @return true if the position is within the grid; false otherwise.
     */
    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        boolean isWithinRowBound = pos.row() >= 0 && pos.row() < rows;
        boolean isWithinColBound = pos.col() >= 0 && pos.col() < cols;

        return isWithinRowBound && isWithinColBound;
    }

    /**
     * Returns the grid itself, which can be used for iteration or other purposes.
     * 
     * @return This grid.
     */
    public Grid<T> getIterable() {
        return this;
    }


}
