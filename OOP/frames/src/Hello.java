import javax.swing.*;

public class Hello {
	
	public static void createAndShowGUI (){
		JFrame frame = new JFrame ("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel ("Hello World");
		frame.getContentPane().add(label);
		JLabel label2 = new JLabel ("tata are nervi");
		frame.getContentPane().add(label2);
        frame.pack();
        frame.setVisible(true);
	}
	public static void main (String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

	}
}
