import java.awt.*;
import java.awt.event.*;

public class EightQueens extends Frame implements ActionListener,Runnable{
	Thread t;
	Label l;Panel p1,p2,p3;
	Button b[][]=new Button[8][8];
	Button start1=new Button("Start");	
	Button quit1=new Button("Exit");
	int flag=0;
	int xx,yy,i=0,j=0,k=1,q=0;
	int qnumber=0;
	int test=0;
	public EightQueens(){
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width-700,d.height-400);
		p1=new Panel();
   	        p1.setLayout(new FlowLayout());
   	        p2=new Panel();
   	        p2.setLayout(new GridLayout(8,8,2,2));
		l=new Label("EIGHT QUEENS GAME");
		p1.add(l);
		p3=new Panel();
		for( i=0;i<8;i++)for(j=0;j<8;j++){
			b[i][j]=new Button();
			p1.add(b[i][j]);
			b[i][j].setEnabled(false);
		}
		for(Button x[]: b)for(Button y : x){
			p2.add(y);
			y.addActionListener(this);
		}
		p3.add(start1);
		p3.add(quit1);
		start1.addActionListener(this);
		quit1.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			 public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		setLayout(new BorderLayout());
		add(p1,BorderLayout.NORTH);
		add(p2,BorderLayout.CENTER);
		add(p3,BorderLayout.SOUTH);
		setVisible(true);
	}
    	public void actionPerformed(ActionEvent e){
		boolean temp=true;
 		String str=e.getActionCommand();
		if(str.equals("Start")){
			for( i=0;i<8;i++){	 
				for( j=0;j<8;j++){
					b[i][j].setEnabled(true);
					b[i][j].setLabel("");
				}
			}			
			if (test==0){
				t=new Thread(this);
				t.start();
				qnumber=0;
			}
			else{
				t.interrupt();
				t=null;
				t=new Thread(this);
				t.start();
				qnumber=0;
			}
		}
		else if(str.equals("Exit"))
			System.exit(0);
		else{
			for(i=0;i<8;i++){
				for(j=0;j<8;j++){
					if(b[i][j]==e.getSource()){
						xx=i;
						yy=j;
					}
				}
			}
			if(b[xx][yy].getLabel().equals("")){
				b[xx][yy].setLabel("Q");
				qnumber++;
				for(i=0;i<8;i++){
					for(j=0;j<8;j++){
						if(i!=xx&&j!=yy){	
							b[xx][j].setLabel("*");
							b[xx][j].setEnabled(false);
							b[i][yy].setLabel("*");
							b[i][yy].setEnabled(false);
						}
					}
				}
				for(i=xx,q=xx,j=yy,k=yy;temp;i++,q--,j++,k--){
					temp=false;
					if(i<8&&j<8){
						temp=true;
						if(!((i==xx)&&(j==yy))){
							b[i][j].setLabel("*");
							b[i][j].setEnabled(false);
						}
					}
					if(i<8&&k>=0){
						temp=true;
						if(!((i==xx)&&(k==yy))){
							b[i][k].setLabel("*");
							b[i][k].setEnabled(false);
						}
					}
					if(q>=0&&j<8){
						temp=true;
						if(!((q==xx)&&(j==yy))){
							b[q][j].setLabel("*");
							b[q][j].setEnabled(false);
						}
					}
					if(q>=0&&k>=0){
						temp=true;
						if(!((q==xx)&&(k==yy))){
							b[q][k].setLabel("*");
							b[q][k].setEnabled(false);
						}
					}
				}
			}
			else{
				b[xx][yy].setLabel("");
				qnumber--;
				for(i=0;i<8;i++){
					for(j=0;j<8;j++){
						if(!(b[i][j].getLabel().equals("Q"))){	
							b[i][j].setLabel("");
							b[i][j].setEnabled(true);
						}
					}
				}
				for(int ii=0;ii<8;ii++)
					for(int jj=0;jj<8;jj++)
						if(b[ii][jj].getLabel().equals("Q"))							
							checking(ii,jj);
			}
		}
		if(qnumber==8){
			disablebutton();	
			Dialog1 d=new Dialog1(this,"Alert",true);
			t.interrupt();
			t=null;
		}
	}
	public void checking(int xx,int yy){
		boolean temp=true;
		for(i=0;i<8;i++){
			for(j=0;j<8;j++){
				if(i!=xx&&j!=yy){
					b[xx][j].setLabel("*");
					b[xx][j].setEnabled(false);
					b[i][yy].setLabel("*");
					b[i][yy].setEnabled(false);
				}
			}
		}
		for(i=xx,q=xx,j=yy,k=yy;temp;i++,q--,j++,k--){
			temp=false;
			if(i<8&&j<8){
				temp=true;
				if(!((i==xx)&&(j==yy))){
					b[i][j].setLabel("*");
					b[i][j].setEnabled(false);
				}
			}
			if(i<8&&k>=0){
				temp=true;
				if(!((i==xx)&&(k==yy))){
					b[i][k].setLabel("*");
					b[i][k].setEnabled(false);
				}
			}
			if(q>=0&&j<8){
				temp=true;
				if(!((q==xx)&&(j==yy))){
					b[q][j].setLabel("*");
					b[q][j].setEnabled(false);
				}
			}
			if(q>=0&&k>=0){
				temp=true;
				if(!((q==xx)&&(k==yy))){
					b[q][k].setLabel("*");
					b[q][k].setEnabled(false);
				}
			}
		}
	}
	public void disablebutton(){	
		for( i=0;i<8;i++)
			for( j=0;j<8;j++)
				b[i][j].setEnabled(false);
		start1.setEnabled(false);
		quit1.setEnabled(false);
	}
	public void enablebutton(){
		start1.setEnabled(true);
		quit1.setEnabled(true);
	}
	public void run(){	
		try{
			test++;
			Thread.sleep(120000);
			disablebutton();
			Dialog1 d=new Dialog1(this,"Alert",false);
			t=null;
			test=0;
		}
		catch(InterruptedException e){
			test=0;
		}
	}	
}
