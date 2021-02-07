/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Java
 */
public class QI_System {
    
    /**
     * to get IP address of system. 
     * @return  the IP
     */
    static public String getIP(){
        String tr=""; 
        try{
             InetAddress IP=InetAddress.getLocalHost();
             tr=IP.getHostAddress();
          }catch(IOException e){
             e.printStackTrace();
          }
            return tr; 
  }
    
    /**
     * To get the Model No of Computer
     * @return 
     */
    public static String M_N(){
     String tr=""; 
       try{
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "wmic csproduct get name, identifyingnumber");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            tr+=line; 
        }
       }catch(IOException e){}
      
       if(tr.length()>30){
       tr=tr.substring(25, tr.length());
       }
       
       tr=tr.trim(); 
       int s=tr.indexOf(" ", 0); 
       
       tr=tr.substring(s, tr.length());
       
       tr=tr.trim();
       if(tr.isEmpty()){
       tr="QuettaInstitute";
       }
    
    return tr; 
    }
    
    /**
     * To Get the Machin Motherboard  Serial No
     * @return 
     */
    
  public static String S_R(){
    
    
     String tr=""; 
       try{
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "wmic bios get serialnumber");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            tr+=line; 
        }
       }catch(IOException e){}
       
       if(tr.length()>12){
       tr=tr.substring(12, tr.length()); }
       tr=tr.trim(); 
       if(tr.isEmpty()){
       tr="QuettaInstitute";
       }
    
    
    return tr; 
    }
    
    
   public static  String getMacAddress() throws UnknownHostException,
            SocketException
    {
        
        
        
        InetAddress ipAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface
                .getByInetAddress(ipAddress);
        byte[] macAddressBytes = networkInterface.getHardwareAddress();
        StringBuilder macAddressBuilder = new StringBuilder();

        for (int macAddressByteIndex = 0; macAddressByteIndex < macAddressBytes.length; macAddressByteIndex++)
        {
            String macAddressHexByte = String.format("%02X",
                    macAddressBytes[macAddressByteIndex]);
            macAddressBuilder.append(macAddressHexByte);

            if (macAddressByteIndex != macAddressBytes.length - 1)
            {
                macAddressBuilder.append(":");
            }
        }

        return macAddressBuilder.toString();
    }
     
    
    
}
