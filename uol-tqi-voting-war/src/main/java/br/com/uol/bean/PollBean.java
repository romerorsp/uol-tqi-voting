package br.com.uol.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.uol.business.poll.Option;
import br.com.uol.business.poll.Poll;

@ManagedBean(name="pollBean")
@RequestScoped
public class PollBean {
	private int option;
	
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

	@SuppressWarnings("serial")
	private static Map<Integer, String> OPTIONS = new HashMap<Integer, String>(){{
		put(0, "Amijubi");
		put(1, "Fuleco");
		put(2, "Zuzeco");
	}};

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}
	
	public void registerVoting(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(option < 0){
			context.addMessage("option", new FacesMessage("Você precisa selecionar um dos nomes para o mascote."));
		}
		
		context.addMessage(null, new FacesMessage("Mensagem", String.format("Você votou em %s", OPTIONS.get(option))));
		context.addMessage(null, new FacesMessage("Sucesso", "O seu voto foi computado!"));
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
