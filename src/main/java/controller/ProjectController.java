/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author lohan
 */
public class ProjectController {
    
    /**
     *
     * @param project
     */
    public void save (Project project){
        
        String sql = "INSERT INTO projects (name, "
                +"description,"
                +"createdAt"
                +"updateAt) VALUES(?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //Cria uma conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            //Cria um PreparedStatement, classe usada para executar a query
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "
                  + ex.getMessage(),ex);
        } finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    
    }
          
   
    public void update(Project project) throws SQLException{
        String sql = "UPDATE project SET "
                +"name = ?, "
                +"description = ?, "
                +"createdAt = ?, "
                +"updatedAt = ?, "
                +"WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
             //Cria uma conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Cria um PreparedStatement, classe usada para executar a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());
            
            //Executndo a query
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
   
    public void removeByid(int IdProject) throws SQLException{
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        
        try {
            
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setndos os valores
            statement.setInt(1, IdProject);
            
            //Executndo a query
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar uma tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
            
      
  }
    
    @SuppressWarnings("empty-statement")
    
    //Lista de tarefas que será devolvida quando a chamada do método acontecer
    public List<Project> getAll() {
        
        String sql = "SELECT * FROM projects";
       
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //Valor retornando pela a exeução da query
            ResultSet resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos no meu resultSet
            while(resultSet.next()){
            
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updated"));
                
                projects.add(project);
            }
            
        } catch (SQLException e) {
            try {
                throw new SQLException("Erro ao inserir a tarefa");
            } catch (SQLException ex) {
                Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            ResultSet resultSet = null;
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return null;
       
 
    }

    public List<Project> getALL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
    
