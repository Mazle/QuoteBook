package hello.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface QuoteService <T> {
    T addQuote (T quote);
    Page<T> getPage(PageRequest pageRequest);
    void deleteAll();
}
