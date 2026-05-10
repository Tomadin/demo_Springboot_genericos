package tomadin.itu.repositories;

import org.springframework.stereotype.Repository;
import tomadin.itu.entities.Autor;

@Repository
public interface AutorRepository extends BaseRepository<Autor, Long> {
}
