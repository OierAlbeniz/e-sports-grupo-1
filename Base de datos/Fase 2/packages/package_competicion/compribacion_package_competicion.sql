/*-----comprobacion de el package de crear, modificar y eliminar competicion------*/


/*esta es la ejecucion de la creacion de competicion desde el package */
--llamamos al procedure que esta dentro de el package 
--le pasamos los diferentes datos para poder meter los datos

execute competicion_pkg.crear_competicion('prue_comp', TO_DATE('2025-02-12', 'YYYY-MM-DD'), TO_DATE('2023-07-31', 'YYYY-MM-DD'), 'abierto', 4);
select * from competicion;



/*esta es la ejecucion de la modificacion de competicion desde el package */
--llamamos al procedure que esta dentro de el package 
--seleccionamos el id_competicion buscando por nombre para porder identificar la competicion

/*en el proyecto java habra un desplegable para seleccionar la competicion
y cuando selecciones se guardara en una variable el id que
servira para poder identificar la competicion y poder usar este procedure*/

select id_competicion from competicion where lower(nombre)='prue_comp';
 execute competicion_pkg.modificar_competicion(12, 'Nuevo nombre', TO_DATE('2025-02-12', 'YYYY-MM-DD'), TO_DATE('2023-07-31', 'YYYY-MM-DD'), 'cerrado', 4);
select * from competicion where id_competicion=12;          --miramos si se ha modificado


/*esta es la ejecucion de la creacion de competicion desde el package */
--llamamos al procedure que esta dentro de el package 
--buscamos el id de la competicion por nombre para poder identificar el equipo
select id_competicion from competicion where lower(nombre)='prue_comp';
 execute competicion_pkg.eliminar_competicion(12);          --ejecutamos con el id que nos a dado la select
 
 