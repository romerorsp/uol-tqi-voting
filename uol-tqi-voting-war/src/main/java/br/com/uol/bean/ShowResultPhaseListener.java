package br.com.uol.bean;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class ShowResultPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -3230652920621105391L;

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		ELContext elContext = context.getELContext();
		if(!ApplicationBean.class.cast(
				context
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(elContext, "#{applicationBean}", ApplicationBean.class).getValue(elContext)).isResultAllowed()){
			context.addMessage(null, new FacesMessage("Resultados Indisponívels", "O Resultado da enquete não está disponível neste momento."));
			context.addMessage(null, new FacesMessage("Redirecionando", "Você será redirecionado para a página de votação."));
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	@Override
	public void afterPhase(PhaseEvent event) {}
}
