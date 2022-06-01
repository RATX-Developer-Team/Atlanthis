//0 titulo de la categoria, 1 enlace a ver la categoria, 2 numero de categoria
let categoriaIPL =  '<div class="categoria">'+
                        '<a href="{1}" aria-label="Dirigirse a la categoria {0}" class="enlaceCategoria"><h4 class="display-6 fs-4 tituloCategoria">{0}</h4></a>'+
                        '<div class="subcategoria{2}">'+ 
                        '</div>'+
                    '</div>'

// 0 Titulo Subcategoria, 1 Enlace a la subcategoria, 2 descripcion subcategoria, 3 temas en la subcategoria, 4 Titulo ultimo hilo, 5 Enlace ultimo hilo, 6 Nombre autor, 7 Enlace perfil autor
let subcategoriaIPL =   '<div class="subcategoria d-flex flex-row justify-content-between">'+ 
                        '<div class="d-flex flex-column">'+ 
                            '<a href="{1}" aria-label="Dirigirse a la subcategoria {0}" class="enlaceSubcategoria"><h4 class="display-6 fs-4">{0}</h4></a>'+
                            '<h5 class="display-6 fs-6">{2}</h5>'+
                        '</div>'+
                        '<div class="d-flex flex-row">'+
                            '<div class="d-flex align-items-center flex-column me-5">'+
                                '<h4 class="display-6 fs-4">{3}</h4>'+
                                '<h5 class="display-6 fs-5">Temas</h5>'+
                            '</div>'+
                            '<div class="d-flex flex-column justify-content-center">'+
                                '<a href="{5}" aria-label="Dirigirse al hilo {4}" class="enlaceHilo"><h4 class="display-6 fs-4">{4}</h4></a>'+
                                '<h5 class="display-6 fs-6">Por <a href="{7}" aria-label="Dirigirse al perfil de {6}" class="enlaceHilo">{6}</a></h5>'+
                            '</div>'+
                        '</div>'+
                        '</div>'