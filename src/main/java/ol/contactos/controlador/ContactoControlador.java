package ol.contactos.controlador;

import ol.contactos.modelo.Contacto;
import ol.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Contacto> contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto) -> logger.info(contacto.toString()));
        modelo.put("contactos", contactos);
        return "index"; //index.html
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(){
        return "agregar"; //redirecciona a agregar.html
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute ("contactoForma") Contacto contacto){
        logger.info("Contacto a agregar: " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
        //se utiliza redirect para que cargue los nuevos datos que se mandaron al BD
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") int idContacto, ModelMap modelo){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("Contacto a editar: " + contacto);
        modelo.put("contacto", contacto);
        return "editar";
    }

    @PostMapping("/editar")
    public String Editar(@ModelAttribute("contacto") Contacto contacto){
        logger.info("Contacto editado: " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("eliminar/{id}")
    public String Eliminar(@PathVariable(value = "id") int idContacto){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("Contacto eliminado: " + contacto);
        contactoServicio.eliminarContacto(contacto);
        return "redirect:/";
    }
}

