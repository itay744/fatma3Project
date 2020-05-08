
public class Events {
	private String name;
	private int id;
	private double pricePerTicket;

	public Events(String name, int id, double pricePerTicket) {

		this.name = name;
		this.id = id;
		if (pricePerTicket<0) {
			throw new NegativePriceException();
		}
		this.pricePerTicket = pricePerTicket;
		
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return new String(name);
	}

	public double getPricePerTicket() {
		return this.pricePerTicket;
	}

}
