/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussystem;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.*;
import java.util.*;

/**
 *
 * @author v801107
 */
public class AddBooking extends javax.swing.JInternalFrame {

    /**
     * Creates new form AddBooking
     */
    public AddBooking(String loginUser) {
        initComponents();
        busSourceCBFillData();
        busDestinationCBFillData();
        this.luser=loginUser;
        this.getContentPane().setBackground(new Color(245, 245, 220)); 

    }
    String luser;
    int bookingNo;
    
    
    
    
    Connection con1;
    PreparedStatement pst;
    
     Connection con2;
    PreparedStatement pst1;
    
    public void infoMessage(String message,String tittle)
    {
       JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);
    }  
    private void table_update() {
       
       
    try {
      
        int c;
        
        java.util.Date departDate = departDateDC.getDate();
        java.util.Date departDate1 = departDateDC1.getDate();

        Class.forName("com.mysql.cj.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/busm", "root", "");

        String selectedSource = sourceCB.getSelectedItem().toString();
        String selectedDestination = destinationCB.getSelectedItem().toString();

        PreparedStatement pst = con1.prepareStatement("SELECT * FROM bus_details WHERE "
                + "((bus_source = ? AND bus_dest = ? AND movement = 'UP' AND depart_date BETWEEN ? AND ?) OR "
                + "(bus_dest = ? AND bus_source = ? AND movement = 'DOWN' AND depart_date BETWEEN ? AND ?)) "
                + "AND TIME_TO_SEC(STR_TO_DATE(depart_time, '%h.%i %p')) > TIME_TO_SEC(CURRENT_TIME())");

        pst.setString(1, selectedSource);
        pst.setString(2, selectedDestination);
        pst.setDate(3, new java.sql.Date(departDate.getTime()));
        pst.setDate(4, new java.sql.Date(departDate1.getTime()));
        pst.setString(5, selectedDestination);
        pst.setString(6, selectedSource);
        pst.setDate(7, new java.sql.Date(departDate.getTime()));
        pst.setDate(8, new java.sql.Date(departDate1.getTime()));
        
        ResultSet rs = pst.executeQuery();

        ResultSetMetaData rsd = rs.getMetaData();
        c = rsd.getColumnCount();

        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        d.setRowCount(0);

        while (rs.next()) {
            
            Vector v2 = new Vector();
                        
            for (int i = 1; i <= c; i++) {
                v2.add(rs.getInt("id"));
                v2.add(rs.getString("bus_no"));
                v2.add(rs.getString("movement"));
                v2.add(rs.getString("bus_source"));
                v2.add(rs.getString("bus_dest"));
                v2.add(rs.getDate("depart_date"));
                v2.add(rs.getString("depart_time"));
                v2.add(rs.getInt("price"));
                v2.add(rs.getInt("total_seat"));
            }

            d.addRow(v2);
            
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(EditBusDetails.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    private void busSourceCBFillData()
    {
       try
       {
          Class.forName("com.mysql.jdbc.Driver");
          String databaseURL = "jdbc:mysql://localhost:3306/busm";
          Connection con = DriverManager.getConnection(databaseURL,"root","");
          Statement stat = con.createStatement();
          String selectQuery="select bus_source from bus_details";
          ResultSet rs=stat.executeQuery(selectQuery);
          while(rs.next())
          {
             sourceCB.addItem(rs.getString("bus_source"));
          }
       }
       catch(ClassNotFoundException | SQLException e)
       {
          System .out.println(e);
       } 
    }
    private void busDestinationCBFillData()
    {
       try
       {
          Class.forName("com.mysql.jdbc.Driver");
          String databaseURL = "jdbc:mysql://localhost:3306/busm";
          Connection con = DriverManager.getConnection(databaseURL,"root","");
          Statement stat = con.createStatement();
          String selectQuery="select bus_dest from bus_details";
          ResultSet rs=stat.executeQuery(selectQuery);
          while(rs.next())
          {
             destinationCB.addItem(rs.getString("bus_dest"));
          }
       }
       catch(ClassNotFoundException | SQLException e)
       {
          System.out.println(e);
       } 
    }
    void bookingTableUpdate(int bookingID)
    {
     
    try {
        int c1;
        this.bookingNo = bookingID;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/busm", "root", "");

            pst1 = con2.prepareStatement("select * from booking_details WHERE booking_id=?");
            pst1.setInt(1, bookingNo);
            ResultSet rs1 = pst1.executeQuery();

            ResultSetMetaData rsd1 = rs1.getMetaData();
            c1 = rsd1.getColumnCount();

            DefaultTableModel d1 = (DefaultTableModel) jTable2.getModel();
            boolean recordExists = false;

            while (rs1.next()) {
                Vector v = new Vector();

                for (int i = 1; i <= c1; i++) {
                    v.add(rs1.getInt("booking_id"));
                    v.add(rs1.getString("booking_user"));
                    v.add(rs1.getString("journey_date"));
                    v.add(rs1.getString("bus_no"));
                    v.add(rs1.getInt("seat_no"));
                    v.add(rs1.getString("payment_status"));
                    v.add(rs1.getString("firstname"));
                    v.add(rs1.getString("lastname"));
                    v.add(rs1.getString("nic_no"));
                }

                
                boolean bookingIdExists = false;
                for (int row = 0; row < d1.getRowCount(); row++) {
                    if (d1.getValueAt(row, 0).equals(rs1.getInt("booking_id"))) {
                        bookingIdExists = true;
                        // Update the existing row
                        for (int col = 0; col < c1; col++) {
                            d1.setValueAt(v.get(col), row, col);
                        }
                        break;
                    }
                }

                
                if (!bookingIdExists) {
                    d1.addRow(v);
                }

                recordExists = true;
            }

            
            if (!recordExists) {
                System.out.println("Record with booking_id " + bookingNo + " not found!");
                // Handle as needed
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddBooking.class.getName()).log(Level.SEVERE, null, ex);
        }

    } catch (SQLException ex) {
        Logger.getLogger(AddBooking.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        destinationCB = new javax.swing.JComboBox<>();
        sourceCB = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        departDateDC1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        departDateDC = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Add Booking Info");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Searching Result.......");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Booking User", "Journey Date", "Bus Number", "Seat Number", "Payment Status", "Firstname", "Lastname", "NIC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Bus Number", "Movement", "Bus Source", "Bus Destination", "Departure Date", "Departure Time", "Price", "Seats"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName(""); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        sourceCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceCBActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Date");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Source");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Upto");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Destination");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Booking Details.......");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(94, 94, 94)
                                .addComponent(departDateDC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(departDateDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sourceCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(66, 66, 66)
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(destinationCB, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(217, 217, 217))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 154, Short.MAX_VALUE)
                        .addGap(506, 506, 506))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(sourceCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(destinationCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel4))
                                    .addComponent(jLabel7)))
                            .addComponent(departDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departDateDC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jButton1)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        dialogbox a = new dialogbox();
        if(selectIndex != -1){
            if (a.result == JOptionPane.YES_OPTION) {
        int busno1 = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        String username = luser;
        
        
        

        BookingForm demo = new BookingForm(this, username, busno1);
   
        demo.setLocationRelativeTo(null);
        demo.setVisible(true);
         }
            else{
                dispose();
            }}
    }//GEN-LAST:event_jTable1MouseClicked

    private void sourceCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceCBActionPerformed
       
    }//GEN-LAST:event_sourceCBActionPerformed

    private static Date removeTime(Date date) {
    if (date == null) return null;
    // Set time part to midnight (00:00:00)
    return new Date(date.getYear(), date.getMonth(), date.getDate());
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    String source1 = Objects.requireNonNull(sourceCB.getSelectedItem()).toString();
    String destination1 = Objects.requireNonNull(destinationCB.getSelectedItem()).toString();
    
    Date currentDate = new Date(); 
    currentDate = removeTime(currentDate); // Set time part to midnight

    Date departDateD = departDateDC.getDate();
    departDateD = removeTime(departDateD); // Set time part to midnight

    Date departDateD1 = departDateDC1.getDate();
    departDateD1 = removeTime(departDateD1); 

        if ("".equals(source1) || "".equals(destination1) || departDateD == null || departDateD1 == null) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields!!", "Alert !!", JOptionPane.ERROR_MESSAGE);
        } else if (departDateD.before(currentDate) || departDateD.after(departDateD1)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid date range!", "Invalid date", JOptionPane.ERROR_MESSAGE);
        } else {
            table_update();
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
   

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser departDateDC;
    private com.toedter.calendar.JDateChooser departDateDC1;
    private javax.swing.JComboBox<String> destinationCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> sourceCB;
    // End of variables declaration//GEN-END:variables

}