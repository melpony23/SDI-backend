package com.SDI.SistemaDeInventario.api;

import com.SDI.SistemaDeInventario.dao.AdministradoresDao;
import com.SDI.SistemaDeInventario.dao.VendedoresDao;
import com.SDI.SistemaDeInventario.dto.Administradores;
import com.SDI.SistemaDeInventario.dto.Vendedores;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdministradoresResource {
    @RequestMapping(method = RequestMethod.GET, value = "/administrador/obtener/{usuario}")
    public List<Administradores> obtenerAdministradorPorUsuario(@PathVariable("usuario") String usuario)
            throws SQLException {
        return new AdministradoresDao().obtenerAdministradorPorUsuario(usuario);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/administrador/ingresar")
    public Administradores insertarAdministrador(@RequestBody Administradores a) throws SQLException {
        return new AdministradoresDao().insertarAdministrador(a);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/administrador/borrar/{usuario}")
    public void borrarAdministrador(@PathVariable("usuario") String usuario) throws SQLException {
        new AdministradoresDao().borrarAdministradorPorUsuario(usuario);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/administrador/actualizar/{usuario}")
    public Administradores editarAdministrador(@RequestBody Administradores a) throws SQLException {
        return new AdministradoresDao().editarAdministradorPorUsuario(a);
    }
}
