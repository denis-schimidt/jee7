package br.com.schimidtsolutions.bean;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import br.com.schimidtsolutions.entity.Cliente;
import br.com.schimidtsolutions.tests.SlowTest;

@RunWith(Arquillian.class)
public class MeuBeanIT {

	private static Integer idCliente;
	private static Cliente clienteRecuperado;
	
	@Inject
	private MeuBean bean;
	
	@Deployment
	public static Archive<?> createDeployment() {
		
		final WebArchive war = ShrinkWrap.create( WebArchive.class, "test-integrado.war" )
				.addAsWebInfResource("META-INF/beans.xml", "classes/META-INF/beans.xml")
				.addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml")
				.addPackages( true, "br.com.schimidtsolutions" )
				.deleteClasses( MeuBeanUnitTest.class );
				
		System.out.println( war.toString(true) );
		
		return war;
	}

	@Test
	@InSequence(1)
	@Category(SlowTest.class)
	public void testImprimir() {
		bean.imprimir( "## AHHHHHHHHHHHH $$" );
	}
	
	@Test
	@InSequence(2)
	@Category(SlowTest.class)
	public void testIncluirCliente() {
		final Cliente cliente = new Cliente( "Fulano da Silva" );
		idCliente = bean.adicionar(cliente).getId();
		assertNotNull( idCliente );
	}
	
	@Test
	@InSequence(3)
	@Category(SlowTest.class)
	public void testConsultarCliente() {
		clienteRecuperado = bean.consultar( new Cliente( idCliente ) );
		assertNotNull( clienteRecuperado.getNome() );
	}
	
	@Test
	@InSequence(4)
	@Category(SlowTest.class)
	public void testAlterarCliente() {
		clienteRecuperado.setNome( "Ciclano da Silva" );
		bean.alterar( clienteRecuperado );
	}
	
	@Test
	@InSequence(5)
	@Category(SlowTest.class)
	public void testExcluirCliente() {
		bean.excluir( clienteRecuperado );
	}
}
