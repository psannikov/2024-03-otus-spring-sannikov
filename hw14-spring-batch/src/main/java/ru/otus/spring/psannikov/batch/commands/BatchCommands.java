package ru.otus.spring.psannikov.batch.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;
import java.util.Properties;

import static ru.otus.spring.psannikov.batch.config.BatchConfig.IMPORT_USER_JOB_NAME;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final Job importUserJob;
    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    @SuppressWarnings("unused")
    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .addString(IMPORT_USER_JOB_NAME, IMPORT_USER_JOB_NAME + LocalDateTime.now())
                .toJobParameters());
        System.out.println(execution);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() throws Exception {
        Properties properties = new Properties();
        Long executionId = jobOperator.start(IMPORT_USER_JOB_NAME, properties);
        System.out.println(jobOperator.getSummary(executionId));
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_USER_JOB_NAME));
    }
}
