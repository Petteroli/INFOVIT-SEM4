package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.TetrisModel.GameState;

public interface ControllableTetrisModel {
    
    public boolean moveTetromino(int deltaRow, int deltaCol);

    public boolean rotateTetromino();

    public boolean dropTetromino();

    public void pasteToBottom();

    public GameState getGameState();

    public int milliseconds();

    public void clockTick();


}
