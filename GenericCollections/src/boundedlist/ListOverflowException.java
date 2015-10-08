/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boundedlist;

/**
 *
 * @author dev64
 */
public class ListOverflowException extends RuntimeException {

    /**
     * Creates a new instance of <code>ListOverFlowException</code> without
     * detail message.
     */
    public ListOverflowException() {
    }

    /**
     * Constructs an instance of <code>ListOverFlowException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ListOverflowException(String msg) {
        super(msg);
    }
}
