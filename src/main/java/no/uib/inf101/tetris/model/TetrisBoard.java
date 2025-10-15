package no.uib.inf101.tetris.model;

import java.util.ArrayList;
import java.util.Collections;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

public class TetrisBoard extends Grid {

    public TetrisBoard(int rows, int cols, Character defaulValue) {
        super(rows, cols, defaulValue);
    }

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');
    }

    public void pasteTromino(Tetromino tetromino) {
        for (GridCell cell : tetromino) {
            set(cell.pos(), cell.symbol());
        }
    }

    public String prettyString() {
        String pString = new String();
        for (GridCell gridcell : this) {
            CellPosition cellPosition = gridcell.pos();
            if (cellPosition.col() == 0 && cellPosition.row() != 0) {
                pString += "\n";
            }
            pString += gridcell.symbol().toString();
        }
        return pString;
    }

    public int clearFilledRows() {
        int filledRows = 0;
        for (int row = rows() - 1; row >= 0; row--) {
            if (isRowFilled(row)) {
                removeRow(row);
                filledRows++;
            }
        }
        fillTopRowWithEmpty();
        return filledRows;

    }

    public boolean isRowFilled(int row) {
        if (row < 0 || row >= rows() || getRow(row).isEmpty()) {
            return false;
        }

        for (Character cell : getRow(row)) {
            if (cell == '-') {
                return false;
            }
        }
        return true;

    }

    public int removeRow(int row) {
        if (row < 0 || row >= rows() || !isRowFilled(row)) {
            return 0;
        }

        for (int j = 0; j < cols(); j++) {
            set(new CellPosition(row, j), '-');
        }
        for (int k = row - 1; k >= 0; k--) {
            setRow(k+1, getRow(k));
        }
        return 1;
    }

    public void copyRowTo(int originalRow, int targetRow) {
        for (int i = 0; i < cols(); i++) {
            set(new CellPosition(targetRow, i), get(new CellPosition(originalRow, i)));
        }
    }

    public void fillTopRowWithEmpty() {
        ArrayList<Character> topEmpty = new ArrayList<>(Collections.nCopies(cols(), '-'));
        setRow(0, topEmpty);

    }

}
