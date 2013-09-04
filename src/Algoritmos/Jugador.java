/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import javax.swing.ImageIcon;

/**
 *
 * @author Coqui
 */
public class Jugador {
    
    
    String nombre;
    public int GANADOS,PERDIDOS,EMPATADOS;
    
    private ImageIcon ficha;
    
    public Jugador(String nombre, String ruta){
        
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;
        
        miFicha ( ruta );
    }
    
    public Jugador ( String nombre, ImageIcon imagen) {
        
        this.nombre = nombre;
        GANADOS = 0 ;
        PERDIDOS = 0 ;
        EMPATADOS= 0 ;
        
        this.ficha = imagen;
        
    }
    
    public void gano(){
        GANADOS ++;
     }
    public void perdio(){
        PERDIDOS ++;
    }
    public void empato(){
        EMPATADOS ++;
    }
    
    public ImageIcon obtenFicha(){
        return ficha;
    }
    
    public void miFicha(String ruta){
            this.ficha = new ImageIcon ( this.getClass().getResource(ruta) );
    }
}

