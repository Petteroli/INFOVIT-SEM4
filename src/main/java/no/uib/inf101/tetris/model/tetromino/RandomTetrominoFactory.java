package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory {
    char[] symbols = {'L','J','S','Z','T','I','O'};
    Random random = new Random();

    @Override
    public Tetromino getNext() {
        char getRandom = symbols[random.nextInt(symbols.length)];
        return Tetromino.newTetromino(getRandom);
    }

}
