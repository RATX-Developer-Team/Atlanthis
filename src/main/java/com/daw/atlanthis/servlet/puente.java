package com.daw.atlanthis.servlet;

import com.daw.atlanthis.DTO.Categorias;
import com.daw.atlanthis.DTO.Hilos;
import com.daw.atlanthis.DTO.Subcategorias;
import com.daw.atlanthis.DTO.Usuarios;
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
            String is = request.getParameter("is");//c = categoria, sc = subcategoria, h = hilo, p = perfil 
            HttpSession session = request.getSession();
            String codigoCategoria = request.getParameter("codigoCategoria");
            String codigoSubcategoria = request.getParameter("codigoSubcategoria");
            String codigoHilo = request.getParameter("codigoHilo");
            String codigoPerfil = request.getParameter("codigoPerfil");
            if (destino != null && !"".equals(destino)) {
                switch (is) {
                    case "c":
                        if (codigoCategoria != null && !"".equals(codigoCategoria)) {
                            Categorias v = utils_.getCtrCategorias().findCategorias(Integer.parseInt(codigoCategoria));
                            session.setAttribute("Categoria", v);
                        }
                        break;
                    case "sc":
                        if (codigoSubcategoria != null && !"".equals(codigoSubcategoria)) {
                            Subcategorias v = utils_.getCtrSubcategorias().findSubcategorias(Integer.parseInt(codigoSubcategoria));
                            session.setAttribute("Subcategoria", v);
                        }
                        break;
                    case "h":
                        if (codigoHilo != null && !"".equals(codigoHilo)) {
                            Hilos v = utils_.getCtrHilos().findHilos(Integer.parseInt(codigoHilo));
                            session.setAttribute("Hilo", v);
                        }
                        break;
                    case "p":
                        if (codigoPerfil != null && !"".equals(codigoPerfil)) {
                            Usuarios v = utils_.getCtrUsuarios().findById_(Integer.parseInt(codigoPerfil));
                            session.setAttribute("Usuario", v);
                        }
                        break;
                    default:
                        break;
                }
                String nextXHTML = destino;
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextXHTML);
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
        return "Servidor de Puente entre JS y JSF del Proyecto Delta.";
    }

}
