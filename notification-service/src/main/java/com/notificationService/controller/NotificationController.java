package com.notificationService.controller;

import com.notificationService.entities.NotifyDTO;
import com.notificationService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class NotificationController {

    @Autowired
    NotificationRepository repository;

    // Will work only if the data is sent through Restemplate from student-service
    @PostMapping("/notify")
    public String notify(@RequestBody Map<String, String> notification) {
        String data = "[MESSAGE FROM RESTTEMPLATE] Student record of : ";
        data += notification.get("name");
        data += " having ID : ";
        data += notification.get("id") + " ";
        data += (notification.get("operation") + " ").toUpperCase();
        data += "successfully!!!";
        System.out.println(data);
        return data;
    }

    @GetMapping("/getLogs/{date}")
    public String getCount(@PathVariable("date") String date) throws ParseException {
        SimpleDateFormat oo = new SimpleDateFormat("dd-MM-yyyy");
        Date obj = oo.parse(date);

        List<NotifyDTO> list = repository.findAll();
        int count = 0;
        int update = 0;
        int delete = 0;
        for (Iterator<NotifyDTO> iterator = list.iterator(); iterator.hasNext(); ) {
            NotifyDTO notifyDTO = (NotifyDTO) iterator.next();
            if (notifyDTO.getNotification().toLowerCase().contains("add")
                    && obj.equals(oo.parse(oo.format(notifyDTO.getDate()))))
                count++;
            else if (notifyDTO.getNotification().toLowerCase().contains("update")
                    && obj.equals(oo.parse(oo.format(notifyDTO.getDate()))))
                update++;
            else if (notifyDTO.getNotification().toLowerCase().contains("delete")
                    && obj.equals(oo.parse(oo.format(notifyDTO.getDate()))))
                delete++;
        }
        return "Total count of students added on date " + obj + " is : " + count + "\n"
                + "Total count of students updated on date " + obj + " is : " + update + "\n"
                + "Total count of students deleted on date  " + obj + " is : " + delete;
    }
}