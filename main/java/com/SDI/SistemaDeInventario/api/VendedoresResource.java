package com.SDI.SistemaDeInventario.api;

import com.SDI.SistemaDeInventario.dao.VendedoresDao;
import com.SDI.SistemaDeInventario.dto.Vendedores;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VendedoresResource {
    @RequestMapping(method = RequestMethod.GET, value = "/vendedor/obtener/{usuario}")
    public List<Vendedores> obtenerEmpleadoPorUsuario(@PathVariable("usuario") String usuario)
            throws SQLException {
        return new VendedoresDao().obtenerVendedorPorUsuario(usuario);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/vendedor/ingresar")
    public Vendedores insertarVendedor(@RequestBody Vendedores v) throws SQLException {
        return new VendedoresDao().insertarVendedor(v);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/vendedor/borrar/{usuario}")
    public void borrarVendedor(@PathVariable("usuario") String usuario) throws SQLException {
        new VendedoresDao().borrarVendedorPorUsuario(usuario);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/vendedor/actualizar/{usuario}")
    public Vendedores editarVendedor(@RequestBody Vendedores v) throws SQLException {
        return new VendedoresDao().editarVendedorPorUsuario(v);
    }

}
