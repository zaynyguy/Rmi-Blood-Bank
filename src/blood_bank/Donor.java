package blood_bank;


import java.io.Serializable;

public class Donor implements Serializable {
    private String donorName;
    private String bloodType;
    private int age;

    public Donor(String donorName, String bloodType, int age) {
        this.donorName = donorName;
        this.bloodType = bloodType;
        this.age = age;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donorName='" + donorName + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", age=" + age +
                '}';
    }
}