package blood_bank;


import java.io.Serializable;

public class Donor implements Serializable {
    private String donorName;
    private String bloodType;
    private int quantity;

   

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donorName='" + donorName + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}