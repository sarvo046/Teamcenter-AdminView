//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.3-b24-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.07.22 at 11:41:15 AM EST 
//


package tceav.manager.procedure.plmxmlpdm.UnusedRequiredTags;

import java.util.Date;
import tceav.manager.procedure.plmxmlpdm.base.DescriptionBase;
import org.w3c.dom.Node;
/*
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
*/

/**
 * <p>Java class for SignoffType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignoffType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.plmxml.org/Schemas/PLMXMLSchema}DescriptionBase">
 *       &lt;attribute name="decision" type="{http://www.plmxml.org/Schemas/PLMXMLSchema}DecisionType" />
 *       &lt;attribute name="decisionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="organisationMemberRef" type="{http://www.plmxml.org/Schemas/PLMXMLSchema}anyURIType" />
 *       &lt;attribute name="resourcePoolRef" type="{http://www.plmxml.org/Schemas/PLMXMLSchema}anyURIType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "SignoffType")
public class SignoffType extends DescriptionBase {

    //@XmlAttribute
    protected DecisionType decision;
    //@XmlAttribute
    protected Date decisionDate;
    //@XmlAttribute
    protected String organisationMemberRef;
    //@XmlAttribute
    protected String resourcePoolRef;
    
    public SignoffType(Node node) {
        super(node);
    }

    /**
     * Gets the value of the decision property.
     * 
     * @return
     *     possible object is
     *     {@link DecisionType }
     *     
     */
    public DecisionType getDecision() {
        return decision;
    }

    /**
     * Sets the value of the decision property.
     * 
     * @param value
     *     allowed object is
     *     {@link DecisionType }
     *     
     */
    public void setDecision(DecisionType value) {
        this.decision = value;
    }

    /**
     * Gets the value of the decisionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDecisionDate() {
        return decisionDate;
    }

    /**
     * Sets the value of the decisionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDecisionDate(Date value) {
        this.decisionDate = value;
    }

    /**
     * Gets the value of the organisationMemberRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisationMemberRef() {
        return organisationMemberRef;
    }

    /**
     * Sets the value of the organisationMemberRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganisationMemberRef(String value) {
        this.organisationMemberRef = value;
    }

    /**
     * Gets the value of the resourcePoolRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourcePoolRef() {
        return resourcePoolRef;
    }

    /**
     * Sets the value of the resourcePoolRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourcePoolRef(String value) {
        this.resourcePoolRef = value;
    }

}
