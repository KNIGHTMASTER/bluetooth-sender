package com.tripoin.bluetoothsender.service.send;

import java.util.List;

/**
 * Created on 7/7/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IRuntimeExecutor {

    List<String> findAllJavaProcess();

    String findPIDofJavaProcess(String p_NameOfJavaProcess);

    void killAnotherJavaProcess(String p_NameOfJavaProcess);

    int startSingleProcess(String p_NameOfProcess, String p_Arguments[]);

    void executeCommand(String p_Command);
}
