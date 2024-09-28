/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Roberto
 */
public class AlgoritmoGenetico {
    private Graph graph;
    private Map<String, Integer> officeDemand;
    private List<Chromosome> population;

    public AlgoritmoGenetico(Graph graph, Map<String, Integer> officeDemand) {
        this.graph = graph;
        this.officeDemand = officeDemand;
        this.population = new ArrayList<>();
    }

    
    public void poblacionInicial(int tamanoPoblacion) {
        for (int i = 0; i < tamanoPoblacion; i++) {
            List<List<String>> randomRoutes = generarRutasAleatorias();
            Chromosome chromosome = new Chromosome(randomRoutes);
            population.add(chromosome);
        }
    }

    // 
    private List<List<String>> generarRutasAleatorias() {
        List<List<String>> routes = new ArrayList<>();
        List<String> offices = new ArrayList<>(officeDemand.keySet());
        Collections.shuffle(offices); // Shuffle the offices to generate random routes
        for (int i = 0; i < 45; i++) {
            Collections.shuffle(offices);
            List<String> route = new ArrayList<>();
            //route.add("Lima"); // Trucks start from Lima, Trujillo, or Arequipa (adjust this as needed)
            //int officesToVisit = offices.size() / trucksPerStorage;
            for (int j=0;j<5;j++){
                route.add(offices.get(j));
            }
            routes.add(route);
            // Assign offices to this truck
//            for (int j = 0; j < officesToVisit && !offices.isEmpty(); j++) {
//                route.add(offices.remove(0)); // Assign office to this truck
//            }
//            routes.add(route);
        }

        return routes;
    }

    // Selection process
    public Chromosome escogerPadre() {
        return population.get(new Random().nextInt(population.size())); // Placeholder for selection method (e.g., roulette wheel or tournament)
    }

    
    public Chromosome cruzar(Chromosome padre1, Chromosome padre2) {
       
        List<List<String>> rutaHijo = new ArrayList<>();
        
        int index_quiebre = padre1.genes.size() / 2;
        rutaHijo.addAll(padre1.subList(0, index_quiebre));
        rutaHijo.addAll(padre2.subList(index_quiebre, padre2.genes.size()));
        return new Chromosome(rutaHijo);
    }

    
    public void mutar(Chromosome chromosome) {
        
        int genIndex = new Random().nextInt(chromosome.genes.size());
        List<String> route = chromosome.genes.get(genIndex).getRuta();
        //Altera al azar la rutas origen destino
        Collections.shuffle(route.subList(1, route.size()));
    }

    
    public Chromosome ejecutar(int generations) {
        for (int generation = 0; generation < generations; generation++) {
            // Calcular valor de los individuos
            for (Chromosome chromosome : population) {
                chromosome.calculateFitness(graph, officeDemand);
            }

            // Ordenar por valor de individuos
            population.sort(Comparator.comparingDouble(c -> c.fitness));

            // Se cruzan padres y nacen hijos
            List<Chromosome> newPopulation = new ArrayList<>();
            while (newPopulation.size() < population.size()) {
                Chromosome padre1 = escogerPadre();
                Chromosome padre2 = escogerPadre();
                Chromosome hijo = cruzar(padre1, padre2);
                mutar(hijo);
                newPopulation.add(hijo);
                //System.out.println(newPopulation.size());
                //System.out.println(population.size());
                //System.out.println("====================");
            }

            population = newPopulation; // Update population with new generation

            
            System.out.println("Generacion " + generation + ": Valor Generacion = " + population.get(0).fitness);
        }

        // Mejor solucion
        return population.get(0);
    }
}
