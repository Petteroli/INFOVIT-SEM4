package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class Tetromino implements Iterable<GridCell> {
    private char symbol;
    private boolean[][] shape;
    private CellPosition cellPosition;

    private Tetromino(char symbol, boolean[][] shape, CellPosition cellPosition) {
        this.symbol = symbol;
        this.shape = shape;
        this.cellPosition = cellPosition;
    }

    public static Tetromino newTetromino(char symbol) {
        if (symbol == 'L') {
            return new Tetromino(symbol, new boolean[][] {{false, false, false}, {true, true, true}, {true, false, false}}, new CellPosition(0, 0));
        }
        if (symbol == 'J') {
            return new Tetromino(symbol, new boolean[][] {{false, false, false}, {true, true, true}, {false, false, true}}, new CellPosition(0, 0));
        }
        if (symbol == 'S') {
            return new Tetromino(symbol, new boolean[][] {{false, false, false}, {false, true, true}, {true, true, false}}, new CellPosition(0, 0));
        }
        if (symbol == 'Z') {
            return new Tetromino(symbol, new boolean[][] {{false, false, false}, {true, true, false}, {false, true, true}}, new CellPosition(0, 0));
        }
        if (symbol == 'T') {
            return new Tetromino(symbol, new boolean[][] {{false, false, false}, {true, true, true}, {false, true, false}}, new CellPosition(0, 0));
        }
        if (symbol == 'I') {
            return new Tetromino(symbol, new boolean[][] {{false, false, false, false}, {true, true, true, true}, {false, false, false, false}, {false, false, false, false}}, new CellPosition(0, 0));
        }
        if (symbol == 'O') {
            return new Tetromino(symbol, new boolean[][] {{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}}, new CellPosition(0, 0));
        }

        throw new IllegalArgumentException("Invalid Tetromino");
    }

    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        return new Tetromino(symbol, shape.clone(), new CellPosition(cellPosition.row() + deltaRow, cellPosition.col() + deltaCol));
    }  

    public Tetromino shiftedToTopCenterOf(GridDimension grid) {
        return new Tetromino(symbol, shape, new CellPosition(-1, grid.cols() / 2 ));
    }

    public int rows() {
        return shape.length;
    }

    public int cols() {
        if (shape.length != 0) {
            return shape[0].length;
        }
        return 0;
    }

    public Iterator<GridCell> iterator() {
        List<GridCell> gridCells = new ArrayList<>();
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                if (shape[i][j]) {
                    CellPosition pos = new CellPosition(cellPosition.row() + i, cellPosition.col() + j);
                    gridCells.add(new GridCell(pos, symbol));
                }
            }    
        }    
        return gridCells.iterator();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Tetromino) {
            Tetromino compare = (Tetromino) object;
            return Arrays.deepEquals(this.shape, compare.shape) && cellPosition.equals(compare.cellPosition);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int deepHash = Arrays.deepHashCode(shape);
        return Objects.hash(shape, cellPosition, deepHash);
    } 

    public Tetromino rotatedTetrominoCopy() {
        boolean[][] newShape = new boolean[rows()][cols()];

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                newShape[i][j] = shape[j][rows() - i - 1];
            }
        }
        return new Tetromino(symbol, newShape, new CellPosition(cellPosition.row(), cellPosition.col()));
        
    }
}
