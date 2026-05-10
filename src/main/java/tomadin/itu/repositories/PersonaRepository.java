package tomadin.itu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tomadin.itu.entities.Persona;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long>{
    List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido);

    Page<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido, Pageable pageable);
}
