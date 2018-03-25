package test.spring.data.repositories;

import org.springframework.data.repository.CrudRepository;
import test.spring.data.model.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {
}