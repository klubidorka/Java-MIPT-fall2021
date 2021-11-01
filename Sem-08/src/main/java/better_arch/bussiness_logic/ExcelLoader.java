package better_arch.bussiness_logic;

import better_arch.db_connection.User;
import better_arch.db_connection.UserDao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Creates Excel tables representing results of QueryRunner
 */
public class ExcelLoader {
    private final static String OUTPUT_XLS_PATH = "./query_results/";
    private final UserDao userDao;

    public ExcelLoader(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Creates excel table with given data
     *
     * @param heading     sheet's name
     * @param columnNames column names
     * @param data        list of rows
     * @param filePath    where to store the table
     * @throws IOException if a problem while saving the table to the filesystem occurred
     */
    private void buildExcelTable(String heading, String[] columnNames,
                                 Iterable<String[]> data, String filePath) throws IOException {
        try (Workbook book = new HSSFWorkbook()) {
            Sheet sheet = book.createSheet(heading);
            Row firstRow = sheet.createRow(0);

            CellStyle style = book.createCellStyle();
            Font font = book.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
            style.setLocked(true);

            // Fill first row with columns names
            int j = 0;
            for (String rawCell : columnNames) {
                Cell currentCell = firstRow.createCell(j++);
                currentCell.setCellValue(rawCell);
                currentCell.setCellStyle(style);
            }

            // Fill all the rest rows
            int i = 1;
            for (String[] rawRow : data) {
                Row row = sheet.createRow(i++);
                j = 0;
                for (String rawCell : rawRow) {
                    Cell currentCell = row.createCell(j++);
                    currentCell.setCellValue(rawCell);
                }
            }

            // Resize columns to fit their data
            for (int x = 0; x < sheet.getRow(0).getPhysicalNumberOfCells(); x++) {
                sheet.autoSizeColumn(x);
            }

            // Save the table
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            book.write(new FileOutputStream(filePath));
        }
    }

    public void buildUsersTable() throws IOException, SQLException {
        Set<User> users = userDao.getAllUsers();
        List<String[]> rawData = new ArrayList<>(users.size());
        for (User user : users) {
            rawData.add(new String[]{user.getName(), user.getAge().toString()});
        }

        buildExcelTable("Our users", new String[]{"Name", "Age"}, rawData, OUTPUT_XLS_PATH + "users.xlsx");
    }
}