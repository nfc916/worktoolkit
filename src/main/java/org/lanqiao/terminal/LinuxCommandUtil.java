package org.lanqiao.terminal;

public class LinuxCommandUtil implements CommanLine {



    /**
     *  工具方法二：执行指令
     * @param cmdlines  需要批量执行的终端操作命令集合，不能执行阻塞式命令
     * @throws Exception
     */
    public  void invockCmdLines(String [] cmdlines) throws Exception{
        Process  process =null;
        for(String scmd:cmdlines){
            process = runtime.exec(new String[] { "zsh", "-c", scmd });
            while(process.isAlive()){
                //等待命令执行完毕，不能执行阻塞式命令
            }
        }
        Thread.sleep(100);
    }


}
