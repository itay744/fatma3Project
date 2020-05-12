import java.io.IOException;

public class WrongGenderInputException extends IOException {
	private String Message;
	
	public WrongGenderInputException() {
		this.Message = "Wrong gender, can be only 'm' or 'f' ";
	}

	public String getMessage() {
		return Message;
	}
	
}
