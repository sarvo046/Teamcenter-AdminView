//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.3-b24-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.07.22 at 11:41:15 AM EST 
//


package tcadminview.plmxmlpdm.classtype;
/*
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
*/

/**
 * <p>Java class for WorkflowTemplateClassificationEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WorkflowTemplateClassificationEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="process"/>
 *     &lt;enumeration value="task"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
//@XmlEnum
public enum WorkflowTemplateClassificationEnum {

    //@XmlEnumValue("process")
    PROCESS("process"),
    //@XmlEnumValue("task")
    TASK("task");
    private final String value;

    WorkflowTemplateClassificationEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WorkflowTemplateClassificationEnum fromValue(String v) {
        for (WorkflowTemplateClassificationEnum c: WorkflowTemplateClassificationEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
