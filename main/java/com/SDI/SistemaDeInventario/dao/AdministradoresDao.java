package com.SDI.SistemaDeInventario.dao;

import com.SDI.SistemaDeInventario.ConnectionManager;
import com.SDI.SistemaDeInventario.dto.Administradores;
import com.SDI.SistemaDeInventario.dto.Vendedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AdministradoresDao {
    private final Connection connection;

    public AdministradoresDao() throws SQLException {
        connection = ConnectionManager.obtenerConexion();
    }

    public List<Administradores> obtenerAdministradorPorUsuario(String usuario) throws SQLException {
        String sql = " select * " +
                " from ADMINISTRADORES " +
                " where usuario=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, usuario);
        ResultSet rs = ps.executeQuery();
        List<Administradores> administradores = new LinkedList<>();
        while (rs.next()) {
            Administradores a = new Administradores(
                    rs.getString("usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getString("contrasenha")
            );
            administradores.add(a);
        }
        return administradores;
    }
    public Administradores insertarAdministrador(Administradores a) throws SQLException {
        String sql = " insert into ADMINISTRADORES(usuario, nombre, apellido, correo, contrasenha) " +
                " values (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, a.getUsuario());
        ps.setString(2, a.getNombre());
        ps.setString(3, a.getApellido());
        ps.setString(4, a.getCorreo());
        ps.setString(5, a.getContrasenha());
        ps.executeUpdate();
        return a;
    }
    public void borrarAdministradorPorUsuario(String usuario) throws SQLException {
        String sql = " delete from ADMINISTRADORES " +
                " where usuario=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, usuario);
        ps.executeUpdate();

    }
    public Administradores editarAdministradorPorUsuario(Administradores a) throws SQLException {
        String sql = " Update ADMINISTRADORES " +
                " set usuario=?, nombre=?, apellido=?, correo=?, contrasenha=? " +
                " where usuario=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, a.getUsuario());
        ps.setString(2, a.getNombre());
        ps.setString(3, a.getApellido());
        ps.setString(4, a.getCorreo());
        ps.setString(5, a.getContrasenha());
        ps.setString(6, a.getUsuario());
        ps.executeUpdate();
        return obtenerAdministradorPorUsuario(a.getUsuario()).get(0);
    }
}
