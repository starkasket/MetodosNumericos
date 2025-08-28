package mx.edu.itses.ojdl.MetodosNumericos.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.ojdl.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Gauss;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Jacobi;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Lagrange;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaCramer;
import mx.edu.itses.ojdl.MetodosNumericos.services.UnidadIIIService;
import mx.edu.itses.ojdl.MetodosNumericos.services.UnidadIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit4Controller {

 
    @Autowired
    private UnidadIVService unidadIVService;
    
  @GetMapping("/unit4")
    public String index(Model model) {
        return "unit4/index";
    }
    
      @GetMapping("/unit4/formddnewton")
    public String formDDNewton(Model model) {
        DDNewton modelDDNewton = new DDNewton();
        model.addAttribute("modelDDNewton", modelDDNewton);
        return "unit4/ddnewton/formddnewton";
    }

    
    @PostMapping("/unit4/solveddnewton")
    public String solveDDNewton(DDNewton modelDDNewton, Errors error, Model model) {
        var solveDDNewton = unidadIVService.algoritmoDDNewton(modelDDNewton);
        model.addAttribute("solveDDNewton", solveDDNewton);
        return "unit4/ddnewton/solveddnewton";
    }
    
       @GetMapping("/unit4/formlagrange")
    public String formLagrange(Model model) {
        Lagrange modelLagrange = new Lagrange();
        model.addAttribute("modelLagrange", modelLagrange);
        return "unit4/lagrange/formlagrange";
    }

    
    @PostMapping("/unit4/solvelagrange")
    public String solveLagrange(Lagrange modelLagrange, Errors error, Model model) {
        var solveLagrange = unidadIVService.algoritmoLagrange(modelLagrange);
        model.addAttribute("solveLagrange", solveLagrange);
        return "unit4/lagrange/solvelagrange";
    }

}

