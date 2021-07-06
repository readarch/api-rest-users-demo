package apirestusers

import apirestusers.controllers.UserController
import apirestusers.requests.RequestPhone
import apirestusers.requests.RequestUser
import apirestusers.responses.GenericResponse
import apirestusers.responses.ResponseError
import apirestusers.responses.ResponseUser
import apirestusers.utils.Constants
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest extends Specification {

    @Autowired
    UserController userController

    def "Ejecutando el metodo: UserController.createUser - Formato de email incorrecto"() {
        expect: "HTTP Status es 200 y obtiene mensaje de validación de formato de email incorrecto:"
        when: "Se envian los datos del usuario"
        List<RequestPhone> phones = new ArrayList<>()
        RequestPhone phone = new RequestPhone("1", "34", "910291029")
        phones.add(phone)
        RequestUser user = new RequestUser("Renzo Araos", "readarchgmail.com", "Readar26", phones)
        ResponseEntity<GenericResponse> entityGenericResponse = userController.createUser(user)
        then: "El nuevo usuario no es creado y se verifica lo siguiente:"
        entityGenericResponse.getStatusCode().value() == HttpStatus.OK.value()
        (entityGenericResponse.getBody().getRespuesta() as ResponseError).getMensaje() == Constants.PROPERTY_MSG_ERROR_FORMATO_EMAIL
    }

    def "Ejecutando el metodo: UserController.createUser - Formato de password incorrecto"() {
        expect: "HTTP Status es 200 y obtiene mensaje de validación de formato de password incorrecto:"
        when: "Se envian los datos del usuario"
        List<RequestPhone> phones = new ArrayList<>()
        RequestPhone phone = new RequestPhone("1", "34", "910291029")
        phones.add(phone)
        RequestUser user = new RequestUser("Renzo Araos", "readarch@gmail.com", "Readar2", phones)
        ResponseEntity<GenericResponse> entityGenericResponse = userController.createUser(user)
        then: "El nuevo usuario no es creado y se verifica lo siguiente:"
        entityGenericResponse.getStatusCode().value() == HttpStatus.OK.value()
        (entityGenericResponse.getBody().getRespuesta() as ResponseError).getMensaje() == Constants.PROPERTY_MSG_ERROR_FORMATO_PASSWORD
    }

    def "Ejecutando el metodo: UserController.createUser - Camino exitoso"() {
        expect: "HTTP Status es 200 y se crea correctamente el usuario:"
        when: "Se envian los datos del usuario"
        List<RequestPhone> phones = new ArrayList<>()
        RequestPhone phone = new RequestPhone("1", "34", "910291029")
        phones.add(phone)
        RequestUser user = new RequestUser("Renzo Araos", "readarch@gmail.com", "Readar26", phones)
        ResponseEntity<GenericResponse> entityGenericResponse = userController.createUser(user)
        then: "El nuevo usuario es creado y se verifica lo siguiente:"
        entityGenericResponse.getStatusCode().value() == HttpStatus.OK.value()
        entityGenericResponse.getBody().getFechaEjecucion()
        (entityGenericResponse.getBody().getRespuesta() as ResponseUser).toString()
    }

    def "Ejecutando el metodo: UserController.createUser - Existencia de email"() {
        expect: "HTTP Status es 200 y se obtiene mensaje de validación de existencia de email:"
        when: "Se envian los datos del usuario"
        List<RequestPhone> phones = new ArrayList<>()
        RequestPhone phone = new RequestPhone("1", "34", "910291029")
        phones.add(phone)
        RequestUser user = new RequestUser("Renzo Araos", "readarch@gmail.com", "Readar26", phones)
        ResponseEntity<GenericResponse> entityGenericResponse = userController.createUser(user)
        then: "El nuevo usuario es creado y se verifica lo siguiente:"
        entityGenericResponse.getStatusCode().value() == HttpStatus.OK.value()
        (entityGenericResponse.getBody().getRespuesta() as ResponseError).getMensaje() == Constants.PROPERTY_MSG_ERROR_EXISTE_EMAIL
    }

    def "Ejecutando el metodo: UserController.createUser - Lanzamiento de Excepcion"() {
        expect: "HTTP Status es 200 y obtiene mensaje de excepcion:"
        when: "Se envian los datos del usuario"
        RequestUser user = null
        ResponseEntity<GenericResponse> entityGenericResponse = userController.createUser(user)
        then: "El nuevo usuario no es creado y se verifica lo siguiente:"
        entityGenericResponse.getStatusCode().value() == HttpStatus.OK.value()
    }
}