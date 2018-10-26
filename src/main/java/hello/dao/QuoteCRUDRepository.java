package hello.dao;

import hello.model.entity.Author;
import hello.model.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface QuoteCRUDRepository extends CrudRepository<Quote, Long> {
    Page<Quote> findAll(Pageable pageable);
    Long countQuoteByAuthor_Id(long authorId);
}
