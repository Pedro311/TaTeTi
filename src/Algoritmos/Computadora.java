/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

/**
 *
 * @author Coqui
 */
public class Computadora {
    
    public static int tiradas = 0;
    
    
    class NodoG{
        
        
        int mejorMovimiento;
        NodoG nodos[];
        boolean miTurno = false;
        int indice;
        int ganador = 0;
        private int[] tablero;
        
        NodoG(){
            
            tablero = new int[9];
            
        }
    }
    
    NodoG arbol = new NodoG();
    
    
    int[] tablero;
    
    public final int miFICHA = 2;
    public int movDisponibles (int[]tablero){
        
        int mov = 0;
        
        for (int i = 0; i < 9; i ++ )
            if ( tablero[i] == 0)
                mov ++;
        return mov;
    }
    
    public int[] posVacias( int[] tablero ){
        
        int [] indices = new int[movDisponibles(tablero)];
        int indice = 0;
        
        
        for ( int i = 0; i < 9; i ++){
            if( tablero [i] == 0 ){
                indices[indice]= i;
                indice++;
            
            }
        }
        return indices;
    }
    
    public int movimiento ( int []tablero ){
        
        this.tablero = tablero;
        tiradas ++;
        System.arraycopy(this.tablero, 0, this.arbol.tablero, 0, 9);
        
        
        movComputadora ( arbol );
        
        return arbol.mejorMovimiento;
                
     }
    public void movComputadora ( NodoG raiz ) {
        
        int movimientos = movDisponibles(raiz.tablero);
        int indices[] = posVacias(raiz.tablero);
        int Max, Min;
        
        
        raiz.nodos = new NodoG[movimientos];
        
        /*Verificamos si hay un ganador.*/
        int ganador = terminado(raiz.tablero);
        if ( ganador == 1 ) ganador = -1;
        else if ( ganador == 2 ) ganador = 1;
  
        if ( ganador!= 0 || movimientos == 0){
            raiz.ganador = ganador;
        }else{

            /*Creamos los datos de cada hijo.*/
            for( int i = 0; i < movimientos; i ++ ){
                
                /*Inicializamos los nodos hijos del arbol.*/
                raiz.nodos[i] = new NodoG();
                System.arraycopy(raiz.tablero, 0, raiz.nodos[i].tablero, 0, 9);
                                
                /*Creamos los diferentes movimientos posibles.*/
                if ( raiz.miTurno )
                    raiz.nodos[i].tablero[indices[i]] = 1;
                else
                    raiz.nodos[i].tablero[indices[i]] = 2;
                
                /*Cambiamos el turno de los hijos*/
                raiz.nodos[i].miTurno = !raiz.miTurno;
                
                
                /*Guardamos el indice de su movimiento.*/
                raiz.nodos[i].indice = indices[i];
                    
                movComputadora(raiz.nodos[i]);
                                
            }

            /*Minimax*/
            if (!raiz.miTurno)
                raiz.ganador = Max(raiz);
            else
                raiz.ganador = Min(raiz);
            
       }    

    }
   
    /*Metodo que calcula el MAXIMO de los nodos hijos de MIN*/
    public int Max( NodoG raiz ){
        int Max = -111;
        /*Maximo para la computadora, buscamos el valor donde gane.*/
        for (int i = 0; i < raiz.nodos.length; i++){
          /*Preguntamos por un nodo con un valor alto MAX*/
            if (raiz.nodos[i].ganador > Max){
                /*Lo asignamos y pasamos el mejor movimiento a la raiz.*/
                Max = raiz.nodos[i].ganador;
                raiz.mejorMovimiento = raiz.nodos[i].indice;
                /*Terminamos de buscar.*/
                if (Max == 1) break;
            }
         }
        
        /*Borramos los nodos.*/
        raiz.nodos = null;
        
        return Max;
    }
    
    /*Metodo que calcula el MINIMO de los nodos hijos de MAX.*/
    public int Min( NodoG raiz ){
        int Min = 111;
        /*Minimo para el jugador*/
        for (int i = 0; i < raiz.nodos.length; i++)
          if (raiz.nodos[i].ganador < Min ){
            Min = raiz.nodos[i].ganador;
            raiz.mejorMovimiento = raiz.nodos[i].indice;
            if (Min == -1) break;
          }
        
        /*Borramos los nodos.*/
        raiz.nodos = null;
        
        return Min;
    }
                
    /*MEtodo que dice si el juego est� terminado.*/
    /*Regresa 0 si nadie gana, 1 si gana jugador 1 y 2 si gana jugador 2*/
    public int terminado( int[] tablero ){
        /*Comprobamos si el juego termin�.*/
        /*Filas*/
        if ( tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[3] == tablero[4] && tablero[3] == tablero[5]  && tablero[3] != 0  )
            return tablero[3];
        else if ( tablero[6] == tablero[7] && tablero[6]== tablero[8]  && tablero[6] != 0 )
            return tablero[6];
        /*Columnas*/
        else if( tablero[0] == tablero[3] && tablero[0] == tablero[6]  && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[1] == tablero[4] && tablero[1] == tablero[7]  && tablero[1] != 0  )
            return tablero[1];
        else if ( tablero[2] == tablero[5] && tablero[2] == tablero[8]  && tablero[2] != 0 )
            return tablero[2];
        /*Diagonales*/
        else if ( tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] !=0 )
            return tablero[0];
        else if ( tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0 )
            return tablero[2];
        
        return 0;
        
    }
    
    /*M�todo que nos dice si gana la computadora.*/
    public boolean puedoGanar(int[] tablero){
        return terminado(tablero) == 2;
    }
    
    /*M�todo que nos dice si pierde la computadora.*/
    public boolean puedoPerder(int[] tablero){
        return terminado(tablero) == 1;
    }
    
    /*M�todo que imprime un vector como un gato. xD*/
    public void imprime(int[] gato){
        for ( int i = 0; i < 9; i ++ ){
            System.out.print(gato[i]+"");
            if ( i == 2 || i == 5 )
                System.out.println();
        }
        
        System.out.println("\r\n");
    }
}

    


