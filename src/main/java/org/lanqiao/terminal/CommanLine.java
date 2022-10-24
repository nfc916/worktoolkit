package org.lanqiao.terminal;

public interface CommanLine {
      Runtime runtime=Runtime.getRuntime();
      void invockCmdLines(String [] cmds)  throws Exception;
}
