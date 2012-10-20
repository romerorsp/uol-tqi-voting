package br.com.uol.business.poll;

import java.io.Serializable;

public interface Vote extends Serializable {
	public Option getOption();
}
