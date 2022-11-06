
import jakarta.ws.rs.Path;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;
import java.util.List;

import classes.Game_product;
import interfaces.IProductProcessor;

@Path("/")
public class Service {
	@Inject
	private IProductProcessor proc;
	
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
		String res = proc.checkUserData(logs.get(0), logs.get(1));
		
		if(res != "0") {
			return Response.ok(jsonb.toJson(res)).build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).build();
 	}
 
 
	@POST
 	@Path("/SignUp")
 	@Consumes("application/json")
	@Produces("application/json")
 	public Response signUp(String fileJSON) throws Exception 
 	{            
		System.out.println("================" + fileJSON + "======================================");
		Jsonb jsonb = JsonbBuilder.create();          
	 	List<String> logs;
	 	logs = jsonb.fromJson(fileJSON, List.class);
		
		boolean res = proc.registrateNewUser(logs.get(0), logs.get(1));
		if(res) {
			return Response.ok().build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).build();
 	}
	
	
	@POST 
 	@Path("/Key")
	@Consumes("application/json")
	@Produces("application/json")
 	public Response pushKey(String user_data)
 	{   
		Jsonb jsonb = JsonbBuilder.create();          
	 	List<String> logs;
	 	logs = jsonb.fromJson(user_data, List.class);
	 	
		boolean res = proc.checkToken(logs.get(0)+"");
		if(res) { 
			return Response.ok(jsonb.toJson(proc.getGameKey(logs.get(1)))).build();
		}else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
 	}
	
	
	@POST
 	@Path("Catalog/GameCatalog")
	@Consumes("application/json")
	@Produces("application/json")
 	public Response pushCatalog(String fileJSON) throws Exception 
 	{    
		Jsonb jsonb = JsonbBuilder.create();          
	 	List<String> logs;
	 	logs = jsonb.fromJson(fileJSON, List.class);
		boolean res = proc.checkToken(logs.get(0));
		if(res) {     
			ArrayList<Game_product> result;
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
}