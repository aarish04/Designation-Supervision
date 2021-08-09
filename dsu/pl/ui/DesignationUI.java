package com.thinking.machines.hr.pl.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.pl.model.*;
import javax.swing.JOptionPane.*;
import javax.swing.JFileChooser.*;
import javax.swing.ListSelectionModel;
import java.io.*;
import javax.swing.filechooser.FileSystemView;
import java.lang.*;

public class DesignationUI extends JFrame implements DocumentListener
{
private JLabel titleLabel;
private JLabel searchCaptionLabel;
private JLabel searchResultLabel;
private JTextField searchTextField;
private JButton searchButton;
private JScrollPane jscrollPane;
private JTable jtable;
private JPanel jpanel1;
private JLabel designationCaptionLabel;
private JLabel designationLabel;
private JTextField designationTextField;
private JPanel jpanel2;
private JButton b1;
private JButton b2;
private JButton b3;
private JButton b4;
private JButton b5;
private char flag='a';
private int flag2=0;
private Container container;
private DesignationModel designationModel;
private Designation designation;
private String title;
private int e=1;
private FileSystemView fsv;
private String find;
private boolean delete=false;
public DesignationUI()
{
titleLabel=new JLabel("Designation Master");
searchResultLabel=new JLabel(" ",JLabel.CENTER);
searchCaptionLabel=new JLabel("Search");
searchTextField=new JTextField();
searchButton=new JButton("x");
designationModel=new DesignationModel();
jtable=new JTable(designationModel); // yaha DesignationModel ka object pass hoga isko
jscrollPane=new JScrollPane(jtable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
jpanel1=new JPanel();
jpanel1.setLayout(null);
designationCaptionLabel=new JLabel("Designation");
designationLabel=new JLabel();
designationTextField=new JTextField();
designationTextField.setVisible(false);
designationTextField.setEnabled(false);
jpanel2=new JPanel();
jpanel2.setLayout(null);
b1=new JButton("A");
b2=new JButton("E");
b3=new JButton("C");
b4=new JButton("D");
b5=new JButton("P");
if(designationModel.list.size()==0)
{
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
jtable.setEnabled(false);
b2.setEnabled(false);
b3.setEnabled(false);
b4.setEnabled(false);
b5.setEnabled(false);
}
else
{
b3.setEnabled(false);
}
Border searchResultBorder=BorderFactory.createLineBorder(Color.GRAY,1);
searchResultLabel.setBorder(searchResultBorder);
Border jpanel1Border=BorderFactory.createLineBorder(Color.GRAY,1);
jpanel1.setBorder(jpanel1Border);
Border designationLabelBorder=BorderFactory.createLineBorder(Color.GRAY,1);
designationLabel.setBorder(designationLabelBorder);
Border jpanel2Border=BorderFactory.createLineBorder(Color.GRAY,1);
jpanel2.setBorder(jpanel2Border);


Font titleFont=new Font("Verdana",Font.BOLD,24);
titleLabel.setFont(titleFont);
Font dataFont=new Font("Times New Roman",Font.PLAIN,22);
searchCaptionLabel.setFont(dataFont);
searchResultLabel.setFont(new Font("Serif",Font.ITALIC,15));
searchResultLabel.setForeground(Color.RED);
designationCaptionLabel.setFont(dataFont);
designationLabel.setFont(new Font("Serif",Font.ITALIC,15));

container=getContentPane();
container.setLayout(null);
int lm,tm;
int width=700;
int height=700;
lm=10;
tm=0;

b1.setBounds(lm+120,tm+45,45,25);
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
if(flag=='a')
{
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
jtable.setEnabled(false);
b1.setText("S");
b2.setEnabled(false);
b3.setEnabled(true);
b4.setEnabled(false);
b5.setEnabled(false);
designationLabel.setEnabled(false);
designationTextField.setEnabled(true);
designationTextField.setVisible(true);
designationTextField.setText("");
designationLabel.setText("");
flag='s';
}
else
{
try
{
title=designationTextField.getText();
designation=new Designation();
designation.setTitle(title);
try
{
designationModel.add(designation);
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(null, modelException.getMessage() ,"Error",JOptionPane.ERROR_MESSAGE);
}
searchTextField.setEnabled(true);
searchButton.setEnabled(true);
b1.setText("A");
b2.setEnabled(true);
b3.setEnabled(false);
b4.setEnabled(true);
b5.setEnabled(true);
jtable.setEnabled(true);
try
{
int index=designationModel.IndexOf(designationTextField.getText());
jtable.setRowSelectionInterval(index,index);
jtable.scrollRectToVisible(new Rectangle(jtable.getCellRect(index,0,true)));
}catch(IllegalArgumentException iae)
{
System.out.println(iae);
}
designationLabel.setText("");
designationTextField.setText("");
designationLabel.setEnabled(false);
designationTextField.setVisible(false);
designationTextField.setEnabled(false);
/*
jtable.setRowSelectionInterval(jtable.getRowCount()-1,jtable.getRowCount()-1);
Rectangle rectangle=jtable.getCellRect(jtable.getRowCount()-1,2,false);
jtable.scrollRectToVisible(rectangle);
*/
flag='a';
}catch(Exception e)
{
System.out.println(e);
} 
}
}
});



b2.setBounds(lm+120+45+30,tm+45,45,25);
b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent av)
{
DesignationInterface di=null;
designation=new Designation();
if(jtable.getSelectionModel().isSelectionEmpty())
{
JOptionPane.showMessageDialog(null,"Select a Designation to Edit!","Error",JOptionPane.ERROR_MESSAGE);
}
else
{
try
{
di=designationModel.getDesignationAt(jtable.getSelectedRow());
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(null,modelException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
}
b1.setEnabled(false);
b4.setEnabled(false);
b5.setEnabled(false);
searchButton.setEnabled(false);
searchTextField.setEnabled(false);
jtable.setEnabled(false);
designationLabel.setEnabled(false);
if(flag2==0)
{
try
{
flag2=1;
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
jtable.setEnabled(false);
b2.setText("U");
b1.setEnabled(false);
b3.setEnabled(true);
b4.setEnabled(false);
b5.setEnabled(false);
designationLabel.setEnabled(false);
designationTextField.setEnabled(true);
designationTextField.setVisible(true);
designationTextField.setText(designationModel.getDesignationAt(jtable.getSelectedRow()).getTitle());

}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(null,"Invalid Row index","Error",JOptionPane.ERROR_MESSAGE);
}
}
else
{
int result=JOptionPane.showConfirmDialog(null," Do you want to edit : "+di.getTitle(),"Alert",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE );
if(result==0)
{
if(designationTextField.getText()==null || designationTextField.getText().length()==0)
{
JOptionPane.showMessageDialog(null,"Designation Required!","Error",JOptionPane.ERROR_MESSAGE);
}
try
{
designation.setTitle(designationTextField.getText());
designation.setCode(di.getCode());
designationModel.update(designation);
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(null,modelException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
}
b1.setEnabled(true);
b2.setText("E");
b4.setEnabled(true);
b3.setEnabled(false);
b5.setEnabled(true);
searchButton.setEnabled(true);
searchTextField.setEnabled(true);
jtable.setEnabled(true);
try
{
int index=designationModel.IndexOf(designationTextField.getText());
jtable.setRowSelectionInterval(index,index);
jtable.scrollRectToVisible(new Rectangle(jtable.getCellRect(index,0,true)));
}catch(IllegalArgumentException iae)
{
System.out.println(iae);
}
designationLabel.setText("");
designationTextField.setText("");
designationTextField.setVisible(false);
flag2=0;
designationLabel.setVisible(true);
//EButton.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
b2.setEnabled(true);
}
//here
else
{
jtable.setEnabled(true);
b1.setEnabled(true);
b2.setText("E");
b2.setEnabled(true);
b3.setEnabled(false);
b4.setEnabled(true);
b5.setEnabled(true);
designationTextField.setText("");
designationTextField.setVisible(false);
searchTextField.setEnabled(true);
searchButton.setEnabled(true);
designationLabel.setText("");
designationLabel.setVisible(true);
jtable.setRowSelectionInterval(0 ,0);
}
}
}
}	
});





b3.setBounds(lm+120+45+30+45+30,tm+45,45,25);
b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
searchTextField.setEnabled(true);
searchButton.setEnabled(true);
jtable.setEnabled(true);
b1.setText("A");
b1.setEnabled(true);
b2.setText("E");
b2.setEnabled(true);
b3.setEnabled(false);
b4.setEnabled(true);
b5.setEnabled(true);
designationLabel.setEnabled(true);
designationTextField.setVisible(false);
designationTextField.setEnabled(false);
designationTextField.setText("");
flag='a';
flag2=0;
}
});
b4.setBounds(lm+120+45+30+45+30+45+30,tm+45,45,25);
b4.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String desi="";
int code=0;
if(jtable.getSelectionModel().isSelectionEmpty())
{
JOptionPane.showMessageDialog(container,"Select a designation to delete.","Error!!",JOptionPane.ERROR_MESSAGE);
}
else
{
int rowIndex=jtable.getSelectedRow();
try
{
desi=designationModel.getDesignationAt(rowIndex).getTitle();
code=designationModel.getDesignationAt(rowIndex).getCode();
}catch(ModelException modelException)
{
System.out.println(modelException.getMessage());
}
int h=JOptionPane.showConfirmDialog(container,"Do you want to delete designation - "+desi+" ?","Confirm!",JOptionPane.WARNING_MESSAGE);
if(h==0)
{
try
{
designationModel.delete(code);
designationLabel.setText(" ");
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(null, modelException.getMessage() ,"Error",JOptionPane.ERROR_MESSAGE);
return;
}
JOptionPane.showMessageDialog(container,"Deleted!");
}
else
{
jtable.setRowSelectionInterval(0 ,0);
}
}
}

});
b5.setBounds(lm+120+45+30+45+30+45+30+45+30,tm+45,45,25);
// pdf
b5.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String p="c:\\";
fsv=FileSystemView.getFileSystemView();
JFileChooser jfc=new JFileChooser(p,fsv);
int i=jfc.showSaveDialog(container);
if(i==JFileChooser.APPROVE_OPTION)
{
File file1=jfc.getSelectedFile();
String name=file1.getAbsolutePath();
int ex=name.lastIndexOf('.');
if(ex==-1)
{
name=name+".pdf";
ex=name.lastIndexOf('.');
}
File file=fsv.createFileObject(name);
String ext=name.substring(ex);
if(!ext.equalsIgnoreCase(".pdf"))
{
JOptionPane.showMessageDialog(container,"(.pdf) files only!","Error",JOptionPane.WARNING_MESSAGE);
}
else
{
File fi=file.getParentFile();
boolean exists=fi.exists();
if(exists==false)
{
JOptionPane.showMessageDialog(container,"Path does not exists!","Error",JOptionPane.WARNING_MESSAGE);
}
if(exists==true)
{
if(file.exists()==true)
{
int s=JOptionPane.showConfirmDialog(container,"File already exists, do you want to override it?","Warning",JOptionPane.WARNING_MESSAGE);
if(s==0)
{
designationModel.exportToPDF(file);
}
}
else
{
designationModel.exportToPDF(file);
} 
}
}
}
}
});
designationCaptionLabel.setBounds(lm-5,tm+20,110,23);
designationLabel.setBounds(lm-5+110+5,tm+20,518,25);
designationTextField.setBounds(lm-5+110+5,tm+20,518,25);
jpanel2.setBounds(lm+5,tm+20+25+10+10+5+3,622,120);
titleLabel.setBounds(lm+10,tm+10,300,40);
searchResultLabel.setBounds(lm+250+10+234+20+16,tm+10+40+5+5,80,15);
searchCaptionLabel.setBounds(lm+10,tm+10+40+5+15+10,60,20);
searchTextField.setBounds(lm+10+60+5,tm+10+40+5+15+10,537,25);


searchButton.setBounds(lm+10+60+5+500+20+20,tm+10+40+5+15+10,45,25);
searchButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
search();
searchTextField.requestFocus();
}
});
jscrollPane.setBounds(lm+10,tm+10+40+5+15+10+25+10,650,300);
jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
jtable.getTableHeader().setResizingAllowed(false);
ListSelectionModel lsm=jtable.getSelectionModel();
lsm.addListSelectionListener(new ListSelectionListener(){
public void valueChanged(ListSelectionEvent lse)
{
int rowIndex=jtable.getSelectedRow();
if(rowIndex<0) return;
DesignationInterface di=null;
try
{
di=designationModel.getDesignationAt(rowIndex);
String designation=di.getTitle();
designationLabel.setText(designation);
}catch(ModelException modelException)
{
System.out.println(modelException.getMessage());
}
}
});
jpanel1.setBounds(lm+10,tm+10+40+5+15+10+25+10+300+10,650,220);

jpanel2.add(b1);
jpanel2.add(b2);
jpanel2.add(b3);
jpanel2.add(b4);
jpanel2.add(b5);

jpanel1.add(designationCaptionLabel);
jpanel1.add(designationLabel);
jpanel1.add(designationTextField);
jpanel1.add(jpanel2);

container.add(titleLabel);
container.add(searchCaptionLabel);
container.add(searchResultLabel);
container.add(searchTextField);
container.add(searchButton);
container.add(jscrollPane);
container.add(jpanel1);
setTitle("Designation Supervision");
setSize(width,height);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2-width/2),(d.height/2-height/2));
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
void search()
{
find=searchTextField.getText().toLowerCase();
searchTextField.getDocument().addDocumentListener(this);
searchTextField.setText("");
}
private void SearchDesignation()
{
try
{
int e=designationModel.indexOf(designationModel.getDesignation(find,false,true));
e=e+1;
jtable.addRowSelectionInterval(e-1,e-1);
searchResultLabel.setText("Found");
}catch(ModelException me)
{
searchResultLabel.setText("Not found");
}
}
public void changedUpdate(DocumentEvent de)
{
SearchDesignation();
}
public void removeUpdate(DocumentEvent de)
{
SearchDesignation();
}
public void insertUpdate(DocumentEvent de)
{
SearchDesignation();
}
}