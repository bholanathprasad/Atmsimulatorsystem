/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASimulatorSys;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{
 
    JTable t1;
    JButton b1;
    String x[] = {"Deposit","Withdraw","Balance"};
    String y[][] = new String[20][5];
    int i=0, j=0;
    
    MiniStatement(){
        super("Mini Statement");
        setSize(1200,650);
        setLocation(200,200);
     		 String pinn = JOptionPane.showInputDialog("Enter PIN");

        
        try{
            conn c1  = new conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '" + pinn + "'");
          //  String s1 = "select * from bank";
          //  ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
               
                y[i][j++]=rs.getString("deposit");
                y[i][j++]=rs.getString("withdraw");
                y[i][j++]=rs.getString("balance");
                
                i++;
                j=0;
            }
            t1 = new JTable(y,x);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        b1 = new JButton("Print");
        add(b1,"South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b1.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            t1.print();
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        new MiniStatement().setVisible(true);
    }
    
}
/* import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class MiniStat {

    private JFrame mainFrame;
    private JLabel statusLabel;
    private JLabel headerLabel;
    private Connection connection;
    private final int card;
    private Statement statement;
    private final JLabel stat[][] = new JLabel[10][5];
    private long millis;

    public MiniStat(int card) throws SQLException, IOException {
        this.card = card;
        Conection conn = new Conection();
        if (conn.isConnection()) {
            connection = conn.conreturn();
        }
        prepareLayout();
        makeStat();
    }

    private void prepareLayout() {
        mainFrame = new JFrame("ATM Simulator System");      //Main Heading name
        mainFrame.setSize(600, 600);         //Set Size of window
        mainFrame.setLocation(383, 84);
        mainFrame.setLayout(null);

        headerLabel = new JLabel("Mini Statement");
        headerLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        headerLabel.setBounds(225, 20, 200, 25);

        millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        statusLabel = new JLabel("");
        statusLabel.setText(date.toString());
        statusLabel.setBounds(10, 530, 250, 25);
        statusLabel.setFont(new Font("Serif", Font.PLAIN, 17));

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.add(headerLabel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void makeStat() throws SQLException, FileNotFoundException, IOException {
        statement = connection.createStatement();
        FileOutputStream fout;
        File file=new File(millis+".txt");
        fout = new FileOutputStream(file);
        int i = 0, y = 50, w = 100, h = 30;
        String sql_que = "SELECT * FROM `transaction` WHERE sCardno=" + card + " OR rCardno=" + card + " ORDER BY `id` DESC LIMIT 10";
        ResultSet rs = statement.executeQuery(sql_que);
        byte b[];
        b = ("Cardno\tRCardno\tChange\tBalance\tType\r\n").getBytes();
        fout.write(b);
        while (rs.next()) {
            String card = rs.getString("sCardno");
            String rcard = rs.getString("rCardno");
            String ch_balance = rs.getString("change_balance");
            String acc_balance = rs.getString("acc_balance");
            String type = rs.getString("type");
            if (rcard != null) {               
                stat[i][0] = new JLabel(card);
                stat[i][1] = new JLabel(ch_balance);
                stat[i][2] = new JLabel(acc_balance);
                stat[i][3] = new JLabel(type);
                stat[i][4] = new JLabel(rcard);
                int x=50;
                for(int k=0;k<5;k++,x+=100)
                    stat[i][k].setBounds(x, y, w, h);
                b = (card + "\t" + ch_balance + "\t" + acc_balance + "\t" + type + "\t" + rcard + "\r\n").getBytes();
                fout.write(b);
                for(int k=0;k<5;k++)
                    mainFrame.add(stat[i][k]);
            } else {
                stat[i][0] = new JLabel(card);
                stat[i][1] = new JLabel(ch_balance);
                stat[i][2] = new JLabel(acc_balance);
                stat[i][3] = new JLabel(type);
                int x=50;
                for(int k=0;k<4;k++,x+=100)
                    stat[i][k].setBounds(x, y, w, h);
                b = (card + "\t" + ch_balance + "\t" + acc_balance + "\t" + type + "\r\n").getBytes();
                fout.write(b);
                for(int k=0;k<4;k++)
                    mainFrame.add(stat[i][k]);
            }
            y += 50;
            i++;
        }
        fout.close();
    }
} */