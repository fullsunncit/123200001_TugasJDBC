/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasjdbc;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author acer
 */
public class InputData extends JFrame {
    public String username, password;
    Connector connector = new Connector(); 
    Statement statement;
    ResultSet rs;
    
    //DEKLARASI KOMPONEN
    JFrame window = new JFrame("Tugas JDBC");
   
    JLabel lUserlogin= new JLabel("Username ");
    JTextField tfUserlogin = new JTextField();
    JLabel lPwlogin = new JLabel("Password ");
    JTextField tfPwlogin = new JTextField();
    JButton btnLogin = new JButton("LOGIN");
    JLabel lUserdaftar = new JLabel("Username  ");
    JTextField tfUserdaftar = new JTextField();
    JLabel lPwdaftar = new JLabel("Password  ");
    JTextField tfPwdaftar = new JTextField();
    JButton btnDaftar = new JButton("DAFTAR");

    public InputData() {
        window.setLayout(null);
        window.setSize(400,200);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(3);

        //ADD COMPONENT
        window.add(lUserlogin);
        window.add(tfUserlogin);
        window.add(lPwlogin);
        window.add(tfPwlogin);
        window.add(btnLogin);
        window.add(lUserdaftar);
        window.add(tfUserdaftar);
        window.add(tfPwdaftar);
        window.add(tfPwdaftar);
        window.add(btnDaftar);
        
        //KIRI
        lUserlogin.setBounds(5, 35, 120, 20);
        tfUserlogin.setBounds(5, 60, 120, 20);
        lPwlogin.setBounds(5, 85, 120, 20);
        tfPwlogin.setBounds(5, 105, 120, 20);
        btnLogin.setBounds(5, 130, 120, 20);
        
        //KANAN
        lUserdaftar.setBounds(200, 35, 120, 20);
        tfUserdaftar.setBounds(200, 60, 120, 20);
        lPwdaftar.setBounds(200, 85, 120, 20);
        tfPwdaftar.setBounds(200, 105, 120, 20);
        btnDaftar.setBounds(200, 130, 120, 20);
        
        btnDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "SELECT * FROM `users` WHERE username = '" + getUserdaftar() +"'"; 
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet rs = connector.statement.executeQuery(query);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(rootPane, "Username Sudah Ada");
                    } else {
                        String inputData = "INSERT INTO `users` (`username`, `password`) VALUES ( '"+ getUserdaftar()+"','"+getPwdaftar()+"')";           
                        connector.statement = connector.koneksi.createStatement();
                        connector.statement.executeUpdate(inputData);
                        System.out.println("Berhasil Mendaftar User");
                        JOptionPane.showMessageDialog(null,"Berhasil Mendaftar User");
                    }
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "SELECT * FROM `users` WHERE username = '" + getUserlogin() +"'"; 
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet rs = connector.statement.executeQuery(query);
                    if (rs.next()) {
                        if (getPwlogin().equals(rs.getString("password"))){
                            System.out.println("Login Berhasil");
                            JOptionPane.showMessageDialog(null, "Login Berhasil");
                        } else {
                            JOptionPane.showMessageDialog(rootPane,"Password Salah");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Username Tidak Ditemukan");
                    }
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        
    }
    
    public String getUserdaftar() {
        return tfUserdaftar.getText();
    }
    
    public String getPwdaftar(){
        return tfPwdaftar.getText();
    }

    public String getUserlogin() {
        return tfUserlogin.getText();
    }

    public String getPwlogin() {
        return tfPwlogin.getText();
    }
    
}
