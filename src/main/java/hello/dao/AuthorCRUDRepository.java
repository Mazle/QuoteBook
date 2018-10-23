package hello.dao;

import hello.model.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorCRUDRepository extends CrudRepository<Author,Long> {
    Author findAuthorByNickName(String nickName);
}
