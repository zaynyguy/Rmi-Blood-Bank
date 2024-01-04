
package blood_bank;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Blood_Bank_Server {
    public static void main(String[] args) {
        try {
            Blood_Bank_Implimentaion bloodBank = new Blood_Bank_Implimentaion();
            
            // Create the RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Bind the implementation object to the registry with a name
            registry.rebind("BloodBank", bloodBank);
            
            System.out.println("Blood Bank server is running.");
        } catch (RemoteException e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}