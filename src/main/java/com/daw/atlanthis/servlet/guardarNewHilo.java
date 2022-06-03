package com.daw.atlanthis.servlet;

import com.daw.atlanthis.DTO.Hilos;
import com.daw.atlanthis.DTO.Respuestas;
import com.daw.atlanthis.DTO.Subcategorias;
import com.daw.atlanthis.utils.Utilidades;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/guardarNewHilo"}, asyncSupported=false)
public class guardarNewHilo extends HttpServlet {
    private JSONObject obj = new JSONObject();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utilidades utils_ = new Utilidades();
            String tit = request.getParameter("tit");
            String con = request.getParameter("con");
            String idSubcate = request.getParameter("idSubcate");
            String idUsu = request.getParameter("idUsu");
            if (tit!=null && con!=null && idSubcate!=null && idUsu!=null) {
                Subcategorias subcate_ = utils_.getCtrSubcategorias().findSubcategorias(Integer.parseInt(idSubcate));
                Date fecha = new Date();
                int hiloID = utils_.lastId_hilos()+1;
                Hilos newHilo = new Hilos(
                        hiloID,
                        subcate_.getCodCategoria(),
                        subcate_.getCodSubcategoria(),
                        tit,
                        fecha,
                        0,
                        0,
                        Integer.parseInt(idUsu)
                );
                utils_.getCtrHilos().create(newHilo);
                
                Respuestas respuesta_ = new Respuestas(
                        null,
                        Integer.parseInt(idUsu),
                        hiloID,
                        fecha,
                        con,
                        0
                );
                utils_.getCtrRespuestas().create(respuesta_);
            }
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(obj.toString());
            out.flush();
            obj = new JSONObject();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(guardarNewHilo.class.getName()).log(Level.SEVERE, null, ex);
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
