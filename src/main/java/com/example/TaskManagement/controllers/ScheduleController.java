package com.example.TaskManagement.controllers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    // Runs every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void runTaskAtFixedRate() {
        System.out.println("Running a task every 10 seconds - " + System.currentTimeMillis());
    }

    // Runs with a fixed delay of 5 seconds after the last execution completes
    @Scheduled(fixedDelay = 5000)
    public void runTaskWithFixedDelay() {
        System.out.println("Running a task with a 5-second delay - " + System.currentTimeMillis());
    }

    // Runs every day at 1:00 AM
    @Scheduled(cron = "0 0 1 * * ?")
    public void runTaskAtSpecificTime() {
        System.out.println("Running a daily task at 1 AM - " + System.currentTimeMillis());
    }
}
