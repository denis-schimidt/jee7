package br.com.schimidtsolutions.bean;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.Resolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MeuBeanTest {

	@Inject
	private MeuBean bean;
	
	@Deployment
	public static Archive<?> createDeployment() {
		
		final WebArchive archive = ShrinkWrap.create( WebArchive.class )
				.addAsLibraries( Resolvers.use( MavenResolverSystem.class ).loadPomFromFile( "pom.xml" ).importTestDependencies().resolve().withoutTransitivity().asFile() )	
				.addAsLibraries( Resolvers.use( MavenResolverSystem.class ).loadPomFromFile( "pom.xml" ).importCompileAndRuntimeDependencies().resolve().withoutTransitivity().asFile() )
				.addPackages( true, "br.com.schimidtsolutions" )
				.addAsWebInfResource( "log4j2.xml", "classes/META-INF/log4j2.xml" )
				.addAsWebInfResource( EmptyAsset.INSTANCE, "classes/META-INF/beans.xml" );
		
		System.out.println( archive.toString(true) );
		
		return archive;
	}

	@Test
	public void testImprimir() {
		bean.imprimir( "Testando 1455..." );
		
	}
}
