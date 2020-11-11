package StudentRating;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server2 {

	public static void main(String[] args) {
		Thread t;
        try
        {
            new Thread().start();
            int port = 1114;
			ServerImpl impl = new ServerImpl();
			LocateRegistry.createRegistry(port);	
			Naming.rebind("rmi://localhost:1114/Server2", impl);
		}
		catch(Exception e) 
		{
			System.out.println("Exception: " + e);
		}
	}

}
