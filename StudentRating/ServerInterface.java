package StudentRating;
import java.rmi.*;
public interface ServerInterface extends Remote 
{
	public void sendData(String data[][],String clgname) throws RemoteException;
	public boolean isResultReady() throws RemoteException;
	public String getTop5(String StudentR[][]) throws RemoteException;
	public float avgMark(String StudentR[][]) throws RemoteException;
	public void classifyStudent(String Student[][]) throws RemoteException;
	
}
