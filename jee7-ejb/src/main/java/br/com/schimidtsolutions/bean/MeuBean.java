package br.com.schimidtsolutions.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class MeuBean {

	public void imprimir( final String string ) {
		System.out.println( string );
	}
}
