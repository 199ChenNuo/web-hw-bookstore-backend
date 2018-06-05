package code.fourTier.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import code.fourTier.entity.Books;


import java.util.List;


public interface BookDao extends CrudRepository<Books,Long> {
    List<Books> findAll(Pageable pageable);
    List<Books> findById(Long id);

}
