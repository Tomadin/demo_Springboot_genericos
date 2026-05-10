package tomadin.itu.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomadin.itu.entities.Libro;
import tomadin.itu.repositories.BaseRepository;
import tomadin.itu.repositories.LibroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl extends BaseServiceImpl<Libro,Long> implements LibroService {
    private LibroRepository libroRepository;

    public LibroServiceImpl(BaseRepository<Libro, Long> baseRepository, LibroRepository libroRepository) {
        super(baseRepository);
        this.libroRepository = libroRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAll() throws Exception {
        try {
            List<Libro> libros = libroRepository.findAll();
            return libros;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Libro findById(Long id) throws Exception {
        try {
            Optional<Libro> libroOptional = libroRepository.findById(id);
            return libroOptional.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Libro save(Libro entity) throws Exception {
        try {
            entity= libroRepository.save(entity);
            return entity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Libro update(Long id, Libro entity) throws Exception {
        try {
            Optional<Libro> libroOptional = libroRepository.findById(id);
            Libro libro = libroOptional.get();

            libro.setTitulo(entity.getTitulo());
            libro.setGenero(entity.getGenero());
            libro.setPaginas(entity.getPaginas());
            libro.setPaginas(entity.getPaginas());
            libro.setFecha(entity.getFecha());
            libro.setAutores(entity.getAutores());



            libro = libroRepository.save(libro);
            return libro;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) throws Exception {
        try {
            if(libroRepository.existsById(id)){
                libroRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
