$(function() {
    console.clear()
    let utils_ = UTILS__
    utils_.cargarPerfil()
    $('.botonEditarMiPerfil').ready(function() {
        let usuCod = $('.codUsuarioFinal').val()
        let perfilCod = utils_.parametro('codUsuario')
        usuCod==perfilCod?$('.botonEditarMiPerfil').show():$('.botonEditarMiPerfil').hide()
    })
});