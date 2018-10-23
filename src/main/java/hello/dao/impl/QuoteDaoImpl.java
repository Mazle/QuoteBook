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

    //todo: #REWRITE переделать так, чтобы не дергал всю базу.
    @Override
    public List<Quote> getQuotesList() {
        return sessionFactory.getCurrentSession().createQuery("from Quote").list();
    }

    //todo: #CHECK: по идее, он не должен сохранить запись в таблицу автора, проверить нужно ли здесь отдельно извлекать из объекта автора и сохранять его в своей таблице.
    @Override
    public void addQuote(Quote quote) {
        sessionFactory.getCurrentSession().save(quote);
    }
}
