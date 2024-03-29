import javax.swing.*;
import java.awt.*;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[] MValues = {3, 6, 9, 12}; // Different values of M
        int numberOfRuns = 15; // Number of runs

        Map<Integer, List<MyPoints>> bestCentroidsByM = new HashMap<>();
        Map<Integer, Double> minErrorByM = new HashMap<>();

        for (int M : MValues) {
            double minError = Double.MAX_VALUE;
            MyPoints bestCentroid = null;

            for (int i = 0; i < numberOfRuns; i++) {
                List<MyPoints> points = loadPointsFromFile("points.txt");

                Kmeans kMeans = new Kmeans(points, M);
                List<MyPoints> centroids = kMeans.runKMeans();

                double clusteringError = kMeans.calculateClusteringError();

                if (clusteringError < minError) {
                    minError = clusteringError;
                    bestCentroid = centroids.get(0); // Update the best centroids
                }
            }

            minErrorByM.put(M, minError);

            // Store the best centroid for this M value
            List<MyPoints> bestCentroidList = new ArrayList<>();
            bestCentroidList.add(bestCentroid);
            bestCentroidsByM.put(M, bestCentroidList);

        }

        // Print minimum errors for each M
        for (Map.Entry<Integer, Double> entry : minErrorByM.entrySet()) {
            System.out.println("Minimum Clustering Error for M=" + entry.getKey() + ": " + entry.getValue());
        }

        // Save best centroids for each M
        for (Map.Entry<Integer, List<MyPoints>> entry : bestCentroidsByM.entrySet()) {
            saveCentroidsToFile("best_centroids_M" + entry.getKey() + ".txt", entry.getValue());
        }
        // Display plots for best centroids by M value



        List<List<MyPoints>> allCentroids = new ArrayList<>();
        for (Map.Entry<Integer, List<MyPoints>> entry : bestCentroidsByM.entrySet()) {
            allCentroids.add(entry.getValue());
        }

        JFrame frame = new JFrame("Combined Plot for All M Values");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Plot.displayPlot(loadPointsFromFile("points.txt"), allCentroids);



    }
    private static List<MyPoints> loadPointsFromFile(String filename) {
        List<MyPoints> points = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] coordinates = line.split(" ");

                if (coordinates.length >= 2) {
                    double x = Double.parseDouble(coordinates[0]);
                    double y = Double.parseDouble(coordinates[1]);

                    points.add(new MyPoints(x, y));
                } else {
                    System.err.println("Invalid data format in the file for line: " + line);
                    // Handle or log the invalid data format as needed
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return points;
    }

    private static void saveCentroidsToFile(String filename, List<MyPoints> centroids) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (MyPoints centroid : centroids) {
                writer.write(centroid.getX() + " " + centroid.getY());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }


}


