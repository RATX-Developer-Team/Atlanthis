/*VARIABLES GLOBALES*/

/*Configuracion general*/
let Config = {
    placeholder_userlogin: 'Email',
    placeholder_passlogin: 'Contraseña'
}

/*
** Clase: Utilidades
** Proyecto: Atlanthis
** Autores: Alejandro Mendoza Zambrana
*/
var UTILS__ = (function() {


    /*
        ** Descripcion: Metodo que rellena los placeholder que no se pueden rellenar por culpa de JSF.
        ** Entrada: String / nombre de la pagina donde se va a aplicar
        ** Salida: /
    */
    function placeholderFix(v) {
        if (v==='inicio') {
            $('.user_login').attr('placeholder', Config.placeholder_userlogin)
            $('.passwd_login').attr('placeholder', Config.placeholder_passlogin)
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
        ocultarError:ocultarError
    }
})()
