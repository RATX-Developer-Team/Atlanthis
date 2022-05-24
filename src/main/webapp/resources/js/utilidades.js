/*VARIABLES GLOBALES*/

/*Configuracion general*/
let Config = {
    placeholder_userlogin: 'Email',
    placeholder_passlogin: 'Contraseña',
    placeholder_fecharegister: 'Fecha de nacimiento',
    placeholder_apellidosregister: 'Apellidos',
    placeholder_nombreregister: 'Nombre',
    pattern_email: '[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$',
    pattern_passwd: '.{8,}'
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
        } else if (v==='register') {
            $('.name_register').attr('placeholder', Config.placeholder_nombreregister)
            $('.apellidos_register').attr('placeholder', Config.placeholder_apellidosregister)
            $('.email_register').attr('placeholder', Config.placeholder_userlogin)
            $('.email_register').attr('pattern', Config.pattern_email)
            $('.passwd_register').attr('placeholder', Config.placeholder_passlogin)
            $('.passwd_register').attr('pattern', Config.pattern_passwd)
            $('.fechanaci_register').attr('placeholder', Config.placeholder_fecharegister)
            $('.fechanaci_register').attr('readonly', false).datepicker({
                dateFormat: 'dd/mm/yy',
                changeMonth: true,
                changeYear: true,
                yearRange: "1920:2021"
            })
        }
        $('input').prop('required',true);
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
