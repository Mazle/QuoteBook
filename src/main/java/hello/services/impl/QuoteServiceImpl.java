package hello.services.impl;

import hello.dao.QuoteDao;
import hello.entity.Quote;
import hello.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteDao repository;

    @Override
    public void addQuote(Quote quote) {

    }

    @Override
    public List<Quote> getListForPage(int numb) {
        return repository.getQuotesList();
    }
}
