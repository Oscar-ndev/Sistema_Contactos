package ol.contactos.repositorio;

import ol.contactos.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactoRepositorio extends JpaRepository<Contacto, Integer> {
}
