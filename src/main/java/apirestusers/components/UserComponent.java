package apirestusers.components;

import apirestusers.models.Phone;
import apirestusers.models.User;
import apirestusers.repositories.UserRepository;
import apirestusers.requests.RequestUser;
import apirestusers.responses.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserComponent {

    UserRepository userRepository;
    TokenJWTComponent tokenJWTComponent;

    @Autowired
    public UserComponent(UserRepository userRepository, TokenJWTComponent tokenJWTComponent) {
        this.userRepository = userRepository;
        this.tokenJWTComponent = tokenJWTComponent;
    }

    /**
     * Método que permite buscar un usuario por medio del correo electrónico en la base de datos H2.
     * @param email - Correo electrónico.
     * @return User - Retorna el usuario encontrado.
     */
    public Optional<User> findUserByEmail(String email) {
        User entity = new User();
        entity.setEmail(email);
        // Parallel Stream es característica propia de Java 8
        return this.userRepository.findAll().parallelStream().filter(user -> user.getEmail().equals(entity.getEmail())).findFirst();
    }

    /**
     * Método que permite guardar/crear un usuario con sus teléfonos en la base de datos H2.
     * @param requestUser - Parámetro de entrada con los datos del usuario a crear.
     * @return ResponseUser - Retorna el usuario creado.
     */
    public ResponseUser saveUser(RequestUser requestUser) {
        User userEntity = new User();
        userEntity.setName(requestUser.getName());
        userEntity.setEmail(requestUser.getEmail());
        userEntity.setPassword(requestUser.getPassword());
        String token = this.tokenJWTComponent.getToken(userEntity.getPassword());
        userEntity.setToken(token);

        // Lambda - forEach es característica propia de Java 8
        requestUser.getPhones().forEach( phone -> {
            Phone phoneEntity = new Phone();
            phoneEntity.setNumber(phone.getNumber());
            phoneEntity.setCitycode(phone.getCitycode());
            phoneEntity.setContrycode(phone.getContrycode());
            phoneEntity.setUser(userEntity);
            userEntity.getPhones().add(phoneEntity);
        });

        User createdUser = userRepository.save(userEntity);
        return new ResponseUser(createdUser.getId(), createdUser.getName(), createdUser.getCreated(), createdUser.getModified(), createdUser.getLastLogin(), createdUser.getToken(), createdUser.getIsActive());
    }

}