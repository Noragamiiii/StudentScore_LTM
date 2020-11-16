package StudentRating;

import java.io.Serializable;

public class Student implements Serializable  {
	String id, name, marks, clg;
	private static final long serialVersionUID = 1190476516911661470L;
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
		return id + " " + name + " " + marks;
	}
}
