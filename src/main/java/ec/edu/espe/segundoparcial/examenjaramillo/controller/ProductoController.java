package ec.edu.espe.segundoparcial.examenjaramillo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examenjaramillo.domain.Comentario;
import ec.edu.espe.segundoparcial.examenjaramillo.domain.Producto;
import ec.edu.espe.segundoparcial.examenjaramillo.service.ProductoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/empresas/{ruc}")
    public ResponseEntity<List<Producto>> listarPorRuc(@PathVariable(name = "ruc") String ruc) {
        log.info("Listando productos de la empresa: {}...", ruc);
        try {
            return ResponseEntity.ok(this.productoService.listarPorRucEmpresa(ruc));
        } catch (RuntimeException rte) {
            log.error("Error al listar productos de la empresa", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codigoUnico}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable(name = "codigoUnico") String codigoUnico) {
        log.info("Obteniendo producto: {}...", codigoUnico);
        try {
            return ResponseEntity.ok(this.productoService.obtenerProducto(codigoUnico));
        } catch (RuntimeException rte) {
            log.error("Error al listar productos de la empresa", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codigoUnico}/comentarios")
    public ResponseEntity<List<Comentario>> obtenerComentarios(@PathVariable(name = "codigoUnico") String codigoUnico) {
        log.info("Obteniendo comentarios del producto: {}...", codigoUnico);
        try {
            return ResponseEntity.ok(this.productoService.obtenerComentarios(codigoUnico));
        } catch (RuntimeException rte) {
            log.error("Error al obtener comentarios del producto", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody Producto producto) {
        log.info("Creando producto: {}...", producto);
        try {
            this.productoService.crear(producto);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al crear producto", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{codigoUnico}/comentarios")
    public ResponseEntity<Void> agregarComentario(@RequestBody Comentario comentario, @PathVariable(name = "codigoUnico") String codigoUnico) {
        log.info("Agregando comentario al producto: {}", codigoUnico);
        try {
            this.productoService.agregarComentario(comentario, codigoUnico);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al agregar comentario al producto", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
