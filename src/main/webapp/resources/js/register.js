$(function() {
    console.clear()
    let utils_ = UTILS__
    utils_.ocultarError()
    utils_.placeholderFix('register')
    
    $.validator.addMethod("checkEmail",
        (value, element) => {
            let entra = true
            $.getJSON('checkEmail', {
                email: value
            }, function (data) {
                $.each(data, function(index,v) {
                    v=="false"?entra=true:entra=false
                })
            })
            return entra
        }, "Ya existe un usuario con este email. Prueba con otro distinto"
    )
    
    let emailName = $('.email_register').attr('name')

    $('.formularioPrincipalRegister').validate({
        rules: {
            [emailName]: {
                checkEmail: true
            }
        },
        errorElement : 'div',
        errorLabelContainer: '.errorTxt'
    })


    PrimeFaces.locales['es'] = {
        closeText: 'Cerrar',
        prevText: 'Anterior',
        nextText: 'Siguiente',
        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
        dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
        dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
        weekHeader: 'Semana',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
        timeOnlyTitle: 'Sólo hora',
        timeText: 'Tiempo',
        hourText: 'Hora',
        minuteText: 'Minuto',
        secondText: 'Segundo',
        millisecondText: 'Milisegundo',
        currentText: 'Fecha actual',
        ampm: false,
        month: 'Mes',
        week: 'Semana',
        day: 'Día',
        allDayText: 'Todo el día',
        today: 'Hoy',
        clear: 'Claro'
    };
});