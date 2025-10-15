package no.uib.inf101.rpg.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import no.uib.inf101.rpg.view.SpriteView;

/**
 * The Controller class listens for keyboard input and updates the controllable model based on the user's actions. 
 * It also manages a timer to trigger periodic updates of the game state, including sprite rendering.
 * This class implements the KeyListener interface to respond to key events.
 */
public class Controller implements KeyListener {

    ControllableModel controllable;
    SpriteView spriteView;
    Timer timer;

    /**
     * Constructs a Controller with the specified controllable model and sprite view.
     * The controller starts a timer that periodically triggers clock ticks.
     *
     * @param controllable The model that can be controlled (e.g., a player).
     * @param spriteView   The view responsible for rendering the sprites.
     */
    public Controller(ControllableModel controllable, SpriteView spriteView) {
        this.controllable = controllable;
        this.spriteView = spriteView;

        this.timer = new Timer(controllable.milliseconds(), this::clockTick);
        this.timer.start(); 
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Called when a key is pressed. This method handles the player's movement and actions during battle based on the key pressed.
     * 
     * If the player is in battle:
     * - Pressing '1' triggers the attack action.
     * - Pressing '2' triggers the run-away action.
     * 
     * If not in battle:
     * - Arrow keys move the player in the corresponding direction (left, right, down, up).
     * 
     * After handling the key press, the sprite view is repainted.
     *
     * @param e The KeyEvent associated with the key pressed action.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (controllable.inBattle()) {
            if (e.getKeyCode() == KeyEvent.VK_1) {
                controllable.attack();
            } else if (e.getKeyCode() == KeyEvent.VK_2) {
                controllable.runAway();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                controllable.movePlayer(0, -1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controllable.movePlayer(0, 1);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                controllable.movePlayer(1, 0);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                controllable.movePlayer(-1, 0);
            }
        }
    
        spriteView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    /**
     * This method is called by the timer at regular intervals to update the controllable model.
     * It triggers the clock tick on the controllable model and repaints the sprite view.
     *
     * @param event The ActionEvent triggered by the timer.
     */
    public void clockTick(ActionEvent event) {
        controllable.clockTick(); 
        spriteView.repaint(); 
        
    }
}
