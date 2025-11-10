package com.hipermaxi.service;

import com.hipermaxi.dtos.ProductoCreateDTO;
import com.hipermaxi.dtos.ProductoDTO;
import com.hipermaxi.dtos.ProductoUpdateDTO;
import com.hipermaxi.mappers.ProductoMapper;
import com.hipermaxi.model.Producto;
import com.hipermaxi.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository=productoRepository;
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        return ProductoMapper.instancia.listaProductoAListaProductoDTO(productoRepository.findAll());
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Integer id) {
        Optional<Producto> producto=productoRepository.findById(id);
        return producto.map(ProductoMapper.instancia::productoAProductoDTO).orElse(null);

    }

    @Override
    public ProductoDTO registrarProducto(ProductoCreateDTO productoCreateDTO) {
        Producto producto=ProductoMapper.instancia.productoCreateDTOAProducto(productoCreateDTO);
        Producto respuestaEntity=productoRepository.save(producto);
        return ProductoMapper.instancia.productoAProductoDTO(respuestaEntity);
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO) {
        Producto producto=ProductoMapper.instancia.productoUpdateDTOAProducto(productoUpdateDTO);
        Producto respuestaEntity=productoRepository.save(producto);
        return ProductoMapper.instancia.productoAProductoDTO(respuestaEntity);
    }

    @Override
    public String eliminarProducto(Integer id) {
        Optional<Producto> productoOptional=productoRepository.findById(id);
        if(productoOptional.isPresent()) {
            productoRepository.delete(productoOptional.get());
            return "Se elimino con exito";
        }else{
            return "No se elimino con exito";
        }
    }
}
