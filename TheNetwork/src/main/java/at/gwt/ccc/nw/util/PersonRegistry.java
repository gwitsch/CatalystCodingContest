package at.gwt.ccc.nw.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import at.gwt.ccc.nw.beans.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Registry containing all people.
 * 
 * @author gotthardwitsch
 *
 */
public class PersonRegistry {
	private static final Logger logger = LogManager.getLogger();
	private static PersonRegistry INSTANCE;


	private Set<Person> people;

	private PersonRegistry() {
		this.people = new TreeSet<>();
	}

	public static PersonRegistry getInstance() {
		if (Objects.isNull(INSTANCE)) {
			throw new IllegalStateException("Registry has not been initialized properly");
		}

		return INSTANCE;
	}

	public boolean exists(String name) {
		return this.people.stream().map(Person::getName).anyMatch(name::equals);
	}

	/**
	 * @param name the person's name
	 * @return the found person or null if not found
	 */
	public Person get(String name) {
		return this.people.stream().filter(p -> name.equals(p.getName())).reduce((v1, v2) -> {
			throw new IllegalStateException("Names in registry must be unique");
		}).orElse(null);
	}

	/**
	 * @param person the person to register
	 */
	public void register(Person person) {
		Objects.requireNonNull(person);
		Objects.requireNonNull(person.getName());

		if (person.getName().isEmpty()) {
			throw new IllegalStateException("Name cannot be empty");
		}

		this.people.add(person);
	}

	public static void init(Path data) throws IOException {
		logger.info("Initializing network");
		INSTANCE = new PersonRegistry();

		initData(data);

		logger.info("Network has been initialized with data");
	}

	private static void initData(Path data) throws IOException {
		Files.lines(data).forEach(PersonRegistry::registerPerson);
	}

	private static void registerPerson(String line) {
		PersonRegistry registry = getInstance();

		String[] comps = line.split(",");

		String name = comps[0];

		Optional<String> fathersName = comps.length >= 2 && !comps[1].isEmpty() ? Optional.of(comps[1]) : Optional.empty();
		Optional<String> mothersName = comps.length >= 3 && !comps[2].isEmpty() ? Optional.of(comps[2]) : Optional.empty();

		Person father = null;
		Person mother = null;

		// check for father existence
		if (fathersName.isPresent()) {
			if (!registry.exists(fathersName.get())) {
				registry.register(new Person(fathersName.get(), null, null));
			}

			father = registry.get(fathersName.get());
		}

		// check for mother existence
		if (mothersName.isPresent()) {
			if (!registry.exists(mothersName.get())) {
				registry.register(new Person(mothersName.get(), null, null));
			}

			mother = registry.get(mothersName.get());
		}

		if (registry.exists(name)) {
			// update existing person
			Person existingPerson = registry.get(name);

			if (Objects.nonNull(father) && Objects.isNull(existingPerson.getFather())) {
				existingPerson.setFather(father);
			}

			if (Objects.nonNull(mother) && Objects.isNull(existingPerson.getMother())) {
				existingPerson.setMother(mother);
			}
		} else {
			// register new person
			registry.register(new Person(name, father, mother));
		}
	}

	/**
	 * Retrieves a person's ancestors.
	 * 
	 * @param personName the person who's ancestor should be search
	 * @return all found ancestors of the given person
	 */
	public Collection<Person> ancestors(String personName) {
		if (!this.exists(personName)) {
			return Collections.emptySet();
		}

		Set<Person> ancestors = new TreeSet<>();

		Person person = this.get(personName);

		Person father = person.getFather();
		Person mother = person.getMother();

		if (Objects.nonNull(father)) {
			ancestors.add(father);
			ancestors.addAll(this.ancestors(father.getName()));
		}

		if (Objects.nonNull(mother)) {
			ancestors.add(mother);
			ancestors.addAll(this.ancestors(mother.getName()));
		}

		return ancestors;
	}
}
