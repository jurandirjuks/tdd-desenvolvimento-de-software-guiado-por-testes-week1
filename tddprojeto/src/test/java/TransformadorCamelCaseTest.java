import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import exceptions.NaoDeveComecarComNumeroException;
import exceptions.NaoDeveConterCaracterExpecialException;
import org.junit.Test;

/**
 * @author peo_jjardim
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 12/01/17 17:56
 */
public class TransformadorCamelCaseTest {

    @Test
    public void testeComStringVazia() {
        List<String> resultado = TransformadorCamelCase.converterCamelCase("");
        assertEquals("Para uma String vazia, nao deveria retornar elementos", 0, resultado.size());
    }

    @Test
    public void testePalavraUnica() {
        String value = "nome";
        List<String> resultado = TransformadorCamelCase.converterCamelCase(value);
        assertEquals("Para uma String comum, deveria retorna ela mesma", 1, resultado.size());
        assertEquals(value, resultado.get(0));
    }

    @Test
    public void testePalavraUnicaIniciandoComCase() {
        String value = "Nome";
        List<String> resultado = TransformadorCamelCase.converterCamelCase(value);
        assertEquals("Para uma String com case, deveria retorna ela mesma lower case", 1, resultado.size());
        assertEquals(value.toLowerCase(), resultado.get(0));
    }

    @Test
    public void testeNomeComposto() {
        String value = "nomeComposto";
        List<String> resultado = TransformadorCamelCase.converterCamelCase(value);
        assertEquals("Deveria retornar 2 elementos", 2, resultado.size());
        assertEquals("nome", resultado.get(0));
        assertEquals("composto", resultado.get(1));

        value = "NomeComposto";
        resultado = TransformadorCamelCase.converterCamelCase(value);
        assertEquals("Deveria retornar 2 elementos", 2, resultado.size());
        assertEquals("nome", resultado.get(0));
        assertEquals("composto", resultado.get(1));
    }

    @Test
    public void testeCPF() {
        String value = "CPF";
        List<String> resultado = TransformadorCamelCase.converterCamelCase(value);
        assertEquals("Deveria retornar 1 elemento", 1, resultado.size());
        assertTrue("CPF".equals(resultado.get(0)));

    }

    @Test
    public void testenumeroCPFContribuinte() {
        String value = "numeroCPFContribuinte";
        List<String> resultado = TransformadorCamelCase.converterCamelCase(value);
        assertEquals("Deveria retornar 3 elementos", 3, resultado.size());
        assertTrue("numero".equals(resultado.get(0)));
        assertTrue("CPF".equals(resultado.get(1)));
        assertTrue("contribuinte".equals(resultado.get(2)));
    }

    @Test(expected = NaoDeveComecarComNumeroException.class)
    public void testeComecaComNumero() {
        String value = "10Primeiros";
        List<String> resultado = TransformadorCamelCase.converterCamelCase(value);
    }

    @Test(expected = NaoDeveConterCaracterExpecialException.class)
    public void testeComCaracterEspecial() {
        String value = "nome#Composto";
        List<String> resultado = TransformadorCamelCase.converterCamelCase(value);
    }
}
