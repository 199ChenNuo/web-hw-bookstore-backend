package code.fourTier.dao;

import code.fourTier.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminDao extends CrudRepository<Admin,Long> {
    public List<Admin> findByName(String name);
}
