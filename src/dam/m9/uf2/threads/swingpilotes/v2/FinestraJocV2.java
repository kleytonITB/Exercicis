package dam.m9.uf2.threads.swingpilotes.v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Canvis respecte la versió anterior:
 *  - cada vegada que es crea una pilota es crea un thread on es dibuixa i es pinta.
 *  - es comparteix el JPanel
 * @author Montse
 * @version 15/10/2020
 *
 */
public class FinestraJocV2 extends JFrame {
    // ho fem amb una classe separada, perquè és la única part de la finestra que ha de programar
    //la funció paint()
    private PanelPilotes panelPilotes = new PanelPilotes();

    public FinestraJocV2() {
        setBounds(600, 300, 400, 350);
        setTitle("Pilotes botant");
        add(panelPilotes, BorderLayout.CENTER);
        JPanel botonera = new JPanel();
        JButton boto1 = new JButton("Pilota va!");
        JButton boto2 = new JButton("Sortir");
        botonera.add(boto1);
        botonera.add(boto2);
        boto1.addActionListener(new ClickBotoPilotaVa());
        boto2.addActionListener(new ClickSortir());
        add(botonera, BorderLayout.SOUTH);
    }

    class ClickSortir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    class ClickBotoPilotaVa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Pilota p = new Pilota();
            panelPilotes.add(p);

            //System.out.println("Crea pilota");
            Runnable r = new ThreadPilota(p, panelPilotes);
            Thread t = new Thread(r);
            t.start();
        }

    }

}

