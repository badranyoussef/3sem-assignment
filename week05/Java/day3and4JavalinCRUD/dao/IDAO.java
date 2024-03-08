package day3and4JavalinCRUD.dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T, ID> {
    List<T> getAll();
    T getById(ID id);
    void create(T t);
    T update(T t);
    void delete(ID id);
}
