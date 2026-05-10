package tomadin.itu.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomadin.itu.entities.Autor;
import tomadin.itu.repositories.AutorRepository;
import tomadin.itu.repositories.BaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor,Long> implements AutorService{
    private AutorRepository autorRepository;

    public AutorServiceImpl(BaseRepository<Autor, Long> baseRepository, AutorRepository autorRepository) {
        super(baseRepository);
        this.autorRepository = autorRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Autor> findAll() throws Exception {
        try {
            List<Autor> autores = autorRepository.findAll();
            return autores;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Autor findById(Long id) throws Exception {
        try {
            Optional<Autor> autorOptional = autorRepository.findById(id);
            return autorOptional.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Autor save(Autor entity) throws Exception {
        try {
            entity= autorRepository.save(entity);
            return entity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Autor update(Long id, Autor entity) throws Exception {
        try {
            Optional<Autor> autorOptional = autorRepository.findById(id);
            Autor autor = autorOptional.get();

            autor.setNombre(entity.getNombre());
            autor.setApellido(entity.getApellido());
            autor.setBiografia(entity.getBiografia());

            autor = autorRepository.save(autor);
            return autor;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) throws Exception {
        try {
            if(autorRepository.existsById(id)){
                autorRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
