/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.*;
import java.util.*;

//public class Graph {
//    private Map<String, Node> nodes;
//    private Map<String, List<String>> adjList;
//
//    public Graph() {
//        nodes = new HashMap<>();
//        adjList = new HashMap<>();
//    }
//
//    // Method to read nodes from the file
//    public void readNodesFromFile(String filename) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(filename));
//        String line;
//        while ((line = br.readLine()) != null) {
//            line = line.trim(); // Remove leading/trailing spaces
//            if (line.isEmpty()) {
//                continue; // Skip empty lines
//            }
//
//            String[] parts = line.split(",");
//            if (parts.length != 7) { // Ensure the line has exactly 7 components
//                System.out.println("Skipping incorrectly formatted node line: " + line);
//                continue; // Skip any line that doesn't match the expected format
//            }
//
//            String code = parts[0];
//            String region = parts[1];
//            String city = parts[2];
//            double latitude = Double.parseDouble(parts[3]);
//            double longitude = Double.parseDouble(parts[4]);
//            String zone = parts[5];
//            int capacity = Integer.parseInt(parts[6]);
//
//            Node node = new Node(code, region, city, latitude, longitude, zone, capacity);
//            nodes.put(code, node);
//            adjList.put(code, new ArrayList<>()); // Initialize adjacency list for the node
//        }
//        br.close();
//    }
//
//    // Method to read relationships between nodes from the file
//    public void readRelationshipsFromFile(String filename) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(filename));
//        String line;
//        while ((line = br.readLine()) != null) {
//            line = line.trim(); // Remove leading/trailing spaces
//            if (line.isEmpty()) {
//                continue; // Skip empty lines
//            }
//
//            // Ensure the format "X => Y" is correct
//            if (!line.contains(" => ")) {
//                System.out.println("Skipping incorrectly formatted relationship line: " + line);
//                continue;
//            }
//
//            String[] parts = line.split(" => ");
//            if (parts.length != 2) {
//                System.out.println("Skipping incorrectly formatted relationship line: " + line);
//                continue;
//            }
//
//            String sourceCode = parts[0].trim();
//            String destinationCode = parts[1].trim();
//
//            // Check if both nodes exist in the graph before adding the relationship
//            if (nodes.containsKey(sourceCode) && nodes.containsKey(destinationCode)) {
//                adjList.get(sourceCode).add(destinationCode);
//            } else {
//                System.out.println("Invalid relationship: " + sourceCode + " => " + destinationCode);
//            }
//        }
//        br.close();
//    }
//
//    // Method to print all nodes
//    public void printNodes() {
//        for (Node node : nodes.values()) {
//            System.out.println(node);
//        }
//    }
//
//    // Method to print all relationships
//    public void printRelationships() {
//        for (String source : adjList.keySet()) {
//            List<String> destinations = adjList.get(source);
//            for (String destination : destinations) {
//                System.out.println(source + " => " + destination);
//            }
//        }
//    }
//    public double calculateDistance(Node node1, Node node2) {
//        // Haversine formula to calculate the great-circle distance
//        final int EARTH_RADIUS = 6371; // Earth radius in kilometers
//
//        double lat1 = Math.toRadians(node1.latitude);
//        double lon1 = Math.toRadians(node1.longitude);
//        double lat2 = Math.toRadians(node2.latitude);
//        double lon2 = Math.toRadians(node2.longitude);
//
//        double deltaLat = lat2 - lat1;
//        double deltaLon = lon2 - lon1;
//
//        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
//                + Math.cos(lat1) * Math.cos(lat2)
//                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        // Distance in kilometers
//        return EARTH_RADIUS * c;
//    }
//}

public class Graph {
    private Map<String, Node> nodes;
    private Map<String, List<String>> adjList;

    public Graph() {
        nodes = new HashMap<>();
        adjList = new HashMap<>();
    }

    // Add a node to the graph
    public void addNode(Node node) {
        nodes.put(node.code, node);
        adjList.put(node.code, new ArrayList<>());
    }

    // Retrieve a node from the graph
    public Node getNode(String nodeCode) {
        return nodes.get(nodeCode);
    }

    // Haversine formula to calculate distance between two nodes
    public double calculateDistance(Node node1, Node node2) {
        final int EARTH_RADIUS = 6371; // Earth radius in kilometers

        // Convert latitudes and longitudes from degrees to radians
        double lat1Rad = Math.toRadians(node1.latitude);
        double lon1Rad = Math.toRadians(node1.longitude);
        double lat2Rad = Math.toRadians(node2.latitude);
        double lon2Rad = Math.toRadians(node2.longitude);

        
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        
        return EARTH_RADIUS * c;
    }
    
    public void readNodesFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim(); 
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length != 7) { 
                System.out.println("Skipping incorrectly formatted node line: " + line);
                continue; 
            }

            String code = parts[0];
            String region = parts[1];
            String city = parts[2];
            double latitude = Double.parseDouble(parts[3]);
            double longitude = Double.parseDouble(parts[4]);
            String zone = parts[5];
            int capacity = Integer.parseInt(parts[6]);

            Node node = new Node(code, region, city, latitude, longitude, zone, capacity);
            nodes.put(code, node);
            adjList.put(code, new ArrayList<>()); 
        }
        br.close();
    }

    
    public void readRelationshipsFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim(); 
            if (line.isEmpty()) {
                continue; 
            }

            
            if (!line.contains(" => ")) {
                System.out.println("Skipping incorrectly formatted relationship line: " + line);
                continue;
            }

            String[] parts = line.split(" => ");
            if (parts.length != 2) {
                System.out.println("Skipping incorrectly formatted relationship line: " + line);
                continue;
            }

            String sourceCode = parts[0].trim();
            String destinationCode = parts[1].trim();

           
            if (nodes.containsKey(sourceCode) && nodes.containsKey(destinationCode)) {
                adjList.get(sourceCode).add(destinationCode);
            } else {
                System.out.println("Invalid relationship: " + sourceCode + " => " + destinationCode);
            }
        }
        br.close();
    }

    
    public void printNodes() {
        for (Node node : nodes.values()) {
            System.out.println(node);
        }
    }
    
    public Node findNodeByCode(String searchCode) {
        return nodes.get(searchCode); // Fetch node directly using its code
    }

    
    public void printRelationships() {
        for (String source : adjList.keySet()) {
            List<String> destinations = adjList.get(source);
            for (String destination : destinations) {
                System.out.println(source + " => " + destination);
            }
        }
    }
    
}