package no.uib.inf101.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import no.uib.inf101.tetris.model.TetrisModel.GameState;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisController implements KeyListener {

    ControllableTetrisModel controllableTetrisModel;
    TetrisView tetrisView;
    Timer timer;

    public TetrisController(TetrisView tetrisView, ControllableTetrisModel controllableTetrisModel) {
        this.tetrisView = tetrisView;
        this.controllableTetrisModel = controllableTetrisModel;

        this.timer = new Timer(controllableTetrisModel.milliseconds(), this::clockTick);
        this.timer.start(); 

        tetrisView.setFocusable(true);
        tetrisView.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME) 
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                controllableTetrisModel.moveTetromino(0, -1);
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controllableTetrisModel.moveTetromino(0, 1);
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (!controllableTetrisModel.moveTetromino(1, 0)){
                    controllableTetrisModel.pasteToBottom();
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                controllableTetrisModel.rotateTetromino();
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                controllableTetrisModel.dropTetromino();
            }
            tetrisView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void clockTick(ActionEvent event) {
        if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            controllableTetrisModel.clockTick(); 
            tetrisView.repaint(); 
        }
    }



}
