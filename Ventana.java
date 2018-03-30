/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbolas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author tonit
 */
public class Ventana extends JFrame implements ActionListener{
    
    private File folder = new File("C:\\DataBaseBolas\\Archivos");
    private ListaDeProductos listaproductos;
    private int ANCHO=1200, ALTO=730;
    private JPanel pFrame, pControlP;

////////////////////////Declaraciones del Panel principal///////////////////
    private JPanel vPrincipal;
    private JButton nuevo;
    private JButton buscar;
    private JButton modificar;
    public Ventana ventana;
    public JPanel pan1, pan2, pan3, pan4, pan5, pan6, pan7, subp1, subp2;
////////////////////////Declaracion del Panel Producto Nuevo///////////////////
    private String txtFoto;
    private JPanel vNuevoP;
    private JButton btnBuscarImagen;
    private JButton btnAgregar, btnBack1, btnModificar, btnEliminar;
    private JLabel precio1;
    private JLabel descripcion;
    private JLabel dirFoto;
    private JTextField txtPrecio1;      
    private JTextField txtDescripcion; 
    private JLabel txtdirFoto;
    private JPanel pN1,pN2,pN3,pN4;
//////////////////////Declaracion del Panel Buscar/////////////////////////
    private ArrayList<Producto> listaMostrarBuscar;
    private JScrollPane scrolPanel;
    private DefaultListModel lista1;
    private JPanel vBuscar;
    private JList lista;
    private JButton btnBuscar, btnCotizar, btnWerever;
    private JPanel pB1, pB2;
    private JTextField txtBuscar;
    private JLabel btnImagen;
    private JTextArea descProducto;
    private JPanel vBus1, vBus2;
    //////////////////////////////Declaraciones del panel Modificar/////////////////
    private JPanel vModificar;
    private int idx;
    
    
    
    
///////////////////Declaracion de Layouts/////////////////////////////////
    private GridLayout grLy;
    private FlowLayout flLy;    
    private Box caja;
    
    public Ventana(){
        listaproductos= new ListaDeProductos();
        
        listaproductos.cargarArchivo();
        
        grLy= new GridLayout(4,1);
        grLy.setHgap(50);
        grLy.setVgap(50);
        caja = Box.createVerticalBox();
        
        
        flLy= new FlowLayout(FlowLayout.CENTER, 3, 1);
        flLy.setHgap(70);
        flLy.setVgap(70);
        
        ///////////////////Inicializacion de Objetos del Panel Principal/////////////////////
        pan1= new JPanel();
        pan2= new JPanel();
        pan3= new JPanel();
        pan4= new JPanel();
        pan5= new JPanel();
        pan6= new JPanel();
        pan7= new JPanel();
        txtFoto= "";
        vPrincipal= new JPanel();
        nuevo= new JButton("Nuevo Producto");
        nuevo.addActionListener(this);
        nuevo.setActionCommand("nuevo");
      
        
        buscar= new JButton("Buscar");
        buscar.addActionListener(this);
        buscar.setActionCommand("buscar");
        
        modificar= new JButton("Guardar Cambios");
        modificar.addActionListener(this);
        modificar.setActionCommand("save");
        
        vPrincipal.setBackground(Color.lightGray);
        pan2.add(nuevo);    
        pan2.setLayout(new FlowLayout());
        pan4.add(buscar);
        pan4.setLayout(new FlowLayout());
        pan6.add(modificar);
        pan6.setLayout(new FlowLayout());
        
        pan1.setBackground(Color.lightGray);
        pan2.setBackground(Color.lightGray);
        pan3.setBackground(Color.lightGray);
        pan4.setBackground(Color.lightGray);
        pan5.setBackground(Color.lightGray);
        pan6.setBackground(Color.lightGray);
        pan7.setBackground(Color.lightGray);
        
        vPrincipal.add(pan1);
        vPrincipal.add(pan2);
        vPrincipal.add(pan3);
        vPrincipal.add(pan4);
        vPrincipal.add(pan5);
        vPrincipal.add(pan6);
        vPrincipal.add(pan7);
        
        vPrincipal.setLayout(new GridLayout(7,1));
        
        
        
        //////////////////Inicializacion de los objeto del panel Nuevo////////////////////////
        pN1= new JPanel();
        pN2= new JPanel();
        pN3= new JPanel();
        pN4= new JPanel();
        vNuevoP= new JPanel();
        btnBuscarImagen= new JButton("Buscar Imagen");        
        btnBuscarImagen.addActionListener(this);
        btnBuscarImagen.setActionCommand("buscarI");
        
        btnAgregar= new JButton("Guardar Cambios");        
        btnAgregar.addActionListener(this);
        btnAgregar.setActionCommand("guardarCambios");
        
        precio1= new JLabel("Precio 1 (Unidad):", JLabel.TRAILING);
        descripcion= new JLabel("Descripcion:", JLabel.TRAILING);
        dirFoto= new JLabel("", JLabel.TRAILING);
        dirFoto.setBorder(BorderFactory.createLineBorder(Color.black));
        dirFoto.setSize(50,50);
        
        subp1 = new JPanel();
        subp1.setLayout(new FlowLayout());
        subp2= new JPanel();
        
        
        dirFoto.setLabelFor(this.btnBuscarImagen);
        txtPrecio1= new JTextField(10);
        txtDescripcion= new JTextField(20);
        txtdirFoto= new JLabel();           
        vNuevoP.setLayout(grLy); 
        vNuevoP.add(pN1);
        vNuevoP.add(pN2);
        vNuevoP.add(pN3);
        vNuevoP.add(pN4);
        pN1.add(precio1);
        pN1.add(txtPrecio1);
        pN1.setLayout(new FlowLayout());
        pN2.add(descripcion);
        pN2.add(txtDescripcion);
        pN2.setLayout(new FlowLayout());
        
        subp1.add(btnBuscarImagen);
        subp2.add(dirFoto);
        
        pN3.add(subp1);
        pN3.add(subp2);
        pN3.setLayout(new GridLayout());
        pN4.add(btnAgregar);
        pN4.setLayout(new FlowLayout());
        
        
        
        /////////////////Inicializacion de los Objetos del Panel Buscar/////////////////
        vBuscar= new JPanel(); 
        vBuscar.setLayout(new BorderLayout());
        listaMostrarBuscar= new ArrayList();
        lista1= new DefaultListModel();
        scrolPanel= new JScrollPane();
        
        lista= new JList(lista1);
        lista.addListSelectionListener((ListSelectionEvent e) -> {
            idx = lista.getSelectedIndex();
            
            if (idx != -1){
                descProducto.setText("");
                descProducto.append(listaMostrarBuscar.get(idx).getDescripcion());
                descProducto.append("\nUnidad: $"+listaMostrarBuscar.get(idx).getPrecio1());
                descProducto.append("\n100 to: $"+listaMostrarBuscar.get(idx).getPrecio1()*100);
                descProducto.append("\n1,000:   $"+listaMostrarBuscar.get(idx).getPrecio1()*1000);
                descProducto.append("\nMillar: $"+listaMostrarBuscar.get(idx).getPrecio1()*10000);
                
                if (!listaMostrarBuscar.get(idx).getDirFoto().equals("SinImagen")) {
                    ImageIcon imag= new ImageIcon(listaMostrarBuscar.get(idx).getDirFoto());
                    Icon icono;
                    icono = new ImageIcon(imag.getImage().getScaledInstance(btnImagen.getWidth(), btnImagen.getHeight(), Image.SCALE_DEFAULT));
                    btnImagen.setIcon(icono);
                    btnImagen.setText("");                    
                }else{
                    btnImagen.setText("SinImagen");
                    btnImagen.setIcon(null);
                }
                
            }
            
        });
        
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.setActionCommand("eliminarProd");
        
        btnBuscar= new JButton("Buscar");
        btnBuscar.addActionListener(this);
        btnBuscar.setActionCommand("buscarProd");
        
        btnWerever= new JButton("modificar");
        btnWerever.addActionListener(this);
        btnWerever.setActionCommand("modificarProd");
        
        btnCotizar= new JButton("Cotizar");
        btnCotizar.addActionListener(this);
        btnCotizar.setActionCommand("cotizarProd");
        
        
        btnImagen= new JLabel();
        
        
        
        
        txtBuscar= new JTextField(10);
        pB1= new JPanel();
        pB1.setLayout(new FlowLayout());
        pB1.add(btnBuscar);
        pB1.add(txtBuscar);
        pB2= new JPanel();
        pB2.setLayout(new FlowLayout());
        pB2.add(btnCotizar);
        pB2.add(btnWerever);
        pB2.add(btnEliminar);
        vBus1= new JPanel();
        vBus1.setLayout(new BorderLayout());
        
        descProducto= new JTextArea();
        Font fuente=new Font("Dial"
                + ""
                + ""
                + "]g", Font.BOLD, 18);
        
       descProducto.setFont(fuente);
       vBus2= new JPanel();
       GridLayout grid2= new GridLayout(2,1);
       grid2.setHgap(10);
        grid2.setVgap(10);
        vBus2.setLayout(grid2);
        vBus2.add(btnImagen);
        vBus2.add(descProducto);
        GridLayout grid= new GridLayout();
        grid.setVgap(20);
        grid.setHgap(20);
        vBuscar.setLayout(grid);
        vBuscar.add(vBus1);
        vBuscar.add(vBus2);
        
        scrolPanel.setViewportView(lista);
        
        vBus1.add(pB1, BorderLayout.NORTH);
        vBus1.add(scrolPanel, BorderLayout.CENTER);
        vBus1.add(pB2, BorderLayout.SOUTH);
        
        /////////////////Declaraciones  del panel  Modificar//////////////////////
        
        
        
        

        
        //////////////////////////Declaracion y armado del Jframe con el Panel Principal////////////////////////////
        
                
        
        setTitle("Base de Datos");
        pControlP= new JPanel();
        pControlP.setLayout(new GridLayout());
        
        pFrame = new JPanel(); 
        pFrame.setLayout(new BorderLayout());
        this.setResizable(true);
        
        pFrame.add(vPrincipal, BorderLayout.WEST);
        pFrame.add(pControlP,BorderLayout.CENTER);
        
        this.add(pFrame);
        this.setLayout(new GridLayout());
        setSize(ANCHO,ALTO);
     

    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "nuevo":
                pControlP.remove(vBuscar);
                pControlP.add(vNuevoP);
                txtDescripcion.setEnabled(true);
                
                this.setSize(ANCHO+200,ALTO+100);            
            break;
            
            case"buscar":
                pControlP.remove(vNuevoP);
                pControlP.add(vBuscar);
                this.setSize(ANCHO+600,ALTO+150);
                                        
            break;
            
            case "buscarI":
                JFileChooser buscadorDoc= new JFileChooser();
                FileNameExtensionFilter filtro= new FileNameExtensionFilter("JPG & GIF Images","jpg","gif","png");
                buscadorDoc.setFileFilter(filtro);
                int valorDeRetorno= buscadorDoc.showOpenDialog(pN1);
                
                if (valorDeRetorno==JFileChooser.APPROVE_OPTION) {
                    txtFoto=buscadorDoc.getSelectedFile().getPath();
                    ImageIcon imag= new ImageIcon(buscadorDoc.getSelectedFile().getPath());
                    Icon icono;
                    icono = new ImageIcon(imag.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                    dirFoto.setIcon(icono);
                    
                    
                } 
            break;
            
            case "guardarCambios":
                Producto nuevoProd= new Producto(txtPrecio1.getText(), txtDescripcion.getText(), txtFoto);
                
                listaproductos.incertarNuevo(nuevoProd);
                if (txtDescripcion.isEnabled()) {
                    JOptionPane.showMessageDialog(null,"Nuevo Producto Agregado!"); 
                    
                }else{
                    JOptionPane.showMessageDialog(null,"Producto Actualizado!"); 
                
                }
                txtPrecio1.setText("");
                txtDescripcion.setText("");
                dirFoto.setIcon(null);
                dirFoto.setText("");
                
            break;
            
            case "buscarProd":
                
                listaMostrarBuscar.clear();
                lista1.clear();
                
                if(!txtBuscar.getText().equals("") && !txtBuscar.getText().equals(" ")){
                    listaMostrarBuscar.addAll(listaproductos.buscar(txtBuscar.getText()));
                    if (!listaMostrarBuscar.isEmpty()) {
                        
                        for (int i = 0; i < listaMostrarBuscar.size(); i++) {
                            lista1.addElement(listaMostrarBuscar.get(i).getDescripcion());
                        }
                                        
                    }
                }
                
                //lista1 para introducir los productos a buscar
                // casilla nombre txtBuscar
                
            break;
            
            case "modificarProd":
                         
                
                
                if (listaMostrarBuscar.size()<1) {
                    JOptionPane.showMessageDialog(null,"Busca y Selecciona un producto!"); 
                }else{                                    
                    pControlP.remove(vBuscar);
                    pControlP.add(vNuevoP);
                    this.setSize(ANCHO+200,ALTO+100);   
                    txtPrecio1.setText(""+listaMostrarBuscar.get(idx).getPrecio1());
                    txtDescripcion.setText(listaMostrarBuscar.get(idx).getDescripcion());
                    txtDescripcion.setEnabled(false);
                    if (!listaMostrarBuscar.get(idx).getDirFoto().equals("SinImagen")) {
                        ImageIcon imag= new ImageIcon(listaMostrarBuscar.get(idx).getDirFoto());
                        Icon icono;
                        icono = new ImageIcon(imag.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        dirFoto.setIcon(icono);
                    }
                }
                
                //idx
                //listaMostrarBuscar
                
             break;
             
            case "save":
                listaproductos.guardar();
                JOptionPane.showMessageDialog(null, "Cambios Guardados");
            break;
            case "eliminarProd":
                listaproductos.eliminar(listaMostrarBuscar.get(idx));
                JOptionPane.showMessageDialog(null, "Producto eliminado");
            break;
        
        }
        
        
        
    }
 }
