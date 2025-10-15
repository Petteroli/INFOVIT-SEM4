package no.uib.inf101.rpg.model.animation;

import java.util.List;


/**
 * Represents an animation consisting of a sequence of frames. The animation progresses over time based on a specified frame rate (frames per second). 
 * Each frame in the animation is represented by an object of type T, which could be a sprite, image, or other drawable objects.
 * 
 * The animation updates its current frame automatically based on the elapsed time since the last frame update.
 *
 * @param <T> The type of objects in the animation frames.
 */
public class Animation<T> {
    private final List<T> frames;
    private final int framerate;
    private int frame;
    private long lastTime;
    private long frameDuration;

    /**
     * Constructs an animation with the specified frames and frame rate.
     *
     * @param frames A list of frames that make up the animation.
     * @param framerate The frame rate in frames per second (FPS) that controls how fast the animation progresses.
     */
    public Animation(List<T> frames, int framerate) {
        this.frames = frames;
        this.framerate = framerate;
        this.frameDuration = 1000 / framerate;
        this.lastTime = System.currentTimeMillis();
        this.frame = 0;
    }

    /**
     * Gets the current frame of the animation.
     *
     * @return The current frame, or null if there are no frames in the animation.
     */
    public T getFrame() {
        if (frameCount() == 0) {
            return null;
        }
        return frames.get(frame);
    }

    /**
     * Sets the current frame to the specified frame index.
     *
     * @param frame The index of the frame to set as the current frame.
     */
    public void setFrame(int frame) {
        this.frame = frame;
    }

    /**
     * Returns the total number of frames in the animation.
     *
     * @return The number of frames in the animation.
     */
    public int frameCount() {
        return frames.size();
    }
    
    /**
     * Increments the current frame based on the frame rate and the elapsed time. 
     * If enough time has passed since the last frame update, the animation advances to the next frame.
     * The frame loops back to the first frame once the last frame is reached.
     */
    public void incrementFrame() {
        if (frameCount() == 0) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= frameDuration) {
            frame = (frame + 1) % frames.size();
            lastTime = currentTime;
        }
    }
}


