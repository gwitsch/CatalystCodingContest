package at.gwt.cc.sr.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import at.gwt.cc.sr.beans.Level1Input;

public class InputParserTest {
	private static final Path INPUT = Paths.get("src/test/resources/level1/input/level1_example.in");
	
	@Test
	public void testParse() throws Exception {
		Level1Input level1 = InputParser.parse(INPUT);
		Assert.assertNotNull(level1);
		
		Assert.assertEquals(5, level1.getRows());
		Assert.assertEquals(5, level1.getCols());
	}

}
