
package JFrame;


import Algoritmos.Computadora;
import Algoritmos.Jugador;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JFJuego extends javax.swing.JFrame implements Runnable {

    JFTipoJuego tipo;
    JFFichas ficha;
    JFCreditos acerca = new JFCreditos(); 

    /*Jugadores.*/
    Jugador jugador1, jugador2;
    Computadora computadora;
    boolean jugando, terminado;
    public final int PERSONAvsPERSONA = 1;
    public final int PERSONAvsMAQUINA = 2;
    public final int JUGADOR1 = 1;
    public final int JUGADOR2 = 2;
    public boolean PENSANDO = false;
    /*Turno de jugador.*/
    int turno = 0;
    int turnoGeneral = 0;
    /*Matriz que representa el juego.*/
    int[] tablero = new int[9];

    /*Tablero en componentes.*/
    JLabel fichas[];
   

    public JFJuego() {
        initComponents();
        Arrays.fill(tablero, 0);
        iniciarComponentes();
        ficha = new JFFichas();
        tipo = new JFTipoJuego(this);
        menuLimpiarTablero.setEnabled(false);
    }

    public void movimiento(JLabel ficha) {
        /*Colocamos la ficha.*/
        if (jugando) {

            /*Reproducimos el sonido.*/
            

            if (!PENSANDO) {
                ponerFicha(ficha);
            }


            if (this.tipo.tipo_juego == PERSONAvsMAQUINA && this.turno == JUGADOR2) {
                PENSANDO = true;
                ponerFichaCPU(computadora.movimiento(this.tablero));
                PENSANDO = false;

                
            }

        }
        /*Si se va a comenzar un juego nuevo*/
        if (terminado) {
            reiniciarJuego();
            return;
        }

        /*Preguntamos si el juego termino o alguien ganó.*/
        if (terminado() != 0) {

           
           

            /*Asignamos resultados.*/
            if (terminado() == 1) {
                jugador1.gano();
                jugador2.perdio();
            } else {
                jugador2.gano();
                jugador1.perdio();
            }

            /*Mostramos la información.*/
            mostrarInformacion();

            /*Detenemos el juego actual.*/
            jugando = false;
            terminado = true;


        } else if (lleno()) {
            /*Asignamos resultados.*/
            jugador1.empato();
            jugador2.empato();
            mensaje("Empate!");

            /*Mostramos la información.*/
            mostrarInformacion();

            /*Detenemos el juego actual.*/
            jugando = false;
            terminado = true;
        }

        /*Movemos el foco al otro jugador.*/
        cambiarFoco();
    }

    public void mensaje(String mensaje) {
        this.lblEstado.setText(mensaje);
    }

    /*Método que recoje el modelo y nos avisa que está listo.*/
    public void recojerModelo() {
        /*Iniciamos los componentes del juego.*/
        iniciarJuego();
    }

    public void mostrarInformacion() {

        /*Establecemos el título.*/


        /*Establecemos las estadísticas del jugador.*/
        this.lblGanados1.setText("Ganados: " + jugador1.GANADOS);
        this.lblPerdidos1.setText("Perdidos: " + jugador1.PERDIDOS);
        this.lblEmpatados1.setText("Empatados: " + jugador1.EMPATADOS);
        this.lblFicha1.setIcon(jugador1.obtenFicha());

        this.lblGanados2.setText("Ganados: " + jugador2.GANADOS);
        this.lblPerdidos2.setText("Perdidos: " + jugador2.PERDIDOS);
        this.lblEmpatados2.setText("Empatados: " + jugador2.EMPATADOS);
        this.lblFicha2.setIcon(jugador2.obtenFicha());

    }

    /*Cambiar foco*/
    public void cambiarFoco() {

        /*Si estamos jugando.*/
        if (!jugando) {
            return;
        }
        /*Si es turno del primer jugador..*/
        if (turno == JUGADOR1) {

        }
    }

    /*Método que nos dice si el tablero se llenó.*/
    public boolean lleno() {
        boolean res = true;
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i] == 0) {
                res = false;
            }
        }
        return res;
    }

    /*Método que dice si el juego está terminado.*/
    /*Regresa 0 si nadie gana, 1 si gana jugador 1 y 2 si gana jugador 2*/
    public int terminado() {
        /*Comprobamos si el juego terminó.*/
        /*Filas*/
        if (tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[3] == tablero[4] && tablero[3] == tablero[5] && tablero[3] != 0) {
            return tablero[3];
        } else if (tablero[6] == tablero[7] && tablero[6] == tablero[8] && tablero[6] != 0) {
            return tablero[6];
        } /*Columnas*/ else if (tablero[0] == tablero[3] && tablero[0] == tablero[6] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[1] == tablero[4] && tablero[1] == tablero[7] && tablero[1] != 0) {
            return tablero[1];
        } else if (tablero[2] == tablero[5] && tablero[2] == tablero[8] && tablero[2] != 0) {
            return tablero[2];
        } /*Diagonales*/ else if (tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0) {
            return tablero[2];
        }

        return 0;

    }

    public boolean estaOcupada(int casilla) {
        return (tablero[casilla] != 0);
    }

    /*Método que inicia un nuevo juego.*/
    public void reiniciarJuego() {

        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero, 0);

        /*Borramos los iconos.*/
        for (int i = 0; i < 9; i++) {
            fichas[i].setIcon(null);
        }

        /*Cambiamos el turno General.*/
        if (this.tipo.tipo_juego == PERSONAvsMAQUINA) {
            turnoGeneral = JUGADOR1;
        } else {
            turnoGeneral = (turnoGeneral == JUGADOR1) ? JUGADOR2 : JUGADOR1;
        }

        turno = turnoGeneral;

        /*Jugando.*/
        if (turno == JUGADOR1) {

        }

        /*Mostramos su información, asignamos los nombres de jugador al panel.*/
        mostrarInformacion();

        jugando = true;
        terminado = false;
    }

    public void limpiarTablero() {
        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero, 0);

        /*Borramos los iconos.*/
        for (int i = 0; i < 9; i++) {
            fichas[i].setIcon(null);
        }
    }

    /*Método que "pone una ficha" en el tablero.*/
    public void ponerFicha(JLabel ficha) {
        try {

            /*Obtenemos la casilla.*/
            int casilla = 0;
            casilla = Integer.parseInt("" + ficha.getName().charAt(1)) - 1;

            /*Comprobamos si la casilla no estaba ocupada.*/
            if (estaOcupada(casilla)) {
                return;
            }

            /*Elegimos la ficha según el turno*/
            if (turno == JUGADOR1) {
                ficha.setIcon(jugador1.obtenFicha());
            } else {
                ficha.setIcon(jugador2.obtenFicha());
            }

            /*Guardamos la representación en el tablero*/
            tablero[casilla] = turno;

            /*Cambiamos el turno.*/
            turno = (turno == JUGADOR1) ? JUGADOR2 : JUGADOR1;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    /*Método que pone una ficha por la computadora.*/
    public void ponerFichaCPU(int indice) {

        if (indice == -1) {
            return;
        }

        switch (indice) {
            case 0:
                this.P1.setIcon(jugador2.obtenFicha());
                break;
            case 1:
                this.P2.setIcon(jugador2.obtenFicha());
                break;
            case 2:
                this.P3.setIcon(jugador2.obtenFicha());
                break;
            case 3:
                this.P4.setIcon(jugador2.obtenFicha());
                break;
            case 4:
                this.P5.setIcon(jugador2.obtenFicha());
                break;
            case 5:
                this.P6.setIcon(jugador2.obtenFicha());
                break;
            case 6:
                this.P7.setIcon(jugador2.obtenFicha());
                break;
            case 7:
                this.P8.setIcon(jugador2.obtenFicha());
                break;
            case 8:
                this.P9.setIcon(jugador2.obtenFicha());
                break;
        }

        this.tablero[indice] = 2;

        /*Cambiamos el turno.*/
        turno = (turno == JUGADOR1) ? JUGADOR2 : JUGADOR1;

    }

    /*Método que inicia los componentes del Gato.*/
    private void iniciarComponentes() {
        /*Icono del formulario.*/
        //this.setIconImage( Image );

        /*Hacemos visible el formulario.*/
        ImageIcon icono = new ImageIcon("src\\gato_Dibujos\\gato.png");
        this.setLocationRelativeTo(null);
        this.setIconImage(icono.getImage());
        this.setVisible(true);

        /*Referenciamos todas las etiquetas.*/
        fichas = new JLabel[9];
        fichas[0] = P1;
        fichas[1] = P2;
        fichas[2] = P3;
        fichas[3] = P4;
        fichas[4] = P5;
        fichas[5] = P6;
        fichas[6] = P7;
        fichas[7] = P8;
        fichas[8] = P9;
    }


    /*Método que inicia el juego una vez obtenido el modelo.*/
    public void iniciarJuego() {
        /*Creamos los jugadores según el tipo de juego.*/
        if (tipo.tipo_juego == PERSONAvsPERSONA) {
            this.jugador1 = new Jugador(tipo.nombre1, ficha.fichas[0]);
            this.jugador2 = new Jugador(tipo.nombre2, ficha.fichas[1]);

            /*Mostramos su información, asignamos los nombres de jugador al panel.*/
            mostrarInformacion();
        } else {
            /*Jugadores*/
            this.jugador1 = new Jugador(tipo.nombre1, ficha.fichas[0]);
            this.jugador2 = new Jugador("Computadora", ficha.fichas[1]);

            /*Creamos la instancia para la computadora.*/
            computadora = new Computadora();

            /*Mostramos su información, asignamos los nombres de jugador al panel.*/
            mostrarInformacion();
        }

        /*Iniciamos el turno en jugador 1*/
        this.turno = 1;
        this.turnoGeneral = JUGADOR1;

        /*Variables de juego.*/
        jugando = true;
        terminado = false;

        /*Deshabilitamos el menú nuevo juego.*/
        /*Movemos el foco.*/
        cambiarFoco();

    }
    
    public void Habilitar_LimpiaTablero(){
        this.menuLimpiarTablero.setEnabled(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        Panel2 = new javax.swing.JPanel();
        lblFicha2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblPerdidos2 = new javax.swing.JLabel();
        lblEmpatados2 = new javax.swing.JLabel();
        lblGanados2 = new javax.swing.JLabel();
        lblJugador2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblJugador1 = new javax.swing.JLabel();
        lblGanados1 = new javax.swing.JLabel();
        lblEmpatados1 = new javax.swing.JLabel();
        lblPerdidos1 = new javax.swing.JLabel();
        lblFicha1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblSonido = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTablero = new javax.swing.JLabel();
        lblSonido1 = new javax.swing.JLabel();
        P2 = new javax.swing.JLabel();
        P3 = new javax.swing.JLabel();
        P6 = new javax.swing.JLabel();
        P5 = new javax.swing.JLabel();
        P4 = new javax.swing.JLabel();
        P7 = new javax.swing.JLabel();
        P8 = new javax.swing.JLabel();
        P9 = new javax.swing.JLabel();
        P10 = new javax.swing.JLabel();
        P11 = new javax.swing.JLabel();
        P12 = new javax.swing.JLabel();
        P13 = new javax.swing.JLabel();
        P14 = new javax.swing.JLabel();
        P15 = new javax.swing.JLabel();
        P16 = new javax.swing.JLabel();
        P17 = new javax.swing.JLabel();
        P18 = new javax.swing.JLabel();
        P1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuIniciar = new javax.swing.JMenuItem();
        menuLimpiarTablero = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuFichas = new javax.swing.JMenuItem();
        menuSonido = new javax.swing.JMenu();
        menuActiv_Desactiv = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEstado.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 10, 800, -1));

        Panel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Jugador 2"));

        lblFicha2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ficha:");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        lblPerdidos2.setText("Juegos Perdidos: 0");

        lblEmpatados2.setText("Juegos Empatados: 0");

        lblGanados2.setText("Juegos Ganados: 0");

        lblJugador2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblJugador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nombre:");

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblJugador2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFicha2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGanados2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPerdidos2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmpatados2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(326, 326, 326))
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGanados2)
                .addGap(18, 18, 18)
                .addComponent(lblEmpatados2)
                .addGap(18, 18, 18)
                .addComponent(lblPerdidos2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFicha2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(Panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 190, 320));

        Panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Jugador 1"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre:");

        lblJugador1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblJugador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblGanados1.setText("Juegos Ganados: 0");

        lblEmpatados1.setText("Juegos Empatados: 0");

        lblPerdidos1.setText("Juegos Perdidos: 0");

        lblFicha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Ficha:");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblJugador1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(lblFicha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(lblGanados1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPerdidos1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmpatados1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap())
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGanados1)
                .addGap(18, 18, 18)
                .addComponent(lblEmpatados1)
                .addGap(18, 18, 18)
                .addComponent(lblPerdidos1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFicha1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.add(Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 190, 320));

        lblSonido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSonido.setToolTipText("Desactivar Sonido");
        lblSonido.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblSonido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSonidoMouseClicked(evt);
            }
        });
        jPanel1.add(lblSonido, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 410, 60, 50));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setToolTipText("Ir al Sitio");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 412, 10, 50));

        lblTablero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblTablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        lblSonido1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSonido1.setToolTipText("Desactivar Sonido");
        lblSonido1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblSonido1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSonido1MouseClicked(evt);
            }
        });
        jPanel1.add(lblSonido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 410, 50, 50));

        P2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P2.setName("p2"); // NOI18N
        P2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P2MouseClicked(evt);
            }
        });
        jPanel1.add(P2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 100, 100));

        P3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P3.setName("p3"); // NOI18N
        P3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P3MouseClicked(evt);
            }
        });
        jPanel1.add(P3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 100, 100));

        P6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P6.setName("p6"); // NOI18N
        P6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P6MouseClicked(evt);
            }
        });
        jPanel1.add(P6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 100, 100));

        P5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P5.setName("p5"); // NOI18N
        P5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P5MouseClicked(evt);
            }
        });
        jPanel1.add(P5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 100, 100));

        P4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P4.setName("p4"); // NOI18N
        P4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P4MouseClicked(evt);
            }
        });
        jPanel1.add(P4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 100, 100));

        P7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P7.setName("p7"); // NOI18N
        P7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P7MouseClicked(evt);
            }
        });
        jPanel1.add(P7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 100, 100));

        P8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P8.setName("p8"); // NOI18N
        P8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P8MouseClicked(evt);
            }
        });
        jPanel1.add(P8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 100, 100));

        P9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P9.setName("p9"); // NOI18N
        P9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P9MouseClicked(evt);
            }
        });
        jPanel1.add(P9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 100, 100));

        P10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P10.setName("p1"); // NOI18N
        P10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P10MouseClicked(evt);
            }
        });
        jPanel1.add(P10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 100, 100));

        P11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P11.setName("p2"); // NOI18N
        P11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P11MouseClicked(evt);
            }
        });
        jPanel1.add(P11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 110, 100));

        P12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P12.setName("p3"); // NOI18N
        P12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P12MouseClicked(evt);
            }
        });
        jPanel1.add(P12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 100, 100));

        P13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P13.setName("p6"); // NOI18N
        P13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P13MouseClicked(evt);
            }
        });
        jPanel1.add(P13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 100, 100));

        P14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P14.setName("p5"); // NOI18N
        P14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P14MouseClicked(evt);
            }
        });
        jPanel1.add(P14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 110, 110));

        P15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P15.setName("p4"); // NOI18N
        P15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P15MouseClicked(evt);
            }
        });
        jPanel1.add(P15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 100, 100));

        P16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P16.setName("p7"); // NOI18N
        P16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P16MouseClicked(evt);
            }
        });
        jPanel1.add(P16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 100, 100));

        P17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P17.setName("p8"); // NOI18N
        P17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P17MouseClicked(evt);
            }
        });
        jPanel1.add(P17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 110, 100));

        P18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P18.setName("p9"); // NOI18N
        P18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P18MouseClicked(evt);
            }
        });
        jPanel1.add(P18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 100, 100));

        P1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/tablero.png"))); // NOI18N
        P1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        P1.setName("p1"); // NOI18N
        P1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P1MouseClicked(evt);
            }
        });
        jPanel1.add(P1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 320, 320));

        jMenu1.setText("Juego");

        menuIniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menuIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/juego1.png"))); // NOI18N
        menuIniciar.setText("Iniciar Juego");
        menuIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIniciarActionPerformed(evt);
            }
        });
        jMenu1.add(menuIniciar);

        menuLimpiarTablero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menuLimpiarTablero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/limpiar1.png"))); // NOI18N
        menuLimpiarTablero.setText("Limpiar Tablero");
        menuLimpiarTablero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLimpiarTableroActionPerformed(evt);
            }
        });
        jMenu1.add(menuLimpiarTablero);
        jMenu1.add(jSeparator5);

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/salir1.png"))); // NOI18N
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configuración");

        menuFichas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        menuFichas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/fichas1.png"))); // NOI18N
        menuFichas.setText("Fichas");
        menuFichas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFichasActionPerformed(evt);
            }
        });
        jMenu2.add(menuFichas);

        menuSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/sonido1.png"))); // NOI18N
        menuSonido.setText("Sonido");

        menuActiv_Desactiv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, 0));
        menuActiv_Desactiv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/activar1.png"))); // NOI18N
        menuActiv_Desactiv.setText("Activar / Desactivar");
        menuActiv_Desactiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActiv_DesactivActionPerformed(evt);
            }
        });
        menuSonido.add(menuActiv_Desactiv);

        jMenu2.add(menuSonido);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Creditos");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/acerca1.png"))); // NOI18N
        jMenuItem1.setText(":)");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIniciarActionPerformed

        ImageIcon icono = new ImageIcon("src\\gato_Dibujos\\gato.png");
        this.tipo.setLocationRelativeTo(null);
        this.tipo.setIconImage(icono.getImage());
        this.tipo.setVisible(true);
    }//GEN-LAST:event_menuIniciarActionPerformed

    private void menuLimpiarTableroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLimpiarTableroActionPerformed
        reiniciarJuego();
    }//GEN-LAST:event_menuLimpiarTableroActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        int res = JOptionPane.showConfirmDialog(rootPane, "Está seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuSalirActionPerformed

    private void menuFichasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFichasActionPerformed
        ImageIcon icono = new ImageIcon("src\\gato_Dibujos\\gato.png");
        ficha.setLocationRelativeTo(null);
        ficha.setIconImage(icono.getImage());
        ficha.setVisible(true);
    }//GEN-LAST:event_menuFichasActionPerformed

    private void menuActiv_DesactivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActiv_DesactivActionPerformed
        
    }//GEN-LAST:event_menuActiv_DesactivActionPerformed

    private void lblSonidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSonidoMouseClicked
      
    }//GEN-LAST:event_lblSonidoMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked

    }//GEN-LAST:event_jLabel17MouseClicked

    private void lblSonido1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSonido1MouseClicked
     
    }//GEN-LAST:event_lblSonido1MouseClicked

    private void P1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P1MouseClicked
        movimiento(P1);
    }//GEN-LAST:event_P1MouseClicked

    private void P2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P2MouseClicked
        movimiento(P2);
    }//GEN-LAST:event_P2MouseClicked

    private void P3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P3MouseClicked
        movimiento(P3);
    }//GEN-LAST:event_P3MouseClicked

    private void P6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P6MouseClicked
        movimiento(P6);
    }//GEN-LAST:event_P6MouseClicked

    private void P5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P5MouseClicked
        movimiento(P5);
    }//GEN-LAST:event_P5MouseClicked

    private void P4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P4MouseClicked
        movimiento(P4);
    }//GEN-LAST:event_P4MouseClicked

    private void P7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P7MouseClicked
        movimiento(P7);
    }//GEN-LAST:event_P7MouseClicked

    private void P8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P8MouseClicked
        movimiento(P8);
    }//GEN-LAST:event_P8MouseClicked

    private void P9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P9MouseClicked
        movimiento(P9);
    }//GEN-LAST:event_P9MouseClicked

    private void P10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P10MouseClicked
        movimiento(P1);
    }//GEN-LAST:event_P10MouseClicked

    private void P11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P11MouseClicked
        movimiento(P2);
    }//GEN-LAST:event_P11MouseClicked

    private void P12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P12MouseClicked
        movimiento(P3);
    }//GEN-LAST:event_P12MouseClicked

    private void P13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P13MouseClicked
        movimiento(P6);
    }//GEN-LAST:event_P13MouseClicked

    private void P14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P14MouseClicked
        movimiento(P5);
    }//GEN-LAST:event_P14MouseClicked

    private void P15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P15MouseClicked
        movimiento(P4);
    }//GEN-LAST:event_P15MouseClicked

    private void P16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P16MouseClicked
        movimiento(P7);
    }//GEN-LAST:event_P16MouseClicked

    private void P17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P17MouseClicked
        movimiento(P8);
    }//GEN-LAST:event_P17MouseClicked

    private void P18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P18MouseClicked
        movimiento(P9);
    }//GEN-LAST:event_P18MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new JFJuego().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel P1;
    private javax.swing.JLabel P10;
    private javax.swing.JLabel P11;
    private javax.swing.JLabel P12;
    private javax.swing.JLabel P13;
    private javax.swing.JLabel P14;
    private javax.swing.JLabel P15;
    private javax.swing.JLabel P16;
    private javax.swing.JLabel P17;
    private javax.swing.JLabel P18;
    private javax.swing.JLabel P2;
    private javax.swing.JLabel P3;
    private javax.swing.JLabel P4;
    private javax.swing.JLabel P5;
    private javax.swing.JLabel P6;
    private javax.swing.JLabel P7;
    private javax.swing.JLabel P8;
    private javax.swing.JLabel P9;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JLabel lblEmpatados1;
    private javax.swing.JLabel lblEmpatados2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFicha1;
    private javax.swing.JLabel lblFicha2;
    private javax.swing.JLabel lblGanados1;
    private javax.swing.JLabel lblGanados2;
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblPerdidos1;
    private javax.swing.JLabel lblPerdidos2;
    private javax.swing.JLabel lblSonido;
    private javax.swing.JLabel lblSonido1;
    private javax.swing.JLabel lblTablero;
    private javax.swing.JMenuItem menuActiv_Desactiv;
    private javax.swing.JMenuItem menuFichas;
    private javax.swing.JMenuItem menuIniciar;
    private javax.swing.JMenuItem menuLimpiarTablero;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenu menuSonido;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
