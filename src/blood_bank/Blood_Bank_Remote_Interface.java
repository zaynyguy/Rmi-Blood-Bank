package blood_bank;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Blood_Bank_Remote_Interface extends Remote {
    void createDonor(String donorName, String bloodType, int age) throws RemoteException;
    String getDonor(String donorName) throws RemoteException;
    List<String> getAllDonors() throws RemoteException;
    void updateDonor(String donorName, String bloodType, int age) throws RemoteException;
    void deleteDonor(String donorName) throws RemoteException;
}
