package com.hipermaxi.service;

import com.hipermaxi.dtos.ProductoCreateDTO;
import com.hipermaxi.dtos.ProductoDTO;
import com.hipermaxi.dtos.ProductoUpdateDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> listarProductos();

    ProductoDTO obtenerProductoPorId(Integer id);

    ProductoDTO registrarProducto(ProductoCreateDTO productoCreateDTO);

    ProductoDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO);

    String eliminarProducto(Integer id);

}
