package hello.dao.impl;

import hello.dao.QuoteDao;
import hello.model.entity.Quote;
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
        return sessionFactory.getCurrentSession().createQuery("from Quote").list();
    }

    @Override
    public void addQuote(Quote quote) {
        sessionFactory.getCurrentSession().save(quote);
    }
}
