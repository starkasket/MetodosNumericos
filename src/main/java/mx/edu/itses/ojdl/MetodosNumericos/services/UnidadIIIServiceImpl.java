package mx.edu.itses.ojdl.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Gauss;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Jacobi;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaCramer;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIIServiceImpl implements UnidadIIIService {

    @Override
    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer) {
        //Almacenamos los determinantes de las matrices
        ArrayList<Double> determinantes = new ArrayList<>();

        //Vector de incógnitas
        ArrayList<Double> vectorX = new ArrayList<>();
        // Tamaño del sistema líneal
        switch (modelCramer.getMN()) {
            case 2 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();

                double[][] MatrizA = {
                    {A.get(0), A.get(1)},
                    {A.get(2), A.get(3)}
                };

                determinantes.add(Det2(MatrizA));
                log.info("Det A: " + determinantes.get(0));
                // Calculamos determinante para X1
                double[][] MatrizX1 = {
                    {b.get(0), A.get(1)},
                    {b.get(1), A.get(3)}
                };
                determinantes.add(Det2(MatrizX1));
                log.info("Det X1: " + determinantes.get(1));

                // Calculamos determinante para X2
                double[][] MatrizX2 = {
                    {A.get(0), b.get(0)},
                    {A.get(2), b.get(1)}
                };
                determinantes.add(Det2(MatrizX2));
                log.info("Det X2: " + determinantes.get(2));

                // Resolviendo las variables
                //Para X1
                vectorX.add(determinantes.get(1) / determinantes.get(0));
                //Para X2
                vectorX.add(determinantes.get(2) / determinantes.get(0));
            }
            case 3 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();
                double[][] MatrizA = new double[modelCramer.getMN()][modelCramer.getMN()];
                double[][] MatrizX1 = new double[modelCramer.getMN()][modelCramer.getMN()];
                double[][] MatrizX2 = new double[modelCramer.getMN()][modelCramer.getMN()];
                double[][] MatrizX3 = new double[modelCramer.getMN()][modelCramer.getMN()];
                // MatrizA
                int index = 0;
                for (int i = 0; i < modelCramer.getMN(); i++) { //renglón
                    for (int j = 0; j < modelCramer.getMN(); j++) { //columna
                        MatrizA[i][j] = A.get(index);
                        index++;
                    }
                }

                // MatrizX1
                index = 0;
                for (int i = 0; i < modelCramer.getMN(); i++) { //renglón
                    for (int j = 0; j < modelCramer.getMN(); j++) { //columna
                        if (j == 0) {
                            MatrizX1[i][j] = b.get(i);
                            index++;
                        } else {
                            MatrizX1[i][j] = A.get(index);
                            index++;
                        }
                    }
                }

                //MatrizX2
                index = 0;
                for (int i = 0; i < modelCramer.getMN(); i++) { //renglón
                    for (int j = 0; j < modelCramer.getMN(); j++) { //columna
                        if (j == 1) {
                            MatrizX2[i][j] = b.get(i);
                            index++;
                        } else {
                            MatrizX2[i][j] = A.get(index);
                            index++;
                        }
                    }
                }

                //MatrizX3
                index = 0;
                for (int i = 0; i < modelCramer.getMN(); i++) { //renglón
                    for (int j = 0; j < modelCramer.getMN(); j++) { //columna
                        if (j == 2) {
                            MatrizX3[i][j] = b.get(i);
                            index++;
                        } else {
                            MatrizX3[i][j] = A.get(index);
                            index++;
                        }
                    }
                }

                determinantes.add(Det3(MatrizA));

                determinantes.add(Det3(MatrizX1));
                determinantes.add(Det3(MatrizX2));
                determinantes.add(Det3(MatrizX3));

                // Resolviendo las variables
                //Para X1
                vectorX.add(determinantes.get(1) / determinantes.get(0));
                //Para X2
                vectorX.add(determinantes.get(2) / determinantes.get(0));
                //Para X3
                vectorX.add(determinantes.get(3) / determinantes.get(0));
            }
        }
        modelCramer.setVectorX(vectorX);
        modelCramer.setDeterminantes(determinantes);
        return modelCramer;
    }

    private double Det2(double[][] A) {
        return A[0][0] * A[1][1] - A[0][1] * A[1][0];
    }

    private double Det3(double[][] A) {
        double D = A[0][0] * Det2(new double[][]{
            {A[1][1], A[1][2]},
            {A[2][1], A[2][2]}
        })
                - A[0][1] * Det2(new double[][]{
            {A[1][0], A[1][2]},
            {A[2][0], A[2][2]}
        }) + A[0][2] * Det2(new double[][]{
            {A[1][0], A[1][1]},
            {A[2][0], A[2][1]}
        });

        return D;
    }

    @Override
    public Gauss AlgoritmoGauss(Gauss modelGauss) {

        ArrayList<Double> vectorX = new ArrayList<>();
        int n = modelGauss.getMN();

        double[][] A = new double[n][n];
        double[] b = new double[n];

        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = modelGauss.getMatrizA().get(index);
                index++;
            }
        }
        for (int i = 0; i < n; i++) {
            b[i] = modelGauss.getVectorB().get(i);
        }

        for (int k = 0; k < n - 1; k++) {
            int maxRow = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(A[i][k]) > Math.abs(A[maxRow][k])) {
                    maxRow = i;
                }
            }

            if (maxRow != k) {
                double[] tempRow = A[k];
                A[k] = A[maxRow];
                A[maxRow] = tempRow;

                double tempB = b[k];
                b[k] = b[maxRow];
                b[maxRow] = tempB;
            }

            for (int i = k + 1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                for (int j = k; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }
                b[i] -= factor * b[k];
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double suma = b[i];
            for (int j = i + 1; j < n; j++) {
                suma -= A[i][j] * x[j];
            }
            x[i] = suma / A[i][i];
        }

        for (double val : x) {
            vectorX.add(val);
        }

        modelGauss.setVectorX(vectorX);
        return modelGauss;

    }

    @Override
    public GaussJordan AlgoritmoGaussJordan(GaussJordan modelGaussJordan) {
        ArrayList<Double> vectorX = new ArrayList<>();
        int n = modelGaussJordan.getMN();

        double[][] A = new double[n][n];
        double[] b = new double[n];

        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = modelGaussJordan.getMatrizA().get(index);
                index++;
            }
        }
        for (int i = 0; i < n; i++) {
            b[i] = modelGaussJordan.getVectorB().get(i);
        }

        for (int i = 0; i < n; i++) {
            int maxRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[maxRow][i])) {
                    maxRow = j;
                }
            }
            if (maxRow != i) {
                double[] tempRow = A[i];
                A[i] = A[maxRow];
                A[maxRow] = tempRow;

                double tempB = b[i];
                b[i] = b[maxRow];
                b[maxRow] = tempB;
            }

            double pivot = A[i][i];
            for (int k = 0; k < n; k++) {
                A[i][k] /= pivot;
            }
            b[i] /= pivot;

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = A[j][i];
                    for (int k = 0; k < n; k++) {
                        A[j][k] -= factor * A[i][k];
                    }
                    b[j] -= factor * b[i];
                }
            }

        }

        for (int i = 0; i < n; i++) {
            vectorX.add(b[i]);
        }

        modelGaussJordan.setVectorX(vectorX);
        return modelGaussJordan;

    }

    @Override
    public ArrayList<Jacobi> AlgoritmoJacobi(Jacobi modelJacobi) {
        int n = modelJacobi.getMN();
        ArrayList<Jacobi> respuesta = new ArrayList<>();

        double[][] A = new double[n][n];
        double[] b = new double[n];
        double[] c = new double[n];
        double[] ck = new double[n];
        double[] Ea = new double[n];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = modelJacobi.getMatrizA().get(index);
                index++;
            }
        }

        for (int i = 0; i < n; i++) {
            b[i] = modelJacobi.getVectorB().get(i);
            c[i] = modelJacobi.getC().get(i);
        }

        for (int it = 1; it <= modelJacobi.getIteracionesMaximas(); it++) {
            for (int j = 0; j < n; j++) {
                double suma = 0;
                for (int k = 0; k < n; k++) {
                    if (k != j) {
                        suma += A[j][k] * c[k];
                    }
                }
                ck[j] = (b[j] - suma) / A[j][j];
            }

            for (int j = 0; j < n; j++) {
                Ea[j] = Funciones.ErrorRelativo(ck[j], c[j]);
            }

            Jacobi renglon = new Jacobi();
            renglon.setIteracion(it);
            ArrayList<Double> cAnterior = new ArrayList<>();

            for (double val : c) {
                cAnterior.add(val);
            }
            renglon.setCAnterior(cAnterior);
            ArrayList<Double> cNuevo = new ArrayList<>();

            for (double val : ck) {
                cNuevo.add(val);
            }
            renglon.setCNuevo(cNuevo);
            ArrayList<Double> errores = new ArrayList<>();

            for (double val : Ea) {
                errores.add(val);
            }
            renglon.setErrores(errores);
            respuesta.add(renglon);

            boolean stop = true;
            for (double err : Ea) {
                if (err > modelJacobi.getEa()) {
                    stop = false;
                    break;
                }
            }
            if (stop) {
                break;
            }

            for (int j = 0; j < n; j++) {
                c[j] = ck[j];
            }
        }

        return respuesta;

    }

    @Override
    public ArrayList<GaussSeidel> AlgoritmoGaussSeidel(GaussSeidel modelGaussSeidel) {

        int n = modelGaussSeidel.getMN();
        ArrayList<GaussSeidel> respuesta = new ArrayList<>();

        double[][] A = new double[n][n];
        double[] b = new double[n];
        double[] c = new double[n];  
        double[] cNuevo = new double[n];
        double[] Ea = new double[n];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = modelGaussSeidel.getMatrizA().get(index);
                index++;
            }
        }

        for (int i = 0; i < n; i++) {
            b[i] = modelGaussSeidel.getVectorB().get(i);
            c[i] = modelGaussSeidel.getC().get(i);
        }

        for (int it = 1; it <= modelGaussSeidel.getIteracionesMaximas(); it++) {
            for (int j = 0; j < n; j++) {
                double suma = 0;
                for (int k = 0; k < n; k++) {
                    if (k != j) {
                        suma += A[j][k] * (k < j ? cNuevo[k] : c[k]);
                    }
                }
                cNuevo[j] = (b[j] - suma) / A[j][j];
            }

            for (int j = 0; j < n; j++) {
                Ea[j] = Funciones.ErrorRelativo(cNuevo[j], c[j]);
            }

            GaussSeidel renglon = new GaussSeidel();
            renglon.setIteracion(it);

            ArrayList<Double> cAnterior = new ArrayList<>();
            for (double val : c) {
                cAnterior.add(val);
            }
            renglon.setCAnterior(cAnterior);

            ArrayList<Double> cNuevos = new ArrayList<>();
            for (double val : cNuevo) {
                cNuevos.add(val);
            }
            renglon.setCNuevo(cNuevos);

            ArrayList<Double> errores = new ArrayList<>();
            for (double val : Ea) {
                errores.add(val);
            }
            renglon.setErrores(errores);

            respuesta.add(renglon);

            boolean stop = true;
            for (double err : Ea) {
                if (err > modelGaussSeidel.getEa()) {
                    stop = false;
                    break;
                }
            }
            if (stop) {
                break;
            }
            for (int j = 0; j < n; j++) {
                c[j] = cNuevo[j];
            }
        }

        return respuesta;

    }

}
