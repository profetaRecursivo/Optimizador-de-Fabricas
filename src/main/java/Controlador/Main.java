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
public class Main
{
    private static String [] nombres;
    private static Modulo [] modulos;
    
    public void run(){
        Scanner sc =  new Scanner(System.in);
        int nodos;System.out.println("Ingrese la cantidad de nodos");
        nodos = sc.nextInt();
        System.out.println("Ingrese la cantidad de aristas");
        int edges = sc.nextInt();
        System.out.println("Ahora ingrese los modulos de la siguiente manera\nNombre, factor de error, cantidad de empleados y habilidad por empleado por empleado");
        GrafoFabrica fabrica = new GrafoFabrica();
        for(int i = 0;i<nodos; i++){
            String nom = sc.nextLine();
            double facEr = sc.nextDouble();
            int cantEmp = sc.nextInt();
            double habEmp = sc.nextDouble();
            nombres[i] = nom;
            modulos[i] = new Modulo(i, nom, facEr, cantEmp, habEmp);
            fabrica.agregarNodo(i);
        }   

        System.out.println("Ahora por favor ingrese el origen y destino, en nombres, indique la cantidad ideal de productos que despacha esa conexion");
        for(int i = 0;i<edges; i++){
            String origen = sc.nextLine();
            String destino = sc.nextLine();
            int peso = sc.nextInt();
            Modulo origenMod = getModuloAt(origen);
            Modulo destinoMod = getModuloAt(destino);
            fabrica.agregarArista(origenMod.index, destinoMod.index, peso, origenMod.factorError);
        }
        System.out.println("Ahora ingrese el nombre del modulo que ingresa la materia prima y el nombre del modulo que despacha la materia prima");
        String fuente = sc.nextLine();
        String sumidero = sc.nextLine();
        int indexFuente = getModuloAt(fuente).index;
        int indexSumidero = getModuloAt(sumidero).index;
        System.out.println("Estimacion ideal: ");
        var parIdeal = fabrica.cuellosDeBotella(indexFuente, indexSumidero, false);
        System.out.println("Cantidad de productos despachados: " + parIdeal.first);
        var listaAristas = parIdeal.second;
        for(var culpable:listaAristas){
            int nodoCulpable = culpable.first;
            System.out.println("El modulo "+nombres[nodoCulpable]+" tiene elementos que podrian ser mejorados");
            System.out.println(calcularMejora(nodoCulpable));
        }
        
        System.out.println("Estimacion en el caso real tomando en cuenta los errores indicados");
        var parReal = fabrica.cuellosDeBotella(indexFuente,indexSumidero, true);
        System.out.println("Cantidad de productos despachados: " + parReal.first);
        var listaAristasReal = parReal.second;
        for(var culpable:listaAristasReal){
            int nodoCulpable = culpable.first;
            System.out.println("El modulo "+nombres[nodoCulpable]+" tiene elementos que podrian ser mejorados");
            System.out.println(calcularMejora(nodoCulpable));
        }
    }

    String calcularMejora(int index){
        String res = "";
        ArrayList<String> mejoras = new ArrayList<String>();
        Modulo mod = modulos[index];
        if(mod.habilidad < 0.5){
            mejoras.add("Puedes considerar que a este modulo se mejore la eficiencia de los empleados");
        }else{
            mejoras.add("Puedes considerar que a este modulo se le aumente la cantidad de empleados");
        }
        mejoras.add("Este modulo tiene una perdida bastante significativa, comete el error de: "+(mod.factorError*100)+" considera mejorar la calidad de las maquinas o aplicar otro tipo de mejora para reducir el error en este modulo");
        for(String mejora:mejoras){
            res+=mejora+"\n";
        }
        return res;
    }

    private static Modulo getModuloAt(String obj){
        for(Modulo mod:modulos){
            if(mod.nombre.equals(obj)){
                return mod;
            }
        }
        return null;
    }
}