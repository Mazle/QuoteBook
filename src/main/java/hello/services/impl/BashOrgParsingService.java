package hello.services.impl;

import hello.dao.AuthorCRUDRepository;
import hello.dao.QuoteCRUDRepository;
import hello.model.entity.Author;
import hello.model.entity.Quote;
import hello.services.JackYarabey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BashOrgParsingService implements JackYarabey {
    final static String URL = "https://bash.im";
    private QuoteCRUDRepository quoteRepository;
    private AuthorCRUDRepository authorRepository;

    public QuoteCRUDRepository getQuoteRepository() {
        return quoteRepository;
    }
    @Autowired
    public void setQuoteRepository(QuoteCRUDRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public AuthorCRUDRepository getAuthorRepository() {
        return authorRepository;
    }
    @Autowired
    public void setAuthorRepository(AuthorCRUDRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void snatchFiftyQuotes() {
        //Проверяем есть ли автор баш орг в базе, добавляем. Потом ищем его id.
        Author author = authorRepository.findAuthorByNickName(URL);
        if (author==null) {
            author = new Author();
            author.setNickName(URL);
            author = authorRepository.save(author);
        }
        Long bashId = author.getId();
        Long quotesCount = quoteRepository.countQuoteByAuthor_Id(bashId);
    //по результату формируем строку url для парсинга
        String parseUrl;
        if (quotesCount==0) {
            parseUrl = URL + "/byrating/1";
        }
        else {
            parseUrl = URL + "/byrating/" + quotesCount/50;
        }
    //пишем парсер

           // File input = new File("/tmp/input.html");
            Document doc = Jsoup.parse( "UTF-8", URL);
            Elements posts = doc.select("div.text");
            List<Quote> testList = new ArrayList<>();
            for (Element element: posts) {
                Quote quote = new Quote();
                quote.setAuthor(author);
                quote.setContent(element.text());
                testList.add(quote);

        }
    }
}
