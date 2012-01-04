import java.awt.*;
import java.awt.event.*;
class Dialog1 extends Dialog implements ActionListener{ 
	Button b;
	eightqueens queen;
	Dialog1(eightqueens parent,String title,boolean result){
		super(parent,title,false);
		queen=parent;
		Label l1=new Label("sorry time up!!GAME OVER");   
		Label l2=new Label("Congratulations!!YOU WON");   
        	setLayout(new FlowLayout());
        	setSize(300,200);
		if(!result) 
			add(l1);	
		else 
			add(l2);     	
		b=new Button("OK");
        	b.addActionListener (this);
        	add(b);
        	addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		setVisible(true);
	}
 	public void actionPerformed(ActionEvent e){
		queen.enablebutton();
		dispose();
	}
}
