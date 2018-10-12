package hello.dao;

import hello.entity.Quote;

import java.util.List;

public interface QuoteDao {
    List<Quote> getQuotesList();
    void addQuote(Quote quote);
}
