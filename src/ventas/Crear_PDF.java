package ventas;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.cell;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.Image;


import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;

import java.sql.*;

/**
 *
 * @author Alejandro
 */
public class Crear_PDF {
    String strNombreDelPDF;
 
    private final  Font fuenteNegra10 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
    private final  Font fuente10 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
    private  Font fuente15 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
 
    Color grisClaro = new Color( 230,230,230);  
    Color azulClaro = new Color( 124,195,255 );

    
    ResultSet rs;
    Document document;
    PdfWriter writer;
    String strRotuloPDF;
    
    
    public Crear_PDF(String titulo, String nomPDF)
    {
        strRotuloPDF = titulo;
        strNombreDelPDF = nomPDF;
        try
        {       //Hoja tamanio carta, rotarla (cambiar a horizontal)
            document = new Document( PageSize.LETTER.rotate() );
             
            writer = PdfWriter.getInstance(
           
                    document,
                    // direccionar el PDF-stream a un archivo
                    new FileOutputStream(strNombreDelPDF));
            document.open();
            
        
            agregarContenido(document);
             
            document.close();
             
            System.out.println("Se ha generado el PDF: "+ strNombreDelPDF);
                 
        } catch (Exception e) 
        {
           
        }
    }
    
    public void agregarContenido(Document document) throws DocumentException
    {
        Paragraph ParrafoHoja = new Paragraph();
                 
        // Agregamos una linea en blanco al principio del documento
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // Colocar un encabezado (en mayusculas)
        ParrafoHoja.add(new Paragraph(strRotuloPDF.toUpperCase (), fuente15) );
        agregarLineasEnBlanco(ParrafoHoja, 1);
       
        agregarTabla(ParrafoHoja);
     
        agregarLineasEnBlanco(ParrafoHoja, 2);
        
        try
        {
            Image im = Image.getInstance("alojamiento3.jpg");
            im.setAlignment(Image.ALIGN_CENTER | Image.TEXTWRAP );
            im.setWidthPercentage (50);
          ParrafoHoja.add(im);
        }
        catch(Exception e)
        {
           
        }
         
        document.add(ParrafoHoja);
 
    }
    
     public void agregarTabla(Paragraph parrafo) throws BadElementException 
    {
        //Anchos de las columnas
        float anchosFilas[] = { 1f,1f,1f,1f,1f };
        PdfPTable tabla = new PdfPTable(anchosFilas);
        String rotulosColumnas[] = {"Producto","Cantidad","Precio","Subtotal","Sucursal"};
        // Porcentaje que ocupa a lo ancho de la pagina del PDF
        tabla.setWidthPercentage(100);
        //Alineacion horizontal centrada
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        //agregar celda que ocupa las 9 columnas de los rotulos
        PdfPCell cell = new PdfPCell(new Paragraph("Ventas"));
        cell.setColspan(9);
        //Centrar contenido de celda
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Color de fondo de la celda
        cell.setBackgroundColor (BaseColor.LIGHT_GRAY);        
        tabla.addCell(cell);
 
        try
        {     
                for(int i=0; i<rotulosColumnas.length; i++)
                {
                    cell = new PdfPCell(new Paragraph(rotulosColumnas[i],fuenteNegra10));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor (BaseColor.LIGHT_GRAY);
                    tabla.addCell(cell);
                }
                 
                rs = llenarTabla2();
 
                
            while (rs.next())
            {           //Agregar 9 celdas
                    cell = new PdfPCell(new Paragraph(rs.getString ("Producto"),fuente10 ));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("Cantidad"),fuente10));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("Precio"))));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("Subtotal"))));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("Sucursal") )));
                    tabla.addCell(cell);
                    
            }
             
                
              
               
             
        }
        catch(Exception e) 
        {
            System.out.println("Excepcion al ejecutar CONSULTA!!!");
           
        }
        //Agregar la tabla con los datos al parrafo que nos llego como entrada
        parrafo.add(tabla);
    } 
    
    public static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) 
    {
        for (int i = 0; i < nLineas; i++) 
            parrafo.add(new Paragraph(" "));
    }
     
    //Abre el documento PDF
    public void abrirPDF()
    {
       
        try
        {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + strNombreDelPDF);
        }
        catch (IOException e) 
        {
           
        }   
    }

    private ResultSet llenarTabla2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
