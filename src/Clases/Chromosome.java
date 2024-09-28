/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author grobe
 */
public class Chromosome {
    //List<List<String>> routes; // Cada lista es un plan de ruta para un camion
    List<Gen> genes;
    double fitness;
    
    public Chromosome(List<List<String>> routes) {
        //this.routes = routes;
        Gen gen;
        Node inicio;
        genes = new ArrayList<>();
        for(int i=0;i<6;i++){
            if(i==4){
                inicio = new Node("Trujillo", "La Libertad", "Trujillo", -8.11176389, -79.02868652, "COSTA", 10000);
            }
            else if(i==5){
                inicio = new Node("Arequipa", "Arequipa", "Arequipa", -16.39881421, -71.537019649, "COSTA", 10000);
            }
            else{
                inicio = new Node("Lima", "Lima", "Lima", -12.046374, -77.042793, "COSTA", 10000);
            }
            gen = new Gen(90,"A00"+String.valueOf(i+1),inicio);
            genes.add(gen);
        }
        for (int i=0;i<15;i++){
            
            if(i>=7 && i<=9){
                inicio = new Node("Trujillo", "La Libertad", "Trujillo", -8.11176389, -79.02868652, "COSTA", 10000);
            }
            else if(i>9){
                inicio = new Node("Arequipa", "Arequipa", "Arequipa", -16.39881421, -71.537019649, "COSTA", 10000);
            }
            else{
                inicio = new Node("Lima", "Lima", "Lima", -12.046374, -77.042793, "COSTA", 10000);
            }
            gen = new Gen(45,"B00"+String.valueOf(i+1),inicio);
            genes.add(gen);
        }
        for (int i=0;i<24;i++){
            if(i>=10&& i<=15){
                inicio = new Node("Trujillo", "La Libertad", "Trujillo", -8.11176389, -79.02868652, "COSTA", 10000);
            }
            else if(i>15){
                inicio = new Node("Arequipa", "Arequipa", "Arequipa", -16.39881421, -71.537019649, "COSTA", 10000);
            }
            else{
                inicio = new Node("Lima", "Lima", "Lima", -12.046374, -77.042793, "COSTA", 10000);
            }
            gen = new Gen(30,"C00"+String.valueOf(i+1),inicio);
            genes.add(gen);
        }
        try {
            for (int i=0;i<genes.size();i++) {
                Gen gen_tmp = genes.get(i);
                gen_tmp.rutas = routes.get(i);
            }
        } catch (Exception e) {
            System.out.println("Error en constructor de cromosoma: " + e.getMessage());
        }
        
    }
    
    
    public double calculateFitness(Graph graph, Map<String, Integer> officeDemand) {
        double totalDistance = 0.0;
        Map<String, Integer> deliveredProducts = new HashMap<>();

        // Iterate over each truck's route
        for (Gen gen : genes) {
            //String currentStorage = route.get(0); //(Lima, Trujillo, or Arequipa)
            //String previousNode = currentStorage;
            //int truckLoad = 45; // Each truck starts with a full load of 45 units
                
            // Iterate over the offices in the truck's route
            for (int i = 1; i < gen.rutas.size(); i++) {
                //String office = route.get(i);
                //Gen gen_actual = genes.get(i);
                Node nodo_inicio;
                Node nodo_destino;
                if(i==0){
                    nodo_inicio = gen.getInicio();
                    
                }
                else{
                    nodo_inicio = graph.findNodeByCode(gen.rutas.get(i-1));
                }
                
                nodo_destino = graph.findNodeByCode(gen.rutas.get(i));
                // Calculate distance between the previous node and the current office
                //Node previousNodeObj = graph.getNode(previousNode);
                //Node currentOfficeObj = graph.getNode(office);
                totalDistance += graph.calculateDistance(nodo_inicio, nodo_destino);
                
                // Deliver products to the office
                //int remainingDemand = officeDemand.get(office) - deliveredProducts.getOrDefault(office, 0);
                //int productsDelivered = Math.min(remainingDemand, truckLoad); // Deliver only as much as the truck can carry
                //deliveredProducts.put(office, deliveredProducts.getOrDefault(office, 0) + productsDelivered);
                //truckLoad -= productsDelivered;

                // If the truck is empty, return to storage, reload, and continue
                //if (truckLoad == 0 && i < route.size() - 1) {
                    // Calculate distance back to the storage and reset truck load
                    //totalDistance += graph.calculateDistance(currentOfficeObj, graph.getNode(currentStorage));
                    //truckLoad = 45;
                    //previousNode = currentStorage; // Start from the storage again
                //} else {
                  //  previousNode = office;
                //}
            }
            return totalDistance;
            // After finishing the route, return to the storage
            //totalDistance += graph.calculateDistance(graph.getNode(previousNode), graph.getNode(currentStorage));
        }

        // Penalize if not all demand is fulfilled
//        double unmetDemandPenalty = 0;
//        for (String office : officeDemand.keySet()) {
//            int demand = officeDemand.get(office);
//            int delivered = deliveredProducts.getOrDefault(office, 0);
//            if (delivered < demand) {
//                unmetDemandPenalty += (demand - delivered) * 1000; // Heavy penalty for unmet demand
//            }
//        }

        // Fitness is the total distance plus any unmet demand penalty
        fitness = totalDistance; //+ unmetDemandPenalty;
        return fitness;
    }
    public List<List<String>> subList (int index_from,int index_to){
        List<List<String>> listaRutas = new ArrayList<>();
        for (int i=index_from;i<index_to;i++){
            listaRutas.add(genes.get(i).rutas);
        }
        return listaRutas;
    }
}