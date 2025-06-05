/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Declaracion del paquete modelo.compras
package Modelo.compras_cxp;

//Importaciones de archivos que se utilizaran
import Controlador.compras_cxp.Bodega;
import Modelo.Conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author visitante
 */

//Declaracion de la clase MetododepagoDao
public class BodegaDAO {
    
    // Consultas SQL para operaciones CRUD en la tabla metodo_pago
    private static final String SQL_SELECT = "SELECT pkid, tipobodega, nombre,direccion,estado FROM bodega";
    private static final String SQL_INSERT = "INSERT INTO bodega(pkid, tipobodega, nombre,direccion,estado) VALUES(?, ?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE bodega SET  tipobodega=?, nombre=?,direccion=?,estado=?WHERE pkid = ?";
    private static final String SQL_DELETE = "DELETE FROM bodega WHERE pkid=?";
    private static final String SQL_QUERY = "SELECT pkid, tipobodega, nombre,direccion,estado  FROM bodega WHERE pkid = ?";
    
    // Se obtiene todas los metodos de pago registradas en la base de datos
    public List<Bodega> select() {
        // Objeto para la conexión a la base de datos
        Connection conn = null;
        // Objeto para preparar la sentencia SQL
        PreparedStatement stmt = null;
        // Objeto para almacenar los resultados de la consulta SQL
        ResultSet rs = null;
        Bodega bodega = null;
        // Lista para guardar los metodos de pago
        List<Bodega> bodegas = new ArrayList<Bodega>();

        try {
            // Se establece la conexión con la base de datos
            conn = Conexion.getConnection();
            // Preparacion para la consulta SQL
            stmt = conn.prepareStatement(SQL_SELECT);
            // Se ejecuta la consulta y se obtiene el resultado
            rs = stmt.executeQuery();
            // Se recorre el ResultSet obtenido de la consulta a la base de datos
            while (rs.next()) {
                //Se obtienen los valores de las variables
    
    
    
                String id_bodega = rs.getString("pkid");
                String tipoBodega = rs.getString("tipobodega");
                String nombreBodega = rs.getString("nombre");
                String direccionBodega=rs.getString("direccion");
                String estadoBodega=rs.getString("estado");
                // Se crea un nuevo objeto Metododepago con los datos obtenidos
                bodega = new Bodega();
                bodega.setId_bodega(id_bodega);
                bodega.setTipoBodega(tipoBodega);
                bodega.setNombreBodega(nombreBodega);
                bodega.setDireccionBodega(direccionBodega);
                bodega.setEstadoBodega(estadoBodega);
                // Se agrega el objeto a la lista de vendedores (métodos de pago)
                bodegas.add(bodega);
            }
        // Si ocurre una excepción SQL, se imprime la traza del error
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // Se cierra el ResultSet
            Conexion.close(rs);
            // Se cierra el Statement
            Conexion.close(stmt);
            //Se cierra la conexion con la base de datos
            Conexion.close(conn);
        }
         // Se retorna la lista de los metodos de pago
        return bodegas;
    }
     //Metodo para insertar compras
    public int insert(Bodega bodega) {
        // Objeto para la conexión a la base de datos
        Connection conn = null;
        // Objeto para preparar la sentencia SQL
        PreparedStatement stmt = null;
        // Variable para almacenar la cantidad de filas afectadas
        int rows = 0;
        try {
            // Se establece la conexión a la base de datos
            conn = Conexion.getConnection();
            // Se prepara la sentencia SQL con los valores que serán insertados
            stmt = conn.prepareStatement(SQL_INSERT);
            
            // Se asignan los valores a los parámetros de la sentencia SQL
            stmt.setString(1, bodega.getId_bodega());
            stmt.setString(2, bodega.getTipoBodega());
            stmt.setString(3, bodega.getNombreBodega());
            stmt.setString(4, bodega.getDireccionBodega());
            stmt.setString(5, bodega.getEstadoBodega());
            System.out.println("ejecutando query:" + SQL_INSERT);
            // Se ejecuta la sentencia, obteniendo la cantidad de filas insertadas
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            // Se imprime cualquier excepción para que ocurra durante el proceso
            ex.printStackTrace(System.out);
        } finally {
            // Se cierran los recursos 
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        // Se retorna el número de filas afectadas por la operación
        return rows;
    }
    
    //Método para actualizar registros
    public int update(Bodega bodega) {
        // Objeto para la conexión a la base de datos
        Connection conn = null;
        // Objeto para preparar la sentencia SQL
        PreparedStatement stmt = null;
        // Variable para almacenar el número de filas afectadas
        int rows = 0;

        try {
            // Se establece la conexión con la base de datos
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            // Se prepara la sentencia SQL para la inserción de datos
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            // Se asignan los valores a compra_cpp
            
            stmt.setString(1, bodega.getTipoBodega());
            stmt.setString(2, bodega.getNombreBodega());
            stmt.setString(3, bodega.getDireccionBodega());
            stmt.setString(4, bodega.getEstadoBodega());
            stmt.setString(5, bodega.getId_bodega());
            
            // Se ejecuta la consulta de inserción y se guarda la cantidad de filas afectadas
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            // En caso de error, se imprime el stack trace para debugging
            ex.printStackTrace(System.out);
        } finally {
             // Se cierra el Statement
            Conexion.close(stmt);
            //Se cierra la conexion con la base de datos
            Conexion.close(conn);
        }
        //Retorno del numero de filas insertadas
        return rows;
    }
     //Metodo para borrar registros
    public int delete(Bodega bodega) {
        // Objeto para la conexión a la base de datos
        Connection conn = null;
        // Objeto para preparar la sentencia SQL
        PreparedStatement stmt = null;
        // Variable para almacenar la cantidad de filas afectadas
        int rows = 0;


        try {
            // Se establece la conexión con la base de datos
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            // Se prepara la sentencia SQL para eliminar un registro
            stmt = conn.prepareStatement(SQL_DELETE);
            // Se establece el número de compra 
            stmt.setString(1, bodega.getId_bodega());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            // Se imprime cualquier error que ocurra durante la ejecución
            ex.printStackTrace(System.out);
        } finally {
            // Se cierran los recursos utilizados
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        // Se retorna la cantidad de filas afectadas
        return rows;
    }

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public Bodega query(Bodega bodega) {    
        // Objeto para la conexión a la base de datos
        Connection conn = null;
        // Objeto para preparar la sentencia SQL
        PreparedStatement stmt = null;
        // Objeto para almacenar los resultados de la consulta SQL
        ResultSet rs = null;
        
        // Lista para almacenar los datos obtenidos
        List<Bodega> bodegas = new ArrayList<Bodega>();
        // Variable int
        int rows = 0;

        try {
            // Se establece la conexión con la base de datos
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            // Se prepara la sentencia SQL utilizando el número de metodo de pago
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, bodega.getId_bodega());
            // Se ejecuta la consulta
            rs = stmt.executeQuery();
           
            while (rs.next()) {
                // Se extraen  datos de cada columna
      
                String id_bodega = rs.getString("pkid");
                String tipoBodega = rs.getString("tipobodega");
                String nombreBodega = rs.getString("nombre");
                String direccionBodega=rs.getString("direccion");
                String estadoBodega=rs.getString("estado");
                
                // Se crea un nuevo objeto Metododepagos asignando sus atributos con los valores obtenidos
                bodega = new Bodega();
                bodega.setId_bodega(id_bodega);
                bodega.setTipoBodega(tipoBodega);
                bodega.setNombreBodega(nombreBodega);
                bodega.setDireccionBodega(direccionBodega);
                bodega.setEstadoBodega(estadoBodega);
                
                //vendedores.add(vendedor); // Si se utiliza un ArrayList
            }
            //System.out.println("Reg
            // Si ocurre un error de SQL, se imprime en consolaistros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
             // Se cierran los recursos 
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return vendedores;  // Si se utiliza un ArrayList
        return bodega;
    }
    /*public void imprimirReporte() {
       //Conexion con la base de datos
        Connection conn = null;
        // Mapa de parámetros que se puede enviar al reporte (aquí está vacío)
        Map p = new HashMap();
        // Objeto que representa el reporte compilado
        JasperReport report;
        // Objeto que representa el reporte ya lleno con los datos
        JasperPrint print;

        try {
            //Conexion con la base de datos
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reportes_compras_cxp/"+ "ReporteMetodoDePago.jrxml");
            //se llena el reporte con los datos obtenidos
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Vendedores");
            view.setVisible(true);

        } catch (Exception e) {
            // Si ocurre cualquier error, se imprime la traza del error en consola
            e.printStackTrace();
        }
    }*/
        
}