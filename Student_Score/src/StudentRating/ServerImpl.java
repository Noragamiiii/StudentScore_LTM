package StudentRating;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	List<Student> list = new ArrayList<>();//luu nguyen 
	int count = 0;

	public ServerImpl() throws RemoteException {
	}

	public void sendData(String data[][], String clgname) throws RemoteException {
		System.out.println("\n\nList from : " + clgname);
		for (int i = 0; i < data.length; i++) {
			Student s = new Student(data[i][0], data[i][1], data[i][2], clgname);
			list.add(s);
			System.out.println(s.display());
		}
		count++;
	}

	public boolean isResultReady() throws RemoteException {
		if (count >= 10)
			return true;
		return false;
	}

	public String getTop5(String StudentR[][]) throws RemoteException {
		String data = "Still Processing the result..";

		System.out.println("\n\nTop 5 Students");
		data = "";
		sortStudent(StudentR);
//		sort();
		for (int i = 0; i < 5; i++) {
			Student student = list.get(i);
			System.out.println(student.display());
			data = data + student.id + "\t" + student.name + "\t" + student.marks + "\n";

		}
		return data;
	}

	public void sortStudent(String Student[][]) throws RemoteException {
		list = TransformData(Student);
		float temp;
		for (int i = 0; i < Student.length; i++) {
			for (int j = i + 1; j < Student.length; j++) {
				if (Float.parseFloat(Student[i][3]) > Float.parseFloat(Student[j][3])) {
					temp = Float.parseFloat(Student[i][3]);
					Student[i][3] = Student[j][3];
					temp = Float.parseFloat(Student[j][3]);
				}
			}
		}
	}

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
			sum += Float.parseFloat(StudentR[i][3]);
		}
		avgmark = sum / avg;
		return avgmark;
	}

	@Override
	public void classifyStudent(String[][] Student) throws RemoteException {
		String classify = "";
		int count = 0;
		for (int i = 0; i < Student.length; i++) {
			if (Float.parseFloat(Student[i][2]) >= 0 && Float.parseFloat(Student[i][2]) <= 100) {
				if (Float.parseFloat(Student[i][2]) >= 8 && Float.parseFloat(Student[i][2]) <= 100 ) {
					classify = "Gioi";
				} else if (Float.parseFloat(Student[i][2]) >= 65 && Float.parseFloat(Student[i][2]) <85) {
					classify = "Kha";
				} else if (Float.parseFloat(Student[i][2]) >= 50 && Float.parseFloat(Student[i][2]) < 65) {
					classify = "Trung Binh";
				} else {
					classify = "Yeu";
				}
			}

		}
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
