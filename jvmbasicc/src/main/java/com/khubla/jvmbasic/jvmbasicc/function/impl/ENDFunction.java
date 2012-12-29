package com.khubla.jvmbasic.jvmbasicc.function.impl;

import com.khubla.jvmbasic.jvmbasicc.compiler.GenerationContext;
import com.khubla.jvmbasic.jvmbasicc.function.BaseFunction;

/**
 * @author tome
 */
public class ENDFunction extends BaseFunction {
   @Override
   public boolean execute(GenerationContext generationContext) throws Exception {
      try {
         /*
          * we're done!
          */
         return false;
      } catch (final Exception e) {
         throw new Exception("Exception in execute", e);
      }
   }
}
