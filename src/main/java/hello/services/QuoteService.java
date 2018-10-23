package hello.services;

import hello.model.entity.Quote;

import java.util.List;

public interface QuoteService {
    void addQuote (Quote quote);
    List<Quote> getListForPage(int pageNumb, int postAmount);
}
