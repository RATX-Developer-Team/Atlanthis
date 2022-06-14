$(function() {
    console.clear()
    let utils_ = UTILS__
    let buscar = utils_.parametro('buscar')
    let obj = {}
    $.getJSON('buscarHilos', {
        bus: buscar,
    }, function (data) {
        $.each(data, function(index,v) {
            obj[index] = JSON.parse(v)
        })
    })

    $('.subcategoriaBusqueda').ready(() => {
        Object.keys(obj).forEach(function(kk) {
            let hilo_
            if (obj[kk].cerrado==1) {
                hilo_ = hilo3IPL.replaceAll('{0}',obj[kk].titulo)
                        .replaceAll('{1}','hilo.xhtml?codigoHilo='+obj[kk].codHilo)
                        .replaceAll('{6}',JSON_.usuarios[obj[kk].codUsuario].nombre)
                        .replaceAll('{7}','perfil.xhtml?codUsuario='+obj[kk].codUsuario)
            } else {
                hilo_ = hiloIPL.replaceAll('{0}',obj[kk].titulo)
                    .replaceAll('{1}','hilo.xhtml?codigoHilo='+obj[kk].codHilo)
                    .replaceAll('{6}',JSON_.usuarios[obj[kk].codUsuario].nombre)
                    .replaceAll('{7}','perfil.xhtml?codUsuario='+obj[kk].codUsuario)
            }
            
            $('.subcategoriaBusqueda').append(hilo_)
        })
    })
});