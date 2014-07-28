package br.com.schimidtsolutions.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teste {

	 public static void main(final String[] args){
		 try
	        {
	            Class.forName("org.h2.Driver");
	            final Connection con = DriverManager.getConnection("jdbc:h2:~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE", "sa", "" );
	            //final Connection con = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE", "sa", "sa" );
	            
	            final Statement stmt = con.createStatement();
	            stmt.executeUpdate( "create user IF NOT EXISTS DEVELOPMENT password 'segfor47' admin" );
	            stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS DEVELOPMENT" );
	            stmt.executeUpdate( "CREATE SEQUENCE IF NOT EXISTS DEVELOPMENT.CLIENTE_SEQ START WITH 1 INCREMENT BY 1" );
	            stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS DEVELOPMENT.CLIENTE(ID INT PRIMARY KEY, NOME VARCHAR(50))" );
	            stmt.executeUpdate( "INSERT INTO DEVELOPMENT.CLIENTE ( id, nome ) VALUES ( DEVELOPMENT.CLIENTE_SEQ.NEXTVAL, 'Claudio' )" );
	            stmt.executeUpdate( "INSERT INTO DEVELOPMENT.CLIENTE (  id, nome  ) VALUES ( DEVELOPMENT.CLIENTE_SEQ.NEXTVAL, 'Bernasconi' )" );
	 
	            final ResultSet rs = stmt.executeQuery("SELECT * FROM DEVELOPMENT.CLIENTE");
	            while( rs.next() )
	            {
	                final String name = rs.getString("nome");
	                System.out.println( name );
	            }
	            stmt.close();
	            con.close();
	        }
	        catch( final Exception e )
	        {
	            System.out.println( e );
	        }
	 }
}
