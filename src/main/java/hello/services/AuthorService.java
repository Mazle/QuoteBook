package hello.services;

import hello.model.entity.Author;

public interface AuthorService {
    Iterable<Author> findAll();
    Author addAuthor(Author author);
    Author findByNickName(String nickName);
}
