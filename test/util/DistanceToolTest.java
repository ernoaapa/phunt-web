package util;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import com.javadocmd.simplelatlng.LatLng;

public class DistanceToolTest {

	@Test
	public void shouldBeCloseEnough() {
		LatLng roskaPankki = new LatLng(60.186825, 24.953538);
		LatLng braahenKentta = new LatLng(60.186777, 24.953987);
		assertTrue(DistanceTool.isCloseEnoughToFind(roskaPankki, braahenKentta));
	}
	
	@Test
	public void shouldNotBeCloseEnough() {
		LatLng sturenKatu13a = new LatLng(60.18936, 24.94753);
		LatLng somewhere = new LatLng(60.18915, 25.94693);
		assertFalse(DistanceTool.isCloseEnoughToFind(sturenKatu13a, somewhere));
	}
}
