package StudentRating;

import java.io.Serializable;

public class Student implements Serializable  {
	String id, name, marks, clg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getClg() {
		return clg;
	}
	public void setClg(String clg) {
		this.clg = clg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
