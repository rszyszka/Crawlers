package kolkokrzyzyk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Szysz
 */
public class Gracz1 extends JFrame implements ActionListener {
    private String imie;
    JTextField wpisz;
    JLabel info;

    public String getImie() {
        return imie;
    }
    
    
    
    Gracz1(){
    
        setSize(400,150);
        setLayout(null);
        
        info = new JLabel("Podaj imię gracza1:");
        info.setBounds(30,35,200,30);
        add(info);
        
        wpisz = new JTextField("");
        wpisz.setBounds(170,35,170,30);
        add(wpisz);
        wpisz.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        imie = wpisz.getText();
        dispose();
       
    }
    
}
