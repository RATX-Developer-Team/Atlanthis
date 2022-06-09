let utils_ = UTILS__

function cambiarSolucion(code) {
    $.getJSON('cambiarSolucion', {
        cod: code
    })
    window.location.href = "hilo.xhtml?codigoHilo="+utils_.parametro('codigoHilo');
}

function subirRespuesta(code) {
    $.getJSON('subirRespuesta', {
        cod: code
    })
    window.location.href = "hilo.xhtml?codigoHilo="+utils_.parametro('codigoHilo');
}

function bajarRespuesta(code) {
    $.getJSON('bajarRespuesta', {
        cod: code
    })
    window.location.href = "hilo.xhtml?codigoHilo="+utils_.parametro('codigoHilo');
}

$(function() {
    console.clear()
    utils_ = UTILS__
    utils_.cargaHilos()

    $('.botonCrearRespuesta').on('click', () => {
        let contenido = $('.ql-editor').html()
        $.getJSON('guardarNewRespuesta', {
            con: contenido,
            idHilo: utils_.parametro('codigoHilo'),
            idUsu: $('.codUsuarioFinal').val()
        })
        window.location.href = "hilo.xhtml?codigoHilo="+utils_.parametro('codigoHilo');
    })

});