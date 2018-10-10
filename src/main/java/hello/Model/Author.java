package hello.Model;

import javax.persistence.*;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "nick_name")
    private String nickName;

    public Author() {
    }

    public Author(long id, String nickName) {
        this.id = id;
        nickName = nickName;
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
        nickName = nickName;
    }
}
