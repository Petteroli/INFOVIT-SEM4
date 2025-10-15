package no.uib.inf101.rpg.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import no.uib.inf101.rpg.controller.ControllableModel;
import no.uib.inf101.rpg.model.animation.Animation;
import no.uib.inf101.rpg.model.animation.SpriteLoader;
import no.uib.inf101.rpg.model.entity.Enemy;
import no.uib.inf101.rpg.model.entity.Entity;
import no.uib.inf101.rpg.model.entity.Entity.EntityType;
import no.uib.inf101.rpg.model.entity.Entity.TurnState;
import no.uib.inf101.rpg.model.entity.Player;
import no.uib.inf101.rpg.model.entity.Win;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.model.grid.GridCell;
import no.uib.inf101.rpg.model.grid.GridDimension;
import no.uib.inf101.rpg.view.Drawable;
import no.uib.inf101.rpg.view.DrawableBox;
import no.uib.inf101.rpg.view.ViewableEntity;
import no.uib.inf101.rpg.view.ViewableGameModel;

/**
 * The {@code GameModel} class represents the core model of the RPG game.
 * It initializes and manages the world, including the player, enemies, walls, and win conditions.
 * 
 * <p>This class implements both {@link ViewableGameModel} and {@link ControllableModel},
 * allowing it to be both viewed and controlled externally (e.g., by a controller).
 * 
 * <p>Main responsibilities:
 * <ul>
 *   <li>Initialize the world grid and entities (player, enemies, walls, win object).</li>
 *   <li>Load and manage sprite animations for various entities.</li>
 *   <li>Handle the player's movement and battle state against enemies.</li>
 *   <li>Track the game's progress, including win conditions and turn states.</li>
 * </ul>
 * 
 * <p>Important fields:
 * <ul>
 *   <li>{@code world} - The game world, storing all entities and managing their positions.</li>
 *   <li>{@code player} - The player entity controlled by the user.</li>
 *   <li>{@code currentEnemy} - The enemy the player is currently battling.</li>
 *   <li>{@code inBattle} - A flag indicating whether the player is currently in a battle.</li>
 *   <li>{@code turnState} - The current state of a turn (e.g., IDLE, PLAYER_TURN, ENEMY_TURN).</li>
 *   <li>{@code turnDelay} - Delay timer between turns.</li>
 *   <li>{@code win} - A flag indicating whether the player has won the game.</li>
 *   <li>{@code attackAnimationDelay} - A timer for handling attack animations.</li>
 * </ul>
 * 
 * <p>Sprites and animations are located in the {@code src/main/java/no/uib/inf101/rpg/sprites/} directory.
 * 
 * @see World
 * @see Player
 * @see Enemy
 * @see Entity
 * @see Animation
 * @see SpriteLoader
 * @see ViewableGameModel
 * @see ControllableModel
 */

public class GameModel implements ViewableGameModel, ControllableModel{

    private final World world;
    private Player player;
    private boolean canMove = true;
    private Enemy currentEnemy;
    private boolean inBattle = false; 
    private TurnState turnState = TurnState.IDLE;
    private int turnDelay = 0;
    private boolean win = false;
    private int attackAnimationDelay = 0;

    public GameModel() {
        world = new World();
        
        //SPRITES
        HashMap<Character,Animation<Drawable>> playerAnimationMap = new HashMap<>();
        playerAnimationMap.put('I', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/player/idle/spr_player_idle_down/idleDown", 2, 4));
        playerAnimationMap.put('R', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/player/walking/spr_player_walking_down/walkingDown", 3, 4));
        playerAnimationMap.put('U', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/player/walking/spr_player_walking_left/walkingLeft", 3, 4));
        playerAnimationMap.put('D', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/player/walking/spr_player_walking_right/walkingRight", 3, 4));
        playerAnimationMap.put('L', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/player/walking/spr_player_walking_up/walkingUp", 3, 4));

        HashMap<Character,Animation<Drawable>> glorbAnimationMap = new HashMap<>();
        glorbAnimationMap.put('I', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/enemies/glorb/idle/idleGlorb", 4, 4));
        glorbAnimationMap.put('B', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/enemies/glorb/battle/battleGlorb", 8, 10));
        glorbAnimationMap.put('A', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/enemies/glorb/attack/attackGlorb", 5, 7));
        
        HashMap<Character,Animation<Drawable>> glorbarizerbAnimationMap = new HashMap<>();
        glorbarizerbAnimationMap.put('I', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/enemies/glorbarizer/idle/idleGlorbarizer", 1, 4));
        glorbarizerbAnimationMap.put('B', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/enemies/glorbarizer/battle/battleGlorbarizer", 6, 10));
        glorbarizerbAnimationMap.put('A', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/enemies/glorbarizer/attack/attackGlorbarizer", 6, 8));

        HashMap<Character,Animation<Drawable>> wallAnimationMap = new HashMap<>();
        ArrayList<Drawable> frames = new ArrayList<Drawable>();
        frames.add(new DrawableBox(new Color(0,0,0)));
        wallAnimationMap.put('W', new Animation<>(frames, 1));
        
        HashMap<Character,Animation<Drawable>> winAnimationMap = new HashMap<>();
        winAnimationMap.put('W', SpriteLoader.loadAnimation("src/main/java/no/uib/inf101/rpg/sprites/win/win", 0, 1));

        //PLAYER
        player = new Player(new CellPosition(8, 2), playerAnimationMap.get('I'), 400, playerAnimationMap);
        world.setPlayer(player);
 
        //WIN
        world.set(new Win(new CellPosition(6, 19),winAnimationMap.get('W'),EntityType.WIN, winAnimationMap));

        //ENEMIES
        world.set(new Enemy("Glorb",new CellPosition(2, 4), 100, 'I', 10 + ((int)(Math.random() * 21)), glorbAnimationMap));
        world.set(new Enemy("Glorb",new CellPosition(11, 10), 100,'I', 10 + ((int)(Math.random() * 21)), glorbAnimationMap));
        world.set(new Enemy("Glorb",new CellPosition(1, 11), 100,'I', 10 + ((int)(Math.random() * 21)), glorbAnimationMap));
        world.set(new Enemy("Glorb",new CellPosition(10, 19), 100,'I', 10 + ((int)(Math.random() * 21)), glorbAnimationMap));

        world.set(new Enemy("Glorbarizer",new CellPosition(14, 0), 100,'I', 20 + ((int)(Math.random() * 21)), glorbarizerbAnimationMap));
        world.set(new Enemy("Glorbarizer",new CellPosition(6, 9), 100,'I', 20 + ((int)(Math.random() * 21)), glorbarizerbAnimationMap));
        world.set(new Enemy("Glorbarizer",new CellPosition(6, 17), 100,'I', 20 + ((int)(Math.random() * 21)), glorbarizerbAnimationMap));
        world.set(new Enemy("Glorbarizer",new CellPosition(3, 19), 100,'I', 20 + ((int)(Math.random() * 21)), glorbarizerbAnimationMap));

        //WALLS
        world.set(new Entity(new CellPosition(6, 0), wallAnimationMap.get('W'), EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(3, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(0, 7), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(7, 0), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 0), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(9, 0), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 1), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 2), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 3), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 0), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(3, 2), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 2), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 3), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 4), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 0), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 1), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10,2), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 3), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(7, 4), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(9, 4), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(3, 4), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 1), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 2), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(0, 0), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 4), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 1), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 2), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 3), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 4), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(13, 1), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(14, 3), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 5), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 7), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(5, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(7, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(9, 6), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(11, 5), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 5), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(13, 5), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 7), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 7), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(14, 7), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(0, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(3, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(5, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(7, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 8), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 9), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 9), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(13, 9), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(5, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(7, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(3, 10), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 11), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 11), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 11), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(13, 11), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(14, 11), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(0, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(7, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 12), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(9, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(11, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(13, 13), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 14), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 14), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 14), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 14), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(5, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(6, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(9, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(11, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(13, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(14, 15), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 16), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 16), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 16), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 17), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 17), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(5, 17), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(7, 17), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 17), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 17), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(13, 17), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(1, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(2, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(3, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(4, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(8, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(9, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(10, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(11, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(12, 18), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(14, 19), wallAnimationMap.get('W'),  EntityType.WALL));
        world.set(new Entity(new CellPosition(5, 5), wallAnimationMap.get('W'),  EntityType.WALL));

    }
    /**
     * Returns the dimensions of the grid world.
     *
     * @return the dimensions of the world grid
     */
    @Override
    public GridDimension getDimension() {
        return world.getDimension();
    }

    /**
     * Returns an iterable of all tiles on the board.
     *
     * @return an iterable collection of grid cells containing viewable entities
     */
    @Override
    public Iterable<GridCell<ViewableEntity>> getTilesOnBoard() {
        return world;
    }
    
    /**
     * Attempts to move the player by the specified row and column deltas.
     * Movement is blocked if movement is not currently allowed, the new position is illegal,
     * a wall blocks the way, or an enemy is encountered (starts a battle instead).
     * If the player moves onto the win entity, the player wins the maze.
     *
     * @param deltaRows number of rows to move (positive or negative)
     * @param deltaCols number of columns to move (positive or negative)
     * @return true if the player successfully moved, false otherwise
     */    
    @Override
    public boolean movePlayer(int deltaRows, int deltaCols) {
        if (!canMove) {
            return false;
        }
        CellPosition currentPos = player.getPosition();
        CellPosition newPos = currentPos.translateBy(deltaRows, deltaCols);

        if (!world.isPositionLegal(newPos)) {
            return false;
        }

        for (GridCell<Entity> cell : world.getIterable()) {
            Entity entity = cell.value();
            if (entity != null && entity.getPosition().equals(newPos)) {
                if (entity.getType() == EntityType.WALL) {
                    return false;
                }
                if (entity.getType() == EntityType.ENEMY) {
                    startFight((Enemy) entity);
                    return false;
                }
            }
        }
        player.moveBy(deltaRows, deltaCols);
        canMove = false;

        Entity landedEntity = world.get(player.getPosition());
        if (landedEntity != null && landedEntity.getType() == EntityType.WIN) {
            win = true;
        }

        return true;
    }

    /**
     * Returns the player entity.
     *
     * @return the player
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the number of milliseconds between animation or logic updates.
     *
     * @return the number of milliseconds for each game tick
     */
    @Override
    public int milliseconds() {
        return 150;
    }


    /**
     * Updates the game state each clock tick.
     * Handles allowing player movement again, updating animations,
     * and controlling enemy turn behavior during battles.
     */
    @Override
    public void clockTick() {
        canMove = true;
    
        if (player != null && player.getAnimation() != null) {
            player.getAnimation().incrementFrame();
        }
    
        if (inBattle && currentEnemy != null && currentEnemy.getAnimation() != null) {
            currentEnemy.getAnimation().incrementFrame();
        }
    
        if (inBattle && currentEnemy != null) {
            if (turnState == TurnState.ENEMY_TURN) {
                if (attackAnimationDelay > 0) {
                    attackAnimationDelay--;
                    if (attackAnimationDelay == 0) {
                        player.takeDamage(currentEnemy.getAttack());
    
                        if (player.getHealth() <= 0) {
                            System.out.println("Player defeated!");
                            endBattle();
                        } else {
                            if (currentEnemy.getAnimationMap().containsKey('B')) {
                                currentEnemy.setAnimation(currentEnemy.getAnimationMap().get('B'));
                            }
                            turnState = TurnState.PLAYER_TURN;
                        }
                    }
                } else {
                    if (currentEnemy.getAnimationMap().containsKey('A')) {
                        currentEnemy.setAnimation(currentEnemy.getAnimationMap().get('A'));
                    }
                    attackAnimationDelay = 8;
                }
            }
        }
    }
    
    /**
     * Returns the current camera view (the visible portion of the world).
     *
     * @return the camera's grid dimension
     */
    @Override
    public GridDimension getCamera() {
        return world.getCamera();
    }

    /**
     * Initiates a battle with the specified enemy.
     *
     * @param enemy the enemy to start fighting
     */
    private void startFight(Enemy enemy) {
        currentEnemy = enemy;
        inBattle = true;
        turnState = TurnState.PLAYER_TURN;

        if (currentEnemy.getAnimationMap().containsKey('B')) {
            currentEnemy.setAnimation(currentEnemy.getAnimationMap().get('B'));
        }
    }

    /**
     * Ends the current battle, clearing the current enemy and battle state.
     */
    public void endBattle() {
        inBattle = false;
        currentEnemy = null;
    }

    /**
     * Checks if the player is currently in a battle.
     *
     * @return true if in battle, false otherwise
     */
    @Override
    public boolean inBattle() {
        return inBattle;
    }

    /**
     * Returns the enemy the player is currently fighting.
     *
     * @return the current enemy, or null if not in battle
     */
    @Override
    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    /**
     * Performs an attack on the current enemy during the player's turn.
     * If the enemy is defeated, the battle ends; otherwise, it becomes the enemy's turn.
     */
    @Override
    public void attack() {
        if (inBattle && currentEnemy != null && turnState == TurnState.PLAYER_TURN) {
            currentEnemy.takeDamage(10 + ((int)(Math.random() * 31))); 
    
            if (currentEnemy.getHealth() <= 0) {
                world.remove(currentEnemy.getPosition());
                endBattle();
            } else {
                turnState = TurnState.ENEMY_TURN;
                turnDelay = 3; 
            }
        }
    }

    /**
     * Attempts to run away from the current battle, immediately ending it.
     */
    @Override
    public void runAway() {    
        if (inBattle) {
            endBattle();
        }
    }

    /**
     * Checks if the player has won the maze by reaching the win entity.
     *
     * @return true if the player has won, false otherwise
     */
    @Override
    public boolean winMaze() {
        return win;
    }

}
 