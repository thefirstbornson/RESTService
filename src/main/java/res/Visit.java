package res;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="tblEvents")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id", nullable = false)
    private URL url;

    @Column(name="visitTimeStamp", columnDefinition="DATETIME")
    private Date visitTimeStamp;

    public Visit() {
    }

    public Visit(Visitor visitor, URL url, Date date) {
        this.visitor = visitor;
        this.url = url;
        this.visitTimeStamp = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Date getVisiTimeStamp() {
        return visitTimeStamp;
    }

    public void setVisiTimeStamp(Date visiTimeStamp) {
        this.visitTimeStamp = visiTimeStamp;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", visitor=" + visitor +
                ", url=" + url +
                ", visitTimeStamp=" + visitTimeStamp +
                '}';
    }
}
