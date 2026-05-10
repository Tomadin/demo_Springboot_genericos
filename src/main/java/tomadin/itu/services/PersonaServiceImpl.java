package tomadin.itu.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomadin.itu.entities.Autor;
import tomadin.itu.entities.Libro;
import tomadin.itu.entities.Persona;
import tomadin.itu.repositories.AutorRepository;
import tomadin.itu.repositories.BaseRepository;
import tomadin.itu.repositories.PersonaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService {

    private PersonaRepository personaRepository;
    private AutorRepository autorRepository;

    public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository, PersonaRepository personaRepository, AutorRepository autorRepository) {
        super(baseRepository);
        this.personaRepository = personaRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() throws Exception {
        try {
            List<Persona> personas = personaRepository.findAll();
            personas.forEach(p -> p.getLibros().forEach(l -> l.getAutores().size()));
            return personas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById(Long id) throws Exception {
        try {
            Optional<Persona> personaOptional = personaRepository.findById(id);
            Persona persona = personaOptional.get();
            persona.getLibros().forEach(l -> l.getAutores().size());
            return persona;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Persona save(Persona entity) throws Exception {
        try {
            for (Libro libro : entity.getLibros()) {
                List<Autor> autoresResueltos = new ArrayList<>();
                for (Autor autor : libro.getAutores()) {
                    if (autor.getId() != null) {
                        autoresResueltos.add(autorRepository.findById(autor.getId())
                                .orElseThrow(() -> new RuntimeException("Autor con id " + autor.getId() + " no encontrado")));
                    } else {
                        autoresResueltos.add(autor);
                    }
                }
                libro.setAutores(autoresResueltos);
            }
            entity = personaRepository.save(entity);
            return entity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona entity) throws Exception {
        try {
            Optional<Persona> personaOptional = personaRepository.findById(id);
            Persona persona = personaOptional.get();

            persona.setNombre(entity.getNombre());
            persona.setApellido(entity.getApellido());
            persona.setDni(entity.getDni());

            persona = personaRepository.save(persona);
            return persona;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) throws Exception {
        try {
            if(personaRepository.existsById(id)){
                personaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<Persona> search(String filtro) throws Exception {
        try {
            List<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro, filtro);
            personas.forEach(p -> p.getLibros().forEach(l -> l.getAutores().size()));
            return personas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> search(String filtro, Pageable pageable) throws Exception {
        try {
            Page<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro, filtro, pageable);
            personas.forEach(p -> p.getLibros().forEach(l -> l.getAutores().size()));
            return personas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
