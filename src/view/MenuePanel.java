package view;

import controller.ButtonController;
import util.ImageLoader;
import view.util.BackgroundPanel;
import view.util.ImageButton;

import javax.swing.*;
import java.awt.*;

public class MenuePanel extends BackgroundPanel {
    private ImageButton btnStartGame;
    private ImageButton btnOption;
    private CardLayoutPanel cardLayoutPanel;
    private ButtonController buttonController;

    public MenuePanel(CardLayoutPanel cardLayoutPanel, GamePanel gamePanel) {
        super(ImageLoader.load("/res/background.png"));
        this.cardLayoutPanel = cardLayoutPanel;
        initVars(gamePanel);
        initComps();
    }

    /**
     * Initializes Variables.
     */
    private void initVars(GamePanel gamePanel) {
        Image[] startImages = {
                ImageLoader.load("/res/btnNewGameN.png"),
                ImageLoader.load("/res/btnNewGameN.png"),
                ImageLoader.load("/res/btnNewGameP.png")
        };

        Image[] optionImages = {

                ImageLoader.load("/res/btnOptionN.png"),
                ImageLoader.load("/res/btnOptionN.png"),
                ImageLoader.load("/res/btnOptionP.png")
        };

        btnStartGame = new ImageButton(startImages, "start");
        btnOption = new ImageButton(optionImages, "option");
        buttonController = new ButtonController(cardLayoutPanel, gamePanel);
    }

    /**
     * Initializes and set up Window Components.
     */
    private void initComps() {
        // panel
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        // start button
        initMenuButton(btnStartGame);

        // start button
        initMenuButton(btnOption);


        // add components
        add(createVerticalSpacing(16));
        add(btnStartGame);
        add(createVerticalSpacing(2));
        add(btnOption);
        add(createVerticalSpacing(8));
    }

    /**
     * Method to create spacing between 2 components.
     *
     * @param len
     * @return
     */
    private JLabel createVerticalSpacing(int len) {
		String builder = "<html>" +
				"<br>".repeat(Math.max(0, len)) +
				"</html>";

        return new JLabel(builder);
    }

    private void initMenuButton(ImageButton button) {
        button.addActionListener(buttonController);
        button.setMaximumSize(new Dimension(800, 200));
        button.setPreferredSize(new Dimension(800, 200));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
