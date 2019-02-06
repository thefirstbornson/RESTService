package dao;

import java.util.Date;

public interface VisitDao extends Dao {
    long getNumberOfVisits (Date start, Date finish);
    long getNumberOfUsers (Date start, Date finish);
    long getNumberOfLoyalUsers (Date start, Date finish, int numberOfPages);
}
