package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageLoader {


    /**
     * Method to load an image from the resources.
     *
     * @param name the name of the image
     * @return the loaded image
     */
    public static Image load(String name) {
        try {
            return ImageIO.read(ImageLoader.class.getResourceAsStream(name));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to load and scale an image to a given size.
     * To load the image we call the {@link #load(String) } method.
     *
     * @param name the image name
     * @param w    the width of the scaled image
     * @param h    the height of the scaled image
     * @return the loaded image as an imageicon
     */
    public static ImageIcon loadAndScale(String name, int w, int h) {
        return new ImageIcon(load(name).getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }

}
