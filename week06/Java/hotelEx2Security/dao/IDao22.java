package hotelEx2Security.dao;

import java.util.List;

public interface IDao22<T, ID> {
    List<T> getAll();
    T get(ID id);
    T create(T t);
    T update(ID id, T t);
    void delete(ID id);
    boolean validatePrimaryKey(String userName);
}
