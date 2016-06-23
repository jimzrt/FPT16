package problem4;

public class Main {
	
	public static void main(String[] args){
		Thread thread = new Thread(new Acquisition());
		thread.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

	        public void run() {
	            System.out.println("Applikation beendet.");
	        }
	    }));
	}

}
