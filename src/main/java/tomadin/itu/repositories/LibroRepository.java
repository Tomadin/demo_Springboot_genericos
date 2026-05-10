package tomadin.itu.repositories;

import org.springframework.stereotype.Repository;
import tomadin.itu.entities.Libro;

@Repository
public interface LibroRepository extends BaseRepository<Libro,Long> {
}
