
package br.com.schimidtsolutions.bean;

import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import br.com.schimidtsolutions.entity.Cliente;

@Stateless
@LocalBean
@Lock(LockType.WRITE)
public class MeuBean {
	
	@PersistenceContext(unitName="estudo")
	private EntityManager em;
	
	@Inject
	private Logger log;

	@Lock(LockType.READ)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void imprimir( final String string ) {
		
		if( string != null && !string.trim().isEmpty() ) {
			log.info( string );
		}
	}
	
	public Cliente adicionar( final Cliente cliente ) {
		em.persist( cliente );
		
		log.info( String.format("%s incluído com sucesso.", cliente.toString() ) );
		
		return cliente;
	}
	
	public Cliente alterar( final Cliente cliente ) {
		final Cliente clienteAlterado = em.merge( cliente );
		
		log.info( String.format( "%s alterado com sucesso.", cliente.toString() ) );
		
		return clienteAlterado;
	}
	
	public void excluir( final Cliente cliente ) {
		em.remove( consultar( cliente ) );
		
		log.info( String.format( "%s excluído com sucesso.", cliente.toString() ) );
	}
	
	@Lock(LockType.READ)
	public Cliente consultar( final Cliente cliente ) {
		return em.find( Cliente.class, cliente.getId() );
	}
}

