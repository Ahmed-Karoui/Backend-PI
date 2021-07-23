package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Feedback implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float rate;
	//@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateRating;
	private transient int idProduct ;
	@ManyToOne
	private Product product;

	public Feedback(int id, float rate, Date dateRating, Product product) {
		super();
		this.id = id;
		this.rate = rate;
		this.dateRating = dateRating;
		this.product = product;
	}

	public Feedback() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Date getDateRating() {
		return dateRating;
	}

	public void setDateRating(Date dateRating) {
		this.dateRating = dateRating;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

}
