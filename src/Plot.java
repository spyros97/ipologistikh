import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Plot extends JPanel {

    private List<MyPoints> points;
    private List<List<MyPoints>> centroidsByM;
    private final int scale = 400; // Increase scale for larger graph

    public Plot(List<MyPoints> points, List<List<MyPoints>> centroidsByM) {
        this.points = points;
        this.centroidsByM = centroidsByM;
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
            g2d.drawLine(x - 3, y, x + 3, y); // Draw horizontal line
            g2d.drawLine(x, y - 3, x, y + 3); // Draw vertical line
        }

        // Plot centroids as "*"
        g2d.setColor(Color.BLACK); // Set color for centroids
        for (List<MyPoints> centroidList : centroidsByM) {
            for (MyPoints centroid : centroidList) {
                int x = (int) (centroid.getX() * scale); // Scaling factor for x-coordinate
                int y = (int) (centroid.getY() * scale); // Scaling factor for y-coordinate
                g2d.drawLine(x - 3, y - 3, x + 3, y + 3); // Draw diagonal line
                g2d.drawLine(x - 3, y + 3, x + 3, y - 3); // Draw diagonal line
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800); // Set preferred size of the panel
    }

    public static void displayPlot(List<MyPoints> points, List<List<MyPoints>> centroidsByM) {
        JFrame frame = new JFrame("Combined Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Plot plot = new Plot(points, centroidsByM);
        JScrollPane scrollPane = new JScrollPane(plot); // Add scroll pane for larger plot
        frame.add(scrollPane);
        frame.setSize(900, 900); // Increase frame size for a larger plot area
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
