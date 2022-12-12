package execute;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import view.menuGestore;

public class main2 {
	public static void main(String[] args) throws InterruptedException, IOException, SQLException, MessagingException  {
		menuGestore MG = new menuGestore();
		MG.menu("gestore");
	}
}
