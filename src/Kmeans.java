import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {


    private List<MyPoints> points;
    private int M;
    private List<MyPoints> centroids;

    public Kmeans(List<MyPoints> points, int M) {
        this.points = points;
        this.M = M;
    }

    public List<MyPoints> runKMeans() {

        if (points.isEmpty()) {
            System.out.println("No points loaded from the file.");
            return null;
        }

        this.centroids = initializeCentroids();

        return centroids;
    }

    public double calculateClusteringError() {
        double totalError = 0.0;

        for (MyPoints point : points) {
            double minDistance = Double.MAX_VALUE;
            for (MyPoints centroid : centroids) {
                double distance = calculateEuclideanDistance(point, centroid);
                minDistance = Math.min(minDistance, distance);
            }
            totalError += minDistance * minDistance; // Αθροίζει το τετράγωνο της ελάχιστης απόστασης
        }
        return totalError;
    }

    private List<MyPoints> initializeCentroids() {

        List<MyPoints> centroids = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < M; i++) {
            int randomIndex = random.nextInt(points.size());
            MyPoints randomPoint = points.get(randomIndex);
            centroids.add(randomPoint); // Add randomly chosen point as centroid
        }
        return centroids;
    }

    private double calculateEuclideanDistance(MyPoints point1, MyPoints point2) {
        double xDiff = point1.getX() - point2.getX();
        double yDiff = point1.getY() - point2.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff); // Υπολογισμός Ευκλείδειας απόστασης
    }
}


