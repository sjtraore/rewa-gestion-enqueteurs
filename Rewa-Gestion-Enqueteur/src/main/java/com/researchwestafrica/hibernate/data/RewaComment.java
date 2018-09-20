package com.researchwestafrica.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rewa_comment database table.
 * 
 */
@Entity
@Table(name="rewa_comment")
@NamedQuery(name="RewaComment.findAll", query="SELECT r FROM RewaComment r")
public class RewaComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idComment;

	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="idAuthor")
	private RewaPerson rewaPerson;

	public RewaComment() {
	}

	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public RewaPerson getRewaPerson() {
		return this.rewaPerson;
	}

	public void setRewaPerson(RewaPerson rewaPerson) {
		this.rewaPerson = rewaPerson;
	}

}