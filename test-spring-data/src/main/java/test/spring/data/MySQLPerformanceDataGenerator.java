package test.spring.data;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import test.spring.data.dao.BillDao;
import test.spring.data.model.Bill;
import test.spring.data.repositories.BillRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class MySQLPerformanceDataGenerator implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(MySQLPerformanceDataGenerator.class);
	}
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillDao billDao;
	
	@Override
	public void run(String... args) throws Exception {
		int size = 500000;
		// saveByDao(size);
		saveByRepository(size);
	}
	
	private void saveByDao(int size) {
		int batch = 2000;
		List<Bill> bills = Lists.newArrayListWithCapacity(batch);
		for (int i = 0; i < size; ++i) {
			bills.add(Bill.create());
			if (bills.size() == batch) {
				billDao.save(bills);
				bills.clear();
			}
		}
		if (!bills.isEmpty()) {
			billDao.save(bills);
		}
	}
	
	private void saveByRepository(int size) {
		List<Bill> bills = Lists.newArrayListWithCapacity(size);
		for (int i = 0; i < size; ++i) {
			bills.add(Bill.create());
		}
		billRepository.saveAll(bills);
	}
}