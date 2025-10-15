package no.uib.inf101.tetris.view;

import java.awt.Color;

public class ColorTheme {

    public Color getCellColor(Character c) {
        Color color = switch (c) {
            case 'L' -> Color.YELLOW;
            case 'J' -> Color.GREEN;
            case 'S' -> Color.LIGHT_GRAY;
            case 'Z' -> Color.BLUE;
            case 'T' -> Color.ORANGE;
            case 'I' -> Color.RED;
            case 'O' -> Color.PINK;
            case '-' -> Color.BLACK;
    

            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
        return color;
    }

    public Color getFrameColor() {
        return Color.WHITE;
    }

    public Color getTransparentLayer() {
        return new Color(0, 0 , 0, 128);
    }

    public Color getBackgroundColor() {
        return new Color(0, 0, 0, 128);
    }

}
