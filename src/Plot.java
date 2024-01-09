import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Plot extends JPanel {

    private List<MyPoints> points;
    private List<MyPoints> centroids;
    private final int scale = 400; // Scaling factor for coordinates

    public Plot(List<MyPoints> points, List<MyPoints> centroids) {
        this.points = points;
        this.centroids = centroids;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Plot points as "+"
        g2d.setColor(Color.BLUE); // Set color for points
        for (MyPoints point : points) {
            int x = (int) (point.getX() * scale); // Scaling factor for x-coordinate
            int y = (int) (point.getY() * scale); // Scaling factor for y-coordinate
            g2d.drawLine(x - 2, y, x + 2, y); // Draw horizontal line
            g2d.drawLine(x, y - 2, x, y + 2); // Draw vertical line
        }

        // Plot centroids
        g2d.setColor(Color.BLACK); // Set color for centroids
        for (MyPoints centroid : centroids) {
            int x = (int) (centroid.getX() * scale); // Scaling factor for x-coordinate
            int y = (int) (centroid.getY() * scale); // Scaling factor for y-coordinate
            g2d.drawLine(x - 3, y - 3, x + 3, y + 3); // Draw diagonal line
            g2d.drawLine(x - 3, y + 3, x + 3, y - 3); // Draw diagonal line
        }

        // Highlight centroid with minimum clustering error
        g2d.setColor(Color.RED);
        MyPoints minErrorCentroid = centroids.get(0); // Assuming the first centroid has minimum error
        int xMin = (int) (minErrorCentroid.getX() * scale);
        int yMin = (int) (minErrorCentroid.getY() * scale);
        g2d.drawRect(xMin - 4, yMin - 4, 8, 8); // Highlight the centroid
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // Set preferred size of the panel
    }
}
