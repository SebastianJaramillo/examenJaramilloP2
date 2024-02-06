package ec.edu.espe.segundoparcial.examenjaramillo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examenjaramillo.domain.Empresa;
import ec.edu.espe.segundoparcial.examenjaramillo.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<Empresa> buscarPorRuc(@PathVariable(name = "ruc") String ruc) {
        log.info("Obteniendo empresa con RUC: {}...", ruc);
        try {
            return ResponseEntity.ok(this.empresaService.buscarPorRuc(ruc));
        } catch (RuntimeException rte) {
            log.error("Error al obtener empresa por RUC", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Void> crear(@RequestBody Empresa empresa) {
        log.info("Creando empresa: {}...", empresa);
        try {
            this.empresaService.crear(empresa);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al crear empresa", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
