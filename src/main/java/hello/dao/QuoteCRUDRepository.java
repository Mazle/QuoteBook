package hello.dao;

import hello.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface QuoteCRUDRepository extends CrudRepository<Quote, Long> {
    Page<Quote> findAll(Pageable pageable);
}
