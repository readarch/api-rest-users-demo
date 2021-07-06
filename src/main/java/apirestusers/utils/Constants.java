package apirestusers.utils;

public class Constants {

    private Constants() {}

    // Propiedades relacionados a las expresiones regulares
    public static final String PROPERTY_REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PROPERTY_REGEX_PASSWORD = "^(?=.*[A-Z]{1})(?=.*[0-9]{2})(?=.*[a-z]{5}).{8}$";

    // Propiedad relacionado al JWT
    public static final Object PROPERTY_JWT_AUD = "www.globallogic.com";

    // Propiedades relacionados a los mensajes de error por validación
    public static final String PROPERTY_MSG_ERROR_FORMATO_EMAIL = "El correo electrónico no tiene un formato válido tal como por ejm: aaaaaaa@dominio.cl";
    public static final String PROPERTY_MSG_ERROR_FORMATO_PASSWORD = "El password no tiene el formato correcto; debe tener exactamente una letra mayúscula, exactamente dos números y exactamente cinco letras minúsculas.";
    public static final String PROPERTY_MSG_ERROR_EXISTE_EMAIL = "El correo electrónico ya se encuentra registrado.";
}