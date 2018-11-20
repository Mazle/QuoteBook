package hello.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
//todo: QUESTION: ужно ли в аннтоациях к полям указывать nullable и unique там, где они прописаны в бд?

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_id_seq")
    @SequenceGenerator(name="quotes_id_seq", sequenceName="quotes_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "content")
    private String content;
   // @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDateTime date;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author")
    private Author author;

    public Quote() {
        this.date = LocalDateTime.now();
    }

    public Quote(long id, String content, LocalDateTime date, Author author) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }



}
