package apirestusers.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class RequestUser {
    private final String name;
    private final String email;
    private final String password;
    private final List<RequestPhone> phones;
}