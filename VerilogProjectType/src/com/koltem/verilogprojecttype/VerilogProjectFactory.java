/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.verilogprojecttype;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Koren
 */
//@ServiceProvider(service = ProjectFactory2.class)
//public class VerilogProjectFactory implements ProjectFactory2 {
@ServiceProvider(service = ProjectFactory.class)
public class VerilogProjectFactory implements ProjectFactory {

    public static final String PROJECT_FILE = "verilogproject.xml";

    //Specifies when a project is a project, i.e.,
    //if "project.txt" is present in a folder:
    @Override
    public boolean isProject(FileObject projectDirectory) {
        return projectDirectory.getFileObject(PROJECT_FILE) != null;
    }

    //Specifies when the project will be opened, i.e., if the project exists:
    @Override
    public Project loadProject(FileObject dir, ProjectState state) throws IOException {
        return isProject(dir) ? new VerilogProject(dir, state) : null;
    }

    @Override
    public void saveProject(final Project project) throws IOException, ClassCastException {
        // leave unimplemented for the moment
    }
/*
    @Override
    public ProjectManager.Result isProject2(FileObject projectDirectory) {
        if (isProject(projectDirectory)) {
            Icon icon = VerilogProject.getIcon();
            return new ProjectManager.Result(icon);
        } else {
            return null;
        }
    }
*/
}
