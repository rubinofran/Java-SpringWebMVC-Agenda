package rubino.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rubino.agenda.modelos.*;

@Controller
public class Controlador {
    
    @Autowired
    private DatosDAO datosDAO;
    
    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("nombres", datosDAO.getNombres());
        return "index";
    }
    
    @PostMapping("/alta")
    public String alta(
            Model model, 
            @RequestParam(value = "nuevoNombre", required = true) String nuevoNombre,
            @RequestParam(value = "nuevoTel", required = true) String nuevoTel) 
    {
        datosDAO.darDeAlta(nuevoNombre, nuevoTel);
        model.addAttribute("nombres", datosDAO.getNombres());
        return "index";
    }

    @PostMapping("/baja")
    public String baja(
            Model model, 
            @RequestParam(value = "nombre", required = true) String nombre) 
    {
        datosDAO.darDeBaja(nombre);
        model.addAttribute("nombres", datosDAO.getNombres());
        return "index";
    }
    
    @PostMapping("/modificacion")
    public String modificacion(
            Model model, 
            @RequestParam(value = "nombre", required = true) String nombre,
            @RequestParam(value = "nuevoTel", required = true) String nuevoTel) 
    {
        datosDAO.modificar(nombre, nuevoTel);
        model.addAttribute("nombres", datosDAO.getNombres());
        return "index";
    }
    
    @PostMapping("/consulta")
    public String consulta(
            Model model, 
            @RequestParam(value = "nombre", required = true) String nombre) 
    {
        model.addAttribute("telefonoEncontrado", datosDAO.getTelefonoPorNombre(nombre));
        model.addAttribute("nombres", datosDAO.getNombres());
        return "index";
    }
}

