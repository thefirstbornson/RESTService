package dao;

public interface Dao <T> {
    T persist(T object)  ;
    T getByPK(Class<T> clazz, long key);
}
