package blood_bank;

    
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Blood_Bank_Implimentaion extends UnicastRemoteObject implements Blood_Bank_Remote_Interface  {
    private Map<String, Donor> donors;

    public Blood_Bank_Implimentaion() throws RemoteException {
        donors = new HashMap<>();
    }

    @Override
    public void createDonor(String donorName, String bloodType, int age) throws RemoteException {
        Donor donor = new Donor(donorName, bloodType, age);
        donors.put(donorName, donor);
        System.out.println("Donor created: " + donor);
    }

    @Override
    public String getDonor(String donorName) throws RemoteException {
        Donor donor = donors.get(donorName);
        if (donor != null) {
            return donor.toString();
        } else {
            return "Donor not found.";
        }
    }

    @Override
    public List<String> getAllDonors() throws RemoteException {
        List<String> donorList = new ArrayList<>();
        for (Donor donor : donors.values()) {
            donorList.add(donor.toString());
        }
        return donorList;
    }

    @Override
    public void updateDonor(String donorName, String bloodType, int age) throws RemoteException {
        Donor donor = donors.get(donorName);
        if (donor != null) {
            donor.setBloodType(bloodType);
            donor.setAge(age);
            System.out.println("Donor updated: " + donor);
        } else {
            System.out.println("Donor not found.");
        }
    }

    @Override
    public void deleteDonor(String donorName) throws RemoteException {
        Donor donor = donors.remove(donorName);
        if (donor != null) {
            System.out.println("Donor deleted: " + donor);
        } else {
            System.out.println("Donor not found.");
        }
    }
}