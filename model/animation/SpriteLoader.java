package no.uib.inf101.rpg.model.animation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import no.uib.inf101.rpg.view.Drawable;
import no.uib.inf101.rpg.view.DrawableBufferedImage;

/**
 * The SpriteLoader class provides methods to load sprite frames and animations 
 * from image files, typically used for the animation of game entities. 
 * It reads image files from disk and returns them as drawable frames that can be used in animations.
 */
public class SpriteLoader {

    /**
     * Constructs a new SpriteLoader. The constructor does not perform any 
     * specific initialization, as all methods are static.
     */
    public SpriteLoader() {}

    /**
     * Loads sprite frames from a sequence of image files. The image files are expected to follow a naming convention, where each frame is stored in separate files.
     *
     * If the `frameCount` is zero, a single image file (with the base path) is loaded. 
     * If the `frameCount` is greater than zero, the method tries to load that number of frames from the given base path with an incremental index appended to the filename.
     *
     * @param basePath  The base path to the image files. The files are expected to be named with an incremental index (e.g., "sprite0.png", "sprite1.png").
     * @param frameCount The number of frames to load. If zero, only a single image (without index) is loaded.
     * @return A list of Drawable objects representing the loaded frames.
     * @throws IOException If an I/O error occurs while reading the image files.
     */
    public static ArrayList<Drawable> loadSpriteFrames(String basePath, int frameCount) throws IOException {
        if (frameCount == 0) {
            ArrayList<Drawable> frames = new ArrayList<>();
            frames.add(new DrawableBufferedImage(ImageIO.read(new File(basePath + ".png"))));
            return frames;
        }

        ArrayList<Drawable> frames = new ArrayList<>();
        for (int i = 0; i < frameCount; i++) {
            frames.add(new DrawableBufferedImage(ImageIO.read(new File(basePath + i + ".png"))));
        }
        return frames;
    }
    
    /**
     * Loads an animation from a sequence of sprite frames. The frames are loaded 
     * from image files, and an animation is created with a specified frame rate.
     * 
     * If an error occurs while loading the frames, an empty animation is returned.
     *
     * @param basePath  The base path to the image files, where frames are named with an incremental index.
     * @param frameCount The number of frames in the animation.
     * @param FPS The frame rate (frames per second) for the animation.
     * @return An Animation object representing the loaded frames with the specified frame rate.
     */
    public static Animation<Drawable> loadAnimation(String basePath, int frameCount, int FPS) {
        ArrayList<Drawable> frames = null;
        try {
            frames = SpriteLoader.loadSpriteFrames(basePath, frameCount); 
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("for file " + basePath);
        }
        
        if (frames == null) {
            return new Animation<>(new ArrayList<>(), FPS); 
        }
        return new Animation<>(frames, FPS); 
    }

} 

