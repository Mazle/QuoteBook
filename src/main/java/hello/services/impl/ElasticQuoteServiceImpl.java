package hello.services.impl;

import hello.dao.ElasticsearchQuoteDTORepository;
import hello.dao.QuoteCRUDRepository;
import hello.model.DTO.QuoteDTO;
import hello.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ElasticQuoteServiceImpl implements QuoteService<QuoteDTO> {

    @Autowired
    private ElasticsearchQuoteDTORepository repository;

    @Override
    public QuoteDTO addQuote(QuoteDTO quote) {
        return repository.save(quote);
    }

    @Override
    public Page<QuoteDTO> getPage(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
