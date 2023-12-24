package blood_bank;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Blood_Bank_Remote_Interface extends Remote {
    public void createDonor(String donorName, String bloodType, double blood_quantity) throws RemoteException;
//    String getDonor(String donorName) throws RemoteException;
    public List<Donor> getAllDonors() throws RemoteException;
    public void updateDonor(String donorName, String bloodType, double blood_quantity) throws RemoteException;
    public void deleteDonor(String donorName) throws RemoteException;
    public boolean isAdminValid(String username, String password) throws RemoteException;
}
