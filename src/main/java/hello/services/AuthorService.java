package hello.services;

import hello.entity.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
