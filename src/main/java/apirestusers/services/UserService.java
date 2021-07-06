package apirestusers.services;

import apirestusers.components.UserComponent;
import apirestusers.models.User;
import apirestusers.requests.RequestUser;
import apirestusers.responses.GenericResponse;
import apirestusers.responses.ResponseError;
import apirestusers.utils.Constants;
import apirestusers.utils.UtilFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    UserComponent userComponent;

    @Autowired
    public UserService(UserComponent userComponent) {
        this.userComponent = userComponent;
    }

    /**
     * Método que permite la creación del usuario con sus teléfonos.
     * @param requestUser - Parámetro de entrada con los datos del usuario a crear.
     * @return GenericResponse - Retorna una respuesta genérica.
     */
    public GenericResponse createUser(RequestUser requestUser) {
        try {
            logger.info("INICIO - UserService.createUser - requestUser = [{}]", requestUser);
            if(!UtilFormat.validEmail(requestUser.getEmail())) {
                return new GenericResponse(new ResponseError(Constants.PROPERTY_MSG_ERROR_FORMATO_EMAIL));
            }

            if(!UtilFormat.validPassword(requestUser.getPassword())) {
                return new GenericResponse(new ResponseError(Constants.PROPERTY_MSG_ERROR_FORMATO_PASSWORD));
            }

            // Optional es característica propia de Java 8
            Optional<User> user = this.userComponent.findUserByEmail(requestUser.getEmail());

            if(user.isPresent()) {
                return new GenericResponse(new ResponseError(Constants.PROPERTY_MSG_ERROR_EXISTE_EMAIL));
            }

            return new GenericResponse(userComponent.saveUser(requestUser));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            logger.info("FIN - UserService.createUser - requestUser = [{}]", requestUser);
        }
    }
}