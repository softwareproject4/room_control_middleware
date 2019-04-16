package com.upm.rest;

import com.upm.rest.db.MemoryStorage;
import com.upm.rest.entity.Temprature;
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
@Path("/temprature")
public class TempratureService {

    @POST
    @Path("/addDevice")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GenericResponse addDevice(Temprature request) {
        System.out.println("Temp Device Added "+request.getDeviceId());
        GenericResponse gr = new GenericResponse();
        MemoryStorage.getTempratureStore().put(request.getDeviceId(), request);

        gr.setMessage("Temprature Device Added Successfully");
        return gr;
    }

    @GET
    @Path("/getCurrentTemprature/{deviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GenericResponse getCurrentTemprature(@PathParam("deviceId") String deviceId) {
        GenericResponse gr = new GenericResponse();

        Temprature temp = MemoryStorage.getTempratureStore().get(deviceId);
        if (temp != null) {
            gr.setMessage("Success");
            gr.setData(temp);
        } else {
            gr.setMessage("Device Id not found");
        }
        return gr;
    }
    
    @GET
    @Path("/getAllDevice")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GenericResponse getAllDevice() {
        GenericResponse gr = new GenericResponse();

        HashMap<String,Temprature> allDevices=MemoryStorage.getTempratureStore();
        Set<String> keys=allDevices.keySet();
        
        List<Temprature> allTemp=new ArrayList<Temprature>();
        
        for(String key:keys){
           allTemp.add(allDevices.get(key));
        }
        
            gr.setMessage("Success");
            gr.setData(allTemp);
        
        return gr;
    }

    @POST
    @Path("/updateTemprature")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GenericResponse updateTemprature(Temprature request) {
        GenericResponse gr = new GenericResponse();
        // Get the Device from storage
        Temprature temp = MemoryStorage.getTempratureStore().get(request.getDeviceId());
        if (temp != null) {
            temp.setTemprature(request.getTemprature());
            MemoryStorage.getTempratureStore().put(request.getDeviceId(), temp);

            gr.setMessage("Temprature Updated Successfully");
        } else {
            gr.setMessage("Device Id not found");
        }
        return gr;
    }

    @DELETE
    @Path("/removeDevice/{deviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GenericResponse removeDevice(@PathParam("deviceId") String deviceId) {
        GenericResponse gr = new GenericResponse();

        Temprature temp = MemoryStorage.getTempratureStore().remove(deviceId);
        if (temp != null) {
            gr.setMessage("Temprature Device removed successfully");
            gr.setData(temp);
        } else {
            gr.setMessage("Device Id not found");
        }
        return gr;
    }

}
