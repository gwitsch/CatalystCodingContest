package at.gwt.ccc.nw.op;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import at.gwt.ccc.nw.beans.Person;
import at.gwt.ccc.nw.util.PersonRegistry;

public class OperationHandler {

	public Collection<Person> handle(String operationLine) {
		Operation operation = this.parseOperation(operationLine);
		List<String> params = this.parseParameters(operationLine);

		switch (operation) {
		case ANCESTORS:
			return PersonRegistry.getInstance().ancestors(params.get(0)).stream()
					.collect(Collectors.toCollection(TreeSet::new));
		}

		return Collections.emptySet();
	}

	private Operation parseOperation(String operationLine) {
		String opName = operationLine.substring(0, operationLine.indexOf('('));
		return Operation.fromName(opName);
	}

	private List<String> parseParameters(String operationLine) {
		int indexOpenParanth = operationLine.indexOf('(');
		int indexCloseParanth = operationLine.lastIndexOf(')');

		String paramStr = operationLine.substring(indexOpenParanth + 1, indexCloseParanth);

		if (paramStr.contains(",")) {
			return Arrays.asList(paramStr.split(","));
		} else {
			return Arrays.asList(paramStr);
		}
	}
}
