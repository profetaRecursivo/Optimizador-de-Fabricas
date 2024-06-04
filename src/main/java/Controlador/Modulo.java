/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author profe
 */
public class Modulo {
    public int index;
    public String nombre;
    public double factorError;
    public int empleados;
    public double habilidad;
    public Modulo(int id, String n,double facError, int cantEmp, double habEmp){
        index = id;
        nombre = n;
        factorError = facError;
        empleados = cantEmp;
        habilidad = habEmp;
    }
}
