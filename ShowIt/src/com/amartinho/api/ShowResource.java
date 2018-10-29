package com.amartinho.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.amartinho.impl.ShowsManager;
import com.amartinho.model.Show;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;



@Path("/shows")
public class ShowResource {
	
		//GETS ALL SHOWS By gender (if no gender provided then shows all)
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Show> getShows(@QueryParam("gender") String gender) {
			ShowsManager sm = ShowsManager.getInstance();		
			if (gender!=null) {
				return sm.getShows(gender);
			} 
			return sm.getShows();
		}
		
		//GETS ALL SHOWS By gender (if no gender provided then shows all)
		//Returns XML data
		@GET
		@Path("/xml")
		@Produces(MediaType.APPLICATION_XML)
		public List<Show> getXMLShows(@QueryParam("gender") String intensity) {
			ShowsManager sm = ShowsManager.getInstance();		
			if (intensity!=null) {
				return sm.getShows(intensity);
			} 
			return sm.getShows();
		}
		
		// GETS a specific show BY NAME 
		@Path("/{name}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Show> getShow(@PathParam("name") String name) {
			
			ShowsManager sm = ShowsManager.getInstance();		
			return sm.getShows(name);
		}
		
		// DELETE a specific show
		@Path("/{name}")
		@DELETE	
		public Response removeShow(@PathParam("name") String name/*,@FormParam("token") String token,@Context UriInfo uriInfo*/) {
			ShowsManager sm = ShowsManager.getInstance();
			//UsersManager um = UsersManager.getInstance();
			
			//try {
				//Jwts.parser().setSigningKey(um.getKey()).parseClaimsJws(token);
				sm.removeShow(name);
				return Response.ok().entity("Show removed!").build();

			//}
			//catch(SignatureException e) {
				//return Response.serverError().status(401).type("text/plain").entity("Invalid token!").build();
			//}

			
		}
		
		/*public boolean getLoggedUser(String username)
		{
			UserData userData = UserData.getInstance();
			List<User> users = userData.getData();
			for (User user:users) {
		
				if(user.getUsername().equalsIgnoreCase(username))
				{
					return true;
				}
			}
			return false;
		}*/
		
		//POSTS a new show
		@POST
		@Consumes("application/x-www-form-urlencoded")
		public Response insertShow(
				@FormParam("name") String name,
				@FormParam("desc") String desc, 
				@FormParam("gender") String gender,
				@FormParam("release") String release,
				@FormParam("seasons") int seasons,
				@FormParam("episodes") int episodes,
				@FormParam("imdb") int imdb,
				@FormParam("cusers") int cusers,
				@Context UriInfo uriInfo) {
			
			//UsersManager um = UsersManager.getInstance();
			ShowsManager sm = ShowsManager.getInstance();
			//try {
				//Jwts.parser().setSigningKey(um.getKey()).parseClaimsJws(token);
								sm.createShow(name,desc,gender,release,seasons,episodes,imdb,cusers);
								UriBuilder builder = uriInfo.getAbsolutePathBuilder();	
								builder.path(name);
								return Response.created(builder.build()).build();
			}
			

		
}
