package br.com.schimidtsolutions.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

@Stateless
@LocalBean
public class MeuBean {
	
	@Inject
	private Logger log;

	public void imprimir( final String string ) {
		log.info( string );
	}
}

