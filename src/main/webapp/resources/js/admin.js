$(function() {
    console.clear()
    let utils_ = UTILS__
    $('.tablaPlugin').ready(function() {
        $('.tablaPlugin').DataTable({
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/es-ES.json'
            }
        })
    })
});