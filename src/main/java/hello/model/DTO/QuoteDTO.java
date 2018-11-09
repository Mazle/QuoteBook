package hello.model.DTO;

import hello.model.entity.Author;
import hello.model.entity.Quote;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
/*
*я искренне пытался обойтись без написания этого класса:
* https://stackoverflow.com/questions/52906779/problem-with-passing-changed-subobject-in-spring-application-via-thymeleaf-templ/52907925?noredirect=1#comment92752282_52907925
* Но после 3 дней простоя плюнул и сделал колхоз. Не стоит оно того, чтобы так долго торчать на этом вопросе и бездействовать.
 */
@Document(indexName = "quote")
public class QuoteDTO {
    @Id
    private long id;
    private String content;

    private LocalDate date;
    private String authorNickName;

    public QuoteDTO() {
    }
    public QuoteDTO(Quote quote) {
        this.id = quote.getId();
        this.content = quote.getContent();
        this.date = quote.getDate();
        this.authorNickName = quote.getAuthor().getNickName();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
