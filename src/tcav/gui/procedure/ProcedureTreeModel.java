/*
 * ProcedureTreeModel.java
 *
 * Created on 5 August 2007, 09:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tcav.gui.procedure;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import tcav.procedure.ProcedureManager;
import tcav.plmxmlpdm.*;
import tcav.plmxmlpdm.type.*;
import tcav.plmxmlpdm.classtype.*;
import tcav.plmxmlpdm.base.*;


/**
 *
 * @author NZR4DL
 */
public class ProcedureTreeModel implements TreeModel {
    
    private ProcedureManager pm;
    private ArrayList<Integer> rootWorkflows;
    private int procedureMode;
    
    public static final int MODE_DEPENDANT_TASKS = 0;
    public static final int MODE_SUB_WORKFLOWS = 1;
    
    /** Creates a new instance of RuleTreeModel */
    public ProcedureTreeModel(ProcedureManager pm, int procedureMode) {
        this.pm = pm;
        this.procedureMode = procedureMode;
        if(pm.getWorkflowTemplates().getIndexesForClassification(
                WorkflowTemplateClassificationEnum.PROCESS).size() != 0)
            rootWorkflows = pm.getWorkflowTemplates().getIndexesForClassification(
                    WorkflowTemplateClassificationEnum.PROCESS);
        else {
            rootWorkflows = new ArrayList<Integer>();
            for(int i=0; i<pm.getHeader().getTraverseRootRefs().size(); i++)
                rootWorkflows.add(pm.getIdIndex(pm.getHeader().getTraverseRootRefs().get(i)));
        }
    }
    
    public int getProcedureMode() {
        return procedureMode;
    }
    
    public void setProcedureMode(int procedureMode) {
        this.procedureMode = procedureMode;
    }
    
    public Object getRoot(){
        if(pm.getWorkflowTemplates().size() == 0)
            return null;
        else
            return
                new NodeReference(
                    pm.getSites().get(0).getId(),
                    pm.getSites().get(0).getName()+" ("+pm.getSites().get(0).getSiteId()+")",
                    NodeReference.PROCEDURE_SITE,
                    TagTypeEnum.Site);
    }
    
    public Object getChild(Object parent, int index){
        NodeReference nrParent = (NodeReference)parent;
        String childId;
        NodeReference nr;
        WorkflowTemplateType wt;
        WorkflowTemplateType wtChild;
        WorkflowActionType wa;
        WorkflowBusinessRuleType wbr;
        WorkflowBusinessRuleHandlerType wbrh;
        
        switch(nrParent.getClassType()){
            case Site:
                wt = pm.getWorkflowTemplates().get(rootWorkflows.get(index));
                return new NodeReference(
                        wt.getId(),
                        wt.getName(),
                        NodeReference.PROCEDURE_WORKFLOW_PROCESS,
                        TagTypeEnum.WorkflowTemplate,
                        wt.getIconKey());
                
            case WorkflowTemplate:
                wt = pm.getWorkflowTemplates().get(pm.getIdIndex(nrParent.getId()));
                
                int workflowSize = 0;
                if(procedureMode == MODE_DEPENDANT_TASKS)
                    workflowSize = wt.getDependencyTaskTemplateRefs().size();
                else if(procedureMode == MODE_SUB_WORKFLOWS)
                    workflowSize = wt.getSubTemplateRefs().size();
                
                if((procedureMode == MODE_DEPENDANT_TASKS) &&
                        (wt.getDependencyTaskTemplateRefs().size() != 0) &&
                        (index < workflowSize) ) {
                    
                    childId = wt.getDependencyTaskTemplateRefs().get(index);
                    wtChild =  pm.getWorkflowTemplates().get(pm.getIdIndex(childId));
                    
                    return new NodeReference(
                            childId,
                            wtChild.getName(),
                            NodeReference.PROCEDURE_DEPENDANT_TASKS,
                            TagTypeEnum.WorkflowTemplate,
                            wtChild.getIconKey());
                    
                } else if((procedureMode == MODE_SUB_WORKFLOWS) &&
                        (wt.getSubTemplateRefs().size() != 0) &&
                        (index < workflowSize)) {
                    
                    childId = wt.getSubTemplateRefs().get(index);
                    wtChild = pm.getWorkflowTemplates().get(pm.getIdIndex(childId));
                    return new NodeReference(
                            childId,
                            wtChild.getName(),
                            NodeReference.PROCEDURE_SUB_WORKFLOW,
                            TagTypeEnum.WorkflowTemplate,
                            wtChild.getIconKey());
                    
                } else if((wt.getActions().size() != 0) &&
                        (index >= workflowSize)) {
                    
                    childId = wt.getActions().get(index-workflowSize);
                    wa = pm.getWorkflowActions().get(pm.getIdIndex(childId));
                    return new NodeReference(
                            wa.getId(),
                            "Action: Type "+wa.getActionType(),
                            NodeReference.PROCEDURE_WORKFLOW_ACTION,
                            TagTypeEnum.WorkflowAction,
                            wa.getActionType());
                    
                } else
                    System.out.println("getChild WorkflowTemplate should not reach this point");
                
                return null;
                
            case WorkflowAction:
                wa = pm.getWorkflowActions().get(pm.getIdIndex(nrParent.getId()));
                
                int handlerSize = wa.getActionHandlerRefs().size();
                
                if((wa.getActionHandlerRefs().size() != 0) && (index < handlerSize)) {
                    
                    childId = wa.getActionHandlerRefs().get(index);
                    WorkflowHandlerType wh = pm.getWorkflowHandlers().get(pm.getIdIndex(childId));
                    return new NodeReference(
                            wh.getId(),
                            wh.getName(),
                            NodeReference.PROCEDURE_WORKFLOW_HANDLER,
                            TagTypeEnum.WorkflowHandler);
                    
                } else if((wa.getRuleRefs().size() != 0) && (index >= handlerSize)) {
                    
                    childId = wa.getRuleRefs().get(index - handlerSize);
                    wbr = pm.getWorkflowBusinessRules().get(pm.getIdIndex(childId));
                    return new NodeReference(
                            wbr.getId(),
                            "Business Rule ",
                            NodeReference.PROCEDURE_WORKFLOW_BUSINESS_RULE,
                            TagTypeEnum.WorkflowBusinessRule);
                    
                } else
                    System.out.println("getChild WorkflowAction should not reach this point");
                
                return null;
                
            case WorkflowBusinessRule:
                wbr = pm.getWorkflowBusinessRules().get(pm.getIdIndex(nrParent.getId()));
                childId = wbr.getRuleHandlerRefs().get(index);
                wbrh = pm.getWorkflowBusinessRuleHandlers().get(pm.getIdIndex(childId));
                return new NodeReference(
                        wbrh.getId(),
                        wbrh.getName(),
                        NodeReference.PROCEDURE_WORKFLOW_BUSINESS_RULE_HANDLER,
                        TagTypeEnum.WorkflowBusinessRuleHandler);
                
            default:
                System.out.println("getChild -ITEM- default Class Type should not reach this point");
                return null;
        }
    }
    
    public int getChildCount(Object parent){
        NodeReference nrParent = (NodeReference)parent;
        int counter = 0;
        WorkflowActionType wa;
        WorkflowBusinessRuleType wbr;
        WorkflowTemplateType wt;
        
        switch(nrParent.getClassType()){
            case Site:
                return rootWorkflows.size();
                
            case WorkflowTemplate:
                wt = pm.getWorkflowTemplates().get(pm.getIdIndex(nrParent.getId()));
                counter = 0;
                if(procedureMode == MODE_DEPENDANT_TASKS)
                    counter += wt.getDependencyTaskTemplateRefs().size();
                else if(procedureMode == MODE_SUB_WORKFLOWS)
                    counter += wt.getSubTemplateRefs().size();
                
                counter += wt.getActions().size();
                
                return counter;
                
            case WorkflowAction:
                wa = pm.getWorkflowActions().get(pm.getIdIndex(nrParent.getId()));
                counter = 0;
                counter += wa.getActionHandlerRefs().size();
                counter += wa.getRuleRefs().size();
                return counter;
                
            case WorkflowBusinessRule:
                wbr = pm.getWorkflowBusinessRules().get(pm.getIdIndex(nrParent.getId()));
                return wbr.getRuleHandlerRefs().size();
                
                
            case WorkflowBusinessRuleHandler:
            case WorkflowHandler:
                return 0;
                
            default:
                System.out.println("getChildCount -ITEM- default should not reach this point");
                return 0;
        }
    }
    
    public boolean isLeaf(Object node){
        NodeReference nr = (NodeReference)node;
        AttribOwnerBase aob;
        int counter = 0;
        
        switch(nr.getClassType()){
            case Site:
                return (rootWorkflows.size() == 0);
                
            case WorkflowTemplate:
                WorkflowTemplateType wt =
                        pm.getWorkflowTemplates().get(pm.getIdIndex(nr.getId()));
                counter = 0;
                
                if(procedureMode == MODE_DEPENDANT_TASKS)
                    counter += wt.getDependencyTaskTemplateRefs().size();
                else if(procedureMode == MODE_SUB_WORKFLOWS)
                    counter += wt.getSubTemplateRefs().size();
                
                counter += wt.getActions().size();
                
                return (counter == 0);
                
            case WorkflowAction:
                WorkflowActionType wa =
                        pm.getWorkflowActions().get(pm.getIdIndex(nr.getId()));
                counter = 0;
                counter += wa.getActionHandlerRefs().size();
                counter += wa.getRuleRefs().size();
                return (counter == 0);
                
            case WorkflowBusinessRule:
                WorkflowBusinessRuleType wbr =
                        pm.getWorkflowBusinessRules().get(pm.getIdIndex(nr.getId()));
                return (wbr.getRuleHandlerRefs().size()==0);
                
            case WorkflowBusinessRuleHandler:
            case WorkflowHandler:
                return true;
                
            default:
                System.out.println("isLeaf -ITEM- default class type should not reach this point");
                return true;
        }
    }
    
    public int getIndexOfChild(Object parent, Object child){
        NodeReference nrParent = (NodeReference)parent;
        NodeReference nrChild = (NodeReference)child;
        
        WorkflowTemplateType wt;
        WorkflowActionType wa;
        WorkflowBusinessRuleType wbr;
        
        switch(nrParent.getClassType()){
            case Site:
                return rootWorkflows.indexOf(pm.getIdIndex(nrChild.getId()));
                
            case WorkflowTemplate:
                wt = pm.getWorkflowTemplates().get(pm.getIdIndex(nrParent.getId()));
                switch(nrChild.getProcedureType()){
                    case NodeReference.PROCEDURE_DEPENDANT_TASKS:
                        wt = pm.getWorkflowTemplates().get(pm.getIdIndex(nrParent.getId()));
                        return wt.getDependencyTaskTemplateRefs().indexOf('#'+nrChild.getId());
                        
                    case NodeReference.PROCEDURE_SUB_WORKFLOW:
                        wt = pm.getWorkflowTemplates().get(pm.getIdIndex(nrParent.getId()));
                        return wt.getSubTemplateRefs().indexOf('#'+nrChild.getId());
                        
                    case NodeReference.PROCEDURE_WORKFLOW_ACTION:
                        int counter = 0;
                        
                        if(procedureMode == MODE_DEPENDANT_TASKS)
                            counter += wt.getDependencyTaskTemplateRefs().size();
                        else if(procedureMode == MODE_SUB_WORKFLOWS)
                            counter += wt.getSubTemplateRefs().size();
                        
                        wt = pm.getWorkflowTemplates().get(pm.getIdIndex(nrParent.getId()));
                        return wt.getActions().indexOf('#'+nrChild.getId())+counter;
                        
                    default:
                        System.out.println("getIndexOfChild WorkflowTemplate should not reach this point\n"+
                                wt.getId()+" : "+wt.getName()+": "+nrChild.getProcedureType());
                        return 0;
                }
                
            case WorkflowAction:
                wa = pm.getWorkflowActions().get(pm.getIdIndex(nrParent.getId()));
                switch(nrChild.getProcedureType()){
                    case NodeReference.PROCEDURE_WORKFLOW_HANDLER:
                        wa = pm.getWorkflowActions().get(pm.getIdIndex(nrParent.getId()));
                        return wa.getActionHandlerRefs().indexOf('#'+nrChild.getId());
                        
                    case NodeReference.PROCEDURE_WORKFLOW_BUSINESS_RULE:
                        wa = pm.getWorkflowActions().get(pm.getIdIndex(nrParent.getId()));
                        return wa.getRuleRefs().indexOf('#'+nrChild.getId())+wa.getActionHandlerRefs().size();
                        
                    default:
                        System.out.println("getIndexOfChild WorkflowAction should not reach this point");
                        return 0;
                }
                
            case WorkflowBusinessRule:
                wbr = pm.getWorkflowBusinessRules().get(pm.getIdIndex(nrParent.getId()));
                return wbr.getRuleHandlerRefs().indexOf('#'+nrChild.getId());
                
            default:
                System.out.println("getIndexOfChild -ITEM- default class type should not reach this point");
                return 0;
        }
    }
    
    public void addTreeModelListener(TreeModelListener listener){
        // not editable
    }
    
    public void removeTreeModelListener(TreeModelListener listener){
        // not editable
    }
    
    public void valueForPathChanged(TreePath path, Object newValue){
        // not editable
    }
    
}

