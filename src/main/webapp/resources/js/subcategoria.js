$(function() {
    console.clear()
    let utils_ = UTILS__
    utils_.cargaSubcategoria()

    $('.botonCrearHilo').on('click', () => {
        let titulo = $('.tituloHilo').val()
        let contenido = $('.ql-editor').html()
        $.getJSON('guardarNewHilo', {
            tit: titulo,
            con: contenido,
            idSubcate: utils_.parametro('codigoSubcategoria'),
            idUsu: $('.codUsuarioFinal').val()
        })
        window.location.href = "subcategoria.xhtml?codigoSubcategoria="+utils_.parametro('codigoSubcategoria');
    })
});