package ec.edu.espe.segundoparcial.examenjaramillo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.segundoparcial.examenjaramillo.domain.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {

    List<Producto> findByCodigoUnico(String codigoUnico);
    List<Producto> findByRucEmpresaOrderByDescripcion(String ruc);
}
