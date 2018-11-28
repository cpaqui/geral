import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class ShowLocalhostIps {
    
    public static void main (String[] args) throws SocketException, UnknownHostException {
	NetworkInterface nif = NetworkInterface.getByName("eth0");
	Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
	
	while (nifAddresses.hasMoreElements()) {
	    InetAddress inetAddress = (InetAddress) nifAddresses.nextElement();
	    if (!inetAddress.isLoopbackAddress() && !(inetAddress instanceof Inet6Address)) {
		System.out.println(inetAddress.getHostAddress());
	    }
	    
	}
    }
}