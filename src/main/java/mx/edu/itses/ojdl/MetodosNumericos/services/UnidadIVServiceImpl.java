package mx.edu.itses.ojdl.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.ojdl.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Lagrange;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIVServiceImpl implements UnidadIVService {

    @Override
    public DDNewton algoritmoDDNewton(DDNewton DDNewton) {
        int n = DDNewton.getN();
        ArrayList<Double> x = DDNewton.getXValues();
        ArrayList<Double> y = DDNewton.getYValues();

        ArrayList<ArrayList<Double>> tabla = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tabla.add(new ArrayList<>());
            tabla.get(i).add(y.get(i));
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                double num = tabla.get(i + 1).get(j - 1) - tabla.get(i).get(j - 1);
                double den = x.get(i + j) - x.get(i);
                double dif = num / den;
                tabla.get(i).add(dif);
            }
        }

        double evalPoint = DDNewton.getEvalPoint();
        double result = tabla.get(0).get(0);
        double product = 1.0;

        for (int j = 1; j < n; j++) {
            product *= (evalPoint - x.get(j - 1));
            result += tabla.get(0).get(j) * product;
        }

        DDNewton.setTablaDiferencias(tabla);
        DDNewton.setResult(result);

        return DDNewton;
    }

    @Override
    public Lagrange algoritmoLagrange(Lagrange lagrange) {
        int n = lagrange.getN();
        ArrayList<Double> x = lagrange.getXValues();
        ArrayList<Double> y = lagrange.getYValues();

        double evalPoint = lagrange.getEvalPoint();
        double result = 0.0;

        for (int i = 0; i < n; i++) {
            double termino = y.get(i);
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    termino *= (evalPoint - x.get(j)) / (x.get(i) - x.get(j));
                }
            }
            result += termino;
        }

        lagrange.setResult(result);
        return lagrange;

    }

}
