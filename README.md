# Retrieve Email content from Script

How to Use:

```
MailReader mailreader=new MailReader("gmail account", "password");

if(mailreader.messages.length>0)
{
  Message msg = mailreader.messages[0];
  String content = mailreader.getContent(msg);
}

```
