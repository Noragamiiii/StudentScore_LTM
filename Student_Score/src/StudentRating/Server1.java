package StudentRating;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
public class Server1 
{
	public static void main(String args[]) 
	{
		//Thread t;
        try
        {
            new Thread().start();
            int port = 1112;
			ServerImpl impl = new ServerImpl();
			LocateRegistry.createRegistry(port);	
			Naming.rebind("rmi://localhost:1112/Server1", (Remote)impl);
		}
		catch(Exception e) 
		{
			System.out.println("Exception: " + e);
		}
	}
}
