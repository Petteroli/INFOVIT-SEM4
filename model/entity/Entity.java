package no.uib.inf101.rpg.model.entity;

import java.util.HashMap;

import no.uib.inf101.rpg.model.animation.Animation;
import no.uib.inf101.rpg.model.grid.CellPosition;
import no.uib.inf101.rpg.view.Drawable;
import no.uib.inf101.rpg.view.ViewableEntity;

/**
 * Represents an entity in the game world, such as a player, enemy, or wall. 
 * The entity can have animations, a visual representation, a position, and a type.
 */
public class Entity implements ViewableEntity {

    protected HashMap<Character, Animation<Drawable>> animationmap;
    private Animation<Drawable> animation;
    private char visual;
    private EntityType type;
    private CellPosition pos;
    private int frame;

    /**
     * Constructs an Entity with specified position, animation map, visual representation, and type.
     *
     * @param pos          The position of the entity.
     * @param animationmap A map of animations associated with different characters.
     * @param visual       The character representing the entity visually.
     * @param type         The type of the entity (e.g., player, wall, etc.).
     */
    public Entity(CellPosition pos,HashMap<Character, Animation<Drawable>> animationmap, char visual, EntityType type) {
        setAnimation(animation);
        this.visual = visual;
        this.type = type;
        if (pos == null) {
            new CellPosition(0, 0);
        }
        else {
            this.pos = pos;
        }
        if (animationmap == null) {
            this.animationmap = new HashMap<>();
        }
        else {
            this.animationmap = animationmap;
        }
        setAnimation(animationmap.get(visual));
    }

    /**
     * Constructs an Entity with specified animation, visual representation, type, and animation map.
     *
     * @param animation    The animation to be applied to the entity.
     * @param visual       The character representing the entity visually.
     * @param type         The type of the entity.
     * @param animationmap A map of animations associated with different characters.
     */
    public Entity(Animation<Drawable> animation, char visual, EntityType type, HashMap<Character, Animation<Drawable>> animationmap) {
        setAnimation(animation);
        this.visual = visual;
        this.type = type;
        this.pos = new CellPosition(0, 0);
        if (animationmap == null) {
            this.animationmap = new HashMap<>();
        }
        else {
            this.animationmap = animationmap;
        }
    }

    /**
     * Constructs an Entity with specified animation, visual representation, and type.
     * Position is set to (0, 0) by default.
     *
     * @param animation The animation to be applied to the entity.
     * @param visual    The character representing the entity visually.
     * @param type      The type of the entity.
     */
    public Entity(Animation<Drawable> animation, char visual, EntityType type) {
        setAnimation(animation);
        this.visual = visual;
        this.type = type;
        this.pos = new CellPosition(0, 0);
        this.animationmap = new HashMap<>();
    }

    /**
     * Constructs an Entity with specified position, animation, visual representation, and type.
     * 
     * @param pos        The position of the entity.
     * @param animation  The animation to be applied to the entity.
     * @param visual     The character representing the entity visually.
     * @param type       The type of the entity.
     */
    public Entity(CellPosition pos, Animation<Drawable> animation, char visual, EntityType type) {
        setAnimation(animation);
        this.visual = visual;
        this.type = type;
        if (pos == null) {
            new CellPosition(0, 0);
        }
        else {
            this.pos = pos;
        }
        this.animationmap = new HashMap<>();
    }

    /**
     * Constructs an Entity with specified position, animation, and type.
     * Visual representation is set to the default character.
     *
     * @param pos       The position of the entity.
     * @param animation The animation to be applied to the entity.
     * @param type      The type of the entity.
     */
    public Entity(CellPosition pos, Animation<Drawable> animation, EntityType type) {
        setAnimation(animation);
        this.type = type;
        if (pos == null) {
            new CellPosition(0, 0);
        }
        else {
            this.pos = pos;
        }
        this.animationmap = new HashMap<>();
    }

    /**
     * Constructs an Entity with specified position, animation, type, and animation map.
     *
     * @param pos          The position of the entity.
     * @param animation    The animation to be applied to the entity.
     * @param type         The type of the entity.
     * @param animationmap A map of animations associated with different characters.
     */
    public Entity(CellPosition pos, Animation<Drawable> animation, EntityType type, HashMap<Character, Animation<Drawable>> animationmap) {
        setAnimation(animation);
        this.type = type;
        if (pos == null) {
            this.pos = new CellPosition(0, 0);
        } else {
            this.pos = pos;
        }
        if (animationmap == null) {
            this.animationmap = new HashMap<>();
        } else {
            this.animationmap = animationmap;
        }
    }

    /**
     * Gets the type of this entity.
     *
     * @return The entity's type.
     */
    public EntityType getType() {
        return type;
    }

    /**
     * Sets the type of this entity.
     *
     * @param type The new type of the entity.
     */
    public void setType(EntityType type) {
        this.type = type;
    }

    /**
     * Gets the visual representation of this entity.
     *
     * @return The character representing the entity visually.
     */
    public char getVisual() {
        return visual;
    }

    /**
     * Sets the visual representation of this entity.
     *
     * @param newVisual The new character representing the entity visually.
     */
    public void setVisual(char newVisual) {
        this.visual = newVisual;
    }

    /**
     * Gets the animation currently associated with this entity.
     *
     * @return The current animation of the entity.
     */
    public Animation<Drawable> getAnimation() {
        return animation;
    }

    /**
     * Sets a new animation for this entity.
     *
     * @param newAnimation The new animation to be set.
     */
    public void setAnimation(Animation<Drawable> newAnimation) {
        if (newAnimation != null) {
            if (animation != newAnimation) {
                frame = 0;
            }
            animation = newAnimation;
        }
    }

    /**
     * Gets the current position of this entity.
     *
     * @return The current position of the entity.
     */
    @Override
    public CellPosition getPosition() {
        return pos;
    }

    /**
     * Sets a new position for this entity.
     *
     * @param newPos The new position of the entity.
     */
    public void setPosition(CellPosition newPos) {
        pos = newPos;
    }

    /**
     * Enum representing different types of entities in the game.
     */
    public enum EntityType {
        PLAYER,
        WALL,
        ENEMY,
        WIN
    }

    /**
     * Enum representing the different turn states for entities.
     */
    public enum TurnState {
        PLAYER_TURN,
        ENEMY_TURN,
        IDLE
    }

    /**
     * Returns the drawable representation of this entity's current animation frame.
     *
     * @return The drawable representation of the current animation frame.
     */
    @Override
    public Drawable getDrawable() {
        if (animation == null) {
            return null; 
        }
        return animation.getFrame();
    }


    /**
     * Gets the name of the entity.
     *
     * @return An empty string (can be overridden in subclasses).
     */
    @Override
    public String getName() {
        return "";
    }

    /**
     * Gets the health of the entity.
     *
     * @return 0 (can be overridden in subclasses).
     */
    @Override
    public int getHealth() {
        return 0;
    }

    /**
     * Gets the map of animations associated with different characters.
     *
     * @return A map of animations.
     */
    public HashMap<Character, Animation<Drawable>> getAnimationMap() {
        return animationmap;
    }

 }
