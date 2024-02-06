package ec.edu.espe.segundoparcial.examenjaramillo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.segundoparcial.examenjaramillo.dao.EmpresaRepository;
import ec.edu.espe.segundoparcial.examenjaramillo.domain.Empresa;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa buscarPorRuc(String ruc) {
        log.info("Se va a buscar una empresa por RUC: {}", ruc);

        List<Empresa> empresas = this.empresaRepository.findByRuc(ruc);
        if (empresas != null && !empresas.isEmpty()) {
            log.info("Empresa obtenida: {}", empresas.getFirst());
            return empresas.getFirst();
        } else {
            throw new RuntimeException("No existe la empresa con RUC: " + ruc);
        }
    }

    @Transactional
    public void crear(Empresa empresa) {
        try {
            empresa.setFechaCreacion(new Date());            
            log.info("Se va a crear la empresa: {}", empresa);
            this.empresaRepository.save(empresa);
            log.info("Empresa creada: {}", empresa);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear empresa", e);
        }
    }
}
