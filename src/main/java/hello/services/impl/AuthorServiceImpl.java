package hello.services.impl;

import hello.dao.AuthorCRUDRepository;
import hello.model.entity.Author;
import hello.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorCRUDRepository repository;

    @Override
    public Iterable<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public Author addAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    public Author findByNickName(String nickName) {
        return repository.findAuthorByNickName(nickName);
    }

}
