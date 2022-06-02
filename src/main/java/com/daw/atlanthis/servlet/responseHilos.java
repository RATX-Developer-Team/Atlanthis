package com.daw.atlanthis.servlet;

import com.daw.atlanthis.DTO.Hilos;
import com.daw.atlanthis.utils.Utilidades;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/responseHilos"}, asyncSupported=false)
public class responseHilos extends HttpServlet {
    private JSONObject obj = new JSONObject();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utilidades utils_ = new Utilidades();
            String hilos = request.getParameter("hilos");
            if (hilos!=null) {
                if ("todos".equals(hilos)) {
                    obj = new JSONObject();
                    List<Hilos> v = utils_.getCtrHilos().findHilosEntities();
                    for (Hilos k:v) {
                        Hilos x = k;
                        obj.put(x.getCodHilo()+"", x.toString());
                    }
                } else {
                    obj = new JSONObject();
                    List<Hilos> v = utils_.getCtrHilos().findHilosEntities();
                    for (Hilos k:v) {
                        Hilos x = k;
                        if (hilos.equals(x.getCodSubcategoria()+"")) {
                            obj.put(x.getCodHilo()+"", x.toString());
                        }
                    }
                }
            }
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(obj.toString());
            out.flush();
            obj = new JSONObject();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(responseHilos.class.getName()).log(Level.SEVERE, null, ex);
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
        return "Servidor de Respuesta del Proyecto Atlanthis, hacia jQuery con JSON";
    }

}
