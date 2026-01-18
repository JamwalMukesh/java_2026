// Dependency Inversion Principle (DIP)
// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

import java.util.List;
import java.util.ArrayList;

class Triplet<T0, T1, T2> {

    private final T0 value0;
    private final T1 value1;
    private final T2 value2;

    public Triplet(T0 value0, T1 value1, T2 value2) {
        this.value0 = value0;
        this.value1 = value1;
        this.value2 = value2;
    }

    public T0 getValue0() {
        return value0;
    }

    public T1 getValue1() {
        return value1;
    }

    public T2 getValue2() {
        return value2;
    }
}

enum Relationship{
	PARENT,
	CHILD,
	SIBLING
}

class Person{
	public String name;

	public Person(String name){
		this.name = name;
	}
}

class Relationships{ // low-level because it links with storage of data
	
	private List<Triplet<Person,Relationship,Person>> relations = new ArrayList<>(); 

	public List<Triplet<Person,Relationship,Person>> getRelations(){
		return relations;
	}

	public void addParentAndChild(Person parent,Person child){
		relations.add(new Triplet<>(parent,Relationship.PARENT,child));
		relations.add(new Triplet<>(child,Relationship.CHILD,parent));
	}
}

class Research{ // high-level because it perform operation on the low-level

	public Research(Relationships relationships){
		List<Triplet<Person,Relationship,Person>> relations;
		relations = relationships.getRelations();

		relations.stream()
				.filter(x -> x.getValue0().name.equals("John")
						&& x.getValue1() == Relationship.PARENT
						)
				.forEach(ch -> System.out.println(
					"John has a child called " + ch.getValue2().name
				));
	}
}

class Demo{
	public static void main(String[] args){
		Person parent = new Person("John");
		Person child1 = new Person("Chris");
		Person child2 = new Person("Matt");

		Relationships relationships = new Relationships();
		relationships.addParentAndChild(parent,child1);
		relationships.addParentAndChild(parent,child2);

		new Research(relationships);
	}
}