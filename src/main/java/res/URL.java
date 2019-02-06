package res;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tblURL")
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="url_id")
    private long id;

    @NaturalId
    @Column(unique = true)
    private String  pageURL;

    @Override
    public int hashCode() {
        return Objects.hashCode(pageURL);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        URL other = (URL) obj;
        return Objects.equals(pageURL, other.getPageURL());
    }


    public URL() {
    }

    public URL(String pageURL) {
        this.pageURL = pageURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }


}
