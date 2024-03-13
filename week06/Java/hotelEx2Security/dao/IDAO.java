package hotelEx2Security.dao;

import java.util.List;

public interface IDAO<T, ID> {
    List<T> getAll();
    T getById(ID id);
    void  create(T t);
    T update(T t);
    void delete(ID id);
}
