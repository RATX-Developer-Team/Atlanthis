package com.daw.atlanthis.servlet;

import com.daw.atlanthis.DTO.Categorias;
import com.daw.atlanthis.DTO.Hilos;
import com.daw.atlanthis.DTO.Subcategorias;
import com.daw.atlanthis.DTO.Usuarios;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class response extends HttpServlet {
    private JSONObject obj = new JSONObject();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utilidades utils_ = new Utilidades();
            String categorias = request.getParameter("categorias");
            String hilos = request.getParameter("hilos");
            String usuario = request.getParameter("usuario");
            if (categorias!=null) {
                switch (categorias) {
                    case "todas":
                        {
                            obj = new JSONObject();
                            List<Categorias> v = utils_.getCtrCategorias().findCategoriasEntities();
                            for (Categorias k:v) {
                                Categorias x = k;
                                obj.put(x.getCodCategoria()+"", x.toString());
                            }       break;
                        }
                    case "sub":
                        {
                            obj = new JSONObject();
                            List<Subcategorias> v = utils_.getCtrSubcategorias().findSubcategoriasEntities();
                            for (Subcategorias k:v) {
                                Subcategorias x = k;
                                obj.put(x.getCodSubcategoria()+"", x.toString());
                            }       break;
                        }
                    default:
                        {
                            obj = new JSONObject();
                            List<Subcategorias> v = utils_.getCtrSubcategorias().findSubcategoriasEntities();
                            for (Subcategorias k:v) {
                                Subcategorias x = k;
                                if (categorias.equals(x.getCodCategoria()+"")) {
                                    obj.put(x.getCodSubcategoria()+"", x.toString());
                                }
                            }       break;
                        }
                }
            } else if (hilos!=null) {
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
            } else if (usuario!=null) {
                obj = new JSONObject();
                List<Usuarios> v = utils_.getCtrUsuarios().findUsuariosEntities();
                for (Usuarios k:v) {
                    Usuarios x = k;
                    if (usuario.equals(x.getCodUsuario()+"")) {
                        obj.put(x.getCodUsuario()+"", x.toString());
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
            Logger.getLogger(response.class.getName()).log(Level.SEVERE, null, ex);
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
