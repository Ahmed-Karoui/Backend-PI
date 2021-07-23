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


public class PropositionDto implements Serializable {

	

private List<DeliveryMan> listDeliveryMan ;
private String codePostal;
public PropositionDto() {
	super();
}
public PropositionDto(List<DeliveryMan> listDeliveryMan, String codePostal) {
	super();
	this.listDeliveryMan = listDeliveryMan;
	this.codePostal = codePostal;
}
public List<DeliveryMan> getListDeliveryMan() {
	return listDeliveryMan;
}
public void setListDeliveryMan(List<DeliveryMan> listDeliveryMan) {
	this.listDeliveryMan = listDeliveryMan;
}
public String getCodePostal() {
	return codePostal;
}
public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
}



}
