package restbs.rest.bookstore.dao;

import org.springframework.data.repository.CrudRepository;
import restbs.rest.bookstore.entity.User;

import java.util.List;


public interface UserDao extends CrudRepository<User,Long> {
    List<User> findByName(String name);
    List<User> findById(int id);
}
