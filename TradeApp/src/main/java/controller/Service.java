package controller;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import builder.Build.Built;
import controller.Interceptor.RegistrationFilter;
import model.classes.GameProduct;
import model.classes.UserPerson;
import model.interfaces.IProductProcessor;

@Path("/")
public class Service {
	@Context
	ContainerRequestContext requestContext;
	
	@Inject @Built
	IProductProcessor proc;
	
	@POST
 	@Path("/SignIn")
 	@Consumes("application/json")
	@Produces("application/json")
 	public Response signIn(String fileJSON) throws Exception 
 	{   
		
		System.out.println("================" + fileJSON + "======================================");
		Jsonb jsonb = JsonbBuilder.create();          
	 	List<String> logs;
	 	logs = jsonb.fromJson(fileJSON, List.class);
		UserPerson res = proc.checkUserData(logs.get(0), logs.get(1));
		
		if(res != null) {
			return Response.ok(jsonb.toJson(res)).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
 	}
 
 
	@GET
	@RegistrationFilter
 	@Path("/SignUp")
	@Produces("application/json")
 	public Response signUp(@Context HttpServletResponse response) throws Exception 
 	{            
	 	String login = (String) requestContext.getProperty("login");
	 	String password = (String) requestContext.getProperty("password");
	 	System.out.println("================" + login+ "  "+ password + "======================================");
	 	
	 	
		boolean res = proc.registrateNewUser(login, password);
		if(res){
			return Response.ok().build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).build();
 	}
	
	
	@GET 
 	@Path("/Key")
	@Produces("application/json")
 	public Response pushKey(@Context HttpServletRequest request, @Context HttpServletResponse response){
		
		String user_data = request.getHeader("logs").strip();
		Jsonb jsonb = JsonbBuilder.create();          
	 	List<String> logs;
	 	logs = jsonb.fromJson(user_data, List.class);
	 	
		boolean res = proc.checkToken(logs.get(0)+"");
		if(res) { 
			String ret_key = proc.getGameKey(logs.get(1));
			if(ret_key == null)
				return Response.status(Response.Status.BAD_REQUEST).build();
			else
				return Response.ok(jsonb.toJson(proc.getGameKey(logs.get(1)))).build();	
		}else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
 	}
	
	
	@GET
 	@Path("Catalog/GameCatalog")
	@Consumes("application/json")
	@Produces("application/json")
 	public Response pushCatalog(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
		String user_data = request.getHeader("logs").strip();
		
		Jsonb jsonb = JsonbBuilder.create();          
	 	List<String> logs;
	 	logs = jsonb.fromJson(user_data, List.class);
	 	System.out.println(user_data);
		boolean res = proc.checkToken(logs.get(0));
		if(res) {     
			List<GameProduct> result;
			result = proc.getFullCatalog();
			if(result != null){
				return Response.ok(jsonb.toJson(result)).build(); 
			}
			else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}else {
			return Response.status(Response.Status.BAD_REQUEST).build(); 
		}
		 
 	}
	
	
	
	@POST
 	@Path("Catalog/PostProduct")
	@Consumes("application/json")
 	public Response postNewProduct(String fileJSON, @Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
		String prodJSON = fileJSON;
		String user_data = request.getHeader("logs").strip();
		
		System.out.println("================" + fileJSON + "======================================");
		
		Jsonb jsonb = JsonbBuilder.create();          
	 	List<String> prodArray;
	 	List<String> userToken;
	 	
	 	prodArray = jsonb.fromJson(prodJSON, List.class);
	 	userToken = jsonb.fromJson(user_data, List.class);
	 	
		boolean res = proc.checkToken(userToken.get(0));
		if(res) {     
			Boolean result;
			result = proc.postProduct(prodArray.get(0), prodArray.get(1), prodArray.get(2), prodArray.get(3));
			
			if(result){
				return Response.ok().build(); 
			}
			
			else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}else {
			return Response.status(Response.Status.BAD_REQUEST).build(); 
		}
		 
 	}
}
