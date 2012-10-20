package br.com.uol.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.uol.business.PollBO;
import br.com.uol.business.ServiceLocator;
import br.com.uol.business.poll.Option;
import br.com.uol.business.poll.Poll;
import br.com.uol.business.poll.VoteImpl;

@ManagedBean(name="pollBean")
@RequestScoped
public class PollBean {
	private static final Logger LOGGER = Logger.getLogger(PollBean.class.getName());

	private int option = -1;
	
	@ManagedProperty("#{applicationBean}")
	private ApplicationBean appBean;

	private SelectItem[] selectItems;

	private Poll poll;
	
	@PostConstruct
	public void init(){
		setPoll(appBean.getPoll());
	}

	public Poll getPoll() {
		return poll;
	}

	private void setPoll(Poll poll) {
		this.poll = poll;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}
	
	public void registerVoting(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(option < 0){
			context.addMessage("option", new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Você precisa selecionar uma das opções."));
			return;
		}
		List<Option> options = poll.getOptions();
		Option o = null;
		for(Option opt: options){
			if(opt.getId() == option){
				o = opt;
				break;
			}
		}
		if(o == null){
			context.addMessage("option", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", String.format("Não foi encontrada nenhuma opção com o valor %d", option)));
			return;
		}
		try{
			ServiceLocator.locate(PollBO.JNDI_PATH, PollBO.class).registerVote(new VoteImpl(o));
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", String.format("Você votou em %s", o.getName())));
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "O seu voto foi computado!"));
		} catch(Exception e){
			e.printStackTrace();
			LOGGER.log(Level.WARNING, String.format("Traing to register a vote, got: %s", e));
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha", "Falha ao tentar computar o voto!"));
		}
	}

	public SelectItem[] getSelectItems() {
		if(this.selectItems != null){
			return this.selectItems;
		}
		List<Option> options = poll.getOptions();
		SelectItem[] items = new SelectItem[options.size()];
		for(int i = 0; i < options.size(); i++){
			Option option = options.get(i);
			items[i] = new SelectItem(option.getId(), option.getName());
		}
		return (this.selectItems = items);
	}

	public Option[] getOptions() {
		return poll.getOptions().toArray(new Option[]{});
	}

	public ApplicationBean getAppBean() {
		return appBean;
	}

	public void setAppBean(ApplicationBean appBean) {
		this.appBean = appBean;
	}
}