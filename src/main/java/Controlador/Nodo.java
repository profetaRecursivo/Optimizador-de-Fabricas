/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author profe
 */
import java.util.*;
public class Nodo {
    public int id;
    public List<Arista> adyacentes;
    public double error;
    public Nodo(int id) {
        this.id = id;
        this.adyacentes = new ArrayList<>();
    }

    public void agregarArista(int destino, int peso, double error) {
        Arista arista = new Arista(destino, peso, error);
        adyacentes.add(arista);
    }
}