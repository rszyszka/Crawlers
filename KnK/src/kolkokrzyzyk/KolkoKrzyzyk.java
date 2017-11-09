package kolkokrzyzyk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Szysz
 */
public class KolkoKrzyzyk extends JFrame implements ActionListener {
    
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
    JMenuBar menuBar;
    JMenu menuPlik;
    JMenuItem mNowaGra, mWyjscie;
    
    KolkoKrzyzyk(){
    
        setSize(618,670);
        setTitle("Kółko i Krzyżyk");
        setLayout(null);
        
        b1 = new JButton("");
        b1.setBounds(0,0,200,200);
        add(b1);
        
        b2 = new JButton("");
        b2.setBounds(200,0,200,200);
        add(b2);
        
        b3 = new JButton("");
        b3.setBounds(400,0,200,200);
        add(b3);
        
        b4 = new JButton("");
        b4.setBounds(0,200,200,200);
        add(b4);
        
        b5 = new JButton("");
        b5.setBounds(200,200,200,200);
        add(b5);
        
        b6 = new JButton("");
        b6.setBounds(400,200,200,200);
        add(b6);
        
        b7 = new JButton("");
        b7.setBounds(0,400,200,200);
        add(b7);
        
        b8 = new JButton("");
        b8.setBounds(200,400,200,200);
        add(b8);
        
        b9 = new JButton("");
        b9.setBounds(400,400,200,200);
        add(b9);
        
        menuBar = new JMenuBar();
        menuPlik = new JMenu("Plik");
        setJMenuBar(menuBar);
        menuBar.add(menuPlik);
        
        mNowaGra = new JMenuItem("Nowa Gra");
        mWyjscie = new JMenuItem("Wyjscie");
        menuPlik.add(mNowaGra);
        menuPlik.addSeparator();
        menuPlik.add(mWyjscie);
        
        mWyjscie.addActionListener(this);
        mNowaGra.addActionListener(this);
        
    }

    public static void main(String[] args) {
        KolkoKrzyzyk plansza = new KolkoKrzyzyk();
        plansza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plansza.setVisible(true);
        
        Gracz1 gracz = new Gracz1();
        gracz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gracz.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object zrodlo = e.getSource();
        
        if(zrodlo == mWyjscie)
            dispose();
        else{
           
            //TODO: Dzialanie przycisku Nowa Gra
            
        }}
        
    
}
