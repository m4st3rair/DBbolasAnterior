package dbolas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;





public class ListaDeProductos {
    private ArrayList<Producto> listaProductos;
    private int indiceDeProd;
    private File folder = new File("C:\\DataBaseBolas\\Archivos");
    
    
    public ListaDeProductos(){    
        indiceDeProd=0;
        listaProductos= new ArrayList<>();
    }

    public void cargarArchivo(){
        
       if(!folder.exists()){
           JOptionPane.showMessageDialog(null,"Creando Directorio");
           folder.mkdirs();
        }
       
       
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).

            archivo = new File (folder+"\\lista.txt");
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(null,"Creando Archivo");
                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (archivo.createNewFile())
                      System.out.println("El fichero se ha creado correctamente");
                    else
                      System.out.println("No ha podido ser creado el fichero");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
               // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                incertarNuevoString(linea);
            }
        catch(Exception e){
              
            e.printStackTrace();
        }finally{
               // En el finally cerramos el fichero, para asegurarnos
               // que se cierra tanto si todo va bien como si salta 
               // una excepcion.
            try{                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
                  e2.printStackTrace();
            }
        }

       
       
        
    }
    
    
    public void incertarNuevoString(String producto){
        String descripcion="", dirFoto="",precio="";
        int bandera=0;          //indica lo que se esta leyendo 0=Descripcion, 1 = Precio, 2 = Direccion de la Imagen
        char[] nProducto= producto.toCharArray();
        
        for (int i = 0; i < nProducto.length; i++) {
            switch(nProducto[i]){
                case '$':
                    bandera=1;
                    i++;
                break;
                case '+':
                    bandera=2;
                    i++;
                break;
                default:
                break;
            }            
            switch(bandera){
                case 0:
                    descripcion= descripcion+nProducto[i];
                break;
                case 1:
                    precio=precio+nProducto[i];
                break;
                case 2:
                    dirFoto=dirFoto+nProducto[i];
                break;
            }
            
        }
        precio=String.valueOf(sinEspacios(precio.toCharArray()));
        
        Producto nuevoProducto= new Producto(precio, descripcion, dirFoto);
        this.incertarNuevo(nuevoProducto);
        
    }

    public ArrayList<Producto> buscar(String prodFoud){   //Algorithmo de busqueda binaria
        if (prodFoud.equals("ALL")) {
            return listaProductos;
        }
        ArrayList<Producto> prod= new ArrayList();
        String prodFound;
        String prodBuscado;
        for (int i = 0; i < listaProductos.size(); i++) {
            prodFound=convertidor(listaProductos.get(i).getDescripcion().toCharArray());
            prodBuscado=convertidor(prodFoud.toCharArray());
            if (prodFound.contains(prodBuscado)) {
                prod.add(listaProductos.get(i));
            }    
        }
        return prod;
    }

    public void incertarNuevo(Producto pNuevo){     //Algoritmo de incercion directa
        
        if (listaProductos.isEmpty()) {
            listaProductos.add(pNuevo);
        }else{
            //las letras A son anteriores B, C, D no se distinguen mayusculas de minusculas
            if(antesDespues(listaProductos.get(0), pNuevo)== 1){        //Caso donde el producto nuevo va antes que el primer producto en lista
                listaProductos.add(0, pNuevo);
            }else if(antesDespues(listaProductos.get(listaProductos.size()-1), pNuevo)== 2){ //Caso donde el producto nuevo va despues del producto final en lista
                listaProductos.add(pNuevo);
            }else{                                                      //Caso en el que se tiene que recorrer todo el array para encontrar el lugar de la incescion
                for (int i = 1; i < listaProductos.size(); i++) {
                    if (antesDespues(listaProductos.get(i), pNuevo)== 1) {
                        listaProductos.add(i, pNuevo);
                        break;
                    }else{
                        
                        if (antesDespues(listaProductos.get(i), pNuevo)== 3) {
                            listaProductos.get(i).setDescripcion(pNuevo.getDescripcion());
                            listaProductos.get(i).setDirFoto(pNuevo.getDirFoto());
                            listaProductos.get(i).setPrecio1(pNuevo.getPrecio1());
                            break;
                        }
                    }
                    
                }
            }
            
        }
        
        
    }
    
    public void eliminar(Producto prod){
        for (int i = 0; i < listaProductos.size(); i++) {
            if (prod.getDescripcion().equals(listaProductos.get(i).getDescripcion())) {
                listaProductos.remove(i);
                break;
            }
        }
    }
    
    public void guardar(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(folder+"\\lista.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < listaProductos.size(); i++){ 
                pw.println(listaProductos.get(i).getDescripcion()+"$"+listaProductos.get(i).getPrecio1()+"+"+listaProductos.get(i).getDirFoto());
            }
        }catch(Exception ed) {
            ed.printStackTrace();
        } finally {
            try {
                        // Nuevamente aprovechamos el finally para 
                        // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
            e2.printStackTrace();
            }
        }

        
    }
    
    public String convertidor(char[] mayusMinus){ //Funcion para tener un texto libre de Espacios y sin distincion de mayusculas y minusculas
        char[] homogeneo = sinEspacios(deMayusculasAMinusculas(mayusMinus));
        return String.valueOf(homogeneo);
    } 
    
    public char[] convertidorC(char[] mayusMinus){ //Funcion para tener un texto libre de Espacios y sin distincion de mayusculas y minusculas
        char[] homogeneo = sinEspacios(deMayusculasAMinusculas(mayusMinus));
        return homogeneo;
    }
    
    public char[] sinEspacios(char[] espacios){
        int numeroEspacios=espacios.length;
        for(int i=0;i<espacios.length;i++){
            if(espacios[i]==32){
                numeroEspacios--;
            }
        }
        char[] nuevo= new char[numeroEspacios];
        int j=0;
        for (int i = 0; i < espacios.length; i++) {
            if(espacios[i]!=' '){
                nuevo[j]=espacios[i];
                j++;
            }
        }
        return nuevo;
    }
    
    public  char[] deMayusculasAMinusculas(char[] convinado){
        char[] homogeneo= new char[convinado.length];
        for (int i = 0; i < convinado.length; i++) {
            if (convinado[i]>64 && convinado[i]<91) {
                int conv=convinado[i]+32;
                homogeneo[i]= (char)conv;
            }else{
                homogeneo[i]= convinado[i];
            }
        }
        return homogeneo;
    }
    
    public int antesDespues(Producto pAnterior, Producto pNuevo){
        char[] pAnt= pAnterior.getDescripcion().toCharArray();
        char[] pNue= pNuevo.getDescripcion().toCharArray();
        pAnt=convertidorC(pAnt);
        pNue=convertidorC(pNue);
        int menor;
        
        
        if(pAnt.length<pNue.length){
            menor=pAnt.length;
        }else{
            menor=pNue.length;
        }
       
        for (int i = 0; i < menor; i++) {
            
            if ((int)pAnt[i]>(int)pNue[i]) {
                return 1;
            }else if((int)pAnt[i]<(int)pNue[i]){
                return 2;
            }
            
        }
        if(pAnt.length<pNue.length){
            return 2;
        }else if (pAnt.length>pNue.length) {
            return 1;
        }
        return 3;//para cuando los productos son iguales
    }
    
    
}
