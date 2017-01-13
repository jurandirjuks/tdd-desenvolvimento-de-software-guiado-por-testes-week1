import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.NaoDeveComecarComNumeroException;
import exceptions.NaoDeveConterCaracterExpecialException;

/**
 * @author peo_jjardim
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 12/01/17 18:03
 */
public class TransformadorCamelCase {
    public static List<String> converterCamelCase(String value) {
        if (verificaSeEntradaVazia(value))
            return Collections.emptyList();

        verificaSeComecaComDigito(value);
        verificaSeContemCaracterEspecial(value);

        return geraListaDePalavras(value);
    }

    private static ArrayList<String> geraListaDePalavras(final String value) {
        ArrayList<String> palavras = new ArrayList<String>();

        final String[] split = separaPalavrasPeloCaracterMaiusculo(value);
        adicionaPalavras(palavras, split);
        return palavras;
    }

    private static boolean verificaSeEntradaVazia(final String value) {
        if (value.isEmpty()) {
            return true;
        }
        return false;
    }

    private static void verificaSeContemCaracterEspecial(final String value) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]*");
        Matcher matcher = pattern.matcher(value);
        boolean encontrouCaracterEspecial = !matcher.matches();

        if (encontrouCaracterEspecial) {
            throw new NaoDeveConterCaracterExpecialException();
        }
    }

    private static void verificaSeComecaComDigito(final String value) {
        if (Character.isDigit(value.charAt(0))) {
            throw new NaoDeveComecarComNumeroException();
        }
    }

    private static void adicionaPalavras(final ArrayList<String> palavras, final String[] split) {
        String acronimo = "";
        for (int i = 0; i < split.length; i++) {
            final String palavra = split[i];
            if (palavra.length() > 1) {
                adicionaAcronimoSeNaoForVazio(palavras, acronimo);
                palavras.add(palavra.toLowerCase());
                acronimo = "";
            } else {
                acronimo += palavra;
            }
        }
        adicionaAcronimoSeNaoForVazio(palavras, acronimo);
    }

    private static void adicionaAcronimoSeNaoForVazio(final ArrayList<String> palavras, String acronimo) {
        if (!acronimo.isEmpty()) {
            palavras.add(acronimo);
        }
    }

    private static String[] separaPalavrasPeloCaracterMaiusculo(final String value) {
        return value.split("(?<!^)(?=[A-Z])");
    }

}
