package apirestusers.controllers;

import apirestusers.requests.RequestUser;
import apirestusers.responses.GenericResponse;
import apirestusers.responses.ResponseError;
import apirestusers.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "createUser", notes = "Servicio que permite crear un usuario con sus teléfonos.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request", response = GenericResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GenericResponse.class),
            @ApiResponse(code = 405, message = "Method Not Allowed", response = GenericResponse.class),
            @ApiResponse(code = 415, message = "Unsupported Media Type", response = GenericResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GenericResponse.class)
    })
    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createUser(@RequestBody RequestUser user) {
        try {
            logger.info("INICIO - UserController.createUser - user = [{}]", user);
            return new ResponseEntity<>(userService.createUser(user), new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new GenericResponse(new ResponseError(ex.getMessage())), new HttpHeaders(), HttpStatus.OK);
        } finally {
            logger.info("FIN - UserController.createUser - user = [{}]", user);
        }
    }
}