package boundedlist;

/**
 *
 * @author dev64
 */
class ListUnderflowException extends RuntimeException {

    public ListUnderflowException() {
    }
    
    public ListUnderflowException(String msg) {
        super(msg);
    } 

}
