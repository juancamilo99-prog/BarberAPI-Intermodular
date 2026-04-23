/*Calendario dinamico*/
document.addEventListener('DOMContentLoaded', function() {

    //VARIABLES GLOBALES
    let fechaSeleccionada = null;
    let horaSeleccionada = null;
    const inputFechaHoraOculto = document.querySelector('#fechaHora');
    const tituloMes = document.querySelector('#titulo-mes');
    const contenedorDias = document.querySelector('#contenedor-dias');
    const horas = document.querySelectorAll('.time-slot');
    const tituloHorasDisponibles = document.querySelector('#titulo-horas-disponibles');

    //LOGICA DE FECHAS
    const nombreMeses = ['ENERO', 'FEBRERO', 'MARZO', 'ABRIL', 'MAYO', 'JUNIO', 'JULIO', 'AGOSTO', 'SEPTIEMBRE', 'OCTUBRE', 'NOVIEMBRE', 'DICIEMBRE'];
    const fechaActual = new Date(); //Día real HOY
    let mesRender = fechaActual.getMonth(); // Visualizamos el mes en el que estamos
    let anioRender = fechaActual.getFullYear(); // Visualizamos el año vigente

    //MOSTRAMOS CALENDARIO
    const renderizarCalendario = (mes, anyo) => {
        //Actualizamos mes
        tituloMes.textContent = `${nombreMeses[mes]}, ${anyo}`;
        contenedorDias.innerHTML = "";

        //Actualiza el título de las horas
        tituloHorasDisponibles.textContent = `DIAS DISPONIBLES - ${nombreMeses[mes]}, ${anyo}`;
        contenedorDias.innerHTML = "";

        //Cálculo de días
        const primerDiasMes = new Date(anyo, mes, 1).getDay();
        //La semana debe ser obligatoriamente 0 o 6 si no se resta 1 día. Ajuste para que lunes sea el primer día
        const inicioSemana = primerDiasMes === 0 ? 6 : primerDiasMes - 1;
        const diasEnMes = new Date(anyo, mes + 1, 0).getDate();
        const diasMesAnterior = new Date(anyo, mes, 0).getDate();

        let htmlDias = "";
        const hoy = new Date();
        const strHoy = hoy.toISOString().split("T")[0]; // Tipo de formato fecha YYYY-MM-DD

        //Rellenar días del mes anterior (grises/muted)
        for (let i = inicioSemana - 1; i >= 0; i--) {
            htmlDias += `<div class="dia muted"><span>${diasMesAnterior - i}</span></div>`;
        }

        //Rellenar dias del mes actual
        for (let i = 1; i <= diasEnMes; i++) {
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

        //Rellenar días del mes siguente y ordena la tabla
        const totalCeldas = inicioSemana + diasEnMes;
        const celdasRestantes = totalCeldas % 7 === 0 ? 0 : 7 - (totalCeldas % 7);
        for (let i = 1; i <= celdasRestantes; i++) {
            htmlDias += `<div class="dia muted"><span>${i}</span></div>`;
        }


        //Se agrega la logica al HTML
        contenedorDias.innerHTML = htmlDias;

        //Volver a activar los clics en los días que ya se seleccionaron
        activarEventosDias();
    };

    //Activar clic Días
    const activarEventosDias = () => {
        const diasDisponibles = document.querySelectorAll(".dia[data-fecha]:not(.disabled)");

        diasDisponibles.forEach(dia => {
            dia.addEventListener('click', function() {
                //Limpiar selección anterior
                diasDisponibles.forEach(d => {
                    d.style.backgroundColor = "";
                    d.style.color = "";
                });

                //Pintar el seleccionado
                this.style.backgroundColor = "var(--accent-gold)";
                this.style.color = "#000";

                fechaSeleccionada = this.dataset.fecha;
                actualizarFormulario();
            });
        });
    };

    // Evento clic en Horas )
    horas.forEach(hora => {
        hora.addEventListener('click', function() {
            horas.forEach(h => {
                h.style.backgroundColor = "#333333";
                h.style.color = "#ffffff";
            });

            this.style.backgroundColor = "var(--accent-gold)";
            this.style.color = "#000";

            horaSeleccionada = this.dataset.hora;
            actualizarFormulario();
        });
    });

    const actualizarFormulario = () => {
        if(fechaSeleccionada && horaSeleccionada){
            inputFechaHoraOculto.value = `${fechaSeleccionada}T${horaSeleccionada}`;
            console.log("Fecha lista para enviar: " + inputFechaHoraOculto.value);
        }
    };

    //NAVEGACIÓN MES anterior / siguiente
    document.querySelector("#btn-prev").addEventListener("click", () => {
        mesRender--;
        if (mesRender < 0) {
            mesRender = 11;
            anioRender--;
        }
        renderizarCalendario(mesRender, anioRender);
    });

    document.querySelector("#btn-next").addEventListener("click", () => {
        mesRender++;
        if (mesRender > 11){
            mesRender = 0;
            anioRender++;
        }
        renderizarCalendario(mesRender, anioRender);
    });

    //Validación antes de enviar formulario
    document.querySelector("#form-reserva").addEventListener("submit", function (e) {
        if (!inputFechaHoraOculto.value){
            e.preventDefault();
            alert("Por favor, selecciona una fecha y una hora en el calendario antes de pagar.");
        }
    });

    //Inicia el calendario cuando carga la página
    renderizarCalendario(mesRender, anioRender);

});