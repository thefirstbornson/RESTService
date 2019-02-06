package res;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tblVisitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visitor_id")
    private long id;

    @NaturalId
    @Column(unique = true)
    private String  userIdentStr;

    @Override
    public int hashCode() {
        return Objects.hashCode(userIdentStr);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Visitor other = (Visitor) obj;
        return Objects.equals(userIdentStr, other.getUserIdentStr());
    }

    Visitor() {
    }

    public Visitor(String userIdentStr) {
        this.userIdentStr =userIdentStr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserIdentStr() {
        return userIdentStr;
    }

    public void setUserIdentStr(String userIdentStr) {
        this.userIdentStr = userIdentStr;
    }



}
