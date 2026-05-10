package tomadin.itu.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomadin.itu.entities.Domicilio;
import tomadin.itu.repositories.BaseRepository;
import tomadin.itu.repositories.DomicilioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, Long> implements DomicilioService {

    private DomicilioRepository domicilioRepository;

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository, DomicilioRepository domicilioRepository) {
        super(baseRepository);
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Domicilio> findAll() throws Exception {
        try {
            List<Domicilio> domicilios = domicilioRepository.findAll();
            return domicilios;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Domicilio findById(Long id) throws Exception {
        try {
            Optional<Domicilio> domicilioOptional = domicilioRepository.findById(id);
            return domicilioOptional.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Domicilio save(Domicilio entity) throws Exception {
        try {
            entity= domicilioRepository.save(entity);
            return entity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Domicilio update(Long id, Domicilio entity) throws Exception {
        try {
            Optional<Domicilio> domicilioOptional = domicilioRepository.findById(id);
            Domicilio domicilio = domicilioOptional.get();

            domicilio.setCalle(entity.getCalle());
            domicilio.setLocalidad(entity.getLocalidad());
            domicilio.setNumero(entity.getNumero());

            domicilio = domicilioRepository.save(domicilio);
            return domicilio;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) throws Exception {
        try {
            if(domicilioRepository.existsById(id)){
                domicilioRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
