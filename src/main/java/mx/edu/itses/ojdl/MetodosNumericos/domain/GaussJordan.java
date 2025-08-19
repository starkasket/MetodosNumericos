
package mx.edu.itses.ojdl.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class GaussJordan {
       private int MN;
    private ArrayList<Double> matrizA;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> vectorX;
    private ArrayList<Double> Determinantes;
    private int NumeroRenglon;
    
    public int IncrementarRenglon(){
        return NumeroRenglon++;
    }
}
