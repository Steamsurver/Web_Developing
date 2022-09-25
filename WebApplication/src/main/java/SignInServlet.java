import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.DataBaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;


public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataBaseController dataBase;
	List<String> table;
	String id = "";
	String del_id = "";
	String login;
    String password;
	 @Override
	   public void init() {
		 dataBase = new DataBaseController();
	   }
	 
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        PrintWriter printWriter = null;
        try
        {
            printWriter = response.getWriter();
        }
        catch (Exception ex)
        {
        }
        try
        {
            HttpSession session = request.getSession(true);
            String view = "sign";
            String gedug =  request.getServletPath();
            
            //==================================================================================
            if(gedug.equals("/SignInServlet")) {
            	login = String.valueOf(request.getParameter("login"));
                password = String.valueOf(request.getParameter("password"));
            	boolean result = dataBase.DataIsCorrect(login, password);
            	id = dataBase.GetDirectorId(login, password);
            	if(result) {
            		table = dataBase.GatTableData(id);
            		request.setAttribute("messege", "Enter is done!");
            		request.setAttribute("table", table);
            		request.setAttribute("sizeOfTable", table.size());
            		view = "table";
            	}else {
            		request.setAttribute("messege", "Enter is denied!");
            		view = "sign";
            	}
            }
            //==================================================================================
            if(gedug.equals("/SignUpServlet")) {
            	login = String.valueOf(request.getParameter("login"));
                password = String.valueOf(request.getParameter("password"));
                id = String.valueOf(request.getParameter("id"));
                
                if(!(login.equals("") || login == null || password.equals("") || password == null || id.equals("") || id == null)) {
                	dataBase.RegistrationNewDirector(id, login, password);
                	request.setAttribute("messege", "You success signed up in base.");
                }
                else
                	request.setAttribute("messege", "You send the incorrect date.");
                view = "sign";
            	}
            //==================================================================================
            if(gedug.equals("/TableServlet")) {
            	view = "table";
            }
            //==================================================================================
            if(gedug.equals("/TableDeleteServlet")){
                view = "table";
                del_id = String.valueOf(request.getParameter("del_id"));
                dataBase.EnterDeleteQwery(del_id);
                table = dataBase.GatTableData(id);
                request.setAttribute("table", table);
        		request.setAttribute("sizeOfTable", table.size());
        		String mess = "User: " + del_id + " is succsessfuly deleted.";
        		request.setAttribute("messege", mess);
            }    
    	    //==================================================================================
            
            if(gedug.equals("/TableAdditionServlet")){
                view = "table";
                String add_id = String.valueOf(request.getParameter("user_id"));
                String add_name = String.valueOf(request.getParameter("user_name"));
                String add_secondName = String.valueOf(request.getParameter("user_secondName"));
                
                id = dataBase.GetDirectorId(login, password);
                dataBase.EnterAdditionQwery(add_id, add_name, add_secondName, id);
                table = dataBase.GatTableData(id);
                
                request.setAttribute("table", table);
        		request.setAttribute("sizeOfTable", table.size());
        		
        		String mess = "User: " + add_id + " is succsessfuly added.";
        		request.setAttribute("messege", mess);
            }    
    	    //==================================================================================
            		
    				
            request.getRequestDispatcher("WEB-INF/"+view+".jsp").forward(request,response);
        }
        catch (Exception ex)
        {
            printWriter.println("Error: "+ex.getMessage());
        }
    }
    //=================================================================================================

	
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String view = "sign";
        String gedug =  request.getServletPath();
		
        if(gedug.equals("/SignUpServlet")){
            view = "signUp";
        }
        
        if(gedug.equals("/TableAdditionServlet")){
            view = "addUser";
        }
        
        if(gedug.equals("/TableDeleteServlet")){
            view = "delUser";
        }
        
        request.getRequestDispatcher("WEB-INF/"+view+".jsp").forward(request,response);
    }
    
	//==============================================================================================
	
	
	
}