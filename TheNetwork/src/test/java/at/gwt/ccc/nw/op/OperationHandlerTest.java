package at.gwt.ccc.nw.op;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import at.gwt.ccc.nw.beans.Person;
import at.gwt.ccc.nw.util.PersonRegistry;

public class OperationHandlerTest {
	
	@BeforeClass
	public static void setup() throws IOException {
		PersonRegistry.init(Paths.get("src/test/resources/test/simple_social_network.txt"));
	}

	@Test
	public void testHandle() throws Exception {
		OperationHandler handler = new OperationHandler();
		
		String op = "ancestors(Enoch)";
		
		Set<String> ancestors = handler.handle(op).stream().map(Person::getName).collect(Collectors.toSet());
		
		Assert.assertEquals(3, ancestors.size());
		
		Assert.assertTrue(ancestors.contains("Adam"));
		Assert.assertTrue(ancestors.contains("Cain"));
		Assert.assertTrue(ancestors.contains("Eve"));
	}
}
