package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;
	private Date publicationDate;
	private int nbLike;
	private int nbDisLike;
	private String Image;
	private boolean validated;
	@JsonIgnore
	@OneToMany(mappedBy = "publication")
	private List<Comment> comments;
	@ManyToOne
	private User user;
	private transient int IdUser;

	
	public Publication(int id, String content, Date publicationDate, int nbLike, int nbDisLike, List<Comment> comments,
			User user, boolean validated , String Image , String title) {
		super(); 
		this.id = id;
		this.content = content;
		this.publicationDate = new Date();
		this.nbLike = nbLike;
		this.nbDisLike = nbDisLike;
		this.comments = comments;
		this.user = user;
		this.validated = false;
		this.Image = Image;
		this.title = title;
	}

	public Publication() {
		super();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public int getNbLike() {
		return nbLike;
	}

	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}

	public int getNbDisLike() {
		return nbDisLike;
	}

	public void setNbDisLike(int nbDisLike) {
		this.nbDisLike = nbDisLike;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int idUser) {
		IdUser = idUser;
	}
	
	
	

	
	
	
	

}
