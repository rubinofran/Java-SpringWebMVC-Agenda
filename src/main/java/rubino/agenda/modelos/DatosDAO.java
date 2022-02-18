package rubino.agenda.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DatosDAO {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    private Integer cantidadRegistros;

    @Autowired
    public DatosDAO(
            @Qualifier("dbName") String dbName,
            @Qualifier("dbURL") String dbURL,
            @Qualifier("dbUser") String dbUser,
            @Qualifier("dbPswd") String dbPswd) {
        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

    public List<String> getNombres() {
        ArrayList<String> resultado = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
            Statement stmt = con.createStatement();
            stmt.execute("SELECT nombre FROM datos");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                resultado.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultado;
    }

    public void darDeAlta(String nuevoNombre, String nuevoTelefono) {
        String u = "INSERT INTO datos (nombre, telefono) " + " VALUES ('" + nuevoNombre + "', '" + nuevoTelefono + "')";
        try {
            Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
            Statement stmt = con.createStatement();
            cantidadRegistros = stmt.executeUpdate(u);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void darDeBaja(String nombre) {
        String u = "DELETE FROM datos WHERE nombre ='" + nombre + "'";
        try {
            Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
            Statement stmt = con.createStatement();
            cantidadRegistros = stmt.executeUpdate(u);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void modificar(String nombre, String nuevoTelefono) {
        String u = "UPDATE datos SET telefono ='" + nuevoTelefono + "' WHERE nombre ='" + nombre + "'";
        try {
            Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
            Statement statement = con.createStatement();
            cantidadRegistros = statement.executeUpdate(u);
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getTelefonoPorNombre(String nombre) {
        String telefono = "No encontrado";
        try {
            Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
            Statement stmt = con.createStatement();
            stmt.execute("SELECT telefono FROM datos WHERE nombre = '" + nombre + "'");
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                telefono = rs.getString(1);
            }
            con.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return telefono;
    }
}

