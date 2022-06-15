/*jQuery Config*/

$.ajaxSetup({
    async: false
});

/*VARIABLES GLOBALES*/
let JSON_ = {
    categorias:{},
    subcategorias:{},
    hilos:{},
    respuestas:{},
    usuarios:{}
}
let response = (url,auxobj,destino) => {
    $.getJSON(url, auxobj, function (data) {
        $.each(data, function(index,v) {
            JSON_[destino][index] = JSON.parse(v)
        })
    })
}

let getData = () => {
    let localJSON = localStorage.getItem('JSONPrincipal')
    let localDATE = localStorage.getItem('JSONDate')
    if (localJSON == null || localDATE == null || localJSON == "" || localDATE == "" ) {
        response("responseCategorias",{
            categorias: 'todas'
        }, 'categorias')

        response("responseSubcategorias",{
            subcategorias: 'todas'
        }, 'subcategorias')

        response("responseHilos",{
            hilos: 'todos'
        }, 'hilos')

        response("responseRespuestas",{
            respuestas: 'todas'
        }, 'respuestas')

        response("responseUsuarios",{
            usuario: 'todos'
        }, 'usuarios')
        localStorage.setItem('JSONPrincipal', JSON.stringify(JSON_));
        localStorage.setItem('JSONDate', new Date());
    } else {
        let now = new Date().getTime()
        let old = Date.parse(localStorage.getItem('JSONDate'))
        let diferencia = now-old
        if (diferencia>2000) {
            response("responseCategorias",{
                categorias: 'todas'
            }, 'categorias')
    
            response("responseSubcategorias",{
                subcategorias: 'todas'
            }, 'subcategorias')
    
            response("responseHilos",{
                hilos: 'todos'
            }, 'hilos')
    
            response("responseRespuestas",{
                respuestas: 'todas'
            }, 'respuestas')
    
            response("responseUsuarios",{
                usuario: 'todos'
            }, 'usuarios')
            localStorage.setItem('JSONPrincipal', JSON.stringify(JSON_));
            localStorage.setItem('JSONDate', new Date());
        } else {
            JSON_ = JSON.parse(localJSON)
        }
    }
    return true
}
let listo = getData();

/*Configuracion general*/
let Config = {
    globalInputsRequired: true,
    placeholder_userlogin: 'Email',
    placeholder_passlogin: 'Contraseña',
    placeholder_fecharegister: 'Fecha de nacimiento',
    placeholder_apellidosregister: 'Apellidos',
    placeholder_nombreregister: 'Nombre',
    pattern_email: '[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$',
    pattern_passwd: '.{8,}',
    calendar_permitirEscritura: false,
    calendar_dateFormat: 'dd/mm/yy',
    calendar_permitirCambiarMes: true,
    calendar_permitirCambiarYear: true,
    calendar_yearRange: '1920:2022'
}

/*
** Clase: Utilidades
** Proyecto: Atlanthis
** Autores: Alejandro Mendoza Zambrana
*/
var UTILS__ = (function() {


    /*
        ** Descripcion: Metodo que devulve el valor de un parametro de la URL
        ** Entrada: Nombre del parametro
        ** Salida: Valor del parametro
    */
    function parametro(v) {
        const params = new Proxy(new URLSearchParams(window.location.search), {
            get: (searchParams, prop) => searchParams.get(prop),
        });
        return params[v]
    }

    /*
        ** Descripcion: Metodo que carga obteniendo un JSON la pagina principal.
        ** Entrada: / 
        ** Salida: /
    */
    function cargaIndex() {
        if (listo) {
            $('.contenedorCategorias').ready(() => {
                Object.keys(JSON_.categorias).forEach(function(k) {
                    let cate_ = categoriaIPL.replaceAll('{0}',JSON_.categorias[k].titulo)
                                            .replaceAll('{1}','categoria.xhtml?codigoCategoria='+JSON_.categorias[k].codCategoria)
                                            .replaceAll('{2}',JSON_.categorias[k].codCategoria)
                    $('.contenedorCategorias').append(cate_) 
                    Object.keys(JSON_.subcategorias).forEach(function(kk) {
                        if (JSON_.subcategorias[kk].codCategoria==JSON_.categorias[k].codCategoria) {
                            let subcate_ = subcategoriaIPL.replaceAll('{0}',JSON_.subcategorias[kk].titulo)
                                        .replaceAll('{1}','subcategoria.xhtml?codigoSubcategoria='+JSON_.subcategorias[kk].codSubcategoria)
                                        .replaceAll('{2}',JSON_.subcategorias[kk].descripcion)
                                        .replaceAll('{3}',JSON_.subcategorias[kk].nHilos)
                                        .replaceAll('{4}',JSON_.hilos[JSON_.subcategorias[kk].lastHilo].titulo)
                                        .replaceAll('{5}','hilo.xhtml?codigoHilo='+JSON_.subcategorias[kk].lastHilo)
                                        .replaceAll('{6}',JSON_.usuarios[JSON_.hilos[JSON_.subcategorias[kk].lastHilo].codUsuario].nombre)
                                        .replaceAll('{7}','perfil.xhtml?codUsuario='+JSON_.hilos[JSON_.subcategorias[kk].lastHilo].codUsuario)
                            $('.subcategoria'+JSON_.categorias[k].codCategoria).append(subcate_)
                        }
                    })
                })
            })
        }
    }

    /*
        ** Descripcion: Metodo que carga obteniendo un JSON la pagina de discusiones.
        ** Entrada: / 
        ** Salida: /
    */
    function cargaDiscusiones() {
        if (listo) {
            $('.contenedorDiscusiones').ready(() => {
                let cate_ = categoriaIPL.replaceAll('{0}','Discusiones')
                                        .replaceAll('{2}','dis')
                $('.contenedorDiscusiones').append(cate_) 
                Object.keys(JSON_.subcategorias).forEach(function(kk) {
                    let subcate_ = subcategoriaIPL.replaceAll('{0}',JSON_.subcategorias[kk].titulo)
                                .replaceAll('{1}','subcategoria.xhtml?codigoSubcategoria='+JSON_.subcategorias[kk].codSubcategoria)
                                .replaceAll('{2}',JSON_.subcategorias[kk].descripcion)
                                .replaceAll('{3}',JSON_.subcategorias[kk].nHilos)
                                .replaceAll('{4}',JSON_.hilos[JSON_.subcategorias[kk].lastHilo].titulo)
                                .replaceAll('{5}','hilo.xhtml?codigoHilo='+JSON_.subcategorias[kk].lastHilo)
                                .replaceAll('{6}',JSON_.usuarios[JSON_.hilos[JSON_.subcategorias[kk].lastHilo].codUsuario].nombre)
                                .replaceAll('{7}','perfil.xhtml?codUsuario='+JSON_.hilos[JSON_.subcategorias[kk].lastHilo].codUsuario)
                    $('.subcategoria'+'dis').append(subcate_)
                })
            })
        }
    }

    /*
        ** Descripcion: Metodo que carga obteniendo un JSON la pagina de categorias.
        ** Entrada: / 
        ** Salida: /
    */
    function cargaCategoria() {
        if (listo) {
            let catCode = parametro('codigoCategoria')
            $('.contenedorCategoria').ready(() => {
                $('.nombreCategoriaCarga').html(JSON_.categorias[catCode].titulo)
                let cate_ = categoriaIPL.replaceAll('{0}',JSON_.categorias[catCode].titulo)
                                        .replaceAll('{1}','categoria.xhtml?codigoCategoria='+catCode)
                                        .replaceAll('{2}',catCode)
                $('.contenedorCategoria').append(cate_) 
                Object.keys(JSON_.subcategorias).forEach(function(kk) {
                    if (JSON_.subcategorias[kk].codCategoria==catCode) {
                        let subcate_ = subcategoriaIPL.replaceAll('{0}',JSON_.subcategorias[kk].titulo)
                                    .replaceAll('{1}','subcategoria.xhtml?codigoSubcategoria='+JSON_.subcategorias[kk].codSubcategoria)
                                    .replaceAll('{2}',JSON_.subcategorias[kk].descripcion)
                                    .replaceAll('{3}',JSON_.subcategorias[kk].nHilos)
                                    .replaceAll('{4}',JSON_.hilos[JSON_.subcategorias[kk].lastHilo].titulo)
                                    .replaceAll('{5}','hilo.xhtml?codigoHilo='+JSON_.subcategorias[kk].lastHilo)
                                    .replaceAll('{6}',JSON_.usuarios[JSON_.hilos[JSON_.subcategorias[kk].lastHilo].codUsuario].nombre)
                                    .replaceAll('{7}','perfil.xhtml?codUsuario='+JSON_.hilos[JSON_.subcategorias[kk].lastHilo].codUsuario)
                        $('.subcategoria'+catCode).append(subcate_)
                    }
                })
            })
        }
    }

    /*
        ** Descripcion: Metodo que carga obteniendo un JSON la pagina de subcategorias.
        ** Entrada: / 
        ** Salida: /
    */
    function cargaSubcategoria() {
        if (listo) {
            let subCode = parametro('codigoSubcategoria')
            $('.contenedorSubcategoria').ready(() => {
                $('.nombreCategoriaCarga').html('<a href="categoria.xhtml?codigoCategoria='+JSON_.subcategorias[subCode].codCategoria+'">'+JSON_.categorias[JSON_.subcategorias[subCode].codCategoria].titulo+'</a>')
                $('.nombreSubcategoriaCarga').html(JSON_.subcategorias[subCode].titulo)

                let subcate_ = categoriaIPL.replaceAll('{0}',JSON_.subcategorias[subCode].titulo)
                                        .replaceAll('{1}','subcategoria.xhtml?codigoSubcategoria='+subCode)
                                        .replaceAll('{2}',subCode)
                $('.contenedorSubcategoria').append(subcate_) 
                Object.keys(JSON_.hilos).forEach(function(kk) {
                    if (JSON_.hilos[kk].codSubcategoria==subCode && JSON_.hilos[kk].anclado == 1) {
                        let hilo_ = hilo2IPL.replaceAll('{0}',JSON_.hilos[kk].titulo)
                                    .replaceAll('{1}','hilo.xhtml?codigoHilo='+JSON_.hilos[kk].codHilo)
                                    .replaceAll('{6}',JSON_.usuarios[JSON_.hilos[kk].codUsuario].nombre)
                                    .replaceAll('{7}','perfil.xhtml?codUsuario='+JSON_.hilos[kk].codUsuario)
                        $('.subcategoria'+subCode).append(hilo_)
                    }
                })
                Object.keys(JSON_.hilos).forEach(function(kk) {
                    if (JSON_.hilos[kk].codSubcategoria==subCode && JSON_.hilos[kk].anclado == "null" || JSON_.hilos[kk].codSubcategoria==subCode && JSON_.hilos[kk].anclado == 0) {
                        let hilo_
                        if (JSON_.hilos[kk].cerrado==1) {
                            hilo_ = hilo3IPL.replaceAll('{0}',JSON_.hilos[kk].titulo)
                                    .replaceAll('{1}','hilo.xhtml?codigoHilo='+JSON_.hilos[kk].codHilo)
                                    .replaceAll('{6}',JSON_.usuarios[JSON_.hilos[kk].codUsuario].nombre)
                                    .replaceAll('{7}','perfil.xhtml?codUsuario='+JSON_.hilos[kk].codUsuario)
                        } else {
                            hilo_ = hiloIPL.replaceAll('{0}',JSON_.hilos[kk].titulo)
                                .replaceAll('{1}','hilo.xhtml?codigoHilo='+JSON_.hilos[kk].codHilo)
                                .replaceAll('{6}',JSON_.usuarios[JSON_.hilos[kk].codUsuario].nombre)
                                .replaceAll('{7}','perfil.xhtml?codUsuario='+JSON_.hilos[kk].codUsuario)
                        }
                        
                        $('.subcategoria'+subCode).append(hilo_)
                    }
                })
            })
        }
    }

    /*
    ** Descripcion: Metodo que carga obteniendo un JSON la pagina de hilo.
    ** Entrada: / 
    ** Salida: /
    */
    function cargaHilos() {
        if (listo) {
            let hiloCode = parametro('codigoHilo')
            $('.contenedorHilos').ready(() => {
                $('.nombreCategoriaCarga').html('<a href="categoria.xhtml?codigoCategoria='+JSON_.hilos[hiloCode].codCategoria+'">'+JSON_.categorias[JSON_.hilos[hiloCode].codCategoria].titulo+'</a>')
                $('.nombreSubcategoriaCarga').html('<a href="subcategoria.xhtml?codigoSubcategoria='+JSON_.hilos[hiloCode].codSubcategoria+'">'+JSON_.subcategorias[JSON_.hilos[hiloCode].codSubcategoria].titulo+'</a>')
                $('.nombreHilo').html(JSON_.hilos[hiloCode].titulo)
                let i_ = 0
                let codPrimera = 0
                //Primera respusta del hilo , con la que se creo
                Object.keys(JSON_.respuestas).forEach(function(index) {
                    if (JSON_.respuestas[index].codHilo == hiloCode && i_ == 0) {
                        let permiso = JSON_.usuarios[JSON_.respuestas[index].codUsuario].permiso
                        if (permiso==null || permiso=="null" || permiso==0) {
                            permiso = 'Usuario'
                        } else if (permiso==1) {
                            permiso = 'Soporte'
                        } else if (permiso==2) {
                            permiso = 'Moderador'
                        } else if (permiso==3) {
                            permiso = 'Administrador'
                        }
                        let respuesta1_ = respuesta1IPL.replaceAll('{1}',JSON_.usuarios[JSON_.respuestas[index].codUsuario].imagen)
                                                .replaceAll('{2}',JSON_.usuarios[JSON_.respuestas[index].codUsuario].nombre)
                                                .replaceAll('{3}',permiso)
                                                .replaceAll('{4}',JSON_.respuestas[index].contenido)
                                                .replaceAll('{5}','perfil.xhtml?codUsuario='+JSON_.respuestas[index].codUsuario)
                        $('.contenedorHilos').append(respuesta1_)
                        i_++
                        codPrimera = JSON_.respuestas[index].codRespuesta
                    }
                })
                //Respuestas que son solucion
                Object.keys(JSON_.respuestas).forEach(function(index) {
                    if (JSON_.respuestas[index].codHilo == hiloCode && JSON_.respuestas[index].solucion == 1) {
                        let permiso = JSON_.usuarios[JSON_.respuestas[index].codUsuario].permiso
                        if (permiso==null || permiso=="null" || permiso==0) {
                            permiso = 'Usuario'
                        } else if (permiso==1) {
                            permiso = 'Soporte'
                        } else if (permiso==2) {
                            permiso = 'Moderador'
                        } else if (permiso==3) {
                            permiso = 'Administrador'
                        }
                        let respuesta1_ = respuesta2IPL.replaceAll('{1}',JSON_.usuarios[JSON_.respuestas[index].codUsuario].imagen)
                                                .replaceAll('{2}',JSON_.usuarios[JSON_.respuestas[index].codUsuario].nombre)
                                                .replaceAll('{3}',permiso)
                                                .replaceAll('{4}',JSON_.respuestas[index].contenido)
                                                .replaceAll('{5}','perfil.xhtml?codUsuario='+JSON_.respuestas[index].codUsuario)         
                        let respu_       
                        if (JSON_.hilos[JSON_.respuestas[index].codHilo].codUsuario == $('.codUsuarioFinal').val()) {
                            respu_ = respuesta1_.replaceAll('{6}','<button onclick="cambiarSolucion('+JSON_.respuestas[index].codRespuesta+')" style="padding: 0.5rem;" class="ui-button ui-widget ui-state-default ui-corner-all ms-3 me-3" type="button"><i class="fa-solid fa-xmark"></i> Solucion</button>')
                        } else {
                            respu_ = respuesta1_.replaceAll('{6}','')
                        }            
                        $('.contenedorHilos').append(respu_)                            
                    }
                })

                let objOrdenado = ordenarRespuesta("votos")
                //Respuestas que son normales ordenadas por votos
                Object.keys(objOrdenado).forEach(function(index) {
                    if (objOrdenado[index].codHilo == hiloCode && objOrdenado[index].solucion == 0 && codPrimera != objOrdenado[index].codRespuesta
                        || objOrdenado[index].codHilo == hiloCode && objOrdenado[index].solucion == null && codPrimera != objOrdenado[index].codRespuesta
                        || objOrdenado[index].codHilo == hiloCode && objOrdenado[index].solucion == "null" && codPrimera != objOrdenado[index].codRespuesta) {
                        let permiso = JSON_.usuarios[objOrdenado[index].codUsuario].permiso
                        if (permiso==null || permiso=="null" || permiso==0) {
                            permiso = 'Usuario'
                        } else if (permiso==1) {
                            permiso = 'Soporte'
                        } else if (permiso==2) {
                            permiso = 'Moderador'
                        } else if (permiso==3) {
                            permiso = 'Administrador'
                        }
                        let respuesta1_ = respuesta3IPL.replaceAll('{1}',JSON_.usuarios[objOrdenado[index].codUsuario].imagen)
                                                .replaceAll('{2}',JSON_.usuarios[objOrdenado[index].codUsuario].nombre)
                                                .replaceAll('{3}',permiso)
                                                .replaceAll('{4}',objOrdenado[index].contenido)
                                                .replaceAll('{5}','perfil.xhtml?codUsuario='+objOrdenado[index].codUsuario)
                                                .replaceAll('{6}','<button onclick="subirRespuesta('+objOrdenado[index].codRespuesta+')" style="padding: 0.5rem;" class="ui-button ui-widget ui-state-default ui-corner-all ms-3 me-1" type="button"><i class="fa-solid fa-square-caret-up"></i></button>')   
                                                .replaceAll('{7}','<button onclick="bajarRespuesta('+objOrdenado[index].codRespuesta+')" style="padding: 0.5rem;" class="ui-button ui-widget ui-state-default ui-corner-all ms-1" type="button"><i class="fa-solid fa-square-caret-down"></i></button>')            

                        let respu_       
                        if (JSON_.hilos[objOrdenado[index].codHilo].codUsuario == $('.codUsuarioFinal').val()) {
                            respu_ = respuesta1_.replaceAll('{8}','<button onclick="cambiarSolucion('+objOrdenado[index].codRespuesta+')" style="padding: 0.5rem;" class="ui-button ui-widget ui-state-default ui-corner-all ms-3 me-3" type="button"><i class="fa-solid fa-check"></i> Solucion</button>')
                        } else {
                            respu_ = respuesta1_.replaceAll('{8}','')
                        }            
                        $('.contenedorHilos').append(respu_)                            
                    }
                })
            })
        }
    }

        /*
            ** Descripcion: Metodo que ordena los objetos respuesta, tal y como se le indica con los parametros de entrada
            ** Entrada: propiedad del objeto por la que se va a ordenar
            ** Salida: objeto ordenado
        */
        function ordenarRespuesta(kk) {
            let o = {}
            let o_ = {}
            Object.keys(JSON_.respuestas).forEach(function(k) {
                o[k] = JSON_.respuestas[k][kk]
            })
    
            let o__ = Object.keys(o).sort(function(a,b){return o[b]-o[a];})
            for (let i=0;i!=Object.keys(JSON_.respuestas).length;i++) {
                o_[i] = JSON_.respuestas[o__[i]]
            }
            
            return o_
        }

    /*
        ** Descripcion: Metodo que carga el perfil de un usuario.
        ** Entrada: / 
        ** Salida: /
    */
    function cargarPerfil() {
        // 0 imagen del usuarios, 1 nombre del usuario, 2 apellido del usuario, 3 fecha de nacimiento, 4 pais, 5 rango del usuario
        if (listo) {
            let usuCode = parametro('codUsuario')
            $('.contenedorPerfiles').ready(() => {
                $('.nombrePerfil').html("Perfil de "+JSON_.usuarios[usuCode].nombre)
                let permiso = JSON_.usuarios[usuCode].permiso
                if (permiso==null || permiso=="null" || permiso==0) {
                    permiso = 'Usuario'
                } else if (permiso==1) {
                    permiso = 'Soporte'
                } else if (permiso==2) {
                    permiso = 'Moderador'
                } else if (permiso==3) {
                    permiso = 'Administrador'
                }
                let perfil_ = perfilIPL.replaceAll('{0}',JSON_.usuarios[usuCode].imagen)
                                        .replaceAll('{1}',JSON_.usuarios[usuCode].nombre)
                                        .replaceAll('{2}',JSON_.usuarios[usuCode].apellidos)
                                        .replaceAll('{3}',JSON_.usuarios[usuCode].fechaNacimiento)
                                        .replaceAll('{4}',JSON_.usuarios[usuCode].pais)
                                        .replaceAll('{5}',permiso)
                $('.contenedorPerfiles').append(perfil_) 
            })
        }
    }

    /*
        ** Descripcion: Metodo que rellena los placeholder que no se pueden rellenar por culpa de JSF.
        ** Entrada: String / nombre de la pagina donde se va a aplicar
        ** Salida: /
    */
    function placeholderFix(v) {
        if (v==='inicio') {
            $('.user_login').attr('placeholder', Config.placeholder_userlogin)
            $('.passwd_login').attr('placeholder', Config.placeholder_passlogin)
        } else if (v==='register') {
            $('.name_register').attr('placeholder', Config.placeholder_nombreregister)
            $('.apellidos_register').attr('placeholder', Config.placeholder_apellidosregister)
            $('.email_register').attr('placeholder', Config.placeholder_userlogin)
            $('.email_register').attr('pattern', Config.pattern_email)
            $('.passwd_register').attr('placeholder', Config.placeholder_passlogin)
            $('.passwd_register').attr('pattern', Config.pattern_passwd)
            $('.fechanaci_register').attr('placeholder', Config.placeholder_fecharegister)
            $('.fechanaci_register').attr('readonly', Config.calendar_permitirEscritura).datepicker({
                dateFormat: Config.calendar_dateFormat,
                changeMonth: Config.calendar_permitirCambiarMes,
                changeYear: Config.calendar_permitirCambiarYear,
                yearRange: Config.calendar_yearRange,
            })
        }
        $('input').prop('required',Config.globalInputsRequired);
    }

    function createAdminStatic() {
        if (listo) {
            let ar_ = []
            let i = 0
            Object.keys(JSON_.respuestas).forEach(function(k) {
                ar_[i] = [JSON_.hilos[JSON_.respuestas[k].codHilo].titulo,parseInt(JSON_.respuestas[k].codHilo, 10)]
                i++
            })
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Titulo Hilo');
                data.addColumn('number', 'Respuestas');
                data.addRows(ar_)
                var options = {'title': 'Hilos con mas Respuestas',
                    colors: ['#0068A3', '#038c8c'],
                    'width': 800,
                    'curveType': 'function',
                    'hAxis': {
                        title: 'Titulos'
                    },
                    'vAxis': {
                        title: 'Hilos con mas respuestas'
                    },
                    'height': 500};
                var chart = new google.visualization.LineChart(document.getElementById('char1'));
                var chart2 = new google.visualization.PieChart(document.getElementById('char2'));
                chart.draw(data, options);
                chart2.draw(data, options);
            }
        }
    }

    /*
        ** Descripcion: Metodo que oculta la caja del mensaje de error en el login cuando este vacio.
        ** Entrada: /
        ** Salida: /
    */
    function ocultarError() {
        let local_ = $('.errorLogin')
        local_.html()=="" || local_.html()==null? local_.hide():local_.show()
    }

    /*
        ** Metodos publicos
    */
    return {
        placeholderFix:placeholderFix,
        ocultarError:ocultarError,
        cargaIndex:cargaIndex,
        cargaCategoria:cargaCategoria,
        parametro:parametro,
        cargarPerfil:cargarPerfil,
        cargaHilos:cargaHilos,
        cargaSubcategoria:cargaSubcategoria,
        createAdminStatic:createAdminStatic,
        cargaDiscusiones:cargaDiscusiones
    }
})()
