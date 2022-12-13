package execute;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import view.menuFacade;
import view.menuGestore;

public class main2 {
	public static void main(String[] args) throws InterruptedException, IOException, SQLException, MessagingException  {
		menuFacade mF = new menuFacade();
		try {
			mF.menu();
		}
		catch (Exception e) {
			System.out.println("Generic error");
		}
	}
}
