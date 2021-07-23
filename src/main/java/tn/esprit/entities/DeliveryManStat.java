package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class DeliveryManStat implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String first_name;
	private String last_name;
	private int total_orders;
	private int orders_delivered;

	public DeliveryManStat() {
		super();
	}
	public DeliveryManStat(int id, String first_name, String last_name, int total_orders, int orders_delivered) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.total_orders = total_orders;
		this.orders_delivered = orders_delivered;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getTotal_orders() {
		return total_orders;
	}
	public void setTotal_orders(int total_orders) {
		this.total_orders = total_orders;
	}
	public int getOrders_delivered() {
		return orders_delivered;
	}
	public void setOrders_delivered(int orders_delivered) {
		this.orders_delivered = orders_delivered;
	}

	
	
	
}
