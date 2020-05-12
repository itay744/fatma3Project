import java.io.IOException;

public class NegativePriceException extends IOException{
private String Message;
	
	public NegativePriceException() {
		this.Message = "Price cannot be nagative";
	}

	public String getMessage() {
		return Message;
	}

}
