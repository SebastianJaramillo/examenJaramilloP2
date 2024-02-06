package ec.edu.espe.segundoparcial.examenjaramillo.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "productos")
public class Producto {
    @Id
    private String id;
    @Indexed(unique = true)
    private String codigoUnico;
    private String rucEmpresa;
    private String descripcion;
    private BigDecimal precio;

    List<Comentario> comentarios;

    @Version
    private Long version;

    public Producto(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", codigoUnico=" + codigoUnico + ", ruc=" + rucEmpresa + ", descripcion=" + descripcion
                + ", precio=" + precio + ", comentarios=" + comentarios + ", version=" + version + "]";
    }
}
