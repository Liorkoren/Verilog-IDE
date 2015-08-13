/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.veriloglanguage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.PropertySupport.ReadOnly;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_V_LOADER=Files of V"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_V_LOADER",
        mimeType = "text/x-v",
        extension = {"v", "V", "vh", "VH"}
)
@DataObject.Registration(
        mimeType = "text/x-v",
        iconBase = "com/koltem/veriloglanguage/verilog.png",
        displayName = "#LBL_V_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/x-v/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class VerilogDataObject extends MultiDataObject {

    public VerilogDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-v", true);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_V_EDITOR",
            iconBase = "com/koltem/veriloglanguage/verilog.png",
            mimeType = "text/x-v",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "V",
            position = 1000
    )
    @Messages("LBL_V_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }

    @Override
    protected Node createNodeDelegate() {
        return new VerilogNode(
                this,
                Children.create(new VerilogChildFactory(this), true),
                getLookup());
    }

    private static class VerilogChildFactory extends ChildFactory<String> {

        private final VerilogDataObject dObj;

        public VerilogChildFactory(VerilogDataObject dObj) {
            this.dObj = dObj;
        }

        @Override
        protected boolean createKeys(List list) {
            FileObject fObj = dObj.getPrimaryFile();
            try {
                List<String> dObjContent = fObj.asLines();
                list.addAll(dObjContent);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(String key) {
            Node childNode = new AbstractNode(Children.LEAF);
            childNode.setDisplayName(key);
            return childNode;
        }

    }

    class VerilogNode extends DataNode {

        public VerilogNode(VerilogDataObject aThis, Children kids, Lookup lookup) {
            super(aThis, kids, lookup);
            setDisplayName(this.getDisplayName());
        }

        @Override
        protected Sheet createSheet() {
            Sheet sheet = super.createSheet();
            Sheet.Set set = Sheet.createPropertiesSet();
            sheet.put(set);

            Property pathProperty = new PropertySupport.ReadOnly<String>("Name", String.class, "Path", "File Full Path") {
                @Override
                public String getValue() {
                    return (this.getDisplayName()+".v");
                }
            };
            set.put(pathProperty);

            set.put(new LineCountProperty(this));
            return sheet;
        }

        private class LineCountProperty extends ReadOnly<Integer> {

            private final VerilogNode node;

            public LineCountProperty(VerilogNode node) {
                super("lineCount", Integer.class, "Line Count", "Number of Lines");
                this.node = node;
            }

            @Override
            public Integer getValue() throws IllegalAccessException, InvocationTargetException {
                int lineCount = 0;
                DataObject abcDobj = node.getDataObject();
                FileObject abcFo = abcDobj.getPrimaryFile();
                try {
                    lineCount = abcFo.asLines().size();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
                return lineCount;
            }
        }
    }
}
