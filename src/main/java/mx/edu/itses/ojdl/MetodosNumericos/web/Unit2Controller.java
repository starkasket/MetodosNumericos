package mx.edu.itses.ojdl.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.ojdl.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.ojdl.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Secante;
import mx.edu.itses.ojdl.MetodosNumericos.domain.SecanteModificado;
import mx.edu.itses.ojdl.MetodosNumericos.services.Funciones;
import mx.edu.itses.ojdl.MetodosNumericos.services.UnidadIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit2Controller {

    @Autowired
    private UnidadIIService bisectionservice;
    @Autowired
    private UnidadIIService regulafalsiservice;
    @Autowired
    private UnidadIIService fixedpointservice;
    @Autowired
    private UnidadIIService newtonraphsonservice;
    @Autowired
    private UnidadIIService secantservice;
    @Autowired
    private UnidadIIService modsecantservice;
    

    @GetMapping("/unit2/formbisection")
    public String formBisection(Model model) {

        Biseccion bisection = new Biseccion();

        model.addAttribute("bisection", bisection);

        return "unit2/bisection/formbisection";
    }

    @PostMapping("/unit2/solvebisection")
    public String solvebisection(Biseccion bisection, Model model) {

        /* double valorFX = Funciones.Ecuacion(bisection.getFX(), bisection.getXL());
         log.info("Valor de FX: " + valorFX);
         */
        var solveBisection = bisectionservice.AlgoritmoBiseccion(bisection);

        model.addAttribute("solveBisection", solveBisection);
        return "/unit2/bisection/solvebisection";
        // return "index";
    }

    @GetMapping("/unit2/formregulafalsi")
    public String formRegulaFalsi(Model model) {

        ReglaFalsa regulafalsi = new ReglaFalsa();

        model.addAttribute("regulafalsi", regulafalsi);

        return "unit2/reglafalsa/formregulafalsi";
    }

    @PostMapping("/unit2/solveregulafalsi")
    public String solvregulafalsi(ReglaFalsa regulafalsi, Model model) {

        var solveRegulaFalsi = regulafalsiservice.AlgoritmoReglaFalsa(regulafalsi);

        model.addAttribute("solveRegulaFalsi", solveRegulaFalsi);
        return "/unit2/reglafalsa/solveregulafalsi";
    }

    @GetMapping("/unit2/formfixedpoint")
    public String formFixedPoint(Model model) {
        PuntoFijo fixedpoint = new PuntoFijo();

        model.addAttribute("fixedpoint", fixedpoint);

        return "unit2/puntofijo/formfixedpoint";
    }

    @PostMapping("/unit2/solvefixedpoint")
    public String solvefixedpoint(PuntoFijo fixedpoint, Model model) {
        var solveFixedPoint = fixedpointservice.AlgoritmoPuntoFijo(fixedpoint);

        model.addAttribute("solveFixedPoint", solveFixedPoint);
        return "/unit2/puntofijo/solvefixedpoint";
    }

    @GetMapping("/unit2/formnewtonraphson")
    public String formNewtonRaphson(Model model) {
        NewtonRaphson newtonraphson = new NewtonRaphson();

        model.addAttribute("newtonraphson", newtonraphson);

        return "unit2/newtonraphson/formnewtonraphson";
    }

    @PostMapping("/unit2/solvenewtonraphson")
    public String solvenewtonraphson(NewtonRaphson newtonraphson, Model model) {
        var solveNewtonRaphson = newtonraphsonservice.AlgoritmoNewtonRaphon(newtonraphson);
        model.addAttribute("solveNewtonRaphson", solveNewtonRaphson);
        return "/unit2/newtonraphson/solvenewtonraphson";
    }
    
    @GetMapping("/unit2/formsecant")
    public String formSecant(Model model){
        Secante secant = new Secante();
        model.addAttribute("secant", secant);
        return "unit2/secante/formsecant";
    }
    
    @PostMapping("/unit2/solvesecant")
    public String solvesecant(Secante secant, Model model){
        var solveSecant = secantservice.AlgoritmoSecante(secant);
        model.addAttribute("solveSecant", solveSecant);
        return "/unit2/secante/solvesecant";
    }
    
    @GetMapping("/unit2/formmodsecant")
    public String formModSecant(Model model){
        SecanteModificado modsecant = new SecanteModificado();
        model.addAttribute("modsecant", modsecant);
        return "unit2/secantemodificado/formmodsecant";
    }
    
    @PostMapping("/unit2/solvemodsecant")
    public String solvemodsecant(SecanteModificado modsecant, Model model){
        var solveModSecant = modsecantservice.AlgoritmoSecanteModificado(modsecant);
        model.addAttribute("solveModSecant", solveModSecant);
        return "/unit2/secantemodificado/solvemodsecant";
    }

}
