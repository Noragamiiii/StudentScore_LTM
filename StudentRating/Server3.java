package StudentRating;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server3 {

	public static void main(String[] args) {
		Thread t;
        try
        {
            new Thread().start();
            int port = 1113;
			ServerImpl impl = new ServerImpl();
			LocateRegistry.createRegistry(port);	
			Naming.rebind("rmi://localhost:1113/Server3", impl);
		}
		catch(Exception e) 
		{
			System.out.println("Exception: " + e);
		}
	}

}
