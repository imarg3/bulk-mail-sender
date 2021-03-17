package org.code;

//import java.awt.Container;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
// import java.util.Properties;

/**
 * @author arpit
 * SendMailFrame Class extends JFrame
 */
public class SendMailFrame
        extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtAttachmentFileName;
    private JTextField txtCCField;
    private JTextField txtEmailAddressFileName;
    private JTextField txtEmailContentFileName;
    private JTextField txtSubject;
    private final String mailConfigFile;

    public SendMailFrame(String mailConfigFile) {
        initComponents();
        this.mailConfigFile = mailConfigFile;
    }

    private void initComponents() {
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel1 = new JLabel();
        this.txtEmailAddressFileName = new JTextField();
        JButton btnBrowseEmailIdFile = new JButton();
        this.txtCCField = new JTextField();
        this.txtSubject = new JTextField();
        this.txtAttachmentFileName = new JTextField();
        JButton btnBrowseAttachmentFile = new JButton();
        JLabel jLabel6 = new JLabel();
        this.txtEmailContentFileName = new JTextField();
        JButton btnBrowseContentFile = new JButton();
        JButton btnSendMail = new JButton();
        JButton btnClose = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("CC : ");

        jLabel4.setText("Subject : ");

        jLabel5.setText("Attachment :");

        jLabel1.setText("TO:");

        btnBrowseEmailIdFile.setText("Browse");
        btnBrowseEmailIdFile.addActionListener(SendMailFrame.this::btnBrowseEmailIdFileActionPerformed);
        this.txtCCField.addActionListener(SendMailFrame.this::txtCCFieldActionPerformed);
        btnBrowseAttachmentFile.setText("Browse");
        btnBrowseAttachmentFile.addActionListener(SendMailFrame.this::btnBrowseAttachmentFileActionPerformed);
        jLabel6.setText("Content :");

        btnBrowseContentFile.setText("Browse");
        btnBrowseContentFile.addActionListener(SendMailFrame.this::btnBrowseContentFileActionPerformed);
        btnSendMail.setText("Send Mail");
        btnSendMail.addActionListener(SendMailFrame.this::btnSendMailActionPerformed);
        btnClose.setText("Close");
        btnClose.addActionListener(evt -> SendMailFrame.this.btnCloseActionPerformed());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(jLabel1, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(jLabel3, GroupLayout.Alignment.LEADING, -1, 71, 32767)).addComponent(jLabel4, -2, 86, -2).addComponent(jLabel6, -2, 86, -2).addComponent(jLabel5)).addGap(42, 42, 42).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.txtSubject, -2, 219, -2).addComponent(this.txtCCField, -2, 219, -2).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.txtEmailAddressFileName, -2, 219, -2).addComponent(this.txtEmailContentFileName, -2, 219, -2)).addGap(26, 26, 26).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(btnBrowseContentFile).addComponent(btnBrowseEmailIdFile).addComponent(btnBrowseAttachmentFile))).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(layout.createSequentialGroup().addComponent(btnSendMail).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(btnClose)).addComponent(this.txtAttachmentFileName, -2, 216, -2))).addContainerGap(69, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(this.txtEmailAddressFileName, -2, -1, -2).addComponent(btnBrowseEmailIdFile)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(this.txtCCField, -2, -1, -2)).addGap(25, 25, 25).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(this.txtSubject, -2, -1, -2)).addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(this.txtEmailContentFileName, -2, -1, -2).addComponent(btnBrowseContentFile)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.txtAttachmentFileName, -2, -1, -2).addComponent(jLabel5).addComponent(btnBrowseAttachmentFile)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnClose).addComponent(btnSendMail)).addContainerGap()));
        pack();
    }

    private void txtCCFieldActionPerformed(ActionEvent evt) {
    }

    private void btnCloseActionPerformed() {
        System.exit(0);
    }

    private void btnBrowseEmailIdFileActionPerformed(ActionEvent evt) {
        JFileChooser jFileOpenDialogEmailId = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("*.txt", "txt");
        jFileOpenDialogEmailId.addChoosableFileFilter(filter);
        int result = jFileOpenDialogEmailId.showOpenDialog(this);
        if (result == 1) {
            jFileOpenDialogEmailId.setVisible(false);
        } else if (jFileOpenDialogEmailId.getSelectedFile().exists()) {
            String strEmailAddressFileName = jFileOpenDialogEmailId.getSelectedFile().getAbsolutePath();
            this.txtEmailAddressFileName.setText(strEmailAddressFileName);
            System.out.println("[INFO]: File with email id exists");
        } else {
            System.out.println("[INFO]: File with email id DOES NOT exists");
        }
    }

    private void btnBrowseContentFileActionPerformed(ActionEvent evt) {
        JFileChooser jFileOpenDialogEmailContent = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("*.txt", "txt");
        jFileOpenDialogEmailContent.addChoosableFileFilter(filter);
        int result = jFileOpenDialogEmailContent.showOpenDialog(this);
        if (result == 1) {
            jFileOpenDialogEmailContent.setVisible(false);
        } else if (jFileOpenDialogEmailContent.getSelectedFile().exists()) {
            String strEmailContentFileName = jFileOpenDialogEmailContent.getSelectedFile().getAbsolutePath();
            this.txtEmailContentFileName.setText(strEmailContentFileName);
            System.out.println("[INFO]: File with email Content exists");
        } else {
            System.out.println("[INFO]: File with email Content DOES NOT exists");
        }
    }

    private void btnBrowseAttachmentFileActionPerformed(ActionEvent evt) {
        JFileChooser jFileOpenDialogAttachment = new JFileChooser();


        int result = jFileOpenDialogAttachment.showOpenDialog(this);
        if (result == 1) {
            jFileOpenDialogAttachment.setVisible(false);
        } else if (jFileOpenDialogAttachment.getSelectedFile().exists()) {
            String strAttachmentFileName = jFileOpenDialogAttachment.getSelectedFile().getAbsolutePath();
            this.txtAttachmentFileName.setText(strAttachmentFileName);
            System.out.println("[INFO]: Attachment File exists");
        } else {
            System.out.println("[INFO]: Attachment File DOES NOT exists");
        }
    }

    private void btnSendMailActionPerformed(ActionEvent evt) {
        String strEmailAddressFileName = this.txtEmailAddressFileName.getText().trim();
        String strEmailContentFileName = this.txtEmailContentFileName.getText().trim();
        String strCC = this.txtCCField.getText().trim();
        String strSubject = this.txtSubject.getText().trim();
        String strAttachmentFileName = this.txtAttachmentFileName.getText().trim();
        if (strEmailAddressFileName.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Please select the file with Email Address");
        } else if (strSubject.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Please enter the Subject");
        } else if (strEmailContentFileName.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Please select the file with Email Content");
        } else {
            String strResult = SendMail.getEmailAddress(mailConfigFile, strEmailAddressFileName, strEmailContentFileName, strCC, strSubject, strAttachmentFileName);
            if (strResult.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(null, "Mail has been sent Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error Occurred in sending Mail");
            }
        }
    }

    public static void main(String[] args) {
        try {
            String mailConfigFile = "mail-config.properties";
            EventQueue.invokeLater(() -> new SendMailFrame(mailConfigFile).setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}