package ol.contactos.servicio;

import ol.contactos.modelo.Contacto;
import ol.contactos.repositorio.IContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServicio implements IContactoServicio{

    @Autowired
    private IContactoRepositorio contactoRepositorio;

    @Override
    public List<Contacto> listarContactos() {
        return contactoRepositorio.findAll();
    }

    @Override
    public Contacto buscarContactoPorId(Integer idContacto) {
       return contactoRepositorio.findById(idContacto).orElse(null);
    }

    @Override
    public void guardarContacto(Contacto contacto) {
        contactoRepositorio.save(contacto);

    }

    @Override
    public void eliminarContacto(Contacto contacto) {
        contactoRepositorio.delete(contacto);
    }
}
