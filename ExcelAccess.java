import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelAccess {
    private String fileLocation;
    private String newFileLocation;

    public ExcelAccess(String fileLocation, String newFileLocation){
        this.fileLocation =fileLocation;
        this.newFileLocation = newFileLocation;
    }

    public TwoMaps readSite(int sitenumber) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(sitenumber);
        Map<Integer, List<String>> data = new HashMap<>();
        Map<Integer, List<CellStyle>> cellStyles = new HashMap<>();
        TwoMaps cdata;
        int i = 0;
        for(Row row : sheet){
            data.put(i, new ArrayList<String>());
            cellStyles.put(i, new ArrayList<CellStyle>());
            for(Cell cell : row){
                switch (cell.getCellType()){
                    case STRING:
                        data.get(i).add(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i).add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i).add(cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        data.get(i).add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        data.get(i).add(cell.getCellFormula() + "");
                        break;
                    default:
                        data.get(i).add(" ");
                        break;
                }
                cellStyles.get(i).add(cell.getCellStyle());
            }
            i++;
        }
        cdata = new TwoMaps(data, cellStyles);
        return cdata;
    }

    public List<TwoMaps> readFile(int startSite, int endSite) throws IOException {
        List<Map> data = new ArrayList<Map>();
        List<Map> cellStyles = new ArrayList<Map>();
        List<TwoMaps> cdata = new ArrayList<TwoMaps>();

        for(int i = startSite; i <= endSite; i++){
            data.add(readSite(i).getData());
            cellStyles.add(readSite(i).getCellStyles());
            cdata.add(new TwoMaps(data.get(i), cellStyles.get(i)));
        }
        return cdata;
    }

    public void writeSite(Map <Integer, List<String>> data, Map<Integer, List<CellStyle>> cellStyles, String name) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(name);
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row row;
        Cell cell;
        int i;
        for(int key : data.keySet()){
            i = 0;
            row = sheet.createRow(key + 1);
            for(String value : data.get(key)){
                cell = row.createCell(i + 1);
                cell.setCellValue(value);
                cell.setCellStyle(cellStyles.get(key).get(i));
                i++;
            }
        }

        File dir = new File(newFileLocation);
        String path = dir.getAbsolutePath();
        String currFileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(currFileLocation);
        workbook.write(outputStream);
        workbook.close();
    }

    public void writeFile(List<TwoMaps> cdata, List<String> name) throws IOException {
        int i = 0;
        for(TwoMaps map : cdata){
            writeSite(map.getData(), map.getCellStyles(), name.get(i));
            i++;
        }
    }
}
