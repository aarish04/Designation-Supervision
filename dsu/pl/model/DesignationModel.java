package com.thinking.machines.hr.pl.model;
import java.awt.*;
import java.util.List;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.common.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Image;
import com.itextpdf.text.Element;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import java.io.*;

public class DesignationModel extends AbstractTableModel
{
private String title[];
private Object data[];
public List<DesignationInterface> list;
DesignationManager dm;
int i;
DesignationInterface d;
public DesignationModel()
{
dm=DesignationManager.getDesignationManager();
populateDataStructures();
}


private void populateDataStructures()
{
title=new String[2];
title[0]="Serial No.";
title[1]="Designations";
try
{
list=dm.getDesignations();
}catch(BLException b)
{
System.out.println(b.getGenericException());
}
int i=0;
data=new Object[list.size()];
while(i<list.size())
{
data[i]=list.get(i).getTitle();
i++;
}
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)
{
return rowIndex+1;
}
return data[rowIndex];
}
public int getColumnCount()
{
return title.length;
}
public int getRowCount()
{
return data.length;
}
public boolean isCellEditable()
{
return false;
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public int IndexOf(String title)
{
int e=0;
try
{
e=list.indexOf(dm.getByTitle(title));
}catch(BLException b)
{
System.out.println(b.getGenericException());
}
return e;
}

public DesignationInterface getDesignationAt(int e) throws ModelException
{
if(e<0 || e>list.size()) throw new ModelException("Invalid index : "+e);
return list.get(e);
}
public void add(DesignationInterface d) throws ModelException
{
try
{
dm.add(d);
}catch(BLException b)
{
java.util.List<String> list=b.getExceptions();
for(int i=0;i<list.size();i++)
{
String g=list.get(i);
throw new ModelException(g);
}
}
populateDataStructures();
fireTableDataChanged();
}
public void update(DesignationInterface designation) throws ModelException
{
try
{
dm.update(designation);
}catch(BLException b)
{
java.util.List<String> list=b.getExceptions();
for(int i=0;i<list.size();i++)
{
String g=list.get(i);
throw new ModelException(g);
}
}
populateDataStructures();
fireTableDataChanged();
}

public void delete(int c) throws ModelException
{
try
{
dm.delete(c);
}catch(BLException b)
{
java.util.List<String> list=b.getExceptions();
for(int i=0;i<list.size();i++)
{
String g=list.get(i);
throw new ModelException(g);
}
}
populateDataStructures();
fireTableDataChanged();
}
public int indexOf(DesignationInterface designationInterface) throws ModelException
{
int j=0;
while(j<list.size())
{
if(list.get(j).getTitle()==designationInterface.getTitle()) 
{
return j;
}
else
{
j++;
}
}
throw new ModelException("");
}
public DesignationInterface getDesignation(String designation,boolean matchCase,boolean partial) throws ModelException
{
int found=0;
int i=0;
if(matchCase==false)
{
for(i=0;i<list.size();i++)
{
String d=list.get(i).getTitle().toLowerCase();
if(designation.equalsIgnoreCase(d) || d.startsWith(designation.toLowerCase()))
{
found=1;
break;
}
}
}
else
{
for(i=0;i<list.size();i++)
{
String d=list.get(i).getTitle();
if(designation.equals(d) || d.startsWith(designation))
{
found=1;
break;
}
}
}
if(found==0)
{
throw new ModelException("Invalid Designation Entered");
}
DesignationInterface clone=new Designation();
POJOCopier.copy(clone,list.get(i));
return clone;
}
public void exportToPDF(File fil)
{
Document document=new Document();
try
{
PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(fil));
PdfPTable table=null;
document.open();
int sz=list.size();
int pageSize=35;
boolean newpage=true;
int sno=0;
int j=1;
int i=0;

while(i<sz)
{
if(newpage==true)
{
table=new PdfPTable(2);
Font timesRomanFont=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.BOLDITALIC);
Chunk page=new Chunk(String.format("Page - %d",j),timesRomanFont);
j++;
document.add(page);

Image image=Image.getInstance("icon.png");
float[] columnWidths={0.1f,0.2f};
table.setWidths(columnWidths);

PdfPCell cell1=new PdfPCell(image,false);
PdfPCell cell2=new PdfPCell(new Paragraph("  Microsoft Corporation"));
cell1.setBorder(0);
cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
cell2.setBorder(0);
table.addCell(cell1);
table.addCell(cell2);

PdfPCell cell3=new PdfPCell(new Paragraph());
PdfPCell cell4=new PdfPCell(new Paragraph("  List of Designations"));
cell3.setBorder(0);
cell4.setBorder(0);
table.addCell(cell3);
table.addCell(cell4);

PdfPCell cell5=new PdfPCell(new Paragraph("  "));
PdfPCell cell6=new PdfPCell(new Paragraph("  "));
cell5.setBorder(0);
cell6.setBorder(0);
table.addCell(cell5);
table.addCell(cell6);

PdfPCell cell7=new PdfPCell(new Paragraph("Serial no."));
PdfPCell cell8=new PdfPCell(new Paragraph("Designations"));
cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell7);
table.addCell(cell8);

PdfPCell cell11=new PdfPCell(new Paragraph("  "));
PdfPCell cell12=new PdfPCell(new Paragraph("  "));
cell11.setBorder(0);
cell12.setBorder(0);
table.addCell(cell11);
table.addCell(cell12);


newpage=false;
}
//add sno and designation to table
PdfPCell cell9;
PdfPCell cell10;

d=list.get(i);
sno++;
i++;
String designation=d.getTitle();
cell9=new PdfPCell(new Paragraph(String.format("%d",sno)));
cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
cell10=new PdfPCell(new Paragraph(designation));
cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
table.addCell(cell9);
table.addCell(cell10);
if(sno%pageSize==0 || sno==sz)
{
table.setSpacingAfter(35f);
document.add(table);
Phrase phrase=new Phrase();
Paragraph paragraph=new Paragraph();
Font underlineFont = new Font(Font.FontFamily.HELVETICA,16,Font.UNDERLINE);
Chunk underlineChunk = new Chunk("Software by - Aarish Sayyed",underlineFont);
phrase.add(underlineChunk);
paragraph.add(phrase);
paragraph.setAlignment(Element.ALIGN_CENTER);
document.add(paragraph);
document.newPage();
newpage=true;
}
}
document.close();
}catch(Exception e)
{
}
}
}