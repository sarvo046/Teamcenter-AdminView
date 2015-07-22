/*
 * ResourceLocator.java
 *
 * Created on 13 July 2007, 11:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tcadminview;

import java.net.URL;
import java.io.IOException;
/**
 *
 * @author nzr4dl
 */
public class ResourceLocator {
    
    /**
     * Creates a new instance of ResourceLocator
     */
    public ResourceLocator() {
    }
    
    public static URL getChangeLog() throws IOException {
        return ResourceLocator.class.getResource("resources/ChangeLog.html");
    }
    
    public static URL getRultreeColumnImage(String image) throws IOException {
        return ResourceLocator.class.getResource("resources/images/ruletree-columns/"+image);
    }
    
    public static URL getRultreeImage(String image) throws IOException {
        return ResourceLocator.class.getResource("resources/images/ruletree/"+image);
    }
    
    public static URL getButtonImage(String image) throws IOException  {
        return ResourceLocator.class.getResource("resources/images/buttons/"+image);
    }
    
    public static URL getPLMXMLPDMSchema() throws IOException {
        return ResourceLocator.class.getResource("resources/schema/PLMXMLPDMSchema.xsd");
    }
    
    public static String getVersion() {
        return "1.1.8 Alpha";
    }
    
    public static String getReleaseDate() {
        return "30-Jul-07";
    }
    
    public static String getApplicationName() {
        return "Teamcenter Admin View";
    }
    
    public static String getAboutInfo() {
        return "<html><p>TcAE Admin View</p><p></p>" +
                        "<p>"+getVersion()+"</p>" +
                        "<p>Release Date: "+getReleaseDate()+"</p><p></p>" +
                        "<p>Developed by:<br> Nader Eloshaiker<br>Math Based Systems<br>General Motors - Holden</p>" +
                        "</html>";
    }
    
}