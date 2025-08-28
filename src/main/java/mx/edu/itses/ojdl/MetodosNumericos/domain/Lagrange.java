package mx.edu.itses.ojdl.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Lagrange {
    private int n;
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;
    private double evalPoint;
    private double result;
}
