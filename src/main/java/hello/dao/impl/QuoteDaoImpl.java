package hello.dao.impl;

import hello.dao.QuoteDao;
import hello.entity.Quote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class QuoteDaoImpl implements QuoteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Quote> getQuotesList() {
        return null;
    }

    @Override
    public void addQuote(Quote quote) {

    }
}
