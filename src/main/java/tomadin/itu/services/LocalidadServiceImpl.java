package tomadin.itu.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomadin.itu.entities.Localidad;
import tomadin.itu.entities.Localidad;
import tomadin.itu.repositories.BaseRepository;
import tomadin.itu.repositories.LocalidadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements LocalidadService {

    private LocalidadRepository localidadRepository;

    public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository, LocalidadRepository localidadRepository) {
        super(baseRepository);
        this.localidadRepository = localidadRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Localidad> findAll() throws Exception {
        try {
            List<Localidad> localidades = localidadRepository.findAll();
            return localidades;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Localidad findById(Long id) throws Exception {
        try {
            Optional<Localidad> localidadOptional = localidadRepository.findById(id);
            return localidadOptional.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Localidad save(Localidad entity) throws Exception {
        try {
            entity= localidadRepository.save(entity);
            return entity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Localidad update(Long id, Localidad entity) throws Exception {
        try {
            Optional<Localidad> localidadOptional = localidadRepository.findById(id);
            Localidad localidad = localidadOptional.get();

            localidad.setDenominacion(entity.getDenominacion());

            localidad = localidadRepository.save(localidad);
            return localidad;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) throws Exception {
        try {
            if(localidadRepository.existsById(id)){
                localidadRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
