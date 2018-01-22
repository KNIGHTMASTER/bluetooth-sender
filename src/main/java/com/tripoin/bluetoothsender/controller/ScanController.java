package com.tripoin.bluetoothsender.controller;

import com.tripoin.bluetoothsender.model.DeviceDTO;
import com.tripoin.bluetoothsender.model.SendDTO;
import com.tripoin.bluetoothsender.service.scanner.IScannerService;
import com.tripoin.bluetoothsender.service.send.IRuntimeExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
public class ScanController {

    @Autowired
    private IScannerService scannerService;

    @Autowired
    private IRuntimeExecutor runtimeExecutor;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanController.class);

    @RequestMapping("/display")
    public String display() {
        return "list-device";
    }

    @PostMapping("/scan")
    public ModelAndView scanDevice() {
        List<DeviceDTO> deviceDTOs = scannerService.scan();
        return new ModelAndView("list-device", "devices", deviceDTOs);
    }

    @GetMapping("/display-send")
    public ModelAndView displaySend(){
        return new ModelAndView("display-send", "sendDTO", new SendDTO());
    }
    @PostMapping("/send")
    public String sendFile(@ModelAttribute SendDTO p_SendDTO) {
        LOGGER.info("Send Data "+p_SendDTO.toString());
        runtimeExecutor.executeCommand("obexftp -b "+p_SendDTO.getMacAddress()+" -p "+p_SendDTO.getFilePath());
        return "list-device";
    }

    @GetMapping("/runcommand")
    public String runCommand() {
        runtimeExecutor.executeCommand("bluetoothctl");
        return "list-device";
    }

    @GetMapping("/runcommand2")
    public String runCommand2() {
        runtimeExecutor.executeCommand("devices");
        return "list-device";
    }
}