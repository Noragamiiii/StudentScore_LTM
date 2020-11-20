package StudentRating;
import java.rmi.*;
import java.util.List;
public interface ServerInterface extends Remote 
{
	public List<Student> TransformData(String Student[][]) throws RemoteException;
	public boolean sendData(String data[][], String className) throws RemoteException;
	public boolean isResultReady() throws RemoteException;
	public String getTop5(String StudentR[][]) throws RemoteException;
	public float avgMark(String StudentR[][]) throws RemoteException;
	public String classifyStudent(String Student[][]) throws RemoteException;
	public String sortStudent(String Student[][]) throws RemoteException;
	public String ListStudent(String Student[][]) throws RemoteException;
	
}
