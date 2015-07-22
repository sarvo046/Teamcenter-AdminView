//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.3-b24-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.07.22 at 11:41:15 AM EST 
//


package tceav.manager.procedure.plmxmlpdm.type;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import tceav.xml.TagTools;
import tceav.manager.procedure.plmxmlpdm.ProcedureTagTypeEnum;
import tceav.manager.procedure.plmxmlpdm.base.AttributeBase;
import tceav.manager.procedure.plmxmlpdm.type.element.UserDataElementType;
/*
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
*/

/**
 * 
 *       The UserData element is a PLMXML Attribute used to represent a table of
 *       title/value pairs. It is up to communicating applications to agree on the
 *       meaning of these tables.
 *     
 *       UserData is based on the plm:Attribute base class so that it may be substituted
 *       wherever PLMXML Attributes are permitted. 
 * 
 *       Attributes:
 * 
 *       type:           A string giving the type of UserData. No specified values.
 * 
 *       Elements:
 * 
 *       UserValue:      One element per entry in the table (at least one).
 *       
 * 
 * <p>Java class for UserDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.plmxml.org/Schemas/PLMXMLSchema}AttributeBase">
 *       &lt;choice>
 *         &lt;element name="UserValue" type="{http://www.plmxml.org/Schemas/PLMXMLSchema}UserDataElementType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
//@XmlAccessorType(XmlAccessType.FIELD)
/*
 @XmlType(name = "UserDataType", propOrder = {
    "userValue"
})
 **/
public class UserDataType extends AttributeBase {

    //@XmlElement(name = "UserValue")
    protected List<UserDataElementType> userValue;
    
    //@XmlAttribute
    public final String typeAttribute = "type";
    protected String type;
    
    
    public UserDataType (Node node) {
        super(node);
        Node currentNode = node;
        NamedNodeMap attrib = currentNode.getAttributes();
        NodeList nodeList = currentNode.getChildNodes();
        
        setType(TagTools.getStringValue(attrib, typeAttribute));
        
        ProcedureTagTypeEnum tagType;
        for (int i=0; i<nodeList.getLength(); i++) {
            currentNode = nodeList.item(i);
            tagType = ProcedureTagTypeEnum.fromValue(currentNode.getNodeName());
            
            switch(tagType) {
                case UserValue:
                    getUserValue().add(new UserDataElementType(currentNode));
                    break;
                    
                default:
                    break;
            }
        }

    }

    public List<UserDataElementType> getUserValue() {
        if (userValue == null)
            userValue = new ArrayList<UserDataElementType>();
        
        return userValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }
    
    /***************
     * Customisation
     ***************/
     private ProcedureTagTypeEnum userDataType;
     
     public void setUserDataType(ProcedureTagTypeEnum userDataType) {
         this.userDataType = userDataType;
     }
     
     public ProcedureTagTypeEnum getUserDataType() {
         if(userDataType == null)
             return ProcedureTagTypeEnum.UserData;
         else
             return userDataType;
     }

}
