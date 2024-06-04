/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author profe
 */
public class Par<T, E> {
    public T first;
    public E second;
    
    public Par(T f, E s) {
        first = f;
        second = s;
    }
    
    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (otro == null || getClass() != otro.getClass()) return false;
        Par<?, ?> otrito = (Par<?, ?>) otro;
        return first.equals(otrito.first) && second.equals(otrito.second);
    }
    
    public String toString(){
        return "("+first.toString()+", "+second.toString()+")";
    }
}
