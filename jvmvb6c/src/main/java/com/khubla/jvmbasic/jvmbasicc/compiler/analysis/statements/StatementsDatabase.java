package com.khubla.jvmbasic.jvmbasicc.compiler.analysis.statements;

import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.jvmbasic.jvmbasicc.antlr.jvmBasicParser.AmprstmtContext;
import com.khubla.jvmbasic.jvmbasicc.antlr.jvmBasicParser.ProgContext;
import com.khubla.jvmbasic.jvmbasicc.compiler.analysis.Analyser;
import com.khubla.jvmbasic.jvmbasicc.compiler.analysis.lines.LineDeclaration;
import com.khubla.jvmbasic.jvmbasicc.compiler.analysis.lines.LinesDatabase;
import com.khubla.jvmbasic.jvmbasicc.compiler.iterator.StatementIterator;
import com.khubla.jvmbasic.jvmbasicc.compiler.iterator.StatementIteratorCallback;

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
public class StatementsDatabase implements Analyser, StatementIteratorCallback {
   /**
    * logger
    */
   private static final Logger logger = LoggerFactory.getLogger(StatementsDatabase.class);
   /**
    * all lines
    */
   private final TreeMap<Integer, StatementDeclaration> statements = new TreeMap<Integer, StatementDeclaration>();
   private final LinesDatabase linesDatabase;

   public StatementsDatabase(LinesDatabase linesDatabase) {
      this.linesDatabase = linesDatabase;
   }

   @Override
   public void analyse(ProgContext progContext) throws Exception {
      final StatementIterator statementIterator = new StatementIterator(linesDatabase);
      statementIterator.iterate(progContext, this);
   }

   @Override
   public void dumpAnalysis() throws Exception {
      /*
       * walk the statements
       */
      logger.info("Statements");
      for (final StatementDeclaration statementDeclaration : statements.values()) {
         logger.info("[" + statementDeclaration.getLineDeclaration().getCodeLine() + ":" + statementDeclaration.getLineDeclaration().getBasicLine() + ":" + statementDeclaration.getLineIndex() + "] "
               + statementDeclaration.getAmprstmtContext().getText());
      }
   }

   public StatementDeclaration findStatementDeclaration(LineDeclaration lineDeclaration) {
      return statements.get(lineDeclaration.getBasicLine());
   }

   public TreeMap<Integer, StatementDeclaration> getStatements() {
      return statements;
   }

   @Override
   public void statement(LineDeclaration lineDeclaration, AmprstmtContext amprstmtContext, int lineIndex) {
      final StatementDeclaration statementDeclaration = new StatementDeclaration(amprstmtContext, lineIndex, lineDeclaration);
      statements.put(lineDeclaration.getBasicLine(), statementDeclaration);
   }
}
