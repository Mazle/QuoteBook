package hello.dao;

import hello.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorCRUDRepository extends CrudRepository<Author,Long> {
}
