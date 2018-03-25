package test.spring.data.model;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "BILL")
public class Bill {
	private static final Calendar CALENDAR = Calendar.getInstance();
	private static final List<String> BILL_TYPE = ImmutableList.of("B1", "B2", "B3");
	private static final List<String> TRADE_TYPE = ImmutableList.of("T1", "T2", "T3");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private long companyId;
	
	@Column
	private Date billDate;
	
	@Column
	private String billType;
	
	@Column
	private String tradeType;
	
	public String toSQL() {
		return String.format("insert into bill (company_id, bill_date, bill_type, trade_type) values (%d, '%s', '%s', '%s')",
				companyId,
				DateFormatUtils.format(billDate, "yyyy-MM-dd"),
				billType,
				tradeType);
	}
	
	public static Bill create() {
		Bill bill = new Bill();
		long companyId = RandomUtils.nextLong(1, 4);
		
		CALENDAR.set(
				2018,
				3,
				RandomUtils.nextInt(1, 4)
		);
		int billTypeIndex = RandomUtils.nextInt(0, 3);
		int tradeTypeIndex = RandomUtils.nextInt(0, 3);
		
		bill.setCompanyId(companyId);
		bill.setBillDate(CALENDAR.getTime());
		bill.setBillType(BILL_TYPE.get(billTypeIndex));
		bill.setTradeType(TRADE_TYPE.get(tradeTypeIndex));
		return bill;
	}
}