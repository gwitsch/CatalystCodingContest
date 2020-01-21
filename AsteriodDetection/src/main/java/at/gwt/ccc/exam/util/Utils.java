package at.gwt.ccc.exam.util;

import java.awt.Polygon;
import java.util.Arrays;
import java.util.Objects;

public final class Utils {
	
	private Utils() {
		// utility class
	}

	/**
	 * see
	 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/TeststwopolygonsforequalityIfbotharenullthismethodreturnstrue.htm
	 */
	public static boolean polyonsEqual(final Polygon p1, final Polygon p2) {
		if (p1 == null) {
			return (p2 == null);
		}
		if (p2 == null) {
			return false;
		}
		if (p1.npoints != p2.npoints) {
			return false;
		}
		if (!Arrays.equals(p1.xpoints, p2.xpoints)) {
			return false;
		}
		if (!Arrays.equals(p1.ypoints, p2.ypoints)) {
			return false;
		}
		return true;
	}

	public static int hashCode(Polygon polygon) {
		return Objects.hashCode(polygon.npoints) + Arrays.hashCode(polygon.xpoints) + Arrays.hashCode(polygon.ypoints);
	}
}
