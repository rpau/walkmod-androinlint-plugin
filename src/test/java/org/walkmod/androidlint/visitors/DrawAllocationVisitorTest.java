package org.walkmod.androidlint.visitors;

import org.junit.Assert;
import org.junit.Test;
import org.walkmod.javalang.ast.CompilationUnit;
import org.walkmod.javalang.ast.body.TypeDeclaration;
import org.walkmod.javalang.test.SemanticTest;

public class DrawAllocationVisitorTest extends SemanticTest {

   @Test
   public void testExtractVarFromOnDraw() throws Exception {
      CompilationUnit cu = compile(
       "import android.view.View; "
      +"import android.util.AttributeSet; "
      +"import android.content.Context; "
      +"import android.graphics.Paint;"
      +"import android.graphics.Color;"
      +"import android.graphics.Canvas;"
      
      + "class PieChart extends View {"
            + "public PieChart(Context context, AttributeSet attrs) {" +
            "   super(context, attrs);" +
            "}" + 
            
            "protected void onDraw(Canvas canvas) {" +
            "  super.onDraw(canvas);" +
               "int x=80;"+
               "int y=80;"+
               "int radius=40;"+
               "Paint paint=new Paint();"+
               "paint.setColor(Color.parseColor(\"#CD5C5C\"));"+
               "canvas.drawCircle(x,x, radius, paint);"+
            "}" + 
         "}");
      DrawAllocationVisitor visitor = new DrawAllocationVisitor();
      cu.accept(visitor, null);
      
      TypeDeclaration td = cu.getTypes().get(0);
      
      Assert.assertEquals(3, td.getMembers().size());
   }
}
