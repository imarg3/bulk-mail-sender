# Bulk Mail Sender Application

This software requires Java to be installed on your machine. So first check whether java is installed or not in your machine using command :

>java -version


## Requirements

You should have proper SMTP configuration in place in order to send
emails. To test your SMTP server working, run command:

>telnet smtp.host.in 25

Change your SMTP hostname instead of *smtp.host.in* & verify port 
*25* as well.

Now, create configuration setting file, eg., config.properties as per your mail server 
and provide proper details below:

- fromMailID (From Email ID)
- host (SMTP Server Host name)
- port (SMTP Server Port)
- delayTimeInSec (Delay Time after sending multiple emails as per sent mail count)
- afterSentMailCount (Total sent mails after application should have to wait)
- username (Valid email id / username for authentication purpose)
- password (Valid password for authentication purpose)

You can set **delayTimeInSec** as per your mail server settings or leave it to 0, if 
you have no idea. Similarly, when you send bulk mails, it is often 
recommended defining mail counts after which application will 
wait. So you can define this mail count **afterSentMailCount**. If you 
do not have any idea, define 30 or 50 depending upon total mails you are 
going to send.

**NOTE :** You should contact your Mail administrator for 
these settings.

## Usage :

Use the interface from your application :

```
public static void main(String[] args) {

    try {
        String mailConfigFile = "config.properties";
        java.awt.EventQueue.invokeLater(() -> new SendMailFrame(mailConfigFile).setVisible(true));
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
```

## Execute application

When you run application on your machine, an interface will be 
popped out, and you need to provide following details :

- TO (mandatory): select file containing valid email ids. All email ids should be comma separated or in new line.
- CC (optional): select file containing valid email ids. All email ids should be comma separated or in new line.
- Subject (mandatory): Email Subject Line
- Content (mandatory): Email Content
- Attachment (optional): Optional Attachment file

Supported File Type : Text file (.txt), Excel & CSV

## Mail Status

When all the emails are sent, you will notify with "Mails Sent successfully" message. 
In case of any errors, kindly refer to output file.

After a successful message, you may either take backup of output.txt or empty the output.txt file content.
