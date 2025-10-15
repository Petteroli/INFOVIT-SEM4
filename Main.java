package no.uib.inf101.rpg;

import java.awt.Dimension;

import javax.swing.JFrame;

import no.uib.inf101.rpg.controller.Controller;
import no.uib.inf101.rpg.model.GameModel;
import no.uib.inf101.rpg.view.SpriteView;

/**
 * The main class that launches the Dungeon RPG game.
 * This class sets up the game model, view, and controller, and initializes the game window.
 * It is the entry point for the application.
 */
public class Main {
	
	public static final String WINDOW_TITLE = "DUNGEON";

	/**
     * The main method that runs the game.
     * It initializes the game model, the view, and the controller, sets up the game window,
     * and starts the game by making the window visible and interactive.
     */
	public static void main(String[] args) {
		GameModel model = new GameModel();
		SpriteView view = new SpriteView(model);
		Controller controller = new Controller(model, view);
		view.addKeyListener(controller); 

		JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view);
		frame.pack();
		frame.setVisible(true);
        Dimension dim = new Dimension(model.getCamera().cols() * SpriteView.SIZE + SpriteView.OUTERMARGIN, model.getCamera().rows() * SpriteView.SIZE + SpriteView.OUTERMARGIN);
		frame.setSize(dim);

    }
}