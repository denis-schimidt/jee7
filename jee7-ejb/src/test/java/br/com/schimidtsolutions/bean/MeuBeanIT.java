package br.com.schimidtsolutions.bean;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import br.com.schimidtsolutions.tests.SlowTests;

@RunWith(Arquillian.class)
public class MeuBeanIT {

	@Inject
	private MeuBean bean;
	
	@Deployment
	public static Archive<?> createDeployment() {
		final JavaArchive javaArchive = ShrinkWrap.create( JavaArchive.class, "test-ejb-integrado.jar" )
				.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" )
				.addPackages( true, "br.com.schimidtsolutions" );
		
		System.out.println( javaArchive.toString(true) );
		
		return javaArchive;
	}

	@Test
	@InSequence(1)
	@Category(SlowTests.class)
	public void testImprimir() {
		bean.imprimir( "#########################  AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" );
	}
}
