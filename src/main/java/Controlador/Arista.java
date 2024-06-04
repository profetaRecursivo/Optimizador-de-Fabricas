/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author profe
 */

public class Arista {
    public int destino;
    public int pesoReal;
    public int pesoIdeal;
    public int flujo;
    public int flujoReal;
    public double error;
    public Arista(int destino, int pesoIdeal, double error) {
        this.destino = destino;
        this.pesoIdeal= pesoIdeal;
        flujo = 0;
        flujoReal = 0;
        this.error = error;
        pesoReal = pesoIdeal - (int)Math.round(((pesoIdeal + 0.0)*error));
    }
}
