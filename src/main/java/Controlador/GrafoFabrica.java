/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.util.*;
/**
 *
 * @author profe
 */
public class GrafoFabrica {
    public Map<Integer, Nodo> nodos;

    public GrafoFabrica() {
        nodos = new HashMap<>();
    }

    public void agregarNodo(int id) {
        nodos.putIfAbsent(id, new Nodo(id));
    }

    public void agregarArista(int origen, int destino, int peso, double error) {
        if (!nodos.containsKey(origen)) {
            agregarNodo(origen);
        }
        if (!nodos.containsKey(destino)) {
            agregarNodo(destino);
        }
        nodos.get(origen).agregarArista(destino, peso, error);
        nodos.get(destino).agregarArista(origen, 0, error);
    }

    public Par<Integer, ArrayList<Par<Integer, Integer>>> cuellosDeBotella(int x, int y, boolean real) {
        int flujo = flujoMaximo(x, y, real);
        var lista = new ArrayList<Par<Integer, Integer>>();
        if(real){
            for (Nodo nodo : nodos.values()) {
                for (Arista arista : nodo.adyacentes) {
                    // Detectar aristas completamente saturadas
                    if (arista.flujoReal == arista.pesoIdeal && arista.pesoIdeal > 0) {
                        lista.add(new Par<>(nodo.id, arista.destino));
                    }
                }
            }
        }else{
            for (Nodo nodo : nodos.values()) {
                for (Arista arista : nodo.adyacentes) {
                    // Detectar aristas completamente saturadas
                    if (arista.flujo == arista.pesoIdeal && arista.pesoIdeal > 0) {
                        lista.add(new Par<>(nodo.id, arista.destino));
                    }
                }
            }
        }

        return new Par(flujo, lista);
    }

    private boolean bfs(Map<Integer, Integer> padre, int fuente, int sumidero, boolean real) {
        Set<Integer> visitado = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        cola.add(fuente);
        visitado.add(fuente);
        if(real){
            while (!cola.isEmpty()) {
                int actual = cola.poll();
                for (Arista arista : nodos.get(actual).adyacentes) {
                    if (!visitado.contains(arista.destino) && arista.pesoReal > arista.flujoReal) {
                        padre.put(arista.destino, actual);
                        visitado.add(arista.destino);
                        cola.add(arista.destino);
                        if (arista.destino == sumidero) {
                            return true;
                        }
                    }
                }
            }
        }else{
            while (!cola.isEmpty()) {
                int actual = cola.poll();
                for (Arista arista : nodos.get(actual).adyacentes) {
                    if (!visitado.contains(arista.destino) && arista.pesoIdeal > arista.flujo) {
                        padre.put(arista.destino, actual);
                        visitado.add(arista.destino);
                        cola.add(arista.destino);
                        if (arista.destino == sumidero) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int flujoMaximo(int fuente, int sumidero, boolean real) {
        int maxFlujo = 0;
        Map<Integer, Integer> padre = new HashMap<>();
        if(real){
            while (bfs(padre, fuente, sumidero, real)) {
                int flujoActual = Integer.MAX_VALUE;
                for (int v = sumidero; v != fuente; v = padre.get(v)) {
                    int u = padre.get(v);
                    for (Arista arista : nodos.get(u).adyacentes) {
                        if (arista.destino == v) {
                            flujoActual = Math.min(flujoActual, arista.pesoReal - arista.flujoReal);
                        }
                    }
                }

                for (int v = sumidero; v != fuente; v = padre.get(v)) {
                    int u = padre.get(v);
                    for (Arista arista : nodos.get(u).adyacentes) {
                        if (arista.destino == v) {
                            arista.flujoReal += flujoActual;
                        }
                    }
                    for (Arista arista : nodos.get(v).adyacentes) {
                        if (arista.destino == u) {
                            arista.flujoReal -= flujoActual;
                        }
                    }
                }

                maxFlujo += flujoActual;
            }
        }else{
            while (bfs(padre, fuente, sumidero, real)) {
                int flujoActual = Integer.MAX_VALUE;
                for (int v = sumidero; v != fuente; v = padre.get(v)) {
                    int u = padre.get(v);
                    for (Arista arista : nodos.get(u).adyacentes) {
                        if (arista.destino == v) {
                            flujoActual = Math.min(flujoActual, arista.pesoIdeal - arista.flujo);
                        }
                    }
                }

                for (int v = sumidero; v != fuente; v = padre.get(v)) {
                    int u = padre.get(v);
                    for (Arista arista : nodos.get(u).adyacentes) {
                        if (arista.destino == v) {
                            arista.flujo += flujoActual;
                        }
                    }
                    for (Arista arista : nodos.get(v).adyacentes) {
                        if (arista.destino == u) {
                            arista.flujo -= flujoActual;
                        }
                    }
                }

                maxFlujo += flujoActual;
            }
        }

        return maxFlujo;
    }
}
