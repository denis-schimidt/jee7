package br.com.schimidtsolutions.bean;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MeuBeanTest {

	@Inject
	private MeuBean bean;
	
	@Deployment(name = "testEJB")
	public static Archive<?> createDeployment() {
		final JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
				.addPackages( true, "br.com.schimidtsolutions" )
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	@Test
	public void testImprimir() {
		bean.imprimir( "Testando 123..." );
	}
}
