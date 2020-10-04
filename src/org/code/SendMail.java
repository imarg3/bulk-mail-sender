package org.code;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
// import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.*;

public class SendMail
{
  static int nWaitTime = 1;
  static int nAfterSentMailCount = 1;
  static String strFrom = "";
  static String strHostIPAddress = "";
  static String username = "";
  static String password = "";
  static int port = 25;
  
  public static String getEmailAddress(String strEmailAddrFileName, String strEmailContentFileName, String strCC, String strSubject, String strAttachmentFileName)
  {
    String strEmailId = "";
    String strEmailContent = "";
    String strEmailContent1 = "";
    String strResult = "";
    int nCounter = 0;
    int nWaitCounter = 0;
   
    Properties props = new Properties();
    try
    {
      props.load(new FileInputStream("config.properties"));
      if (props.getProperty("fromMailID") != null)
      {
        strFrom =  props.getProperty("fromMailID");
        strHostIPAddress = props.getProperty("host");
        nWaitTime = Integer.parseInt(props.getProperty("delayTimeInSec")) * 1000;
        nAfterSentMailCount = Integer.parseInt(props.getProperty("afterSentMailCount"));
        username = props.getProperty("username");
        password = props.getProperty("password");
        port = Integer.valueOf(props.getProperty("port"));
      }
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    try
    {
      System.out.println("[INFO] : File Name is: " + strEmailAddrFileName);
      System.out.println("[INFO] : Email Content File Name is: " + strEmailContentFileName);
      System.out.println("[INFO] : CC Email ID in getEmailAddress()==" + strCC);
      
      FileInputStream fstream = new FileInputStream(strEmailAddrFileName);
      DataInputStream in = new DataInputStream(fstream);  
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      FileInputStream fstreamEmailContent = new FileInputStream(strEmailContentFileName);
      DataInputStream inEmailContent = new DataInputStream(fstreamEmailContent);
      BufferedReader brEmailContent = new BufferedReader(new InputStreamReader(inEmailContent));
      while ((strEmailContent = brEmailContent.readLine()) != null) {
        strEmailContent1 = strEmailContent1 + strEmailContent + "\n";
      }
      while ((strEmailId = br.readLine()) != null)
      {
        strEmailId = strEmailId.trim();
        System.out.println("Email ID is to send mail to is  "+strEmailId);       
        
        if (strEmailId.equalsIgnoreCase(""))
        {
          System.out.println("[INFO] : Email Id is blank or INVALID: Hence no mail Sent." + strEmailId);
        }
        else if (strEmailId.indexOf(",") == -1)
        {
          if ((strEmailId.indexOf('@') == -1) || (strEmailId.indexOf('.') == -1))
          {
            System.out.println("[INFO] : Email Id is INVALID. No '@': Hence no mail Sent." + strEmailId);
          }
          else if (strAttachmentFileName.equalsIgnoreCase(""))
          {
        	// strEmailId = MailUtils.getPunyCodeValue(strEmailId);
        	// System.out.println("Punycode "+strEmailId);
        	         	 
            strResult = sendMail(strEmailId, strEmailContent1, strCC, strSubject);
            System.out.println("[INFO] :SendMail() called....Count=" + ++nCounter);
            nWaitCounter++;
            if (nWaitCounter == nAfterSentMailCount)
            {
              System.out.println("Application is currently waiting for " + nWaitTime + " seconds ............." + new Date());
              Thread.sleep(nWaitTime);
              System.out.println("Application Resumed after wait state............." + new Date());
              nWaitCounter = 0;
            }
          }
          else
          {
            System.out.println("[INFO] :SendMailWithAttachment() called....Count=" + ++nCounter);
            strResult = sendMailWithAttachment(strEmailId, strEmailContent1, strCC, strSubject, strAttachmentFileName);
            nWaitCounter++;
            if (nWaitCounter == nAfterSentMailCount)
            {
              System.out.println("Application is currently waiting for " + nWaitTime + " seconds ............." + new Date());
              Thread.sleep(nWaitTime);
              System.out.println("Application Resumed after wait state............." + new Date());
              nWaitCounter = 0;
            }
          }
        }
        else
        {
          String[] arrEmailIds = strEmailId.split(",");
          for (int i = 0; i < arrEmailIds.length; i++) {
            if ((arrEmailIds[i].indexOf('@') == -1) || (arrEmailIds[i].indexOf('.') == -1))
            {
              System.out.println("[INFO] : Email Id is INVALID. No '@': Hence no mail Sent." + arrEmailIds[i]);
            }
            else if (strAttachmentFileName.equalsIgnoreCase(""))
            {
              System.out.println("[INFO] :SendMail() called....Count=" + ++nCounter);
              // arrEmailIds[i] = MailUtils.getPunyCodeValue(arrEmailIds[i]);              
              // System.out.println("PunyCode Value is "+arrEmailIds[i]);
              strResult = sendMail(arrEmailIds[i], strEmailContent1, strCC, strSubject);
              nWaitCounter++;
              if (nWaitCounter == nAfterSentMailCount)
              {
                System.out.println("Application is currently waiting for " + nWaitTime + " seconds ............." + new Date());
                Thread.sleep(nWaitTime);
                System.out.println("Application Resumed after wait state............." + new Date());
                nWaitCounter = 0;
              }
            }
            else
            {
              System.out.println("[INFO] :SendMailWithAttachment() called....Count=" + ++nCounter);
              strResult = sendMailWithAttachment(arrEmailIds[i], strEmailContent1, strCC, strSubject, strAttachmentFileName);
              nWaitCounter++;
              if (nWaitCounter == nAfterSentMailCount)
              {
                System.out.println("Application is currently waiting for " + nWaitTime + " seconds ............." + new Date());
                Thread.sleep(nWaitTime);
                System.out.println("Application Resumed after wait state............." + new Date());
                nWaitCounter = 0;
              }
            }
          }
        }
      }
      System.out.println("Mails Sent successfully....!!");
      
      fstream.close();
      br.close();
      in.close();
      fstreamEmailContent.close();
      brEmailContent.close();
      inEmailContent.close();
    }
    catch (Exception e)
    {
      strResult = "failure";
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    return strResult;
  }
  
  public static String sendMail(String strEmailId, String strEmailContent, String strCC, String strSubject){
    System.out.println("fromMailID= " + strFrom);
    System.out.println("host=" + strHostIPAddress);
    String strResult = "";
    String to = strEmailId;
    String from = strFrom;
    String host = strHostIPAddress;

    String subject = strSubject;
    String msgText1 = strEmailContent;

    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
	props.put("mail.transport.protocol", "smtp" );
    props.put("mail.smtp.starttls.enable","true" );
    //props.put("mail.smtp.host","smtp.test.in"); // smtp.test.in
    //props.put("mail.smtp.ssl.trust", "smtp.test.in"); // smtp.test.in
    props.put("mail.smtp.auth", "true" ); // true
    props.put("mail.smtp.port",port); // smtp port
    props.put("mail.debug", "true");
	
    Session session = Session.getInstance(props,    	
    		new Authenticator() {
            	protected PasswordAuthentication getPasswordAuthentication(){
            		return new PasswordAuthentication(username, password);
            	}
            } );
    try
    {
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      
      InternetAddress[] address = InternetAddress.parse(to);
      msg.setRecipients(Message.RecipientType.TO, address);
      
      if (!strCC.equalsIgnoreCase(""))
      {
        InternetAddress[] addressCC = InternetAddress.parse(strCC);
        msg.setRecipients(Message.RecipientType.CC, addressCC);
      }
      msg.setSubject(subject);
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(msgText1);
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      
      msg.setContent(mp, "utf-8");
      msg.setSentDate(new Date());
      Transport.send(msg);
      strResult = "success";
    }
    catch (MessagingException mEx)
    {
      System.out.println(mEx.getMessage());
      strResult = "failure";
      mEx.printStackTrace();
      Exception ex = null;
      if ((ex = mEx.getNextException()) != null)
      {
        ex.printStackTrace();
        strResult = "failure";
      }
    }
    return strResult;
  }
  
  public static String sendMailWithAttachment(String strEmailId, String strEmailContent, String strCC, String strSubject, String strAttachmentFileName)
  {
    String strResult = "";
    String to = strEmailId;
    String from = strFrom;
    String host = strHostIPAddress;
    
	System.out.println("username is "+username+ "  and password is "+password );
    String subject = strSubject;
    String msgText1 = strEmailContent;
    String strFileName = strAttachmentFileName;
    
    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
	props.put("mail.transport.protocol", "smtp" );
    props.put("mail.smtp.starttls.enable","true" );
    //props.put("mail.smtp.host","smtp.test.in");
    //props.put("mail.smtp.ssl.trust", "smtp.test.in");
    props.put("mail.smtp.auth", "true" );
    props.put("mail.smtp.port",port);
    
    Session session = Session.getInstance(props, new Authenticator() 
    {
    	protected PasswordAuthentication getPasswordAuthentication()
    	{
    		return new PasswordAuthentication(username, password);
    	}
    });
    try
    {
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      
      InternetAddress[] addressTo = InternetAddress.parse(to);
      msg.setRecipients(Message.RecipientType.TO, addressTo);
      if (!strCC.equalsIgnoreCase(""))
      {
        InternetAddress[] addressCC = InternetAddress.parse(strCC);
        msg.setRecipients(Message.RecipientType.CC, addressCC);
      }
      msg.setSubject(subject);
      
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(msgText1, "utf-8");
      
      MimeBodyPart mbp2 = new MimeBodyPart();
      

      FileDataSource fds = new FileDataSource(strFileName);
      mbp2.setDataHandler(new DataHandler(fds));
      mbp2.setFileName(fds.getName());
      
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);
      
      msg.setContent(mp);
      msg.setSentDate(new Date());
      Transport.send(msg);
      strResult = "success";
    }
    catch (MessagingException mEx)
    {
      System.out.println(mEx.getMessage());
      strResult = "failure";
      mEx.printStackTrace();
      Exception ex = null;
      if ((ex = mEx.getNextException()) != null)
      {
        strResult = "failure";
        ex.printStackTrace();
      }
    }
    return strResult;
  }
}
