package code.fourTier.dao;

import code.fourTier.entity.OrderForm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderFormDao extends CrudRepository<OrderForm, Long> {
    List<OrderForm> findByUserid(int userid);
    List<OrderForm> findById(int id);
}
