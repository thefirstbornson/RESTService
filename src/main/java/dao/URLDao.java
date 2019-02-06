package dao;

import res.URL;

public interface URLDao extends Dao {
    URL getByURL(String url);
}
