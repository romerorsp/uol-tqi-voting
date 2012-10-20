package br.com.uol.business.poll;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Poll")
@Table(name="tblPoll")
@Cacheable(false)
public class PollImpl implements Poll {

	private static final long serialVersionUID = -4529311515267414587L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="short", unique=true)
	private String shortName;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=OptionImpl.class, cascade=CascadeType.ALL, mappedBy="poll")
	private List<Option> options;

	@Column(name="question", nullable=false)
	private String question;

	@Override
	public List<Option> getOptions() {
		return this.options;
	}

	@Override
	public String getQuestion() {
		return this.question;
	}

	@Override
	public int getTotalVotes() {
		int result = 0;
		for(Option opt: this.options){
			result += opt.getTotalVotes();
		}
		return result;
	}

	public int getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}
}