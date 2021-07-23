package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


public class AffectationDto implements Serializable {

	

private List<Order> orders ;
private int idDeliveryMan;
public List<Order> getOrders() {
	return orders;
}
public void setOrders(List<Order> orders) {
	this.orders = orders;
}
public int getIdDeliveryMan() {
	return idDeliveryMan;
}
public void setIdDeliveryMan(int idDeliveryMan) {
	this.idDeliveryMan = idDeliveryMan;
}
public AffectationDto(List<Order> orders, int idDeliveryMan) {
	super();
	this.orders = orders;
	this.idDeliveryMan = idDeliveryMan;
}
public AffectationDto() {
	super();
}


}
