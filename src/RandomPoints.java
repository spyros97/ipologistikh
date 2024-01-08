


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPoints {

    public static void writeRandomPointsToFile(String filename, List<MyPoints> points) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            for (MyPoints point : points) {
                writer.write(point.toString()); // Assuming MyPoints class has overridden toString()
                writer.newLine();
            }

            writer.close();
            System.out.println("Points written to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<MyPoints> createRandomPoints() {
        List<MyPoints> points = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 150; i++) {
            points.add(new MyPoints(0.8 + 0.4 * random.nextDouble(), 0.8 + 0.4 * random.nextDouble()));
            points.add(new MyPoints(0.5 * random.nextDouble(), 0.5 * random.nextDouble()));
            points.add(new MyPoints(1.5 + 0.5 * random.nextDouble(), 0.5 * random.nextDouble()));
            points.add(new MyPoints(0.5 * random.nextDouble(), 1.5 + 0.5 * random.nextDouble()));
            points.add(new MyPoints(1.5 + 0.5 * random.nextDouble(), 1.5 + 0.5 * random.nextDouble()));
            points.add(new MyPoints(2 * random.nextDouble(), 2 * random.nextDouble()));

        }

        for (int i = 0; i < 75; i++) {
            points.add(new MyPoints(0.4 * random.nextDouble(), 0.8 + 0.4 * random.nextDouble()));
            points.add(new MyPoints(1.6 + 0.4 * random.nextDouble(), 0.8 + 0.4 * random.nextDouble()));
            points.add(new MyPoints(0.8 + 0.4 * random.nextDouble(), 0.3 + 0.4 * random.nextDouble()));
            points.add(new MyPoints(0.8 + 0.4 * random.nextDouble(), 1.3 + 0.4 * random.nextDouble()));


        }

        return points;
    }

    public static void main(String[] args) {
        List<MyPoints> points = createRandomPoints();
        writeRandomPointsToFile("points.txt", points);
    }


}

