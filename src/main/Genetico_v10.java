/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import Clases.Graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import Clases.AlgoritmoGenetico;
import Clases.Chromosome;
import Clases.Node;
/**
 *
 * @author Roberto
 */
public class Genetico_v10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //graph contiene:
            //-nodes:   Un HashMap que almacena los nodos del grafo, donde cada clave es el código del nodo (como una ciudad o ubicación) y el valor es el propio objeto Node.
            //-adjList: Un HashMap que almacena la lista de adyacencia del grafo, donde cada clave es el código de un nodo y el valor es una lista de los códigos de los nodos adyacentes (conexiones directas).
            Graph graph = new Graph();

            //oficinas : Nodos
            // Por cada línea válida, se crea un objeto Node con los detalles de cada nodo (como código, región, ciudad, latitud, longitud, zona, y capacidad).
            // Luego, los nodos se almacenan en el mapa nodes del grafo y también se inicializan sus listas de adyacencia en adjList.
            graph.readNodesFromFile("C:/Users/Roberto/Downloads/oficinas.txt"); // File with node details
            
            //Tramos : Relación entre los nodos.
            //Leemos el archivo. Cada línea del archivo describe una relación en el formato "X => Y", donde X es un nodo de origen y Y es un nodo de destino
            //añade estas relaciones a la lista de adyacencia (adjList) del grafo, indicando qué nodos están conectados entre sí.
            graph.readRelationshipsFromFile("C:/Users/Roberto/Downloads/tramos.txt"); // File with relationships

            //Hasta aqui todo el grafo está lleno

            //Oficinas que necesitan productos y cuantos
            //Leemos ventas.txt que contiene la demanda de cada oficina (nodo)
            //Map<String, Integer>: la clave (String) representa el código de la oficina, y el valor (Integer) representa la cantidad de demanda o ventas asociada a esa oficina.
            Map<String, Integer> officeDemand = readDemandFromFile("C:/Users/Roberto/Downloads/ventas.txt");


            //ga: Proporciona la estructura del grafo con las oficinas y sus conexiones con la demanda de cada oficina.
            AlgoritmoGenetico ga = new AlgoritmoGenetico(graph, officeDemand);

//--------------------------------------------------------------------------------------------------
            ga.poblacionInicial(100); // Initialize population with 100 chromosomes and 45 trucks
            //Node lima = new Node("Lima", "Lima", "Lima", -12.046374, -77.042793, "COSTA", 10000);
            //Node trujillo = new Node("Trujillo", "La Libertad", "Trujillo", -8.11176389, -79.02868652, "COSTA", 10000);
            //Node arequipa = new Node("Arequipa", "Arequipa", "Arequipa", -16.39881421, -71.537019649, "COSTA", 10000);
            //graph.addNode(lima);
            //graph.addNode(trujillo);
            //graph.addNode(arequipa);

            //1000 generaciones. El máximo serán 100 individuos. 
            // Al terminar las iteraciones ya tenemos los costos de cada individuo y finalmente devuelve la mejor solución el cual 
            //sera como cromosoma.
            //En este minuto no tenemos la mejor solución.
            //Los grafos se están leyendo bien
            //Oficinas, generación aleatoria de rutas inicial, cruce entre dos individuos se está creando correctamente. 
            Chromosome bestSolution = ga.ejecutar(1000);
//            for (Map.Entry<String, Integer> entry : officeDemand.entrySet()) {
//                System.out.println("Office Code: " + entry.getKey() + ", Total Demand: " + entry.getValue());
//            }
            //System.out.println("Nodes:");
            //graph.printNodes();
            //System.out.println("\nRelationships:");
            //graph.printRelationships();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Map<String, Integer> readDemandFromFile(String filePath) {
        Map<String, Integer> officeDemand = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                
                String[] parts = line.split("=>");

                if (parts.length >= 2) {
                    
                    String[] officeData = parts[1].split(",");
                    if (officeData.length >= 2) {
                        String officeCode = officeData[0].trim(); // "230101"
                        int demand = Integer.parseInt(officeData[1].trim()); // "4"

                        // Update the demand for the office in the map
                        officeDemand.put(officeCode, officeDemand.getOrDefault(officeCode, 0) + demand);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return officeDemand;
    }
}
