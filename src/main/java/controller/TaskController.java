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
import model.Task;
import util.ConnectionFactory;



public class TaskController {

   
    public void save (Task task){
        
        String sql = "INSERT INTO tasks (idProject"
                +"name,"
                +"description,"
                +"completed,"
                +"notes,"
                +"deadline,"
                +"createdAt"
                +"updateAt) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        @SuppressWarnings("UnusedAssignment")
        Connection connection = null;
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(
                    1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "
                  + ex.getMessage(),ex);
        } finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    
    }
          
   
    public void update(Task task) throws SQLException{
        String sql = "UPDATE tasks SET "
                + "idProject = ?, "
                +"name = ?, "
                +"description = ?, "
                +"notes = ?, "
                +"completed = ?, "
                +"deadline = ?,"
                +"createdAt = ?, "
                +"updatedAt = ?, "
                +"WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5 , task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));
            statement.setInt(9, task.getId());
            
            //Executndo a query
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
   
    @SuppressWarnings("null")
    public void removeByid(int taskId) throws Exception{
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        
        try {
            
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setndos os valores
            statement.setInt(1, taskId);
            
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
    public List<Task> getAll(int idProject) throws SQLException{
        
        String sql = "SELECT FROM tasks WHERE idProject = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        @SuppressWarnings("UnusedAssignment")
        ResultSet resultSet = null;
        
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        List<Task> tasks = new ArrayList<>();
        
        try {
            //Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //Setsndo o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            
            //Valor retornando pela a exeução da query
            resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos no meu resultSet
            while(resultSet.next()){
            
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));       
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updated"));
                
                tasks.add(task);
            }
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //Lists de tarefas que foi criada e carregada do banco de dados
        return tasks; 
    }
    
}      
   
      
    
    
