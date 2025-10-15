package no.uib.inf101.rpg.view;

import java.awt.Color;

/**
 * This class provides a set of color themes for various elements in the game, including cells, win conditions, game over states, and UI elements like frames and menus.
 * The colors are retrieved based on specific game-related conditions or characters.
 */
public class ColorTheme {

    /**
     * Returns a color corresponding to a given character used in the grid or game elements.
     * The character is mapped to a specific color to represent different elements or entities.
     *
     * @param c The character whose corresponding color is to be retrieved.
     * @return The color associated with the given character.
     * @throws IllegalArgumentException if the character does not have an associated color.
     */
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

    /**
     * Returns the color associated with the "win" condition, typically used for success or victory.
     *
     * @return The color representing a win state.
     */
    public Color getWinColor() {
        return Color.GREEN;
    }

    /**
     * Returns the color used for borders in the UI or game elements.
     *
     * @return The color used for borders.
     */
    public Color getBorderColor() {
        return Color.BLACK;
    }

    /**
     * Returns the color associated with the "game over" state, typically used for failure or game over.
     *
     * @return The color representing a game over state.
     */
    public Color getGameOverColor() {
        return Color.RED;
    }

    /**
     * Returns the color used for the frame of the game window or UI elements.
     *
     * @return The color used for the frame.
     */
    public Color getFrameColor() {
        return Color.WHITE;
    }

    /**
     * Returns the color used for battle menus or UI elements during combat.
     *
     * @return The color used for battle menus.
     */
    public Color getBattleMenuColor() {
        return Color.WHITE;
    }

    /**
     * Returns the background color used for the game or UI elements.
     *
     * @return The color used for the background.
     */
    public Color getBackgroundColor() {
        return new Color(0, 0, 0, 128);
    }

}
