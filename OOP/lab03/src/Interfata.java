import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Interfata {
	
	static private JPanel container;
    static private JFrame frame;
    
	public Interfata (){
		container = new JPanel();
	    frame = new JFrame("lab03");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true); 
        frame.setResizable(false);
        JButton button = new JButton("Butonas");
        button.setSize(30,20);
        button.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("You clicked the button");
            }
        });      
 
        frame.getContentPane().add(button);

	}
    public void createAndShowGUI(String s) {
        
        JLabel label = new JLabel (s);
        container.add(label);
        frame.getContentPane().add(container);
        
   /*     JButton button = new JButton("Butonas");
        button.setSize(20,20);
        button.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("You clicked the button");
            }
        });      
 
        frame.getContentPane().add(button);
    */    
    }

       	

}

