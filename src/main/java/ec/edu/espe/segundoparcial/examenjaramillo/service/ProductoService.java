package ec.edu.espe.segundoparcial.examenjaramillo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.segundoparcial.examenjaramillo.dao.EmpresaRepository;
import ec.edu.espe.segundoparcial.examenjaramillo.dao.ProductoRepository;
import ec.edu.espe.segundoparcial.examenjaramillo.domain.Comentario;
import ec.edu.espe.segundoparcial.examenjaramillo.domain.Empresa;
import ec.edu.espe.segundoparcial.examenjaramillo.domain.Producto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final EmpresaRepository empresaRepository;

    public ProductoService(ProductoRepository productoRepository, EmpresaRepository empresaRepository) {
        this.productoRepository = productoRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<Producto> listarPorRucEmpresa(String ruc) {
        log.info("Se va a listar productos de la empresa: {}", ruc);

        List<Empresa> empresas = this.empresaRepository.findByRuc(ruc);
        if (empresas != null && !empresas.isEmpty()) {
            List<Producto> productos = this.productoRepository.findByRucEmpresaOrderByDescripcion(ruc);
            if (productos != null && !productos.isEmpty()) {
                log.info("Productos obtenidos: {}", productos.size());
                return productos;
            } else {
                throw new RuntimeException("No existen productos en la empresa: " + ruc);
            }
        } else {
            throw new RuntimeException("No existe la empresa: " + ruc);
        }
    }

    public Producto obtenerProducto(String codigoUnico) {
        log.info("Se va a obtener el producto: {}", codigoUnico);

        List<Producto> productos = this.productoRepository.findByCodigoUnico(codigoUnico);
        if (productos != null && !productos.isEmpty()) {
            log.info("Producto obtenido: {}", productos.getFirst());
            return productos.getFirst();
        } else {
            throw new RuntimeException("No existe el producto: " + codigoUnico);
        }
    }

    public List<Comentario> obtenerComentarios(String codigoUnico) {
        log.info("Se va a obtener los comentarios del producto: {}", codigoUnico);

        List<Producto> productos = this.productoRepository.findByCodigoUnico(codigoUnico);
        if (productos != null && !productos.isEmpty()) {
            List<Comentario> comentarios = productos.getFirst().getComentarios();
            if (comentarios != null && !comentarios.isEmpty()) {
                log.info("Comentarios obtenidos: {}", comentarios.size());
                return comentarios;
            } else {
                throw new RuntimeException("No existen comentarios en el producto: " + codigoUnico);
            }
        } else {
            throw new RuntimeException("No existe el producto: " + codigoUnico);
        }
    }

    @Transactional
    public void crear(Producto producto) {
        try {
            log.info("Se va a crear el producto: {}", producto);
            this.productoRepository.save(producto);
            log.info("Producto creado: {}", producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear producto", e);
        }
    }

    @Transactional
    public void agregarComentario(Comentario comentario, String codigoUnico) {
        try {
            log.info("Se va a agregar un comentario al producto: {}", codigoUnico);

            List<Producto> productos = this.productoRepository.findByCodigoUnico(codigoUnico);
            if (productos != null && !productos.isEmpty()) {
                comentario.setFechaComentario(new Date());
                if(productos.getFirst().getComentarios() == null) {
                    productos.getFirst().setComentarios(new ArrayList<>());
                }
                productos.getFirst().getComentarios().add(comentario);
                this.productoRepository.save(productos.getFirst());
                log.info("Comentario agregado: {} al producto: {}", comentario, codigoUnico);
            } else {
                log.error("Error al obtener producto: {}", codigoUnico);
                throw new RuntimeException("No existe el producto: " + codigoUnico);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar comentario", e);
        }
    }
}
