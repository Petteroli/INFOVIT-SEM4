package no.uib.inf101.rpg.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import no.uib.inf101.rpg.model.entity.Player;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.model.grid.GridCell;
import no.uib.inf101.rpg.model.grid.GridDimension;

/**
 * The SpriteView class extends JPanel and represents the graphical view of the game. 
 * It is responsible for rendering the game world, including the maze, player, enemies, and battle menu. 
 * It periodically repaints the view using a Timer to ensure smooth updates.
 */
public class SpriteView extends JPanel {

    public static final int OUTERMARGIN = 15;
    public static final int CELLMARGIN = 2;
    public static final int PREFERREDSIDESIZE = 100;
    public static final int SIZE = 64;


    private ViewableGameModel viewableGameModel;
    private ColorTheme colorTheme;
    private Timer timer;

    /**
     * Constructs a SpriteView with the specified game model.
     *
     * @param viewableGameModel The game model to be displayed in the view.
     */
    public SpriteView(ViewableGameModel viewableGameModel) {
        this.viewableGameModel = viewableGameModel;

        this.colorTheme = new ColorTheme();
        this.setBackground(colorTheme.getBackgroundColor());
        
        this.setFocusable(true);
        this.setPreferredSize(getDefaultSize(viewableGameModel.getDimension()));

        timer = new Timer(1000 / 30, e -> repaint());
        timer.start();
    }

    /**
     * Paints the component by rendering the game world.
     * This method is automatically called during the repaint process.
     *
     * @param g The Graphics object used to paint the component.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    /**
     * Draws the game world, including the maze, player, and entities.
     * Displays the game over screen if the player is dead, or the win screen if the player wins.
     *
     * @param g2 The Graphics2D object used to render the game.
     */
    private void drawGame(Graphics2D g2) {
        if (viewableGameModel.winMaze() == true) {
            drawMazeWin(g2);
            return;
        }
        
        Player player = viewableGameModel.getPlayer();
        if (player.getHealth() <= 0) {
            drawGameOverScreen(g2);
            return;
        }

        Rectangle2D box = new Rectangle2D.Double(
                OUTERMARGIN,
                OUTERMARGIN,
                this.getWidth() - OUTERMARGIN * 2,
                this.getHeight() - OUTERMARGIN * 2);
        
        g2.setColor(colorTheme.getFrameColor());
        g2.fill(box);

        CellPositionToPixelConverter converterModel = new CellPositionToPixelConverter(box,viewableGameModel.getCamera(), CELLMARGIN);

        drawCells(g2, viewableGameModel.getTilesOnBoard(), converterModel, colorTheme);
        drawEntity(g2, viewableGameModel.getPlayer(), viewableGameModel.getPlayer().getCameraPosition(), converterModel, colorTheme);
        
        if (viewableGameModel.inBattle()) {
            drawFightScreen(g2);
        }
    }

    /**
     * Draws the battle screen when the player encounters an enemy.
     *
     * @param g2 The Graphics2D object used to render the battle screen.
     */
    private void drawFightScreen(Graphics2D g2) {
        int boxX = OUTERMARGIN;
        int boxY = this.getHeight() - 200;
        int boxWidth = this.getWidth() - OUTERMARGIN * 2;
        int boxHeight = 180;

        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(boxX, boxY, boxWidth, boxHeight);

        g2.setColor(colorTheme.getBattleMenuColor());
        g2.setFont(g2.getFont().deriveFont(18f));

        ViewableEntity enemy = viewableGameModel.getCurrentEnemy();
        ViewableEntity player = viewableGameModel.getPlayer();
  
        drawEntity(g2, enemy, (double) boxX + 100, (double) boxY);

        g2.drawString("You encountered a " + enemy.getName(), boxX + 20, boxY + 30);
        g2.drawString("Enemy HP: " + enemy.getHealth(), boxX + 20, boxY + 60);
        g2.drawString("HP : " + player.getHealth(), boxX + 20, boxY + 120);
        g2.drawString("1. Attack", boxX + 20, boxY + 100);
        g2.drawString("2. Run", boxX + 150, boxY + 100);

    }

    /**
     * Draws the win screen when the player wins the maze.
     *
     * @param g2 The Graphics2D object used to render the win screen.
     */
    private void drawMazeWin(Graphics2D g2) {
        int boxX = OUTERMARGIN;
        int boxY = this.getHeight() - 200;
        int boxWidth = this.getWidth() - OUTERMARGIN * 2;
        int boxHeight = 180;

        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(boxX, boxY, boxWidth, boxHeight);

        g2.setColor(colorTheme.getBattleMenuColor());
        g2.setFont(g2.getFont().deriveFont(18f));

        String text = "YOU WIN!";
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = (this.getWidth() - textWidth) / 2;
        int y = this.getHeight() / 2;
    
        g2.drawString(text, x, y);
    }

    /**
     * Draws the "Game Over" screen when the player's health reaches zero.
     * A black overlay is drawn with the "GAME OVER" message centered on the screen.
     *
     * @param g2 The Graphics2D object used to render the game over screen.
     */
    private void drawGameOverScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 220)); 
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    
        g2.setColor(colorTheme.getGameOverColor());
        g2.setFont(g2.getFont().deriveFont(60f));
        
        String text = "GAME OVER";
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = (this.getWidth() - textWidth) / 2;
        int y = this.getHeight() / 2;
    
        g2.drawString(text, x, y);
    }

    /**
     * Draws all the cells in the grid, including the entities within each cell.
     * This method iterates through the provided cells and calls `drawEntity()` for each entity found in the grid cells.
     *
     * @param g2 The Graphics2D object used to render the grid cells.
     * @param iterable An iterable collection of grid cells containing the entities to be drawn.
     * @param converterModel The converter used to translate cell positions to pixel positions.
     * @param colorTheme The color theme used to set colors for the drawn entities.
     */
    private static void drawCells(Graphics2D g2, Iterable<GridCell<ViewableEntity>> iterable, CellPositionToPixelConverter converterModel, ColorTheme colorTheme) {
        for (GridCell<ViewableEntity> cell : iterable) {
            CellPosition pos = cell.pos();
            ViewableEntity entity = cell.value();
            drawEntity(g2, entity, pos, converterModel, colorTheme);
        }
    }

    /**
     * Draws a single entity at a specified cell position.
     * This method checks if the entity is not null and if it has a drawable, then calls the `drawSelf()` method of the drawable.
     *
     * @param g2 The Graphics2D object used to render the entity.
     * @param entity The entity to be drawn.
     * @param pos The position of the entity in the grid.
     * @param converterModel The converter used to translate cell positions to pixel positions.
     * @param colorTheme The color theme used to set colors for the drawn entity.
     */
    private static void drawEntity(Graphics2D g2, ViewableEntity entity, CellPosition pos, CellPositionToPixelConverter converterModel, ColorTheme colorTheme) {
       if (entity != null) {
            Drawable drawable = entity.getDrawable();
            
             if (drawable != null) {
                 drawable.drawSelf(g2, pos, converterModel);
             } 
         }
    }

    /**
     * Draws a single entity at a specified (x, y) position.
     * This method checks if the entity is not null and if it has a drawable, then calls the `drawSelf()` method of the drawable.
     *
     * @param g2 The Graphics2D object used to render the entity.
     * @param entity The entity to be drawn.
     * @param posx The x-coordinate to draw the entity.
     * @param posy The y-coordinate to draw the entity.
     */
    private void drawEntity(Graphics2D g2, ViewableEntity entity, double posx, double posy) {
        if (entity != null) {
            Drawable drawable = entity.getDrawable();
            if (drawable != null) {
                entity.getDrawable().drawSelf(g2, posx, posy);
            }
        }
    }

    /**
     * Calculates and returns the default size of the game area based on the grid dimensions.
     * This size includes margins for the outer area and the cell spacing.
     *
     * @param gd The grid dimensions to calculate the size from.
     * @return The default size (width and height) for the game area.
     */
    private static Dimension getDefaultSize(GridDimension gd) {
        int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        return new Dimension(width, height);
    }


}

