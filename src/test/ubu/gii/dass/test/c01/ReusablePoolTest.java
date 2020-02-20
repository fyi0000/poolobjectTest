/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Vector;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * Clase de tsts
 * @author <a href="mailto:fyi0000@alu.ubu.es">Fco Javier Yagüe Izquierdo<a>
 * @author <a href="mailto:jaa0124@alu.ubu.es">Johnson Arrobo Acaro<a>
 */
public class ReusablePoolTest {

	private ReusablePool poolTests1;
	private ReusablePool poolTests2;

	/**
	 * Se crea una nueva intancia de ReusableTest antes de cada test
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		poolTests1 = ReusablePool.getInstance();
		poolTests2 = ReusablePool.getInstance();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		poolTests1 = poolTests2 = null;
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(poolTests1);
		assertNotNull(poolTests2);
		assertTrue(poolTests1 instanceof ReusablePool);
		assertTrue(poolTests2 instanceof ReusablePool);
	}

}
