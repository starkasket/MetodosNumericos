package mx.edu.itses.ojdl.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Gauss;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Jacobi;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaCramer;

public interface UnidadIIIService {
    public ReglaCramer AlgoritmoReglaCramer (ReglaCramer modelCramer);
    
    public Gauss AlgoritmoGauss (Gauss modelGauss);
    
    public GaussJordan AlgoritmoGaussJordan (GaussJordan modelGaussJordan);
    
    public ArrayList<Jacobi> AlgoritmoJacobi (Jacobi modelJacobi);
    
    public ArrayList<GaussSeidel> AlgoritmoGaussSeidel (GaussSeidel modelGaussSeidel);
    
}
