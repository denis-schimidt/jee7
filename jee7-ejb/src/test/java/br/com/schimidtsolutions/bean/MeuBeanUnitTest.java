package br.com.schimidtsolutions.bean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;

import br.com.schimidtsolutions.tests.FastTest;

@RunWith(MockitoJUnitRunner.class)
public class MeuBeanUnitTest {

	private static final String[] PARAMETROS_VALIDOS = { "Teste Simples", "çáà@#$ºª", "nulo", "159*/Ésb♣◙│o▬", "vazio" };
	private static final String[] PARAMETROS_INVALIDOS = { null, "", " " };
	
	@Mock
	private Logger log;

	@InjectMocks
	private MeuBean bean;

	@Before
	public void init() {
		bean = new MeuBean();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Category(FastTest.class)
	public void testImprimirValorValido() {

		for (final String parametroValido : PARAMETROS_VALIDOS) {
			final LogAnswer answer = new LogAnswer();

			doAnswer(answer).doNothing().when(log).info(anyString());

			bean.imprimir(parametroValido);
			
			assertTrue( answer.response );
		}
	}
	
	@Test
	@Category(FastTest.class)
	public void testImprimirValorInvalido() {

		for (final String parametroInvalido : PARAMETROS_INVALIDOS) {
			final LogAnswer answer = new LogAnswer();

			doAnswer(answer).doNothing().when(log).info(anyString());

			bean.imprimir(parametroInvalido);
			
			assertFalse( answer.response );
		}
	}

	private class LogAnswer implements Answer<Boolean> {
		private Boolean response = Boolean.FALSE;

		@Override
		public Boolean answer(final InvocationOnMock invocation) throws Throwable {
			String valorLogado = null;
			
			if (invocation.getArguments().length > 0) {
				valorLogado = String.valueOf( invocation.getArguments()[0] );
				response = Boolean.valueOf(!valorLogado.equals(null));
			}

			System.out.printf( "Resposta %b - Valor logado: %s \n", response, valorLogado );
			
			return null;
		}
	}
}
