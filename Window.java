import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.*;
import java.util.Random;

public class Window {
    protected static final int renderPanelXSize = 800;
    protected static final int renderPanelYSize = 600;
    protected static final int statsPanelXSize = 300;

    protected final JFrame mainFrame;
    protected final JPanel renderPanel;
    protected final JPanel statsPanel;
    protected final JLabel frameRenderTimeLabel;

    protected final Renderer renderer;

    Window() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(renderPanelXSize + statsPanelXSize, renderPanelYSize);
        mainFrame.setVisible(true);

        Container pane = mainFrame.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.RED);

        renderPanel = new JPanel(new BorderLayout());
        renderPanel.setSize(renderPanelXSize, renderPanelYSize);
        pane.add(renderPanel, BorderLayout.WEST);
        
        statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setSize(statsPanelXSize, renderPanelYSize);
        pane.add(statsPanel);
        frameRenderTimeLabel = new JLabel();
        frameRenderTimeLabel.setForeground(Color.WHITE);
        statsPanel.add(frameRenderTimeLabel, BorderLayout.LINE_START);

        renderer = new JPanelRenderer();

        
        loop((x, y) -> {
            double cameraPosX = 0.0;
            double cameraPosY = 0.0;

            double middleX = x - renderPanelXSize / 2;
            double middleY = y - renderPanelYSize / 2;
            int radius = 200;

            

            if (Math.pow((middleX), 2) + Math.pow((middleY), 2) > (radius * radius)) return 0xffffff;
            else return 0x000000;
        });
    }

    protected void loop(PerPixel perPixel) {
        ImageData imageData = new ImageData(renderPanelXSize, renderPanelYSize);
        while (true) {
            for (int i = 0; i < renderPanelXSize; i++) {
                for (int j = 0; j < renderPanelYSize; j++) {
                    imageData.setData(i, j, perPixel.run(i, j));
                }
            }
            long timeElapsed = renderer.render(imageData, renderPanel);
            frameRenderTimeLabel.setText("Frame render time: " + timeElapsed + "ms");
        }
    }
}
