package grupo_8_sc303_mv_proyectono1;

import java.util.ArrayList;
import java.util.List;

public class CategoriaCombo {
    private String nombreCategoria;
    private List<Combo> combos;

    public CategoriaCombo(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.combos = new ArrayList<>();
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void agregarCombo(Combo combo) {
        combos.add(combo);
    }

    public List<Combo> getCombos() {
        return combos; 
    }
} 