/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussystem;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;

/**
 *
 * @author v801107
 */
public class AddBusDetails extends javax.swing.JInternalFrame {

    /**
     * Creates new form AddBusDetails
     */
   
    
    public AddBusDetails() {
        initComponents();
        this.getContentPane().setBackground(new Color(144, 238, 144)); 

        setupTimeSpinner();
        
        setupComboBoxModel();
    }

    Connection con1;
    PreparedStatement pst;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bus_noTF = new javax.swing.JTextField();
        bus_sourceTF = new javax.swing.JTextField();
        bus_destTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        priceTF = new javax.swing.JTextField();
        seatTF = new javax.swing.JTextField();
        departDateDC = new com.toedter.calendar.JDateChooser();
        jSpinner1 = new javax.swing.JSpinner();
        moveCB = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Add Bus Details");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Bus No.");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Source");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Destination");

        bus_noTF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        bus_sourceTF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bus_sourceTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bus_sourceTFActionPerformed(evt);
            }
        });

        bus_destTF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Reset");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Movement");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Date");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Time");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Price");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Seat");

        priceTF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        seatTF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jSpinner1.setModel(new javax.swing.SpinnerListModel(new String[] {"00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"}));

        moveCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bus_noTF, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(bus_sourceTF)
                            .addComponent(priceTF, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(departDateDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(146, 146, 146)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(moveCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bus_destTF, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(seatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bus_noTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(moveCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(bus_sourceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(bus_destTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(seatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(departDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bus_sourceTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bus_sourceTFActionPerformed
        
    }//GEN-LAST:event_bus_sourceTFActionPerformed

     public void infoMessage(String message,String tittle)
    {
       JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);
    }   
     
     public void clearFieldValue()
     {
       bus_noTF.setText("");
       bus_sourceTF.setText("");
       bus_sourceTF.setText("");
       moveCB.setSelectedItem("");
       departDateDC.setDate(null);
       setupTimeSpinner();
       priceTF.setText("");
       seatTF.setText("");
     }
    
     private boolean isValidInteger(String input) {
    try {
        Integer.parseInt(input);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
    }
  
  private void setupTimeSpinner() {
        SpinnerListModel spinnerModel = createTimeSpinnerModel();
        jSpinner1.setModel(spinnerModel);
    }

    private SpinnerListModel createTimeSpinnerModel() {
        List<String> timeList = generateTimeList();
        return new SpinnerListModel(timeList);
    }

    private List<String> generateTimeList() {
        List<String> timeList = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("00");
        
        timeList.add("");
        
        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 60; minute += 15) {
                String formattedHour = decimalFormat.format(hour);
                String formattedMinute = decimalFormat.format(minute);
                String timeString = formattedHour + ":" + formattedMinute;
                timeList.add(timeString);
            }
        }

        return timeList;
    }

    private void setupComboBoxModel() {
    DefaultComboBoxModel<String> comboBoxModel = createComboBoxModel();
    moveCB.setModel(comboBoxModel);
    }

    private DefaultComboBoxModel<String> createComboBoxModel() {
    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    comboBoxModel.addElement(""); // Add an empty string
    comboBoxModel.addElement("UP");
    comboBoxModel.addElement("DOWN");

    return comboBoxModel;
    }
  
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

                                            

        String busnoD = bus_noTF.getText();
        
        String bussourceD = bus_sourceTF.getText();
        String busdestD = bus_destTF.getText();
        java.util.Date departDateD = departDateDC.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Object selectedValue = jSpinner1.getValue();
        Object busMoveD1 = moveCB.getSelectedItem();
        String departDate = "";
        
        String combinedDateTimeString;
        Date currentDate = new Date();
        String priceText = priceTF.getText();
        String seatText = seatTF.getText();
        
        String busMoveD = "";
         if (busMoveD1 instanceof String) {
             busMoveD = (String) busMoveD1;
         } else if (busMoveD1 != null) {
             busMoveD = busMoveD1.toString();
         }
        
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
     
        Date combinedDateTime = null;
       
        String departTimeD = "";
        if (selectedValue instanceof String) {
    departTimeD = (String) selectedValue;
} else if (selectedValue != null) {
    departTimeD = selectedValue.toString();
}
        if (departDateD != null) {
    departDate = dateFormat.format(departDateD);
    combinedDateTimeString = departDate + " " + departTimeD;
    try {
        combinedDateTime = dateTimeFormat.parse(combinedDateTimeString);
    } catch (ParseException ex) {
        Logger.getLogger(AddBusDetails.class.getName()).log(Level.SEVERE, null, ex);
    }
}

int priceD = 0;
int seatD = 0;

if (isValidInteger(priceText) || isValidInteger(seatText)) {
    priceD = Integer.parseInt(priceText);
    seatD = Integer.parseInt(seatText);
}

if (busnoD.isEmpty() || busMoveD.isEmpty() || bussourceD.isEmpty() || busdestD.isEmpty() || departDateD == null || departTimeD.isEmpty() || priceText.isEmpty() || seatText.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Incomplete Information", JOptionPane.ERROR_MESSAGE);
} else if (combinedDateTime == null || combinedDateTime.before(currentDate)) {
    JOptionPane.showMessageDialog(this, "Please enter a valid future date and time!", "Invalid date", JOptionPane.ERROR_MESSAGE);
} else if (!isValidInteger(priceText) || !isValidInteger(seatText)) {
    JOptionPane.showMessageDialog(this, "Please enter valid numeric values for price and seat count!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
} else if (priceD <= 0 || seatD <= 0) {
    JOptionPane.showMessageDialog(this, "Please enter valid numeric values greater than 0 for price and seat count!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
} else {
         
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddBusDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        String databaseURL = "jdbc:mysql://localhost:3306/busm";
        
       
            
        try (Connection con = DriverManager.getConnection(databaseURL, "root", "")) {
            
            String selectQuery = "SELECT COUNT(*) FROM bus_details WHERE bus_no = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, busnoD);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);

                        if (count > 0) {
                            infoMessage("Already Bus Details are Added", "Create Fresh Entry !!");
                        } else {
                            
                            String insertQuery = "INSERT INTO bus_details VALUES (null,?,?,?,?,?,?,?,?)";
                            try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
                                insertStatement.setString(1, busnoD);
                                insertStatement.setString(2, busMoveD);
                                insertStatement.setString(3, bussourceD);
                                insertStatement.setString(4, busdestD);
                                insertStatement.setString(5, departDate);
                                insertStatement.setString(6, departTimeD);
                                insertStatement.setInt(7, priceD);
                                insertStatement.setInt(8, seatD);

                                insertStatement.executeUpdate();

                                infoMessage("Bus Details are Added", "Great Work !!");
                                clearFieldValue();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            
            
        }
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bus_destTF;
    private javax.swing.JTextField bus_noTF;
    private javax.swing.JTextField bus_sourceTF;
    private com.toedter.calendar.JDateChooser departDateDC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JComboBox<String> moveCB;
    private javax.swing.JTextField priceTF;
    private javax.swing.JTextField seatTF;
    // End of variables declaration//GEN-END:variables
}
