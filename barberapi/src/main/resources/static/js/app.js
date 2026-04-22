/*Calendario dinamico*/
document.addEventListener('DOMContentLoaded',  () => {

    //VARIABLES GLOBALES
    let fechaSeleccionada = null;
    let horaSeleccionada = null;
    const inputfechaHoraOculto = document.querySelector('#fechaHora');
    const tituloMes = document.querySelector('#titulo-mes');
    const contenedorDias = document.querySelector('#contenedor-dias');
    const horas = document.querySelectorAll(".time-slot");


    //LOGICA DE FECHAS
    const nombreMeses = ['ENERO', 'FEBRERO', 'MARZO', 'ABRIL', 'MAYO', 'JUNIO', 'JULIO', 'AGOSTO', 'SEPTIEMBRE', 'OCTUBRE', 'NOVIEMBRE', 'DICIEMBRE'];
    const fechaActual =  new Date(); //Día real HOY
    let mesRender = fechaActual.getMonth() // Visualizamos el mes en el que estamos
    let anioRender = fechaActual.getFullYear() // Visualizamos el año vigente

    //MOSTRAMOS CALENDARIO
    const renderizarCalendario = (mes, anyo) => {
        //Actualizamos mes
        tituloMes.textContent = `${nombreMeses[mes]}, ${anyo}`;
        contenedorDias.innerHTML = "";

        //Cálculo de días
        const primerDiasMes = new Date(anyo, mes, 1).getDay();
        //La semana debe ser obligatoriamente 0 o 6 si no se resta 1 día. Ajuste para que lunes sea el primer día
        const inicioSemana = primerDiasMes === 0 ? 6 : primerDiasMes - 1;
        const diasEnMes = new Date(anyo, mes +  1, 0).getDate(); // Meses con 31 días
        const diasMesAnterior = new Date(anyo,mes, 0).getDate();

        let htmlDias = "";
        const hoy = new Date();
        const strHoy = hoy.toISOString().split("T")[0]; // Tipo de formato fecha YYYY-MM-DD

        //Rellenar días del mes anterior (grises/muted)
        for (let i = inicioSemana - 1; i>=0; i--) {
            htmlDias += `<div class="dia muted"><span>${diasMesAnterior-1}</span></div>`;
        }

        //rellenar dias del mes actual
        for (let i = 1; i<=diasEnMes; i++) {
            let mesStr = (mes + 1).toString().padStart(2, '0');
            let diasStr = i.toString().padStart(2, '0');
            let fechaCompleta = `${anyo}-${mesStr}-${diasStr}`;

            let claseExtra = "";
            //Bloquear dias anteriores a HOY
            if (fechaCompleta < strHoy) {
                claseExtra = "disabled";
            }

            htmlDias += `<div class="dia ${claseExtra}" data-fecha="${fechaCompleta}"><span>${i}</span></div>`;
        }
    }
})