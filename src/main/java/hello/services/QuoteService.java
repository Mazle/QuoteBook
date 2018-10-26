package hello.services;

import hello.model.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface QuoteService {
    void addQuote (Quote quote);
    Page<Quote> getPage(PageRequest pageRequest);
    Long countQuotesOfAuthorWithId(Long authorId);
}
