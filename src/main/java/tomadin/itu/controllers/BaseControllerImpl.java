package tomadin.itu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tomadin.itu.dto.ErrorResponse;
import tomadin.itu.entities.Base;
import tomadin.itu.services.BaseServiceImpl;

import java.io.Serializable;

public class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E,Long>, ID extends Serializable> implements BaseController<E, ID> {

    @Autowired
    protected S servicio;

    @Override
    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocurrio un error mientras se listaban las entidades."));
        }
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<?> getAll(Pageable pageable) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocurrio un error mientras se listaban las entidades."));
        }
    }

    @Override
    public ResponseEntity<?> getOne(ID id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById((Long) id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Ocurrio un error mientras se listaba la entidad."));
        }
    }

    @Override
    public ResponseEntity<?> save(E entity) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocurrio un error mientras se guardaba la entidad."));
        }
    }

    @Override
    public ResponseEntity<?> update(ID id, E entity) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update((Long) id,entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocurrio un error mientras se actualizaba la entidad."));
        }
    }

    @Override
    public ResponseEntity<?> delete(ID id) {
        try{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(servicio.delete((Long) id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocurrio un error mientras se eliminada la entidad."));
        }
    }
}
