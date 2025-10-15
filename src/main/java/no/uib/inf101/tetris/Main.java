package no.uib.inf101.tetris;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;


import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

public class Main {
	
	public static final String WINDOW_TITLE = "INF101 Tetris";

	public static void main(String[] args) {
		TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
		TetrisModel model = new TetrisModel(tetrominoFactory);
		TetrisView view = new TetrisView(model);
		TetrisController tetrisController = new TetrisController(view, model);

		

		JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view);
		frame.pack();
		frame.setVisible(true);
		// Open GUI in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}

}
