package com.khubla.jvmbasic.jvmbasicc.function.impl;

import org.objectweb.asm.Opcodes;

import com.khubla.jvmbasic.jvmbasicc.compiler.GenerationContext;
import com.khubla.jvmbasic.jvmbasicc.compiler.RTLHelper;
import com.khubla.jvmbasic.jvmbasicc.function.BaseFunction;

/**
 * <p>
 * <code>
 *       executionContext.push(StringFunctions.MID(executionContext.resolveValue(executionContext.pop()), executionContext.resolveValue(executionContext.pop()),
 *           executionContext.resolveValue(executionContext.pop())));
 * </code>
 * </p>
 * 
 * @author tome
 */
public class MIDFunction extends BaseFunction {
   @Override
   public boolean execute(GenerationContext generationContext) throws Exception {
      try {
         /*
          * get the operands
          */
         processTree(generationContext);
         /*
          * there should be 7 values, of which are ( <string>, <start>, <length> )
          */
         if (generationContext.getCommonTree().getChildCount() == 7) {
            generationContext.getMethodVisitor().visitVarInsn(Opcodes.ALOAD, 0);
            generationContext.getMethodVisitor().visitFieldInsn(Opcodes.GETFIELD, generationContext.getClassName(), RTLHelper.EXECUTIONCONTEXT_NAME, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT_TYPE);
            generationContext.getMethodVisitor().visitVarInsn(Opcodes.ALOAD, 0);
            generationContext.getMethodVisitor().visitFieldInsn(Opcodes.GETFIELD, generationContext.getClassName(), RTLHelper.EXECUTIONCONTEXT_NAME, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT_TYPE);
            generationContext.getMethodVisitor().visitVarInsn(Opcodes.ALOAD, 0);
            generationContext.getMethodVisitor().visitFieldInsn(Opcodes.GETFIELD, generationContext.getClassName(), RTLHelper.EXECUTIONCONTEXT_NAME, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT_TYPE);
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKEVIRTUAL, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT, "pop", "()Lcom/khubla/jvmbasic/jvmbasicrt/Value;");
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKEVIRTUAL, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT, "resolveValue",
                  "(Lcom/khubla/jvmbasic/jvmbasicrt/Value;)Lcom/khubla/jvmbasic/jvmbasicrt/Value;");
            generationContext.getMethodVisitor().visitVarInsn(Opcodes.ALOAD, 0);
            generationContext.getMethodVisitor().visitFieldInsn(Opcodes.GETFIELD, generationContext.getClassName(), RTLHelper.EXECUTIONCONTEXT_NAME, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT_TYPE);
            generationContext.getMethodVisitor().visitVarInsn(Opcodes.ALOAD, 0);
            generationContext.getMethodVisitor().visitFieldInsn(Opcodes.GETFIELD, generationContext.getClassName(), RTLHelper.EXECUTIONCONTEXT_NAME, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT_TYPE);
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKEVIRTUAL, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT, "pop", "()Lcom/khubla/jvmbasic/jvmbasicrt/Value;");
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKEVIRTUAL, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT, "resolveValue",
                  "(Lcom/khubla/jvmbasic/jvmbasicrt/Value;)Lcom/khubla/jvmbasic/jvmbasicrt/Value;");
            generationContext.getMethodVisitor().visitVarInsn(Opcodes.ALOAD, 0);
            generationContext.getMethodVisitor().visitFieldInsn(Opcodes.GETFIELD, generationContext.getClassName(), RTLHelper.EXECUTIONCONTEXT_NAME, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT_TYPE);
            generationContext.getMethodVisitor().visitVarInsn(Opcodes.ALOAD, 0);
            generationContext.getMethodVisitor().visitFieldInsn(Opcodes.GETFIELD, generationContext.getClassName(), RTLHelper.EXECUTIONCONTEXT_NAME, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT_TYPE);
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKEVIRTUAL, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT, "pop", "()Lcom/khubla/jvmbasic/jvmbasicrt/Value;");
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKEVIRTUAL, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT, "resolveValue",
                  "(Lcom/khubla/jvmbasic/jvmbasicrt/Value;)Lcom/khubla/jvmbasic/jvmbasicrt/Value;");
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKESTATIC, "com/khubla/jvmbasic/jvmbasicrt/StringFunctions", "MID",
                  "(Lcom/khubla/jvmbasic/jvmbasicrt/Value;Lcom/khubla/jvmbasic/jvmbasicrt/Value;Lcom/khubla/jvmbasic/jvmbasicrt/Value;)Ljava/lang/String;");
            generationContext.getMethodVisitor().visitMethodInsn(Opcodes.INVOKEVIRTUAL, RTLHelper.JASIC_RUNTIME_EXECUTIONCONTEXT, "push", "(Ljava/lang/String;)V");
            return true;
         } else {
            throw new Exception("Invalid number of values passed to DIM");
         }
      } catch (final Exception e) {
         throw new Exception("Exception in execute", e);
      }
   }
}
