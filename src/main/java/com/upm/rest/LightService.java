package com.upm.rest;
 
import com.upm.rest.db.MemoryStorage;
import com.upm.rest.entity.Light;
import com.upm.rest.response.GenericResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
/**
 * @author 
 *
 */
@Path("/light")
public class LightService {

  
  @POST
  @Path("/addLight")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public GenericResponse addLight(Light request){
      GenericResponse gr=new GenericResponse();
      MemoryStorage.getLightStore().put(request.getDeviceId(), request);
    
      gr.setMessage("Light Added Successfully");
      return gr;
  }
  
  @GET
  @Path("/getLight/{deviceId}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public GenericResponse getLight(@PathParam("deviceId") String deviceId) {
   GenericResponse gr=new GenericResponse();
   
   Light temp=MemoryStorage.getLightStore().get(deviceId);
   if(temp!=null){
     gr.setMessage("Success");
     gr.setData(temp);
   }else{
       gr.setMessage("Device Id not found");
   } 
   return gr;
  }
  
  @GET
  @Path("/getAllLight")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public GenericResponse getAllLight() {
   GenericResponse gr=new GenericResponse();
  
      HashMap<String,Light> allLights=MemoryStorage.getLightStore();
      Set<String> keys=allLights.keySet();
      List<Light> lightList=new ArrayList<Light>();
      for (String key : keys) {
          lightList.add(allLights.get(key));
      }
   
     gr.setMessage("Success");
     gr.setData(lightList);
   
   return gr;
  }
  
  
  @POST
  @Path("/switchOnLight/{deviceId}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public GenericResponse switchOnLight(@PathParam("deviceId") String deviceId){
      GenericResponse gr=new GenericResponse();
       Light tempLight=MemoryStorage.getLightStore().get(deviceId);
       tempLight.setStatus("ON");
    
       MemoryStorage.getLightStore().put(deviceId, tempLight);
       
      gr.setMessage("Light Switched ON Successfully");
      return gr;
  }
  
  @POST
  @Path("/switchOffLight/{deviceId}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public GenericResponse switchOffLight(@PathParam("deviceId") String deviceId){
      GenericResponse gr=new GenericResponse();
       Light tempLight=MemoryStorage.getLightStore().get(deviceId);
       tempLight.setStatus("OFF");
    
       MemoryStorage.getLightStore().put(deviceId, tempLight);
       
      gr.setMessage("Light Switched OFF Successfully");
      return gr;
  }
  
  
  @DELETE
  @Path("/removeLight/{deviceId}")
  @Produces(MediaType.APPLICATION_JSON)
  public GenericResponse removeLight(@PathParam("deviceId")String deviceId){
      GenericResponse gr=new GenericResponse();
     Light light= MemoryStorage.getLightStore().remove(deviceId);
     if(light!=null)
      gr.setMessage("Light Removed Successfully");
     else
      gr.setMessage("No Light with this device Id found");
     
      return gr;
  }
  
}