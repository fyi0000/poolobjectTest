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

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * 
	 */
	@Test
	public void testAcquireReusable() {

		assertThrows(NotFreeInstanceException.class, () -> {
			int sizeReusable = 0;
			for (int i = 0; i < 3; i++) { // rebasamos el limite por defecto de 2
				assertNotNull(poolTests1.acquireReusable());
				assertNotNull(poolTests2.acquireReusable());
				sizeReusable++;
			}
			assertEquals(sizeReusable, 2);

		});
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		assertThrows(DuplicatedInstanceException.class, () -> {
			Reusable reusable = poolTests1.acquireReusable();
			poolTests1.releaseReusable(reusable);
			poolTests1.releaseReusable(reusable); // exception: reusable already in reusables

		});

		Reusable reusableNoExistente = new Reusable(); // reusable not in reusables
		Reusable reusableNoExistente2 = new Reusable();// reusable not in reusables
		Vector<Reusable> reusables = new Vector<>();
		try {
			poolTests2.releaseReusable(reusableNoExistente);
			poolTests2.releaseReusable(reusableNoExistente2);
			while (true) {
				reusables.add(poolTests2.acquireReusable());
			}

		} catch (DuplicatedInstanceException | NotFreeInstanceException ex) {
			assertEquals(reusables.size(), 4);
		}

	}

}
