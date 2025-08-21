package mx.edu.itses.ojdl.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Jacobi {

    private int MN;
    private ArrayList<Double> matrizA;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> C;
    private int IteracionesMaximas;
    private double Ea;
    private ArrayList<Double> errores;
     private int iteracion;
    private ArrayList<Double> cAnterior;
    private ArrayList<Double> cNuevo;

}
