package StudentRating;

public class Student {
	String id, name, marks, clg;

	Student(String id, String name, String marks, String clg) {
		this.id = id;
		this.name = name;
		this.marks = marks;
		this.clg = clg;
	}
	public Student(String id, String name, String marks) {
		this.id = id;
		this.name = name;
		this.marks = marks;
	}

	String display() {
		return id + " " + name + " " + marks + " " + clg;
	}
}
