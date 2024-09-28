/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roberto
 */
public class Gen {
    private int capacidad;
    private String codigo;
    private Node inicio;
    List<String> rutas;
    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the inicio
     */
    public Node getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Node inicio) {
        this.inicio = inicio;
    }
    
    
    public Gen(int capacidad,String codigo,Node inicio){
        this.capacidad=capacidad;
        this.codigo = codigo;
        this.inicio= inicio;
        this.rutas = new ArrayList<>();
    }
    public List<String> getRuta(){
        return this.rutas;
    }
    
    public void setRuta(List<String> newRuta){
        this.rutas= newRuta;
    }
    
    public void addRuta(String point){
        this.rutas.add(point);
    }
    
}
