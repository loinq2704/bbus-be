package com.fpt.bbusbe.utils;

import com.fpt.bbusbe.model.enums.Role;
import com.fpt.bbusbe.model.dto.request.UserCreationRequest;
import com.fpt.bbusbe.model.enums.Gender;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static List<UserCreationRequest> excelToUsers(MultipartFile file) {
        try {
            List<UserCreationRequest> users = new ArrayList<>();
            InputStream is = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Bỏ qua dòng tiêu đề
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                UserCreationRequest user = new UserCreationRequest();
                user.setUsername(currentRow.getCell(0).getStringCellValue());
                user.setPassword(currentRow.getCell(1).getStringCellValue());
//                user.setPhone(currentRow.getCell(2).getStringCellValue());
                user.setPhone(getCellValue(currentRow.getCell(2)));
                user.setName(currentRow.getCell(3).getStringCellValue());
                user.setGender(Gender.valueOf(currentRow.getCell(4).getStringCellValue()));
//                user.setDob(Date.valueOf(currentRow.getCell(5).getStringCellValue()));
                user.setDob(Date.valueOf(getCellValue(currentRow.getCell(5))));
                user.setEmail(currentRow.getCell(6).getStringCellValue());
                user.setAvatar(currentRow.getCell(7).getStringCellValue());
                user.setAddress(currentRow.getCell(8).getStringCellValue());
//                user.setType(UserType.valueOf(currentRow.getCell(9).getStringCellValue()));
//                user.setStatus(UserStatus.valueOf(currentRow.getCell(10).getStringCellValue()));
                user.setRole(Role.valueOf(currentRow.getCell(9).getStringCellValue()));

                users.add(user);
            }
            workbook.close();
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Nếu là ngày tháng, định dạng về yyyy-MM-dd
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(cell.getDateCellValue());
                } else {
                    // Nếu là số, chuyển thành chuỗi
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }


}
