package br.com.uol.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.jboss.logmanager.Level;

@ManagedBean(name="applicationBean")
@ApplicationScoped
public class ApplicationBean {
	private static final String DATE_STR_FORMAT = "dd-MM-yyyy-hh:mm";

	private static final Logger LOGGER = Logger.getLogger(ApplicationBean.class.getName());
	
	@ManagedProperty(value="#{initParam.showResultDate}")
	private String dateStr;
	private double amijubiVote = 20;
	private double fulecoVote = 25;
	private double zuzecoVote = 55;
	private Date date;
	
	@PostConstruct
	public void initialize(){
		if(!(getDateStr() == null || getDateStr().isEmpty())){
			try {
				date = new SimpleDateFormat(DATE_STR_FORMAT).parse(getDateStr());
			} catch (ParseException e) {
				LOGGER.log(Level.INFO, String.format("It seems no date was setted up for starting to show the poll results, due: %s", e.getLocalizedMessage()));
			}
		}
	}
	
	public double getFulecoVote() {
		return fulecoVote;
	}

	public void setFulecoVote(double fulecoVote) {
		this.fulecoVote = fulecoVote;
	}

	public double getAmijubiVote() {
		return amijubiVote;
	}

	public void setAmijubiVote(double amijubiVote) {
		this.amijubiVote = amijubiVote;
	}

	public double getZuzecoVote() {
		return zuzecoVote;
	}

	public void setZuzecoVote(double zuzecoVote) {
		this.zuzecoVote = zuzecoVote;
	}
	
	public boolean isResultAllowed(){
		return date != null && new Date().after(date);
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}