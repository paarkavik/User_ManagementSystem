
public class CustomException extends Exception{
	 public CustomException(String message) {
	        super(ConsoleColors.RED_BOLD+message+ConsoleColors.RESET);
	    }
}
