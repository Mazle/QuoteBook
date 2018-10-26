package hello.dao;

import hello.model.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorCRUDRepository extends CrudRepository<Author,Long> {
    Author findAuthorByNickName(String nickName);
/*
    //todo:#TEST: Возможно убрать кавычки
    @Query("SELECT id\n" +
            "    FROM authors where nick_name = ':nickName'")
    Long findIdByNickName(@Param("nickName") String nickName);
*/
}
