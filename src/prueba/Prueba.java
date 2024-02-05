/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import componenteCaja.ArrastreListenerVertical;
import componenteCajonera.ArrastreListenerHorizontal;
import componentePulsar.PulsacionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author gonza
 */
public class Prueba extends javax.swing.JFrame {

    JDialogVictoria jDialogVictoria;
    JDialogDerrota jDialogDerrota;

    private boolean llaveEnCaja;
    private boolean llaveEnCajonera;
    private boolean llaveEnComponentePulsar;

    private Clip clip;

    private boolean juegoActivo = true;

    /**
     * Creates new form Prueba
     */
    public Prueba() {
        initComponents();
        jDialogVictoria = new JDialogVictoria(this, true);
        jDialogDerrota = new JDialogDerrota(this, true);
        reiniciarJuego();

        componentePulsar1.addPulsacionListener(new PulsacionListener() {
            @Override
            public void pulsacion() {
                System.out.println("Se ha pulsado la telca 'a'");
                detenerSonidoTiempoDeJuego();
                deshabilitarComponentes();
                componentePulsar1.setRutaImagen(new File("src/images/pulsador_cerrado.png"));
                verificarLlaveEnComponente(componentePulsar1);
            }
        });
        jPanelCaja1.addArrastreListenerVertical(new ArrastreListenerVertical() {
            @Override
            public void arrastre() {
                System.out.println("Se ha producido un gesto de arrastre" + new File("src/images/caja_abierta.png").getAbsolutePath());
                detenerSonidoTiempoDeJuego();
                deshabilitarComponentes();
                jPanelCaja1.setRutaImagen(new File("src/images/caja_abierta.png"));
                verificarLlaveEnComponente(jPanelCaja1);
            }
        });
        jPanelCajonera1.addArrastreListenerHorizontal(new ArrastreListenerHorizontal() {
            @Override
            public void arrastre() {
                System.out.println("Se ha producido un gesto de arrastre" + new File("src/images/cajonera_abierta.png").getAbsolutePath());
                detenerSonidoTiempoDeJuego();
                deshabilitarComponentes();
                jPanelCajonera1.setRutaImagen(new File("src/images/cajonera_abierta.png"));
                verificarLlaveEnComponente(jPanelCajonera1);
            }
        });

    }

    private void reiniciarJuego() {
        habilitarComponentes();
        reproducirSonido("src/sounds/Sonido_tiempo_de_juego.wav");
        // Reiniciar la ubicación de la llave de manera aleatoria
        Random random = new Random();
        // 0: caja, 1: cajonera, 2: componentePulsar
        int componenteConLlave = random.nextInt(3);
        llaveEnCaja = componenteConLlave == 0;
        llaveEnCajonera = componenteConLlave == 1;
        llaveEnComponentePulsar = componenteConLlave == 2;
    }

    private void verificarLlaveEnComponente(Component componente) {
        if (!juegoActivo) {
            return;
        }

        boolean llaveEnEsteComponente = false;
        if (componente == jPanelCaja1) {
            llaveEnEsteComponente = llaveEnCaja;
        } else if (componente == jPanelCajonera1) {
            llaveEnEsteComponente = llaveEnCajonera;
        } else if (componente == componentePulsar1) {
            llaveEnEsteComponente = llaveEnComponentePulsar;
        }

        if (llaveEnEsteComponente) {
            mostrarVictoria();
        } else {
            mostrarDerrota();
        }
    }

    private void mostrarVictoria() {
        juegoActivo = false;
        reproducirSonido("src/sounds/Sonido_respuesta_correcta.wav");
        jDialogVictoria.setVisible(true);

    }

    private void mostrarDerrota() {
        juegoActivo = false;
        reproducirSonido("src/sounds/Sonido_respuesta_incorrecta.wav");
        jDialogDerrota.setVisible(true);

    }

    private void reproducirSonido(String rutaSonido) {
        try {
            File archivoSonido = new File(rutaSonido);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoSonido.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Método para detener el sonido del tiempo de juego
    private void detenerSonidoTiempoDeJuego() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReintentar = new javax.swing.JButton();
        jPanelCaja1 = new componenteCaja.JPanelCaja();
        jPanelCajonera1 = new componenteCajonera.JPanelCajonera();
        componentePulsar1 = new componentePulsar.ComponentePulsar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonReintentar.setText("REINTENTAR");
        jButtonReintentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReintentarActionPerformed(evt);
            }
        });

        jPanelCaja1.setRutaImagen(new java.io.File("C:\\Users\\gonza\\OneDrive\\Documentos\\NetBeansProjects\\Prueba\\src\\images\\caja_cerrada.png"));

        javax.swing.GroupLayout jPanelCaja1Layout = new javax.swing.GroupLayout(jPanelCaja1);
        jPanelCaja1.setLayout(jPanelCaja1Layout);
        jPanelCaja1Layout.setHorizontalGroup(
            jPanelCaja1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        jPanelCaja1Layout.setVerticalGroup(
            jPanelCaja1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        jPanelCajonera1.setRutaImagen(new java.io.File("C:\\Users\\gonza\\OneDrive\\Documentos\\NetBeansProjects\\Prueba\\src\\images\\cajonera_cerrada.png"));

        javax.swing.GroupLayout jPanelCajonera1Layout = new javax.swing.GroupLayout(jPanelCajonera1);
        jPanelCajonera1.setLayout(jPanelCajonera1Layout);
        jPanelCajonera1Layout.setHorizontalGroup(
            jPanelCajonera1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );
        jPanelCajonera1Layout.setVerticalGroup(
            jPanelCajonera1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );

        componentePulsar1.setRutaImagen(new java.io.File("C:\\Users\\gonza\\OneDrive\\Documentos\\NetBeansProjects\\Prueba\\src\\images\\pulsador_abierto.png"));

        javax.swing.GroupLayout componentePulsar1Layout = new javax.swing.GroupLayout(componentePulsar1);
        componentePulsar1.setLayout(componentePulsar1Layout);
        componentePulsar1Layout.setHorizontalGroup(
            componentePulsar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        componentePulsar1Layout.setVerticalGroup(
            componentePulsar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jPanelCaja1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelCajonera1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(componentePulsar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jButtonReintentar, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButtonReintentar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCaja1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelCajonera1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(componentePulsar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReintentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReintentarActionPerformed
        Prueba frame = new Prueba();
        this.dispose();
        frame.setVisible(true);
        habilitarComponentes();
    }//GEN-LAST:event_jButtonReintentarActionPerformed

    private void deshabilitarComponentes() {
        jPanelCaja1.setEnabled(false);
        jPanelCajonera1.setEnabled(false);
        componentePulsar1.setEnabled(false);
    }

    private void habilitarComponentes() {
        jPanelCaja1.setEnabled(true);
        jPanelCajonera1.setEnabled(true);
        componentePulsar1.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentePulsar.ComponentePulsar componentePulsar1;
    private javax.swing.JButton jButtonReintentar;
    private componenteCaja.JPanelCaja jPanelCaja1;
    private componenteCajonera.JPanelCajonera jPanelCajonera1;
    // End of variables declaration//GEN-END:variables

}
