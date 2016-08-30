/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author juan
 */
public class VentasFrame extends javax.swing.JFrame {

    /**
     * Creates new form VentasFrame
     */
    public VentasFrame() {
        initComponents();
        
        Clientes(jcmbcliente);
        Productox(jcmbProductos);
    }
int activo1=0;
int activo2=0;
int activo3=0;
int filas=0;
int columnas=0;
    
public String llenartabla(int Cantidad, String Producto){
                    try {
            Connection cn = Conexion.conectar("SQL");
            Connection cn1 = Conexion.conectar("MySQL");
            Connection cn2 = Conexion.conectar("Oracle");
            int fila=0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select existencia from Productos where descripcion='"+Producto+"'");
             if(rs.next())
             if(rs.getInt(1)>= Cantidad){
                 jtabledatos.setValueAt(jcmbProductos.getSelectedItem(), fila, 0);
                 jtabledatos.setValueAt(Cantidad, fila, 1);
                 jtabledatos.setValueAt(jtxtprecio.getText(), fila, 2);
                 jtabledatos.setValueAt(Integer.parseInt(jtxtprecio.getText())*Cantidad, fila, 3);
                 jtabledatos.setValueAt(1, fila, 4);
                 activo1=1;
                 return "Termino";
             }
             else{
                 jtabledatos.setValueAt(jcmbProductos.getSelectedItem(), fila, 0);
                 jtabledatos.setValueAt(rs.getInt(1), fila, 1);
                 jtabledatos.setValueAt(jtxtprecio.getText(), fila, 2);
                 jtabledatos.setValueAt(Integer.parseInt(jtxtprecio.getText())*rs.getInt(1), fila, 3);
                 jtabledatos.setValueAt(1, fila, 4);
                 fila++;
                 activo1=1;
                 Cantidad = Cantidad -rs.getInt(1);
             }
             //JOptionPane.showMessageDialog(this, "faltan " + Cantidad);
             st = cn1.createStatement();
             rs = st.executeQuery("select existencia from Productos where descripcion='"+Producto+"';");
             if(rs.next())
             if(rs.getInt(2)>= Cantidad){
                 jtabledatos.setValueAt(jcmbProductos.getSelectedItem(), fila, 0);
                 jtabledatos.setValueAt(Cantidad, fila, 1);
                 jtabledatos.setValueAt(jtxtprecio.getText(), fila, 2);
                 jtabledatos.setValueAt(Integer.parseInt(jtxtprecio.getText())*Cantidad, fila, 3);
                 jtabledatos.setValueAt(2, fila, 4);
                 activo2=1;
                 return "Termino";
             }
             else{
                 jtabledatos.setValueAt(jcmbProductos.getSelectedItem(), fila, 0);
                 jtabledatos.setValueAt(rs.getInt(1), fila, 1);
                 jtabledatos.setValueAt(jtxtprecio.getText(), fila, 2);
                 jtabledatos.setValueAt(Integer.parseInt(jtxtprecio.getText())*rs.getInt(1), fila, 3);
                 jtabledatos.setValueAt(2, fila, 4);
                 fila++;
                 activo2=1;
                 Cantidad = Cantidad -rs.getInt(1);
             }
             JOptionPane.showMessageDialog(this, "faltan " + Cantidad);
             st = cn2.createStatement();
              rs = st.executeQuery("select existencia from Productos where descripcion='"+Producto+"'");
             if(rs.next())
             if(rs.getInt(3)>= Cantidad){
                 jtabledatos.setValueAt(jcmbProductos.getSelectedItem(), fila, 0);
                 jtabledatos.setValueAt(Cantidad, fila, 1);
                 jtabledatos.setValueAt(jtxtprecio.getText(), fila, 2);
                 jtabledatos.setValueAt(Integer.parseInt(jtxtprecio.getText())*Cantidad, fila, 3);
                 jtabledatos.setValueAt(2, fila, 4);
                 activo3=1;
                 return "Termino";
             }
             else{
                 jtabledatos.setValueAt(jcmbProductos.getSelectedItem(), fila, 0);
                 jtabledatos.setValueAt(rs.getInt(1), fila, 1);
                 jtabledatos.setValueAt(jtxtprecio.getText(), fila, 2);
                 jtabledatos.setValueAt(Integer.parseInt(jtxtprecio.getText())*rs.getInt(1), fila, 3);
                 jtabledatos.setValueAt(2, fila, 4);
                 fila++;
                 activo3=1;
                 Cantidad = Cantidad -rs.getInt(1);
             }
             JOptionPane.showMessageDialog(this, "faltan " + Cantidad);
             
             
                
        } catch (SQLException ex) {
            Logger.getLogger(VentasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "k onda k pex";
}   
    
    
    
 public JComboBox Existencias(JComboBox cmb, String Producto){
        try {
            Connection cn = Conexion.conectar("SQL");
            Connection cn1 = Conexion.conectar("MySQL");
            Connection cn2 = Conexion.conectar("Oracle");
            int total=0;
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("select existencia from Productos where descripcion='"+Producto+"'");
             if(rs.next())
            total+=rs.getInt(1);
            st = cn1.createStatement();
            rs = st.executeQuery("select existencia from Productos where descripcion='"+Producto+"';");
            if(rs.next())
                total+=rs.getInt(1);
            st = cn2.createStatement();
            rs = st.executeQuery("select existencia from Productos where descripcion='"+Producto+"'");
            if(rs.next())
                total+=rs.getInt(1);

            cmb.removeAllItems();
            for (int i = total; i >= 0; i--) {
                cmb.addItem(i);
            }
            return cmb; 
   
        } catch (SQLException ex) {
            Logger.getLogger(VentasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmb;    
 }   
    
    
    
    public JComboBox Clientes(JComboBox cmb){
        try {
            Connection cn = Conexion.conectar("SQL");
            Connection cn1 = Conexion.conectar("MySQL");
            Connection cn2 = Conexion.conectar("Oracle");

            ArrayList clientes = new ArrayList();
            String nombre="";
            Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("select nombre from Clientes");
                while(rs.next()){
                    nombre=rs.getString(1);
                    int i=0;
                    for (i=0; i<clientes.size();) {
                        if (!nombre.equals(clientes.get(i).toString())) {
                         i++;
                        }
                        else
                            break;                      
                    }
                    if(i==clientes.size())
                    clientes.add(nombre);  
                }

           st = cn1.createStatement();
           rs = st.executeQuery("select nombre from Clientes;");
                while(rs.next()){
                    nombre=rs.getString(1);
                    int i=0;
                    for (i=0; i<clientes.size();) {
                        if (!nombre.equals(clientes.get(i).toString())) {
                            
                           i++;
                        }
                        else
                            break;                      
                    }
                    if(i==clientes.size())
                    clientes.add(nombre);                  
                }
                 st = cn2.createStatement();
                rs = st.executeQuery("select nombre from Clientes");
                while(rs.next()){
                    nombre=rs.getString(1);
                    int i=0;
                    for (i=0; i<clientes.size();) {
                        if (!nombre.equals(clientes.get(i).toString())) {
                         i++;
                        }
                        else
                            break;                      
                    }
                    if(i==clientes.size())
                    clientes.add(nombre);  
                }
                cmb.removeAll();
                cmb.addItem("--");
                for (int i = 0; i < clientes.size(); i++) {
                cmb.addItem(clientes.get(i).toString());
            }
                return cmb;
            
        } catch (SQLException ex) {
            Logger.getLogger(VentasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmb;
                }

    
    
    
    public int precio(String Producto){
        try {
            Connection cn = Conexion.conectar("SQL");
            Connection cn1 = Conexion.conectar("MySQL");
            Connection cn2 = Conexion.conectar("Oracle");
            
            Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("select precio from Productos where descripcion='"+Producto+"'");
            if(rs.next())
            return rs.getInt(1);
            st = cn1.createStatement();
            rs = st.executeQuery("select precio from Productos where descripcion='"+Producto+"';");
            if(rs.next())
                return rs.getInt(1);
            st = cn2.createStatement();
            rs = st.executeQuery("select precio from Productos where descripcion='"+Producto+"'");
            if(rs.next())
                return rs.getInt(1);
           } catch (SQLException ex) {
            Logger.getLogger(VentasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
        public JComboBox Productox(JComboBox cmb){
        try {
            Connection cn = Conexion.conectar("SQL");
            Connection cn1 = Conexion.conectar("MySQL");
            Connection cn2 = Conexion.conectar("Oracle");
            ArrayList productos = new ArrayList();
            String descripcion="";
            Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("select descripcion from Productos");
                while(rs.next()){
                    descripcion=rs.getString(1);
                    int i=0;
                    for (i=0; i<productos.size();) {
                        if (!descripcion.equals(productos.get(i).toString())) {
                           i++;
                        }
                        else
                            break;                      
                    }
                    if(i==productos.size())
                    productos.add(descripcion);  
                }
           st = cn1.createStatement();
           rs = st.executeQuery("select descripcion from Productos;");
                while(rs.next()){
                    descripcion=rs.getString(1);
                    int i=0;
                    for (i=0; i<productos.size();) {
                        if (!descripcion.equals(productos.get(i).toString())) {
                           i++;
                        }
                        else
                            break;                      
                    }
                    if(i==productos.size())
                    productos.add(descripcion);                  
                }
                st = cn2.createStatement();
           rs = st.executeQuery("select descripcion from productos");
                while(rs.next()){
                    descripcion=rs.getString(1);
                    int i=0;
                    for (i=0; i<productos.size();) {
                        if (!descripcion.equals(productos.get(i).toString())) {
                           i++;
                        }
                        else
                            break;                      
                    }
                    if(i==productos.size())
                    productos.add(descripcion);                  
                }
 
                cmb.removeAll();
                cmb.addItem("--");
                for (int i = 0; i < productos.size(); i++) {
                cmb.addItem(productos.get(i).toString());
            }
               
            
        } catch (SQLException ex) {
            Logger.getLogger(VentasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmb;
                }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcmbcliente = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcmbProductos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcmbExistencia = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtxtprecio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabledatos = new javax.swing.JTable();
        jtxttotal1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cliente");

        jcmbcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbclienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Producto");

        jcmbProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbProductosActionPerformed(evt);
            }
        });

        jLabel3.setText("Existencias");

        jcmbExistencia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbExistenciaItemStateChanged(evt);
            }
        });
        jcmbExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbExistenciaActionPerformed(evt);
            }
        });

        jLabel4.setText("Precio");

        jtxtprecio.setEnabled(false);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtabledatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Subtotal", "Sucursal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtabledatos);

        jtxttotal1.setEnabled(false);
        jtxttotal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxttotal1ActionPerformed(evt);
            }
        });

        jLabel6.setText("TOTAL:");

        jButton2.setText("Compra");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Crear PDF");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jcmbcliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcmbExistencia, 0, 199, Short.MAX_VALUE)
                                        .addGap(28, 28, 28))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addGap(57, 57, 57)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(23, 23, 23)))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxttotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxttotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbProductosActionPerformed
        Existencias(jcmbExistencia, jcmbProductos.getSelectedItem().toString());
        jtxtprecio.setText(precio(jcmbProductos.getSelectedItem().toString())+"");
    }//GEN-LAST:event_jcmbProductosActionPerformed

    private void jcmbExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbExistenciaActionPerformed
      
    }//GEN-LAST:event_jcmbExistenciaActionPerformed

    private void jcmbExistenciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbExistenciaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbExistenciaItemStateChanged

    public void llenarTabla2(){
        try{
                 Connection cn = Conexion.conectar("SQL");
            Connection cn1 = Conexion.conectar("MySQL");
            Connection cn2 = Conexion.conectar("Oracle");
            String nombre=jcmbcliente.getSelectedItem().toString();
        String producto=jcmbProductos.getSelectedItem().toString();
        String cantidad=jcmbExistencia.getSelectedItem().toString();
        int precio=0;
        int sucursal=0;
       
          
        //consulta de clinetes hacia sql server
            Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("select * from Productos where descripcion='"+producto+"'");
       
             while(rs.next()){
                 precio=rs.getInt("Precio");
             }
             rs=st.executeQuery("select * from clientes where nombre='"+nombre+"'");
             while(rs.next()){
                 sucursal=rs.getInt("idCliente");
             }
             
             //consulta de clientes hacia MySql
             
                
             st = cn1.createStatement();
             rs = st.executeQuery("select * from Productos where descripcion='"+producto+"'");
         
             while(rs.next()){
                 precio=rs.getInt("Precio");
             }
             rs=st.executeQuery("select * from clientes where nombre='"+nombre+"'");
             while(rs.next()){
                 sucursal=rs.getInt("idCliente");
             }
             
             st = cn2.createStatement();
             rs = st.executeQuery("select * from Productos where descripcion='"+producto+"'");
          
             while(rs.next()){
                 precio=rs.getInt("Precio");
             }
             rs=st.executeQuery("select * from clientes where nombre='"+nombre+"'");
             while(rs.next()){
                 sucursal=rs.getInt("idCliente");
             }
             
            
             
                
             int cant=Integer.parseInt(cantidad);
             jtabledatos.setValueAt(producto, filas, columnas++);
             jtabledatos.setValueAt(cantidad, filas, columnas++);
             jtabledatos.setValueAt(precio, filas, columnas++);
             jtabledatos.setValueAt((precio* cant)+"", filas, columnas++);
             jtabledatos.setValueAt(sucursal, filas, columnas++);
              
             filas++;
             columnas=0;
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la tabla"+e);
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        llenarTabla2();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtxttotal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxttotal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxttotal1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

       try{
           String []productos= new String[4];
           String []cantidades= new String[4];
           int contP=0;
           int contV=0;
           for(int i=0; i<=3; i++){//filas
               if(jtabledatos.getValueAt(i, 0) !=  null)
              // System.out.println(jtabledatos.getValueAt(i, 0).toString());
               productos[i]=jtabledatos.getValueAt(i, 0).toString();
               if(jtabledatos.getValueAt(i, 1) !=  null)
//             System.out.println(jtabledatos.getValueAt(i, 1).toString());
             cantidades[i]=jtabledatos.getValueAt(i, 1).toString();
               if(jtabledatos.getValueAt(i, 2) !=  null)
//             System.out.println(jtabledatos.getValueAt(i, 1).toString());
             cantidades[i]=jtabledatos.getValueAt(i, 2).toString();
                   
               
               
           }
           Connection cn = Conexion.conectar("SQL");
           Statement st = cn.createStatement();
           for(int i=0; i<productos.length; i++){
               if(productos[i] != null && cantidades[i] != null){
                   
                   st.execute("update productos set existencia=existencia-"+cantidades[i]+"where descripcion='"+productos[i]+"'");
                   System.out.println(productos[i] + "  "+ cantidades[i]);
               }
               
           }
           Connection cn1 = Conexion.conectar("MySQL");
             st = cn1.createStatement();
           for(int i=1; i<productos.length; i++){
            
               if(productos[i] != null && cantidades[i] != null){
                   st.execute("update productos set existencia=existencia - "+cantidades[i]+"where descripcion='"+productos[i]+"';");
                   System.out.println("Actualizado");
                   
                   
               }
               
           }
           Connection cn2 = Conexion.conectar("Oracle");
             st = cn2.createStatement();
           for(int i=2; i<productos.length; i++){
            
               if(productos[i] != null && cantidades[i] != null){
                 st.execute("update productos set existencia=existencia-"+cantidades[i]+"where descripcion='"+productos[i]+"'");
                   System.out.println("Actualizado");
                   
                   
               }
               
           }
           
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Error en compra --> "+e);
       }
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jcmbclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbclienteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
String strTituloPDF = "Reporte De Ventas";
    String strNombrePDF = "Ventas_reporte.pdf";
        Crear_PDF a = new Crear_PDF(strTituloPDF, strNombrePDF);
        a.abrirPDF();
    
    
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmbExistencia;
    private javax.swing.JComboBox jcmbProductos;
    private javax.swing.JComboBox jcmbcliente;
    private javax.swing.JTable jtabledatos;
    private javax.swing.JTextField jtxtprecio;
    private javax.swing.JTextField jtxttotal1;
    // End of variables declaration//GEN-END:variables
}
