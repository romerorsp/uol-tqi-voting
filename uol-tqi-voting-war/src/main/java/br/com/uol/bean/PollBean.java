package br.com.uol.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="pollBean")
@RequestScoped
public class PollBean {
	private int option;

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
}
