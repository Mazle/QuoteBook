package hello.model.entity;

import javax.persistence.*;

//todo: как только возникнет необходимость написать метод addQuote, сразу же написать его. пока не совсем понимаю зачем он.
@Entity
@Table(name="authors")
public class Author {
    @Id
    @SequenceGenerator(name="author_id_seq", sequenceName="author_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_id_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "nick_name")
    private String nickName;
    /*
    mappedBy указывает на поле дочернего класса(Entity, в бд нет такого поля), ссылающегося на наш, т.е. родительский
    это поле в дочернем классе помечено как @ManyToOne
    ? Может ли Отсутствовать OneToMany  в родительском классе- да, тогда получится однонаправленная связь.
    ? что будет, если не написать mappedBy
        > он создаст дополнительную таблицу связей между дочерним и owner, в качестве
        альтернативного способа обеспечения связи один ко многим, но эффект останется таким же
        >Если mappedBy не указывать,то Hibernate при сохранении объектов сначала сохранит один объект, а потом тут же второй,
        то есть два раза выпониться команда INSERT. Если поставить это свойство, то foreign key не будет устанавливаться два раза.
     */

    //@OneToMany(mappedBy = "")
    //private List<Quote> quoteList;

    public Author() {
    }

    public Author(long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
