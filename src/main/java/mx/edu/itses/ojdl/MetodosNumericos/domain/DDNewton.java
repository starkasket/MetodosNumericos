
package mx.edu.itses.ojdl.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class DDNewton {
       private int n;  
    private ArrayList<Double> xValues  = new ArrayList<>();; 
    private ArrayList<Double> yValues  = new ArrayList<>();; 
    private double evalPoint;          
    private double result;             
    
    private ArrayList<ArrayList<Double>> tablaDiferencias;
 
}
