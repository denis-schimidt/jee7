package br.com.schimidtsolutions.producer;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logmanager.Logger;

public class LogProducer {

	@Produces
	public Logger newIntance( final InjectionPoint injectionPoint ) {
		return org.jboss.logmanager.Logger.getLogger( injectionPoint.getMember().getDeclaringClass().getName() );
	}
}
