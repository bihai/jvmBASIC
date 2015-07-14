package com.khubla.jvmbasic.jvmbasicc.compiler.analysis.lines;

import java.util.TreeMap;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.objectweb.asm.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.jvmbasic.jvmbasicc.antlr.jvmBasicParser.LineContext;
import com.khubla.jvmbasic.jvmbasicc.antlr.jvmBasicParser.LinenumberContext;
import com.khubla.jvmbasic.jvmbasicc.antlr.jvmBasicParser.ProgContext;
import com.khubla.jvmbasic.jvmbasicc.compiler.analysis.Analyser;
import com.khubla.jvmbasic.jvmbasicc.compiler.iterator.LineIterator;
import com.khubla.jvmbasic.jvmbasicc.compiler.iterator.LineIteratorCallback;

/*
 * jvmBasic Copyright 2012, khubla.com
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * @author tom
 */
public class LinesDatabase implements LineIteratorCallback, Analyser {
   /**
    * logger
    */
   private static final Logger logger = LoggerFactory.getLogger(LinesDatabase.class);
   /**
    * all lines
    */
   private final TreeMap<Integer, LineDeclaration> lines = new TreeMap<Integer, LineDeclaration>();

   /**
    * add a label
    */
   private LineDeclaration addLine(LineContext lineContext, int codeLine, int basicLine) {
      if (null == getLine(basicLine)) {
         final LineDeclaration lineDeclaration = new LineDeclaration(lineContext, codeLine, basicLine, new Label());
         lines.put(basicLine, lineDeclaration);
         return lineDeclaration;
      } else {
         return null;
      }
   }

   @Override
   public void analyse(ProgContext progContext) throws Exception {
      LineIterator.iterate(progContext, this);
   }

   @Override
   public void dumpAnalysis() throws Exception {
      logger.info("Lines");
      for (final LineDeclaration lineDeclaraction : lines.values()) {
         logger.info("[" + lineDeclaraction.getCodeLine() + ":" + lineDeclaraction.getBasicLine() + "] " + lineDeclaraction.getLineContext().getText());
      }
   }

   /**
    * get line
    */
   public LineDeclaration getLine(int lineNumber) {
      return lines.get(lineNumber);
   }

   /**
    * get next line number
    */
   public int getNextLineNumber(int lineNumber) {
      return lines.higherKey(lineNumber);
   }

   @Override
   public void line(LineContext lineContext) {
      final ParseTree subTree = lineContext.getChild(0);
      if (subTree.getClass() != TerminalNodeImpl.class) {
         final LinenumberContext linenumberContext = (LinenumberContext) subTree;
         final int basicLineNumber = Integer.parseInt(linenumberContext.getText());
         final int codeLineNumber = lineContext.start.getLine();
         addLine(lineContext, codeLineNumber, basicLineNumber);
      }
   }
}
