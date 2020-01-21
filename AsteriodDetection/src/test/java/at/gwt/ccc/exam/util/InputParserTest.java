package at.gwt.ccc.exam.util;

import at.gwt.ccc.exam.beans.Image;
import at.gwt.ccc.exam.beans.Observation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import static org.junit.Assert.*;

public class InputParserTest {
	private static final Path INPUT = Paths.get("src/test/resources/level1/input/lvl1-0.inp");


	@Test
	public void parseMissionData() throws IOException {
		Observation observation = InputParser.parseMissionData(INPUT);

		Assert.assertNotNull(observation);

		// check for images

		Assert.assertEquals(1000, observation.getStart());
		Assert.assertEquals(9999, observation.getEnd());

		Collection<Image> images = observation.getImages();
		Assert.assertEquals(3, images.size());
	}
}