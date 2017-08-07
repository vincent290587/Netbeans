/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import myStravaUpload.CSVWriter;

/**
 *
 * @author vincent
 */
public class myConfiguration extends javax.swing.JPanel implements SerialPortEventListener {

    Thread t;
    SerialPort _port;
    BufferedReader _input;
    OutputStream _output;
    ArrayList _portList;
    myIHM _parent;
    boolean isConnected;
    boolean isDownloading;
    public List<String> _lignes;

    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 250000;

    /**
     * Creates new form myConfiguration
     *
     * @param parent_
     */
    public myConfiguration(myIHM parent_) {
        initComponents();
        jLabel1.setText("");
        _port = null;
        _portList = new ArrayList();
        _lignes = new ArrayList();
        _parent = parent_;
        isConnected = false;
        isDownloading = false;
        jButton4.setVisible(false);
    }

    public void appendLine(String texte) {
        jTextArea1.append(texte + "\n");
        jTextArea1.repaint();
        jTextArea1.getParent().repaint();
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
    }

    public void appendLineRL(String texte) {

        String ligne;
        int pos;

        if (jTextArea1.getRows() > 30) {
            try {
                ligne = jTextArea1.getDocument().getText(0, 100);
                pos = ligne.indexOf("\n");
                if (pos > 0) {
                    jTextArea1.getDocument().remove(0, pos);
                } else {
                    jTextArea1.getDocument().remove(0, 50);
                }

            } catch (BadLocationException ex) {
                Logger.getLogger(myConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        jTextArea1.append(texte + "\n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
    }

    public void initSerialList() {

        Enumeration ports = CommPortIdentifier.getPortIdentifiers();

        _portList.clear();
        jComboBox1.removeAllItems();

        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                _portList.add(port.getName());
                jComboBox1.addItem(port.getName());
            }
        }

        if (_portList.isEmpty()) {
            jComboBox1.addItem("None");
        }
    }

    public void connectAction() {

        String p_name = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());

        if (p_name == null) {
            appendLine("Action invalide");
            return;
        }

        if (!p_name.startsWith("COM")) {
            appendLine("Port incorrect: " + p_name);
            return;
        }

        appendLine("Connection au port " + p_name);

        if (connectSerial(p_name) == 1) {
            appendLine("Erreur  ");
        } else {
            appendLine("Connecté");
            isConnected = true;
            jButton4.setVisible(true);
        }

    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    public synchronized void disconnectSerial() {
        if (_port != null) {
            _port.removeEventListener();
            _port.close();
            appendLine("Disconnected");
            isConnected = false;
            jButton4.setVisible(false);
        }
    }

    public int connectSerial(String port_name_) {

        disconnectSerial();

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            if (currPortId.getName().equals(port_name_)) {
                portId = currPortId;
                break;
            }
        }

        if (portId == null) {
            appendLine("Erreur de connection au " + port_name_);
            return 1;
        }

        try {

            _port = (SerialPort) portId.open(port_name_, TIME_OUT);
            _port.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            _port.addEventListener(this);
            _input = new BufferedReader(new InputStreamReader(_port.getInputStream()));
            _output = _port.getOutputStream();

            _port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            _port.notifyOnCTS(true);
            _port.notifyOnDataAvailable(true);

        } catch (PortInUseException | UnsupportedCommOperationException | TooManyListenersException | IOException ex) {
            Logger.getLogger(myConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }

        return 0;
    }

    public void sendString(String str_) {

        String tmp = str_ + "\n";

        appendLine("  -->" + str_);

//        try {
//
//            while (!_port.isCTS()) {
//                Thread.sleep(5);
//            }
//
//        } catch (InterruptedException ex) {
//            Logger.getLogger(myConfiguration.class.getName()).log(Level.SEVERE, null, ex);
//        }

        if (isConnected) {
            try {
                _output.write(tmp.getBytes(), 0, tmp.length());
            } catch (IOException ex) {
                Logger.getLogger(myConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    
    public void serialEvent(SerialPortEvent spe) {
        try {
            switch (spe.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:

                    String inputLine = _input.readLine();

                    if (inputLine.startsWith("##LOG_START##")) {
                        _lignes.clear();
                        isDownloading = true;
                        t.stop();
                        // thread pour repeindre le text area
                        t = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    jTextArea1.repaint();
                                    jTextArea1.getParent().repaint();
                                    Thread.sleep(300);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(myConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        };
                        t.setPriority(Thread.MIN_PRIORITY);
                        t.start();
                    } else if (inputLine.startsWith("##LOG_STOP##")) {
                        
                        // acknowledge and stop download
                        this.sendString("$DWN,3");
                        
                        if (isDownloading) {
                            _parent._serial.appendLine("Historique reçu");
                            isDownloading = false;
                            
                            CSVWriter csv = new CSVWriter();
                            csv.writePath("C:\\Users\\vincent\\Desktop\\today.csv", _lignes);
                        
                            t.stop();
                            t = new Thread() {
                                @Override
                                public void run() {
                                    _parent.getUpload().registerDownload(_lignes);
                                }
                            };
                            t.setPriority(Thread.MAX_PRIORITY);
                            t.start();
                        }

                    }

                    if (isDownloading == true) {
                        _lignes.add(inputLine);
                        appendLineRL(" <--" + inputLine);
                    } else {
                        appendLine(" <--" + inputLine);
                    }

                    System.out.println(inputLine);
                    break;
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                    break;
                case SerialPortEvent.CTS:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public void sauverGPX() {
        _parent._serial.appendLine("Historique reçu");
        t.interrupt();
        t = new Thread() {
            @Override
            public void run() {
                _parent.getUpload().registerDownload(_lignes);
            }
        };
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jButton1.setText("List COM ports");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));

        jButton2.setText("Connect");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Disconnect");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        initSerialList();
        jComboBox1.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        jTextArea1.setText(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        t = new Thread() {
            @Override
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {
                    connectAction();
                    Thread.sleep(1000000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        //connectAction();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        disconnectSerial();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}
