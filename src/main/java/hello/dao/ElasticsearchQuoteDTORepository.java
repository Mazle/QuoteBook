package hello.dao;

import hello.model.DTO.QuoteDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchQuoteDTORepository extends ElasticsearchRepository<QuoteDTO,Long> {
}
