/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Roberto
 */
public class Node {
    String code;
    String region;
    String city;
    double latitude;
    double longitude;
    String zone;
    int capacity;

    public Node(String code, String region, String city, double latitude, double longitude, String zone, int capacity) {
        this.code = code;
        this.region = region;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zone = zone;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Node{" +
                "codigo='" + code + '\'' +
                ", departamento='" + region + '\'' +
                ", ciudad='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", region='" + zone + '\'' +
                ", capacidad=" + capacity +
                '}';
    }
}

