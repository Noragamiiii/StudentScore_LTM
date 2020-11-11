package StudentRating;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	List<Student> list = new ArrayList<>();
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
		if (isResultReady()) {
			System.out.println("\n\nTop 5 Students");
			data = "";
			sort();
			for (int i = 0; i < 5; i++) {
				
				Student student = list.get(i);
				System.out.println(student.display());
				data = data + student.id + "\t" + student.name + "\t" + student.marks + "\n";
			}
		}
		return data;
	}
		public void sortStudent(List<Student> list) throws RemoteException {
			
			if(isResultReady()) {
				for(int i = 0; i<= list.size(); i++) {
					
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
		float avgmark ;
		float sum = 0;
		int avg = StudentR.length;
		for(int i = 0; i<StudentR.length; i++) {
			sum += Float.parseFloat(StudentR[i][3]);
		}
		avgmark = sum/avg;
		return avgmark;
	}

	@Override
	public void classifyStudent(String[][] Student) throws RemoteException {
				
		
	}

}