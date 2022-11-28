package controller.Interceptor;

import java.io.IOException;
import java.util.List;

import jakarta.ws.rs.ext.Provider;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;


@Provider
@RegistrationFilter
public class Interceptor implements ContainerRequestFilter {
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	
    	 String header = requestContext.getHeaderString("logs").strip();
    	 Jsonb jsonb = JsonbBuilder.create();          
 	 	 List<String> logs;
 	 	 logs = jsonb.fromJson(header, List.class);
 	 	
         if (header == null) {
             throw new NotAuthorizedException("null data for authorisation");           
         }else if(logs.get(0).isEmpty() || logs.get(1).isEmpty()){
        	 throw new NotAuthorizedException("null data for authorisation");    
         }else if(logs.get(0)=="" || logs.get(1)==""){
        	 throw new NotAuthorizedException("bad data for authorisation");    
         }
         
         
         requestContext.setProperty("login", logs.get(0));
         requestContext.setProperty("password", logs.get(1));
    }
    
}
