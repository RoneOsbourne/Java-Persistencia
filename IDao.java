package persistencia;

import java.util.List;

public interface class IDao<T> {
    void insert(T t);
    void delete(int id);
    void update(T t);
    List<T> getAll();
    T getById(int id);
}
