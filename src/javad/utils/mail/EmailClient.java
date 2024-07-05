package javad.utils.mail;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import com.sun.mail.imap.IMAPStore;

public class EmailClient {

	protected IMAPStore imapStore;
	
	public void login(String host, String port, String username, String password) {
		
		Properties props = new Properties();
		props.put("mail.store.protocol", "imaps");
		props.put("mail.imaps.port", port);
		props.put("mail.imaps.starttls.enable", "true");
		
		Session s = Session.getDefaultInstance(props);
		
		
		try {
			Store store = s.getStore("imaps");
			store.connect(host, username, password);
			this.imapStore = (IMAPStore)store;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getMailCount() {
		if(imapStore == null) {
			return 0;
		}
		
		try {
			Folder f = imapStore.getFolder("INBOX");
			f.open(Folder.READ_ONLY);
			return f.getMessageCount();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public Folder getFolder(String folder) {
		try {
			return imapStore.getFolder(folder);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getUnreadedMailCount() {
		if(imapStore == null) {
			return 0;
		}
		
		try {
			Folder f = imapStore.getFolder("INBOX");
			f.open(Folder.READ_ONLY);
			return f.getUnreadMessageCount();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public Message[] getUnreadedMails() {
		if(imapStore == null) {
			return null;
		}
		
		try {
			Folder f = imapStore.getFolder("INBOX");
			f.open(Folder.READ_ONLY);
			Message[] msg = f.getMessages();
			for(Message m : msg) {
				System.out.println(m.getSubject());
				System.out.println(m.getFrom()[0]);
				try {
					System.out.println(m.getContent());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return msg;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Message[] getMessages() {
		
		if(imapStore == null) {
			return null;
		}
		
		try {
			Folder f = imapStore.getFolder("INBOX");
			f.open(Folder.HOLDS_FOLDERS);
			f.open(Folder.READ_WRITE);
			return f.getMessages();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void close() {
		try {
			imapStore.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
