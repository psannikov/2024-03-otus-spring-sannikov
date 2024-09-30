package ru.otus.spring.psannikov.front.end.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskRepoDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService {

    public void exportTasksToExcel(List<TaskRepoDto> tasks, String filepath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("parent_task");
        header.createCell(1).setCellValue("id");
        header.createCell(2).setCellValue("title");
        header.createCell(3).setCellValue("task_description");
        header.createCell(4).setCellValue("status");
        header.createCell(5).setCellValue("last_work");

        int rowIndex = 1;
        String currentParentTask = null;

        for (TaskRepoDto task : tasks) {
            String parentTask = task.getParent_task();
            if (parentTask == null || parentTask.trim().isEmpty()) {
                currentParentTask = task.getTask_description();
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(currentParentTask);
            } else {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue(task.getId());
                row.createCell(2).setCellValue(task.getTitle());
                row.createCell(3).setCellValue(task.getTask_description());
                row.createCell(4).setCellValue(task.getStatus());
                row.createCell(5).setCellValue(task.getLast_work());
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
