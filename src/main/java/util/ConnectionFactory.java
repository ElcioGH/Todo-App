/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import static util.ConnectionFactory.DRIVER;
import static util.ConnectionFactory.PASS;
import static util.ConnectionFactory.URL;
import static util.ConnectionFactory.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lohan
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/TodoApp";
    public static final String USER = "root";
    public static final String PASS = "";

   public static Connection getConnection() throws SQLException{
          
          try {
              Class.forName(DRIVER);
              return DriverManager.getConnection(URL, USER, PASS);
              
          } catch (ClassNotFoundException | SQLException ex){
              throw new RuntimeException("Erro na conexão com o banco de dados", ex);
              
          }
      }
   
      
      public static void closeConnection(Connection connection, PreparedStatement statement){
         
          try {
              if (connection != null){
                  connection.close();
              }
          } catch (SQLException ex){
              throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
          }
      }
      

      public static void closeConnection(Connection connection,
              PreparedStatement statement, ResultSet resultSet){
         
          try {
              if (connection != null){
                  connection.close();
              }
              
              if(statement != null){
                  statement.close();
                  
              }
              
              if(resultSet != null){
                  resultSet.close();
                  
              }
          } catch (SQLException ex){
              throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
          }
      }

    private ConnectionFactory() {
    }

    private static class resultet {

        public resultet() {
        }
    }
}
    
