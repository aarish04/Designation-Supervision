package com.thinking.machines.hr.dl.interfaces;
import java.util.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public interface DesignationDAOInterface
{
public void add(DesignationDTOInterface designationDTO) throws DAOException;
public void update(DesignationDTOInterface designationDTO) throws DAOException;
public void delete(int code) throws DAOException;
public int getCount() throws DAOException;
public List<DesignationDTOInterface>getAll() throws DAOException;
public DesignationDTOInterface getByCode(int code)throws DAOException;
public DesignationDTOInterface getByTitle(String title)throws DAOException;
public boolean codeExists(int code) throws DAOException;
public boolean titleExists(String title) throws DAOException;
}