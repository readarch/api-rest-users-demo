package apirestusers.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class RequestPhone {
    private final String number;
    private final String citycode;
    private final String contrycode;
}