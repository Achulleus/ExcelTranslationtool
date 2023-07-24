import org.apache.poi.ss.usermodel.CellStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoMaps {
    private Map<Integer, List<String>> data = new HashMap<>();
    private Map<Integer, List<CellStyle>> cellStyles = new HashMap<>();

    public TwoMaps(Map<Integer, List<String>> data, Map<Integer, List<CellStyle>> cellSyles){
        this.data = data;
        this.cellStyles = cellSyles;
    }

    public Map<Integer, List<String>> getData() {
        return data;
    }

    public void setData(Map<Integer, List<String>> data) {
        this.data = data;
    }

    public Map<Integer, List<CellStyle>> getCellStyles() {
        return cellStyles;
    }

    public void setCellStyles(Map<Integer, List<CellStyle>> cellStyles) {
        this.cellStyles = cellStyles;
    }

    //Bei Fehler: Funktioniert data = data. Map in andere Map kopieren?
}
