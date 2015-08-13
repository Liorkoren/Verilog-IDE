/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koltem.veriloglanguage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "com.koltem.veriloglanguage.StartAnalyzerActionListener"
)
@ActionRegistration(
        displayName = "#CTL_StartAnalyzerActionListener"
)
@ActionReference(path = "Loaders/text/x-v/Actions", position = 0)
@Messages("CTL_StartAnalyzerActionListener=Start Analyzer")
public final class StartAnalyzerActionListener implements ActionListener {

    private final VerilogDataObject context;

    public StartAnalyzerActionListener(VerilogDataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        FileObject f = context.getPrimaryFile();
        String displayName = FileUtil.getFileDisplayName(f);
        String msg = "I am " + displayName + ". Hear me roar!";
        msg += System.lineSeparator() + "Module N7ame : ";
        msg += searchUsingBufferedReader(displayName, "module");
        NotifyDescriptor nd = new NotifyDescriptor.Message(msg);
        DialogDisplayer.getDefault().notify(nd);
    }

    private static String searchUsingBufferedReader(String filePath, String searchQuery) /*throws IOException*/ {
        searchQuery = searchQuery.trim();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(searchQuery)) {
                    return line;
                } else {
                }
            }
        } catch (FileNotFoundException fnfex) {
            System.err.println("Exception while openning file " + fnfex.toString());
            Exceptions.printStackTrace(fnfex);
        } catch (IOException ioex) {
            System.err.println("Exception while reading file " + ioex.toString());
            Exceptions.printStackTrace(ioex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.err.println("Exception while closing bufferedreader " + e.toString());
                Exceptions.printStackTrace(e);
            }
        }
        return "NA";//null;
    }
}
