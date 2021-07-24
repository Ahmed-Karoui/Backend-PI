package tn.esprit.dto;

public class CountGroupByOrderStatus {
	
	private String status;
	private Long orderStatusNumber;
	
	public CountGroupByOrderStatus(String status, Long orderStatusNumber) {
		super();
		this.status = status;
		this.orderStatusNumber = orderStatusNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getOrderStatusNumber() {
		return orderStatusNumber;
	}
	public void setOrderStatusNumber(Long orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}

}
