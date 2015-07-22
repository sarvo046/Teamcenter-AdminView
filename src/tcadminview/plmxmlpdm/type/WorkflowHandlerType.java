//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.3-b24-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.07.22 at 11:41:15 AM EST 
//


package tcadminview.plmxmlpdm.type;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import tcadminview.plmxmlpdm.*;
import tcadminview.plmxmlpdm.base.AttribOwnerBase;

/*
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
*/

/**
 * 
 *       A WorkflowHandler is a small program or function. Handlers are used to 
 *       extend and customize workflow tasks. A WorkflowHandler object has one or more 
 *       arguments associated with it.  It also has a back pointer to its parent workflow
 *       action object.
 *       
 * 
 * <p>Java class for WorkflowHandlerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkflowHandlerType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.plmxml.org/Schemas/PLMXMLSchema}AttribOwnerBase">
 *       &lt;sequence>
 *         &lt;element name="Arguments" type="{http://www.plmxml.org/Schemas/PLMXMLSchema}UserDataType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="parentRef" type="{http://www.plmxml.org/Schemas/PLMXMLSchema}anyURIType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
//@XmlAccessorType(XmlAccessType.FIELD)
/*
 @XmlType(name = "WorkflowHandlerType", propOrder = {
    "arguments"
})
 **/
public class WorkflowHandlerType extends AttribOwnerBase {

    //@XmlElement(name = "Arguments")
    protected UserDataType arguments;
    
    //@XmlAttribute
    protected final String parentRefAttribute = "parentRef";
    protected String parentRef;

    public WorkflowHandlerType(Node node) {
        super(node);
        Node currentNode = node;
        NamedNodeMap attrib = currentNode.getAttributes();
        NodeList nodeList = currentNode.getChildNodes();
        
        setParentRef(TagTools.getStringValue(attrib, parentRefAttribute));
        
        TagTypeEnum tagType;
        for (int i=0; i<nodeList.getLength(); i++) {
            currentNode = nodeList.item(i);
            tagType = TagTypeEnum.fromValue(currentNode.getNodeName());
            
            switch(tagType) {
                case Arguments:
                    setArguments(new UserDataType(currentNode));
                    break;
                    
                default:
                    break;
            }
        }
    }
    /**
     * Gets the value of the arguments property.
     * 
     * @return
     *     possible object is
     *     {@link UserDataType }
     *     
     */
    public UserDataType getArguments() {
        return arguments;
    }

    /**
     * Sets the value of the arguments property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDataType }
     *     
     */
    public void setArguments(UserDataType value) {
        this.arguments = value;
    }

    /**
     * Gets the value of the parentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentRef() {
        return parentRef;
    }

    /**
     * Sets the value of the parentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentRef(String value) {
        this.parentRef = value;
    }

}
