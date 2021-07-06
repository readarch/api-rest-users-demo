package apirestusers.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilFormat {

    private  UtilFormat() {}

    /**
     * Método que valida el formato del correo electrónico mediante expresión regular.
     * @param email - El email debe de cumplir el formato tal como el siguiente ejemplo: aaaaaaa@dominio.cl
     * @return boolean - Si el correo electrónico es válido retorna true; de lo contrario, retorna false.
     */
    public static boolean validEmail(String email) {
        Pattern pattern = getPattern(Constants.PROPERTY_REGEX_EMAIL);
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    /**
     * Método que valida el formato de la contraseña mediante expresión regular.
     * @param password - La contraseña debe de tener exactamente una letra mayúscula, exactamente dos números y exactamente cinco letras minúsculas.
     * @return boolean - Si la contraseña es válida retorna true; de lo contrario, retorna false.
     */
    public static boolean validPassword(String password) {
        Pattern pattern = getPattern(Constants.PROPERTY_REGEX_PASSWORD);
        Matcher mather = pattern.matcher(password);
        return mather.find();
    }

    /**
     * Método que retorna el Pattern al recibir la expresión regular.
     * @param regex - Parámetro que contiene la expresión regular.
     * @return Pattern
     */
    private static Pattern getPattern(String regex) {
        return Pattern.compile(regex);
    }

}