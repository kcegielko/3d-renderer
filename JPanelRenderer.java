import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.*;

public class JPanelRenderer implements Renderer<JPanel> {
    
    public long render(ImageData imageData, JPanel renderPanel) {
        Instant start = Instant.now();
        BufferedImage bi = new BufferedImage(imageData.getXSize(), imageData.getYSize(), BufferedImage.TYPE_INT_RGB);
        ImageIcon icon = new ImageIcon(bi);
        renderPanel.removeAll();
        renderPanel.add(new JLabel(icon));
        for (int x = 0; x < imageData.getXSize(); x++) {
            for (int y = 0; y < imageData.getYSize(); y++) {
                bi.setRGB(x, y, imageData.getData(x, y));
            }
        }
        renderPanel.revalidate();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        return timeElapsed;
    }
}
