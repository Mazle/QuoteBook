package hello.services.impl;

import hello.model.DTO.QuoteDTO;
import hello.services.QuoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class ElasticQuoteServiceImpl implements QuoteService<QuoteDTO> {

    @Override
    public QuoteDTO addQuote(QuoteDTO quote) {
        return null;
    }

    @Override
    public Page<QuoteDTO> getPage(PageRequest pageRequest) {
        return null;
    }
}
