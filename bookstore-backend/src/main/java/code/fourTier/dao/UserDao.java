package code.fourTier.dao;

import org.springframework.data.repository.CrudRepository;
import code.fourTier.entity.User;

import java.util.List;


public interface UserDao extends CrudRepository<User,Long> {
    List<User> findByName(String name);
    List<User> findById(int id);
}
