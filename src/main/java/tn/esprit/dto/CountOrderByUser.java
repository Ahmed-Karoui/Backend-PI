package tn.esprit.dto;

public class CountOrderByUser {
	
	private String firstNameUser;
	private Long orderNumber;
	
	public CountOrderByUser(String firstNameUser, Long orderNumber) {
		super();
		this.firstNameUser = firstNameUser;
		this.orderNumber = orderNumber;
	}
	
	public String getFirstNameUser() {
		return firstNameUser;
	}
	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}
	public Long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	

}
