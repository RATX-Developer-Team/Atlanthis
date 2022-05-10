package com.daw.atlanthis.servlet;

import com.daw.atlanthis.DTO.Articulo;
import com.daw.atlanthis.utils.Utilidades;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class puente extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utilidades utils_ = new Utilidades();
            String destino = request.getParameter("destino");
            String is = request.getParameter("is");//a = articulo, c = categoria, sc = subcategoria 
            HttpSession session = request.getSession();
            String codigoArt = request.getParameter("codigoArt");
            String codigoCategoria = request.getParameter("codigoCategoria");
            String codigoSubcategoria = request.getParameter("codigoSubcategoria");
            if (destino != null && !"".equals(destino)) {
                switch (is) {
                    case "a":
                        if (codigoArt != null && !"".equals(codigoArt)) {
                            Articulo v = utils_.getCtrArticulo().findArticulo(Integer.parseInt(codigoArt));
                            session.setAttribute("Articulo", v);
                        }
                        break;
                    case "c":
                        if (codigoCategoria != null && !"".equals(codigoCategoria)) {
                            
                        }
                        break;
                    case "sc":
                        if (codigoSubcategoria != null && !"".equals(codigoSubcategoria)) {
                            
                        }
                        break;
                    default:
                        break;
                }
                String nextJSP = destino;
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                dispatcher.forward(request,response);
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(puente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servidor de Puente entre JS y JSF del Proyecto Atlanthis.";
    }

}
