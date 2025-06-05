/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Declaracion de paquete controlador
package Controlador.compras_cxp;




/**
 *
 * @author visitante
 */

                              

//Definiendo clase publica metododepago
public class Bodega {
    
    //Declarando variables int y String 
    String id_bodega;
    String tipoBodega;
    String nombreBodega;
    String direccionBodega;
    String estadoBodega;

    @Override
    public String toString() {
        return "Bodega{" + "id_bodega=" + id_bodega + ", tipoBodega=" + tipoBodega + ", nombreBodega=" + nombreBodega + ", direccionBodega=" + direccionBodega + ", estadoBodega=" + estadoBodega + '}';
    }

    public void setId_bodega(String id_bodega) {
        this.id_bodega = id_bodega;
    }

    public void setTipoBodega(String tipoBodega) {
        this.tipoBodega = tipoBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public void setDireccionBodega(String direccionBodega) {
        this.direccionBodega = direccionBodega;
    }

    public void setEstadoBodega(String estadoBodega) {
        this.estadoBodega = estadoBodega;
    }

    public String getId_bodega() {
        return id_bodega;
    }

    public String getTipoBodega() {
        return tipoBodega;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public String getDireccionBodega() {
        return direccionBodega;
    }

    public String getEstadoBodega() {
        return estadoBodega;
    }

    public Bodega(String id_bodega, String tipoBodega, String nombreBodega, String direccionBodega, String estadoBodega) {
        this.id_bodega = id_bodega;
        this.tipoBodega = tipoBodega;
        this.nombreBodega = nombreBodega;
        this.direccionBodega = direccionBodega;
        this.estadoBodega = estadoBodega;
    }
    
   
    
    //Constructor vacio
    public Bodega() {
    }

    
}