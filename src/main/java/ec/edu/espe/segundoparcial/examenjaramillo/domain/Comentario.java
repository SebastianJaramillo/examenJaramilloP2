package ec.edu.espe.segundoparcial.examenjaramillo.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comentario {
    
    private Integer calificacion;
    private String comentario;
    private String usuario;
    private Date fechaComentario;
}
