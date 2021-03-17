package com.arpitram;

import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.function.Supplier;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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
  private static String strFrom = "";
  private static String strHostIPAddress = "";
  private static String username = "";
  private static String password = "";
  private static int port = 25;

  public static String getEmailAddress(String configFile, String strEmailAddrFileName, String strEmailContentFileName, String strCC, String strSubject, String strAttachmentFileName)
  {
    String strEmailId;
    String strEmailContent;
    StringBuilder strEmailContent1 = new StringBuilder();
    String strResult = "";
    int nCounter = 0;
    int nWaitCounter = 0;

    setUpMailConfiguration(configFile);

    try
    {
      System.out.println("[INFO] : File Name is: " + strEmailAddrFileName);
      System.out.println("[INFO] : Email Content File Name is: " + strEmailContentFileName);
      System.out.println("[INFO] : CC Email ID in getEmailAddress()==" + strCC);

      Supplier<Date> dateSupplier = Date::new;
      FileInputStream fstream = getFileInputStream(strEmailAddrFileName);
      DataInputStream in = getDataInputStream(fstream);
      BufferedReader br = getBufferedReader(in);

      FileInputStream fstreamEmailContent = getFileInputStream(strEmailContentFileName);
      DataInputStream inEmailContent = getDataInputStream(fstreamEmailContent);
      BufferedReader brEmailContent = getBufferedReader(inEmailContent);
      while ((strEmailContent = brEmailContent.readLine()) != null) {
        strEmailContent1.append(strEmailContent).append("\n");
      }
      while ((strEmailId = br.readLine()) != null)
      {
        strEmailId = strEmailId.trim();
        System.out.println("Email ID is to send mail to is  "+strEmailId);       
        
        if (strEmailId.equalsIgnoreCase(""))
        {
          System.out.println("[INFO] : Email Id is blank or INVALID: Hence no mail Sent." + strEmailId);
        }
        else if (!strEmailId.contains(","))
        {
          if ((strEmailId.indexOf('@') == -1) || (strEmailId.indexOf('.') == -1))
          {
            System.out.println("[INFO] : Email Id is INVALID. No '@': Hence no mail Sent." + strEmailId);
          }
          else if (strAttachmentFileName.equalsIgnoreCase(""))
          {
            strResult = sendMail(strEmailId, strEmailContent1.toString(), strCC, strSubject);
            System.out.println("[INFO] :SendMail() called....Count=" + ++nCounter);
            nWaitCounter++;
            if (nWaitCounter == nAfterSentMailCount)
            {
              System.out.println("Application is currently waiting for " + nWaitTime + " seconds ............." + dateSupplier.get());
              Thread.sleep(nWaitTime);
              System.out.println("Application Resumed after wait state............." + dateSupplier.get());
              nWaitCounter = 0;
            }
          }
          else
          {
            System.out.println("[INFO] :SendMailWithAttachment() called....Count=" + ++nCounter);
            strResult = sendMailWithAttachment(strEmailId, strEmailContent1.toString(), strCC, strSubject, strAttachmentFileName);
            nWaitCounter++;
            if (nWaitCounter == nAfterSentMailCount)
            {
              System.out.println("Application is currently waiting for " + nWaitTime + " seconds ............." + dateSupplier.get());
              Thread.sleep(nWaitTime);
              System.out.println("Application Resumed after wait state............." + dateSupplier.get());
              nWaitCounter = 0;
            }
          }
        }
        else
        {
          String[] arrEmailIds = strEmailId.split(",");
          for (String arrEmailId : arrEmailIds) {
            if ((arrEmailId.indexOf('@') == -1) || (arrEmailId.indexOf('.') == -1)) {
              System.out.println("[INFO] : Email Id is INVALID. No '@': Hence no mail Sent." + arrEmailId);
            } else if (strAttachmentFileName.equalsIgnoreCase("")) {
              System.out.println("[INFO] :SendMail() called....Count=" + ++nCounter);
              strResult = sendMail(arrEmailId, strEmailContent1.toString(), strCC, strSubject);
              nWaitCounter++;
              if (nWaitCounter == nAfterSentMailCount) {
                System.out.println("Application is currently waiting for " + nWaitTime + " seconds ............." + new Date());
                Thread.sleep(nWaitTime);
                System.out.println("Application Resumed after wait state............." + new Date());
                nWaitCounter = 0;
              }
            } else {
              System.out.println("[INFO] :SendMailWithAttachment() called....Count=" + ++nCounter);
              strResult = sendMailWithAttachment(arrEmailId, strEmailContent1.toString(), strCC, strSubject, strAttachmentFileName);
              nWaitCounter++;
              if (nWaitCounter == nAfterSentMailCount) {
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

  private static void setUpMailConfiguration(String configFile) {
    Properties props = new Properties();
    try
    {
      props.load(getFileInputStream(configFile));
      if (validateSMTPHostNullCheck(props))
      {
        strFrom =  props.getProperty("fromMailID");
        strHostIPAddress = props.getProperty("host");
        nWaitTime = Integer.parseInt(props.getProperty("delayTimeInSec")) * 1000;
        nAfterSentMailCount = Integer.parseInt(props.getProperty("afterSentMailCount"));
        username = props.getProperty("username");
        password = props.getProperty("password");
        port = Integer.parseInt(props.getProperty("port"));
      } else {
        throw new IllegalArgumentException("Please pass valid SMTP hostname!");
      }
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  private static boolean validateSMTPHostNullCheck(Properties props) {
    return props.getProperty("fromMailID") != null && props.getProperty("host") != null;
  }

  private static FileInputStream getFileInputStream(String strEmailAddrFileName) throws FileNotFoundException {
    return new FileInputStream(strEmailAddrFileName);
  }

  private static DataInputStream getDataInputStream(FileInputStream fstream) {
    return new DataInputStream(fstream);
  }

  private static BufferedReader getBufferedReader(DataInputStream in) {
    return new BufferedReader(new InputStreamReader(in));
  }

  private static String sendMail(String strEmailId, String strEmailContent, String strCC, String strSubject){
    System.out.println("fromMailID= " + strFrom);
    System.out.println("host=" + strHostIPAddress);
    String strResult;
    String from = strFrom;
    String host = strHostIPAddress;

    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
	props.put("mail.transport.protocol", "smtp" );
    props.put("mail.smtp.starttls.enable","true" );
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
      
      InternetAddress[] address = InternetAddress.parse(strEmailId);
      msg.setRecipients(Message.RecipientType.TO, address);
      
      if (!strCC.equalsIgnoreCase(""))
      {
        InternetAddress[] addressCC = InternetAddress.parse(strCC);
        msg.setRecipients(Message.RecipientType.CC, addressCC);
      }
      msg.setSubject(strSubject);
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(strEmailContent);
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
      Exception ex;
      if ((ex = mEx.getNextException()) != null)
      {
        ex.printStackTrace();
        strResult = "failure";
      }
    }
    return strResult;
  }

  private static String sendMailWithAttachment(String strEmailId, String strEmailContent, String strCC, String strSubject, String strAttachmentFileName)
  {
    String strResult;
    String from = strFrom;
    String host = strHostIPAddress;
    
	System.out.println("username is "+username+ "  and password is "+password );

    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
	props.put("mail.transport.protocol", "smtp" );
    props.put("mail.smtp.starttls.enable","true" );
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
      
      InternetAddress[] addressTo = InternetAddress.parse(strEmailId);
      msg.setRecipients(Message.RecipientType.TO, addressTo);
      if (!strCC.equalsIgnoreCase(""))
      {
        InternetAddress[] addressCC = InternetAddress.parse(strCC);
        msg.setRecipients(Message.RecipientType.CC, addressCC);
      }
      msg.setSubject(strSubject);
      
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(strEmailContent, "utf-8");
      
      MimeBodyPart mbp2 = new MimeBodyPart();
      

      FileDataSource fds = new FileDataSource(strAttachmentFileName);
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
      Exception ex;
      if ((ex = mEx.getNextException()) != null)
      {
        strResult = "failure";
        ex.printStackTrace();
      }
    }
    return strResult;
  }
}
