package hello.dao;

import hello.model.DTO.QuoteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchQuoteDTORepository extends ElasticsearchRepository<QuoteDTO,Long> {
    Page<QuoteDTO> findAll(Pageable pageable);
}
