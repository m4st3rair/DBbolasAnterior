
package dbolas;

public class Producto {
        private double precio1;        
        private String descripcion; // de solo 65 caracteres
        private String dirFoto;
        private int indiceProd;


    public Producto(String precio1, String descripcion, String dirFoto){
        this.precio1=Double.parseDouble(precio1);
        this.descripcion=descripcion;
        if (dirFoto.equals("")) {
            this.dirFoto="SinFoto";
        }else{
            this.dirFoto= dirFoto;        
        }
    }
    public double getPrecio1() {
        return precio1;
    }
    public void setPrecio1(double precio1) {
        this.precio1 = precio1;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDirFoto() {
        return dirFoto;
    }
    public void setDirFoto(String dirFoto) {
        this.dirFoto = dirFoto;
    }
    public int getIndiceProd() {
        return indiceProd;
    }

    public void setIndiceProd(int indiceProd) {
        this.indiceProd = indiceProd;
    }
}
