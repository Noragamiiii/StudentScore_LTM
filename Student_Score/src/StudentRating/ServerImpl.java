package StudentRating;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	private static final long serialVersionUID = 1L;
	List<Student> list = new ArrayList<>();//luu nguyen 
	 int count = 0;

	public ServerImpl() throws RemoteException {
	}
	// thay vì trả về void--> boolean --> 
	@Override
	public boolean sendData(String data[][], String className) throws RemoteException {
		System.out.println("Result from: " + className);
		for (int i = 0; i < data.length; i++) {
			Student s = new Student(data[i][0], data[i][1], data[i][2]);
			list.add(s);
			System.out.println(s.display());
			count++;
		}
		if(count >= 10) {
			return true;
		}
		return false;
//		System.out.println("Count: "+ count);
	}
	@Override
	public String ListStudent(String Student[][]) {
		String student= "";
		for (int i = 0; i < Student.length; i++) {
			Student st = list.get(i);
			System.out.println(st.display());
			student = student + st.id + "\t" + st.name + "\t" + st.marks + "\n";
		}
		return student;
	}
	
@Override
	public boolean isResultReady() throws RemoteException {
		if (count >= 10)
			return true;
		return false;
	}
	// tab 2
	@Override
	public String getTop5(String StudentR[][]) throws RemoteException {
		String data = "Still Processing the result..";
		System.out.println("\n\nTop 5 Students");
		data = "";
		//sortStudent(StudentR);
		sort();
		for (int i = 0; i < 5; i++) {
			Student student = list.get(i);
			System.out.println(student.display());
			data = data + student.id + "\t" + student.name + "\t" + student.marks + "\n";
		}
		return data;
	}
// tab 1
	@Override
	public String sortStudent(String Student[][]) throws RemoteException {
		String data = "Still Processing the result..";
		System.out.println("\n\nTop 5 Students");
		data = "";
		//sortStudent(StudentR);
		sort();
		for (int i = 0; i < Student.length; i++) {
			Student student = list.get(i);
			System.out.println(student.display());
			data = data + student.id + "\t" + student.name + "\t" + student.marks + "\n";
		}
		return data;
	}
	// tab 3
		@Override
		public String classifyStudent(String[][] Student) throws RemoteException {
			String classify = "";
			String data = "";
			
			for (int i = 0; i < Student.length; i++) {
				
				Student student = list.get(i);

				if (Float.parseFloat(Student[i][2]) >= 0 && Float.parseFloat(Student[i][2]) <= 100) {
					if (Float.parseFloat(Student[i][2]) >= 80 && Float.parseFloat(Student[i][2]) <= 100 ) {
						classify = "Gioi";
					} else if (Float.parseFloat(Student[i][2]) >= 65 && Float.parseFloat(Student[i][2]) < 85) {
						classify = "Kha";
					} else if (Float.parseFloat(Student[i][2]) >= 50 && Float.parseFloat(Student[i][2]) < 65) {
						classify = "Trung Binh";
					} else {
						classify = "Yeu";
					}
				}
				data = data + student.id + "\t" + student.name + "\t" + student.marks + "\t" +  classify + "\n";
			}
			return data;
		}

		
		// do not use
	void sort() {
		Comparator cmp = new MarksComparator();
		Collections.sort(list, cmp);
	}

	class MarksComparator implements Comparator<Student> {
		@Override
		public int compare(Student obj1, Student obj2) {
			return (obj1.marks.compareTo(obj2.marks) > 0) ? -1 : (obj1.marks.compareTo(obj2.marks) < 0) ? 1 : 0;
		}
	}

	// tinh diem trung binh cua ca lop
	@Override
	public float avgMark(String[][] StudentR) throws RemoteException {
		float avgmark;
		float sum = 0;
		int avg = StudentR.length;
		for (int i = 0; i < StudentR.length; i++) {
			sum += Float.parseFloat(StudentR[i][2]);
		}
		avgmark = sum / avg;
		return avgmark;
	}


	@Override
	public List<Student> TransformData(String[][] Student) throws RemoteException {

		for (int i = 0; i < Student.length; i++) {
			Student s = new Student(Student[i][0], Student[i][1], Student[i][2]);
			list.add(s);
		}

		return list;
	}
}
