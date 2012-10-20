package br.com.uol.business.poll;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Option")
@Table(name="tblQuestions")
@Cacheable(false)
public class OptionImpl implements Option {

	private static final long serialVersionUID = -7483300644860624056L;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="id_poll", nullable=false)
	private PollImpl poll;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="votes")
	private int totalVotes;

	@Column(name="name", nullable=false, unique=true)
	private String name;

	@Column(name="description", nullable=true, unique=false)
	private String description;

	@Override
	public Poll getPoll() {
		return poll;
	}

	@Override
	public int getTotalVotes() {
		return totalVotes;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}