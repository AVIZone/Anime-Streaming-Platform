/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
import java.sql.*;


/**
 *
 * @author Abhay
 */
@MultipartConfig
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //getting details
            List<String> l=new ArrayList<>();
            String name=request.getParameter("full_name");
            l.add(name);
            String username=request.getParameter("user_name");
            l.add(username);
            String email=request.getParameter("user_email");
            l.add(email);
            String password=request.getParameter("user_password");
           l.add(password);
            Part part=request.getPart("image");
            String filename=part.getSubmittedFileName();
            
           //connection........
            try {
                
                Thread.sleep(2000);
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","Aditi123.");
                
                //Query
                String q="insert into user(name, username, email, password, image) values(?,?,?,?,?)";
                
                PreparedStatement pst =con.prepareStatement(q);
                
                for(int i=1;i<=l.size();i++){
                pst.setString(i, l.get(i-1));
                }
                
                pst.setString(l.size()+1, filename);
                pst.executeUpdate();
                
                InputStream is=part.getInputStream();
                byte[]data=new byte[is.available()];
                is.read(data);
                String path=request.getServletContext().getRealPath("/")+"img"+File.separator+filename;
//                out.println(path);
                
                FileOutputStream fos=new FileOutputStream(path);
                
                fos.write(data);
                fos.close();
                
                out.println("Done");
                
            } catch (Exception e) {
                out.println(e);
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
