package org.kidmasta.cmdboot.service.impl;

import org.kidmasta.cmdboot.service.CommandService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by root on 14/06/2015.
 */
@Service(value="CommandService")
public class CommandServiceImpl implements CommandService {

    private Log log = LogFactory.getLog(CommandServiceImpl.class);

    public String executeCommand(String command) {
        log.info("executeCommand ["+command +"]");


        String out=null;
        String errors=null;
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader outputReader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader processErrorReader =
                    new BufferedReader(new InputStreamReader(process.getErrorStream()));

            out = processLines(processErrorReader);
            errors = processLines(processErrorReader);


        } catch (IOException |InterruptedException e) {
            log.error("Fatal error" ,e);
        }


        log.info("executeCommand ["+command +"]["+out+"]["+errors+"]");
        return out;
    }


    private String processLines(BufferedReader reader) throws IOException {
        StringBuffer output = new StringBuffer();
        String line = "";
        String out = null;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }
        out=output.toString();
        return out;

    }
}
