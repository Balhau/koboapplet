import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.exceptions.KoboSQLException;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.sql.KoboSQLite;


public class tests {
	public static void main(String[] args) throws IOException, ClassNotFoundException, KoboSQLException{
		 List<String> ldv=DeviceUtils.getDevicesInfo();
		 IKoboDatabase koboDatabase=new KoboSQLite(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
		 List<String> books=koboDatabase.getAllBookNames();
		 for(String book: books) System.out.println(book);
		 
		 List<String> rBooks=koboDatabase.getReadingBookNames();
		 for(String book: rBooks) System.out.println(book);
	}
}
