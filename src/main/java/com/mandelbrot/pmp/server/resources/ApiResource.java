package com.mandelbrot.pmp.server.resources;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.mandelbrot.pmp.server.model.ApiModel;
import com.mandelbrot.pmp.server.service.ApiService;

@Path("/mandelbrot")
public class ApiResource {

	@GET
	@Produces("image/png")
	@Path("/{ciMin}/{ciMax}/{crMin}/{crMax}/{x}/{y}/{itr}")
	public Response  getResponse(@BeanParam ApiModel model ) throws IOException{
		ApiService service=new ApiService();
		byte[] mandelbrot = service.mandelbrot(model.getCrMin(), model.getCrMax(), model.getCiMin(), model.getCiMax(), model.getX(), model.getY(), model.getItr());
		System.out.println("ciMax:"+model.getCiMax()+"ciMin"+model.getCiMin());
		System.out.println("X:"+model.getX()+"Y"+model.getY());
		ResponseBuilder response = Response.ok(mandelbrot);
		response.header("Content-Disposition","attachment; filename=image_from_server.png");
		response.status(200);
		return response.build();
	}
}
