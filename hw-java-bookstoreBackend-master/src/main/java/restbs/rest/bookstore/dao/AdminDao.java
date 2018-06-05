package restbs.rest.bookstore.dao;

import org.springframework.data.repository.CrudRepository;
import restbs.rest.bookstore.entity.Admin;
import java.util.List;

public interface AdminDao extends CrudRepository<Admin,Long> {
    public List<Admin> findByName(String name);
}
