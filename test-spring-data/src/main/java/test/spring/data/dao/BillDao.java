package test.spring.data.dao;

import com.landawn.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import test.spring.data.model.Bill;

import java.util.List;

@Component
public class BillDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(List<Bill> bills) {
		String[] sqls = StreamEx.of(bills)
				.map(b -> b.toSQL())
				.toArray(String.class);
		jdbcTemplate.batchUpdate(sqls);
	}
}