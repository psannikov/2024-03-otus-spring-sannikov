package ru.otus.spring.psannikov.front.end.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService {

    @Value("${application.fileName}")
    private String fileName;

    public File exportTasksToExcel(List<TaskDto> tasks) {

        File xlsxFile = new File(fileName);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");

        createHeadersRow(sheet);

        createDataRows(tasks, sheet);

        try (FileOutputStream outputStream = new FileOutputStream(xlsxFile)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xlsxFile;
    }

    private static void createHeadersRow(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("parent_task");
        header.createCell(1).setCellValue("id");
        header.createCell(2).setCellValue("title");
        header.createCell(3).setCellValue("task_description");
        header.createCell(4).setCellValue("status");
    }

    private static void createDataRows(List<TaskDto> tasks, Sheet sheet) {
        int rowIndex = 1;
        String currentParentTask = null;

        for (TaskDto task : tasks) {
            String parentTask = task.getParentName();
            if (parentTask == null || parentTask.trim().isEmpty()) {
                currentParentTask = task.getTitle();
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(currentParentTask);
            } else {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue(task.getId());
                row.createCell(2).setCellValue(task.getTitle());
                row.createCell(3).setCellValue(task.getDescription());
                row.createCell(4).setCellValue(task.getStatusDescription());
            }
        }
    }
}
