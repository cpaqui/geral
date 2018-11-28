class Hello {

    static main(args){
	NetworkInterface nif = NetworkInterface.getByName("eth0");
	Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
	
	while (nifAddresses.hasMoreElements()) {
	    InetAddress inetAddress = (InetAddress) nifAddresses.nextElement();
	    if (!inetAddress.isLoopbackAddress() && !(inetAddress instanceof Inet6Address)) {
		println(inetAddress.getHostAddress());
	    }
	    
	}
	
    }

    static sum(a,b,c=0){
	a+b+c;
    }
}