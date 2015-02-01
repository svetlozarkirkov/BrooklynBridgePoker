
/** Objects of this class extends RuntimeException
 * 	and is a exception when you try to get a card that does no exist 
 */


public class NoSuchCardException extends RuntimeException {

	/** If you need to send a message with the throw
	 */
	public NoSuchCardException(String msg) {
		super(msg);
	}

	/** If you only need to throw a exception 
	 */
	public NoSuchCardException() {
		super();
	}

	private static final long serialVersionUID = 1L;
}
