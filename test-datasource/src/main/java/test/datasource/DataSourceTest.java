package test.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class DataSourceTest implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DataSourceTest.class);
		app.run();
	}

	@Autowired
	private DataSource dataSource;

	@Override
	public void run(String... strings) throws Exception {
		Connection conn = dataSource.getConnection();
		for (int i = 0; i < 100; ++i) {
			System.out.println(i);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from MV_DSFR02_DSV2");
			rs.next();
			System.out.println(rs.getObject(2));
			rs.close();
			statement.close();
		}
	}
}