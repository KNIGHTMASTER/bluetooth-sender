package com.tripoin.bluetoothsender.service.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/7/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class RuntimeExecutor implements IRuntimeExecutor{

    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeExecutor.class);

    @Override
    public List<String> findAllJavaProcess() {
        List<String> result = new ArrayList<>();
        String [] COMMAND = {"pgrep", "-fl", "java"};
        try {
            String process;
            Process p = Runtime.getRuntime().exec(COMMAND);
            p.waitFor();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null) {
                result.add(process);
            }
            input.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String findPIDofJavaProcess(String p_NameOfJavaProcess) {
        List<String> allJavaProcess = findAllJavaProcess();
        String PID = null;
        for (String jp : allJavaProcess){
            if (jp.contains(p_NameOfJavaProcess)){
                LOGGER.info(jp);
                PID = jp.split(" ")[0];
            }
        }
        return PID;
    }

    @Override
    public void killAnotherJavaProcess(String p_NameOfJavaProcess) {
        String PID = findPIDofJavaProcess(p_NameOfJavaProcess);
        if (PID != null){
            final String COMMAND = "kill -9 ".concat(PID);
            try {
                Runtime.getRuntime().exec(COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                LOGGER.info(p_NameOfJavaProcess.concat(" Has Been Killed!"));
            }
        }

    }

    @Override
    public int startSingleProcess(String p_NameOfProcess, String p_Arguments[]) {
        String command = "java -jar ".concat(p_NameOfProcess);
        String arguments = " ";
        if (p_Arguments.length > 0){
            for (String  args : p_Arguments){
                arguments += args.concat(" ");
            }
        }
        command += arguments.concat(" &");
        LOGGER.info("COMMAND "+command);
        try {
            String process;
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null) {
                LOGGER.info(process);
                if (process.contains("Started DaemonApp"))
                    break;
            }
            input.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void executeCommand(String p_Command) {
        LOGGER.info("Command "+p_Command);
        try {
            String process;
            Process p = Runtime.getRuntime().exec(p_Command);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null) {
                LOGGER.info(process);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
