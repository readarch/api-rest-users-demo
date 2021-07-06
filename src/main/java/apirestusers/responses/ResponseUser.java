package apirestusers.responses;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class ResponseUser {
    private final String id;
    private final String name;
    private final Date created;
    private final Date modified;
    private final Date lastLogin;
    private final String token;
    private final boolean isActive;
}