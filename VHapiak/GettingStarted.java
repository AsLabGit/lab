package gettingstarted2;

import recoder.CrossReferenceServiceConfiguration;
import recoder.io.*;    
import recoder.java.CompilationUnit;
import recoder.service.*;
import recoder.java.declaration.*; 
import recoder.abstraction.*;
import recoder.convenience.TreeWalker;
import recoder.java.reference.MemberReference;
import java.util.*;

public class GettingStarted2 {
    public static void main(String[] args) {
        CrossReferenceServiceConfiguration crsc = new CrossReferenceServiceConfiguration();
        
        crsc.getProjectSettings().setProperty(PropertyNames.INPUT_PATH, "check");
        
        crsc.getProjectSettings().ensureSystemClassesAreInPath();
        
        SourceFileRepository sfr = crsc.getSourceFileRepository();
        List<CompilationUnit> cul = null;
        try {
            cul = sfr.getAllCompilationUnitsFromPath();
            }
        catch (Exception ex1) {
            System.out.println("Exception ex1");
            }
                
        crsc.getChangeHistory().updateModel();
        
        for (CompilationUnit cu : cul) {
            TreeWalker tw = new TreeWalker(cu);
            while (tw.next()) {
                System.out.println(tw.getProgramElement().getClass());
            }
        }
        
        NameInfo ni = crsc.getNameInfo();
        ClassDeclaration cd = (ClassDeclaration)ni.getClassType("AnyClass");
        MethodDeclaration md = (MethodDeclaration)cd.getMethods().get(0);
        
        
        List<MemberReference> mrs = crsc.getCrossReferenceSourceInfo().getReferences(md);
        
        
        System.out.println(mrs.size());
    }
}
