package exceptions;

/**
 * @author peo_jjardim
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 13/01/17 15:04
 */
public class NaoDeveConterCaracterExpecialException extends RuntimeException {

    public NaoDeveConterCaracterExpecialException() {
        super("Não deve conter caracter especial");
    }

}
