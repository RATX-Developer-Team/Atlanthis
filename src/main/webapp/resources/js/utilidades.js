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
                        if (JSON_.hilos[kk].codSubcategoria==subCode) {
                            let hilo_ = hiloIPL.replaceAll('{0}',JSON_.hilos[kk].titulo)
                                        .replaceAll('{1}','hilo.xhtml?codigoHilo='+JSON_.hilos[kk].codHilo)
                                        .replaceAll('{6}',JSON_.usuarios[JSON_.hilos[kk].codUsuario].nombre)
                                        .replaceAll('{7}','perfil.xhtml?codUsuario='+JSON_.hilos[kk].codUsuario)
                            $('.subcategoria'+subCode).append(hilo_)
                        }
                    })
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
        cargaSubcategoria:cargaSubcategoria
    }
})()
