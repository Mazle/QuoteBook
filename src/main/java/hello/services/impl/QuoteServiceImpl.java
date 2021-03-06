package hello.services.impl;

import hello.dao.AuthorCRUDRepository;
import hello.dao.QuoteCRUDRepository;
import hello.dao.QuoteDao;
import hello.model.entity.Author;
import hello.model.entity.Quote;
import hello.services.JackYarabey;
import hello.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService <Quote> {

    @Autowired
    private QuoteCRUDRepository repository;

    @Override
    public Quote addQuote(Quote quote) {
        return repository.save(quote);
    }

    @Override
    public Page<Quote> getPage(PageRequest pageRequest) {
       return repository.findAll(pageRequest);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }


}
