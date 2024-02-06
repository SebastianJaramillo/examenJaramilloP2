package ec.edu.espe.segundoparcial.examenjaramillo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.segundoparcial.examenjaramillo.domain.Empresa;
import java.util.List;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
    
    List<Empresa> findByRuc(String ruc);
    
}
