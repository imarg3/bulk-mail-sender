# This software requires Java to be installed on your machine. So first check whether java is installed or not in your machine using command :

>java -version

# Change configuration setting in config.properties as per your mail server :
- fromMailID (From Email ID)
- host (SMTP Server Host name)
- port (SMTP Server Port)
- delayTimeInSec (Delay Time after sending multiple emails as per sent mail count)
- afterSentMailCount (Total sent mails after application should have to wait)
- username (Valid email id / username for authentication purpose)
- password (Valid password for authentication purpose)

# Execute application

To run application on your machine, execute run.bat file on Windows machine.

An interface will be popped out and you need to provide following details :

- TO (mandatory): select file containing valid email ids. All email ids should be comma separated or in new line.
- CC (optional): select file containing valid email ids. All email ids should be comma separated or in new line.
- Subject (mandatory): Email Subject Line
- Content (mandatory): Email Content
- Attachment (optional): Optional Attachment file

Supported File Type : Text file (.txt), Excel & CSV

# Mail Status

When all the emails are sent, you will notified with "Mails Sent successfully" message. In case of any errors, kindly refer to output file.

After successful message, you may either take backup of output.txt or empty the output.txt file content.
