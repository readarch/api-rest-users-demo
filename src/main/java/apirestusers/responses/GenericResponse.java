package apirestusers.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GenericResponse {

    @JsonProperty("fechaEjecucion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime fechaEjecucion; // Clase LocalDateTime es característica propia de Java 8

    @JsonProperty("respuesta")
    private final Object respuesta;

    public GenericResponse(Object respuesta) {
        this.fechaEjecucion = LocalDateTime.now();
        this.respuesta = respuesta;
    }
}