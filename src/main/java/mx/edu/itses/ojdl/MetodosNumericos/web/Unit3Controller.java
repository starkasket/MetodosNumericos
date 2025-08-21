package mx.edu.itses.ojdl.MetodosNumericos.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Gauss;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Jacobi;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaCramer;
import mx.edu.itses.ojdl.MetodosNumericos.services.UnidadIIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit3Controller {

    @Autowired
    private UnidadIIIService unidadIIIService;

    @GetMapping("/unit3")
    public String index(Model model) {
        return "unit3/index";
    }

    @GetMapping("/unit3/formreglacramer")
    public String formReglaCramer(Model model) {
        ReglaCramer modelCramer = new ReglaCramer();
        model.addAttribute("modelCramer", modelCramer);
        return "unit3/reglacramer/formreglacramer";
    }

    @PostMapping("/unit3/solvecramer")
    public String solveCramer(ReglaCramer modelCramer, Errors error, Model model) {
        // log.info("OBJETOS: " + modelCramer);
        var solveCramer = unidadIIIService.AlgoritmoReglaCramer(modelCramer);
        // log.info("Solución: " + solveCramer.getVectorX());
        model.addAttribute("solveCramer", solveCramer);
        return "unit3/reglacramer/solvecramer";
    }

    @GetMapping("/unit3/formgauss")
    public String formGauss(Model model) {
        Gauss modelGauss = new Gauss();
        model.addAttribute("modelGauss", modelGauss);
        return "unit3/gauss/formgauss";
    }

    @PostMapping("/unit3/solvegauss")
    public String solveGauss(Gauss modelGauss, Errors error, Model model) {
        var solveGauss = unidadIIIService.AlgoritmoGauss(modelGauss);
        model.addAttribute("solveGauss", solveGauss);
        return "unit3/gauss/solvegauss";
    }

    @GetMapping("/unit3/formgaussjordan")
    public String formGaussJordan(Model model) {
        Gauss modelGaussJordan = new Gauss();
        model.addAttribute("modelGaussJordan", modelGaussJordan);
        return "unit3/gaussjordan/formgaussjordan";
    }

    @PostMapping("/unit3/solvegaussjordan")
    public String solveGaussJordan(GaussJordan modelGaussJordan, Errors error, Model model) {
        var solveGaussJordan = unidadIIIService.AlgoritmoGaussJordan(modelGaussJordan);
        model.addAttribute("solveGaussJordan", solveGaussJordan);
        return "unit3/gaussjordan/solvegaussjordan";
    }

    @GetMapping("/unit3/formjacobi")
    public String formJacobi(Model model) {
        Jacobi modelJacobi = new Jacobi();
        model.addAttribute("modelJacobi", modelJacobi);
        return "unit3/jacobi/formjacobi";
    }

    @PostMapping("/unit3/solvejacobi")
    public String solveJacobi(Jacobi modelJacobi, Errors error, Model model) {
        int n = modelJacobi.getMN();
        var solveJacobi = unidadIIIService.AlgoritmoJacobi(modelJacobi);
        model.addAttribute("solveJacobi", solveJacobi);
        model.addAttribute("n", n);
        return "unit3/jacobi/solvejacobi";
    }
    @GetMapping("/unit3/formgaussseidel")
    public String formGaussSeidel(Model model) {
        GaussSeidel modelGaussSeidel = new GaussSeidel();
        model.addAttribute("modelGaussSeidel", modelGaussSeidel);
        return "unit3/gaussseidel/formgaussseidel";
    }

    @PostMapping("/unit3/solvegaussseidel")
    public String solveGaussSeidel(GaussSeidel modelGaussSeidel, Errors error, Model model) {
        int n = modelGaussSeidel.getMN();
        var solveGaussSeidel = unidadIIIService.AlgoritmoGaussSeidel(modelGaussSeidel);
        model.addAttribute("solveGaussSeidel", solveGaussSeidel);
        model.addAttribute("n", n);
        return "unit3/gaussseidel/solvegaussseidel";
    }
}
