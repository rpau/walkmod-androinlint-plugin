package org.walkmod.androidlint.visitors;

import java.lang.reflect.Method;

import org.walkmod.javalang.ast.MethodSymbolData;
import org.walkmod.javalang.ast.body.MethodDeclaration;
import org.walkmod.javalang.compiler.symbols.RequiresSemanticAnalysis;
import org.walkmod.javalang.visitors.VoidVisitorAdapter;
import org.walkmod.walkers.VisitorContext;

@RequiresSemanticAnalysis
public class DrawAllocationVisitor extends VoidVisitorAdapter<VisitorContext> {

   private ClassLoader cl;

   public void setClassLoader(ClassLoader cl) {
      this.cl = cl;
   }

   @Override
   public void visit(MethodDeclaration md, VisitorContext ctx) {
      if ("onDraw".equals(md.getName())) {
         MethodSymbolData msd = md.getSymbolData();
         Method method = msd.getMethod();

         Class<?> clazz = method.getDeclaringClass();
         Class<?> superClazz;
         try {
            superClazz = Class.forName("android.view.View", false, cl);
            if (superClazz.isAssignableFrom(clazz)) {
               
               //extract (non-conflicting) variables to fields
               
            }
         }catch (Throwable e) {}

      }
   }
   
   
}
