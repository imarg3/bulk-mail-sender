package org.cdac.gist;

//import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.PrintStream;

import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.GroupLayout.ParallelGroup;
//import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
//import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author arpit
 * SendMailFrame Class extends JFrame
 */
public class SendMailFrame
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  private JButton btnBrowseAttachmentFile;
  private JButton btnBrowseContentFile;
  private JButton btnBrowseEmailIdFile;
  private JButton btnClose;
  private JButton btnSendMail;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JTextField txtAttachmentFileName;
  private JTextField txtCCField;
  private JTextField txtEmailAddrFileName;
  private JTextField txtEmailContentFileName;
  private JTextField txtSubject;
  
  public SendMailFrame()
  {
    initComponents();
  }
  
  private void initComponents()
  {
    this.jLabel3 = new JLabel();
    this.jLabel4 = new JLabel();
    this.jLabel5 = new JLabel();
    this.jLabel1 = new JLabel();
    this.txtEmailAddrFileName = new JTextField();
    this.btnBrowseEmailIdFile = new JButton();
    this.txtCCField = new JTextField();
    this.txtSubject = new JTextField();
    this.txtAttachmentFileName = new JTextField();
    this.btnBrowseAttachmentFile = new JButton();
    this.jLabel6 = new JLabel();
    this.txtEmailContentFileName = new JTextField();
    this.btnBrowseContentFile = new JButton();
    this.btnSendMail = new JButton();
    this.btnClose = new JButton();
    
    setDefaultCloseOperation(3);
    
    this.jLabel3.setText("CC : ");
    
    this.jLabel4.setText("Subject : ");
    
    this.jLabel5.setText("Attachment :");
    
    this.jLabel1.setText("TO:");
    
    this.btnBrowseEmailIdFile.setText("Browse");
    this.btnBrowseEmailIdFile.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SendMailFrame.this.btnBrowseEmailIdFileActionPerformed(evt);
      }
    });
    this.txtCCField.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SendMailFrame.this.txtCCFieldActionPerformed(evt);
      }
    });
    this.btnBrowseAttachmentFile.setText("Browse");
    this.btnBrowseAttachmentFile.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SendMailFrame.this.btnBrowseAttachmentFileActionPerformed(evt);
      }
    });
    this.jLabel6.setText("Content :");
    
    this.btnBrowseContentFile.setText("Browse");
    this.btnBrowseContentFile.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SendMailFrame.this.btnBrowseContentFileActionPerformed(evt);
      }
    });
    this.btnSendMail.setText("Send Mail");
    this.btnSendMail.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SendMailFrame.this.btnSendMailActionPerformed(evt);
      }
    });
    this.btnClose.setText("Close");
    this.btnClose.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SendMailFrame.this.btnCloseActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel3, GroupLayout.Alignment.LEADING, -1, 71, 32767)).addComponent(this.jLabel4, -2, 86, -2).addComponent(this.jLabel6, -2, 86, -2).addComponent(this.jLabel5)).addGap(42, 42, 42).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.txtSubject, -2, 219, -2).addComponent(this.txtCCField, -2, 219, -2).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.txtEmailAddrFileName, -2, 219, -2).addComponent(this.txtEmailContentFileName, -2, 219, -2)).addGap(26, 26, 26).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.btnBrowseContentFile).addComponent(this.btnBrowseEmailIdFile).addComponent(this.btnBrowseAttachmentFile))).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(layout.createSequentialGroup().addComponent(this.btnSendMail).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.btnClose)).addComponent(this.txtAttachmentFileName, -2, 216, -2))).addContainerGap(69, 32767)));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.txtEmailAddrFileName, -2, -1, -2).addComponent(this.btnBrowseEmailIdFile)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.txtCCField, -2, -1, -2)).addGap(25, 25, 25).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.txtSubject, -2, -1, -2)).addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6).addComponent(this.txtEmailContentFileName, -2, -1, -2).addComponent(this.btnBrowseContentFile)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.txtAttachmentFileName, -2, -1, -2).addComponent(this.jLabel5).addComponent(this.btnBrowseAttachmentFile)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.btnClose).addComponent(this.btnSendMail)).addContainerGap()));
    pack();
  }
  
  private void txtCCFieldActionPerformed(ActionEvent evt) {}
  
  private void btnCloseActionPerformed(ActionEvent evt)
  {
    System.exit(0);
  }
  
  private void btnBrowseEmailIdFileActionPerformed(ActionEvent evt)
  {
    JFileChooser jFileOpenDialogEmailId = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("*.txt", new String[] { "txt" });
    jFileOpenDialogEmailId.addChoosableFileFilter(filter);
    int result = jFileOpenDialogEmailId.showOpenDialog(this);
    if (result == 1)
    {
      jFileOpenDialogEmailId.setVisible(false);
    }
    else if (jFileOpenDialogEmailId.getSelectedFile().exists())
    {
      String strEmailAddrFileName = jFileOpenDialogEmailId.getSelectedFile().getAbsolutePath();
      this.txtEmailAddrFileName.setText(strEmailAddrFileName);
      System.out.println("[INFO]: File with email id exists");
    }
    else
    {
      System.out.println("[INFO]: File with email id DOES NOT exists");
    }
  }
  
  private void btnBrowseContentFileActionPerformed(ActionEvent evt)
  {
    JFileChooser jFileOpenDialogEmailContent = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("*.txt", new String[] { "txt" });
    jFileOpenDialogEmailContent.addChoosableFileFilter(filter);
    int result = jFileOpenDialogEmailContent.showOpenDialog(this);
    if (result == 1)
    {
      jFileOpenDialogEmailContent.setVisible(false);
    }
    else if (jFileOpenDialogEmailContent.getSelectedFile().exists())
    {
      String strEmailContentFileName = jFileOpenDialogEmailContent.getSelectedFile().getAbsolutePath();
      this.txtEmailContentFileName.setText(strEmailContentFileName);
      System.out.println("[INFO]: File with email Content exists");
    }
    else
    {
      System.out.println("[INFO]: File with email Content DOES NOT exists");
    }
  }
  
  private void btnBrowseAttachmentFileActionPerformed(ActionEvent evt)
  {
    JFileChooser jFileOpenDialogAttachment = new JFileChooser();
    

    int result = jFileOpenDialogAttachment.showOpenDialog(this);
    if (result == 1)
    {
      jFileOpenDialogAttachment.setVisible(false);
    }
    else if (jFileOpenDialogAttachment.getSelectedFile().exists())
    {
      String strAttachmentFileName = jFileOpenDialogAttachment.getSelectedFile().getAbsolutePath();
      this.txtAttachmentFileName.setText(strAttachmentFileName);
      System.out.println("[INFO]: Attachment File exists");
    }
    else
    {
      System.out.println("[INFO]: Attachment File DOES NOT exists");
    }
  }
  
  private void btnSendMailActionPerformed(ActionEvent evt)
  {
    String strEmailAddrFileName = this.txtEmailAddrFileName.getText().trim();
    String strEmailContentFileName = this.txtEmailContentFileName.getText().trim();
    String strCC = this.txtCCField.getText().trim();
    String strSubject = this.txtSubject.getText().trim();
    String strAttachmentFileName = this.txtAttachmentFileName.getText().trim();
    if (strEmailAddrFileName.equalsIgnoreCase(""))
    {
      JOptionPane.showMessageDialog(null, "Please select the file with Email Address");
    }
    else if (strSubject.equalsIgnoreCase(""))
    {
      JOptionPane.showMessageDialog(null, "Please enter the Subject");
    }
    else if (strEmailContentFileName.equalsIgnoreCase(""))
    {
      JOptionPane.showMessageDialog(null, "Please select the file with Email Content");
    }
    else
    {
      String strResult = SendMail.getEmailAddress(strEmailAddrFileName, strEmailContentFileName, strCC, strSubject, strAttachmentFileName);
      if (strResult.equalsIgnoreCase("success")) {
        JOptionPane.showMessageDialog(null, "Mail has been sent Successfully");
      } else {
        JOptionPane.showMessageDialog(null, "Error Occurred in sending Mail");
      }
    }
  }
  
  public static void main(String[] args)
  {
    try
    {
      EventQueue.invokeLater(new Runnable()
      {
        public void run()
        {
          new SendMailFrame().setVisible(true);
        }
      });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}