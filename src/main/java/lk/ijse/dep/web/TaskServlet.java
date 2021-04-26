package lk.ijse.dep.web;


import lk.ijse.dep.web.model.Task;
import lk.ijse.dep.web.util.Priority;
import org.apache.commons.dbcp2.BasicDataSource;


import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Shalitha Anuradha <shalithaanuradha123@gmail.com>
 * @since : 2021-04-24
 **/
@WebServlet(name = "TaskServlet", urlPatterns = "/api/v1/tasks/*")
public class TaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final BasicDataSource bds=(BasicDataSource)getServletContext().getAttribute("cp");

        try(Connection connection=bds.getConnection();) {
            Jsonb jsonb = JsonbBuilder.create();
            Task task = jsonb.fromJson(request.getReader(), Task.class);
            System.out.println(task);
            System.out.println(task.getPriority().toString());
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO task VALUE (?,?,?,?)");
            pstm.setObject(1,task.getId());
            pstm.setObject(2,task.getText());
            pstm.setObject(3,task.getPriority().toString());
            pstm.setObject(4,task.isCompleted());
            boolean sucess=pstm.executeUpdate()>0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
/*{
    "id":"T007",
    "text":"GMMM",
    "priority": "PRIORITY2",
    "complete": true
}*/   /* <----Example for JSON request*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        final BasicDataSource bds=(BasicDataSource)getServletContext().getAttribute("cp");

        try(Connection connection=bds.getConnection();) {

            PrintWriter out = response.getWriter();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM task");
            ArrayList<Task> taskList= new ArrayList<>();
            ResultSet rst = pstm.executeQuery();
            while (rst.next()){
                String id=rst.getString(1);
                String text=rst.getString(2);
                Priority priority=Enum.valueOf(Priority.class,rst.getString(3));
                boolean complete = rst.getBoolean(4);
                ;
                taskList.add(new Task(id, text, priority, complete));
            }

            Jsonb jsonb = JsonbBuilder.create();
            out.println(jsonb.toJson(taskList));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
