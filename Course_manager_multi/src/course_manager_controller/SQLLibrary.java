package course_manager_controller;

import java.sql.Connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class SQLLibrary {

	public Connection SQLOpenConnection(String strServerName, String strUser, String strPassWord, String strDBName,
			int iPortNumber) {

		Connection objReturn = null;

		SQLServerDataSource objData = new SQLServerDataSource();
		objData.setServerName(strServerName);
		objData.setUser(strUser);
		objData.setPassword(strPassWord);
		objData.setDatabaseName(strDBName);
		objData.setPortNumber(iPortNumber);

		try {
			objReturn = objData.getConnection();

		} catch (SQLServerException e) {

			e.printStackTrace();
		}

		return objReturn;
	}

}
