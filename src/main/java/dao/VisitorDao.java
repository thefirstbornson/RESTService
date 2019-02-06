package dao;

import res.URL;
import res.Visitor;

public interface VisitorDao extends Dao  {
    Visitor getByUserIdentStr(String url);
}
