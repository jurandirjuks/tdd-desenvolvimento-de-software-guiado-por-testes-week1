package exceptions;

/**
 * @author peo_jjardim
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 13/01/17 14:52
 */
public class NaoDeveComecarComNumeroException extends RuntimeException {

    public NaoDeveComecarComNumeroException() {
        super("A string nao deve comecar com numero");
    }
}
