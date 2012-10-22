package br.com.uol.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SQLAppender {

	private static boolean debugMode = false;

	public static void main(String... args) {
		String prop;
		debugMode = (prop=System.getProperty("debugMode")) != null && "true".equalsIgnoreCase(prop);
		if(args.length < 5){
			System.out.println("[USAGE] <jdbc-driver-class> <jdbc-url-connection> <user> <password> <sql-script-file> ...");
			return;
		}
		System.out.println(String.format("[INFO] got driver %s, connection %s user %s psswd %s", args[0], args[1], args[2], args[3]));
		int i = 4;
		try {
			Class.forName(args[0]);
			Connection connection = DriverManager.getConnection(args[1], args[2], args[3]);
			for(; i < args.length; i++){
				createSQLBatch(connection, args[i]);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] verify if you typed the right JDBC driver. [0]");
		} catch (SQLException e) {
			System.out.println(String.format("[ERROR] it was not possible to stablish a JDBC connection through url \"%s\", user \"%s\" and password \"%s\".", args[1], args[2], args[3]));
		} catch (FileNotFoundException e) {
			System.out.println(String.format("[ERROR] it was not possible to find file with name %s. [%d]", args[i], i));
		} catch (IOException e) {
			System.out.println(String.format("[ERROR] it was not possible to load file with name %s, due %s. [%d]", args[i], i));
		}
	}

	private static void createSQLBatch(Connection connection, String fileName) throws IOException, SQLException {
		connection.setAutoCommit(false);
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		StringBuilder buffer = new StringBuilder();
		Statement statement;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(String.format("[ERROR] It was not possible to execute %s file due %s", fileName, e.getLocalizedMessage()));
			return;
		}
		while((line=reader.readLine()) != null){
			line = new String(line.getBytes(), Charset.forName("UTF-8"));
			if(debugMode){
				System.out.println(String.format("[INFO] read line: %s", line));
			}
			line = line.trim();
			if(line.startsWith("--") || line.startsWith("//")){
				System.out.println(line);
				continue;
			}
			try{
				if("GO".equalsIgnoreCase(line)){
					statement.addBatch(buffer.toString().trim());
					if(debugMode){
						System.out.println(String.format("[INFO] Adding to batch: %s", buffer.toString().trim()));
					}
					buffer = new StringBuilder();
				} else if(line.endsWith(";")){
					buffer.append(line.substring(0, line.length() - 1));
					statement.addBatch(buffer.toString());
					if(debugMode){
						System.out.println(String.format("[INFO] Adding to batch: %s", buffer.toString()));
					}
					buffer = new StringBuilder();
				} else{
					buffer.append(line.concat(" "));
				}
			} catch(SQLException e){
				System.out.println(String.format("[ERROR] It was not possible to execute the SQL statement (%s) due %s", fileName, e.getLocalizedMessage()));
			}
		}
		if(buffer.length() > 0){
			statement.addBatch(buffer.toString());
		}
		process(statement, fileName);
		connection.commit();
	}

	private static void process(final Statement statement, final String fileName) {
		try {
			if(debugMode){
				System.out.println("[INFO] Executing batch... ");
			}
			int[] result = statement.executeBatch();
			if(debugMode){
				System.out.println(String.format("[INFO] RESULT: %s.", result == null? "null": Arrays.toString(result)));
			}
		} catch (SQLException e) {
			System.out.println(String.format("[ERROR] It was not possible to execute the SQL statement (%s) due %s", fileName, e.getLocalizedMessage()));
		}
	}
}