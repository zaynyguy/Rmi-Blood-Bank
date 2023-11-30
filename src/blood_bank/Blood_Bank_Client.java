
package blood_bank;

import java.rmi.Naming;

/**
 *
 * @author mac
 */
public class Blood_Bank_Client {

    public static void main(String arg[])
        {
try
{
Blood_Bank_Remote_Interface server = (Blood_Bank_Remote_Interface)Naming.lookup("rmi://127.0.0.1:1234/BloodBank");










}
catch(Exception e)
{
System.out.println(e);
}
}
}
    

