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

import br.com.uol.business.PollBO;
import br.com.uol.business.ServiceLocator;
import br.com.uol.business.poll.Poll;

@ManagedBean(name="applicationBean")
@ApplicationScoped
public class ApplicationBean {
	private static final String DATE_STR_FORMAT = "dd-MM-yyyy-hh:mm";

	private static final Logger LOGGER = Logger.getLogger(ApplicationBean.class.getName());
	
	@ManagedProperty(value="#{initParam.showResultDate}")
	private String dateStr;
	@ManagedProperty(value="#{initParam.pollShortName}")
	private String pollShortName;
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

	public boolean isResultAllowed(){
		return date != null && new Date().after(date);
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Poll getPoll() {
		return ServiceLocator.locate(PollBO.JNDI_PATH, PollBO.class).getPollByShortName(getPollShortName());
	}

	public String getPollShortName() {
		return pollShortName;
	}

	public void setPollShortName(String pollShortName) {
		this.pollShortName = pollShortName;
	}
}