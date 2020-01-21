package at.gwt.ccc.nw.beans;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person implements Comparable<Person> {
	private String name;
	private Person father;
	private Person mother;

	public Person(String name, Person father, Person mother) {
		super();
		this.name = name;
		this.father = father;
		this.mother = mother;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(Stream.of(this, this.father, this.mother).filter(Objects::nonNull)
				.map(Person::getName).collect(Collectors.joining(","))).toString();
	}

	@Override
	public int compareTo(Person o) {
		return this.name.compareTo(o.getName());
	}
}
