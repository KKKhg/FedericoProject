package vo;

import java.sql.Date;

import lombok.Data;

@Data
public class FcOrderVO {

	private int fcOrderSeq;
	private String fcId;
	private String fcOrderDate;
	private String fcOrderFlag;
	private int sumPrice;
	
}
