package com.privalov.Plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Base64;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class Base64Image extends Plugin {

    public static final String TAG = "BASE64IMAGE";

	/**
     * Executes the request and returns PluginResult.
     *
     * @param action        The action to execute.
     * @param args          JSONArry of arguments for the plugin.
     * @param callbackId    The callback id used when calling back into JavaScript.
     * @return              A PluginResult object with a status and message.
     */
    public PluginResult execute(String action, JSONArray args, String callbackId) {
        try {
        	 if(action.equals("convert")){
            	
            	String imageURI = args.getString( 0 );
            	FileInputStream fileInpStream = null;
            	File imgFile = null;
            	
            	try {
            		imgFile = new File( imageURI.replace( "file://", "" ) );
            		fileInpStream = new FileInputStream( imageURI.replace( "file://", "" ) );  
            		byte[] b = new byte[(int) imgFile.length()];
            		try {
            			fileInpStream.read(b);
            		} catch (Exception e) {
            		    e.printStackTrace();
            		}
				
            	String returnString = Base64.encodeToString( b, Base64.DEFAULT );
            	b = null;
            	
				Log.v(TAG, returnString);
				Log.v(TAG, "Convert complete");
            	
				return new PluginResult(PluginResult.Status.OK, returnString);
				
            	} catch ( FileNotFoundException e ) {
					return new PluginResult( PluginResult.Status.ERROR, "Could not load image: "+imageURI );
				}
            } else {
            	Log.v(TAG, "Resize INVALID_ACTION");
                return new PluginResult(PluginResult.Status.INVALID_ACTION);
            }
        } catch (JSONException e) {
        	Log.v(TAG, "Resize JSON_EXCEPTION");
            return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
        }
    }
    
}
