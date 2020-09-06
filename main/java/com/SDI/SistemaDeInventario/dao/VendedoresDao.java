package com.SDI.SistemaDeInventario.dao;

import com.SDI.SistemaDeInventario.ConnectionManager;
import com.SDI.SistemaDeInventario.dto.Vendedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class VendedoresDao {
    private final Connection connection;

    public VendedoresDao() throws SQLException {
        connection = ConnectionManager.obtenerConexion();
    }

    public List<Vendedores> obtenerVendedorPorUsuario(String usuario) throws SQLException {
        String sql = " select * " +
                " from VENDEDORES " +
                " where usuario=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, usuario);
        ResultSet rs = ps.executeQuery();
        List<Vendedores> vendedores = new LinkedList<>();
        while (rs.next()) {
            Vendedores v = new Vendedores(
                    rs.getString("usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getString("contrasenha")
            );
            vendedores.add(v);
        }
        return vendedores;
    }
    public Vendedores insertarVendedor(Vendedores v) throws SQLException {
        String sql = " insert into VENDEDORES(usuario, nombre, apellido, correo, contrasenha) " +
                " values (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, v.getUsuario());
        ps.setString(2, v.getNombre());
        ps.setString(3, v.getApellido());
        ps.setString(4, v.getCorreo());
        ps.setString(5, v.getContrasenha());
        ps.executeUpdate();
        return v;
    }
    public void borrarVendedorPorUsuario(String usuario) throws SQLException {
        String sql = " delete from VENDEDORES " +
                " where usuario=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, usuario);
        ps.executeUpdate();

    }
    public Vendedores editarVendedorPorUsuario(Vendedores v) throws SQLException {
        String sql = " Update VENDEDORES " +
                " set usuario=?, nombre=?, apellido=?, correo=?, contrasenha=? " +
                " where usuario=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, v.getUsuario());
        ps.setString(2, v.getNombre());
        ps.setString(3, v.getApellido());
        ps.setString(4, v.getCorreo());
        ps.setString(5, v.getContrasenha());
        ps.setString(6, v.getUsuario());
        ps.executeUpdate();
        return obtenerVendedorPorUsuario(v.getUsuario()).get(0);
    }



}
