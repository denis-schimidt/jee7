package br.com.schimidtsolutions.bean;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import br.com.schimidtsolutions.tests.SlowTests;

public class MeuBeanTest {

	@Test
	@Category(SlowTests.class)
	public void testImprimir() {
		System.out.println( "Testando 1455..." );
	}
}
