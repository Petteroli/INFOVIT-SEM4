package no.uib.inf101.tetris.model;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    TetrisBoard board;
    TetrominoFactory tetrominoFactory;
    Tetromino fallingTetromino;
    GameState gameState;
    TetrisSong music;

    public TetrisModel(TetrominoFactory tetrominoFactory) {
        this.music = new TetrisSong();
        this.gameState = GameState.ACTIVE_GAME;
        board = new TetrisBoard(15,10,'-');
        this.tetrominoFactory = new RandomTetrominoFactory();
        fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board);
        music.run();
    }

    @Override
    public Iterable<GridCell> getTetrominoCells() {
        List<GridCell> cells = new ArrayList<>();
        fallingTetromino.iterator().forEachRemaining(cells::add);
        return cells;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell> getTilesOnBoard() {
        return board;
    }
    
    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino candidate = fallingTetromino.shiftedBy(deltaRow, deltaCol);
        if (isPositionLegal(candidate)) {
            fallingTetromino = candidate;
            return true;        
        }

        return false;
    }

    public boolean isPositionLegal(Tetromino tetromino) {
        for (GridCell cell : tetromino) {
            CellPosition pos = cell.pos();

            if (board.positionIsOnGrid(pos) == false) {
                return false;
            }
            
            if (board.get(pos) != '-') {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean rotateTetromino() {
        Tetromino candidate = fallingTetromino.rotatedTetrominoCopy();
        if (isPositionLegal(candidate)) {
            fallingTetromino = candidate;
            return true;
        }
        return false;
    }

    @Override
    public boolean dropTetromino() {
        while (moveTetromino(1, 0));
        pasteToBottom();
        return true;
    }

    public void nextTetromino() {
        fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board);
        if (isPositionLegal(fallingTetromino) == false) {
            this.gameState = GameState.GAME_OVER;
        }
    }

    public void pasteToBottom() {
        board.pasteTromino(fallingTetromino);
        board.clearFilledRows();
        nextTetromino();
    }

    public enum GameState {
        ACTIVE_GAME, GAME_OVER
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public int milliseconds() {
        return 1000;
    }

    @Override
    public void clockTick() {
        if (!moveTetromino(1, 0)) {
            pasteToBottom();
        }
    }
}
