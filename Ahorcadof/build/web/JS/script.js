document.addEventListener('DOMContentLoaded', () => {
    const palabra_Oculta = document.getElementById('palabra-oculta');
    const imagen_Ahorcado = document.getElementById('imagen-ahorcado');
    const teclado_Letras = document.getElementById('teclado-letras');
    const mensaje = document.getElementById('mensaje');
    const pistas = document.getElementById('pistas');
    const temporizador_Elemento = document.getElementById('temporizador');

    const btn_Iniciar = document.getElementById('btn-iniciar');
    const btn_Pausar = document.getElementById('btn-pausar');
    const btn_Reiniciar = document.getElementById('btn-reiniciar');
    const btn_Salir = document.getElementById('btn-salir');

    let objeto_Seleccionado = null;
    let palabra_Mostrada = '';
    let errores = 0;
    const max_Errores = 6;
    let juego_En_Pausa = false;

    let tiempo_Restante = 120;
    let temporizador_Intervalo;

    function manejar_Teclado(habilitar) {
        const botones_Teclado = Array.from(teclado_Letras.children);
        botones_Teclado.forEach(boton => {
            boton.disabled = !habilitar;
        });
    }

    async function iniciar_Juego() {
        try {
            const respuesta = await fetch('./Controlador?accion=obtenerPalabra');
            if (!respuesta.ok) {
                throw new Error('Error al obtener la palabra del servidor');
            }
            const palabra_Data = await respuesta.json();

            objeto_Seleccionado = {
                palabra: palabra_Data.palabra.toUpperCase(),
                pistas: [palabra_Data.pista_1, palabra_Data.pista_2, palabra_Data.pista_3]
            };

            palabra_Mostrada = '_'.repeat(objeto_Seleccionado.palabra.length);
            errores = 0;

            palabra_Oculta.textContent = palabra_Mostrada.split('').join(' ');
            imagen_Ahorcado.src = 'Images/1.png';
            mensaje.textContent = '';
            pistas.innerHTML = '<h4>Pistas:</h4>';
            objeto_Seleccionado.pistas.forEach(p => {
                const li = document.createElement('p');
                li.textContent = `- ${p}`;
                pistas.appendChild(li);
            });

            crear_Teclado();
            manejar_Teclado(true);
            iniciar_Temporizador();
        } catch (error) {
            console.error(error);
            mensaje.textContent = 'No se pudo conectar con el servidor.';
        }
    }

    function crear_Teclado() {
        teclado_Letras.innerHTML = '';
        const letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for (let letra of letras) {
            const boton = document.createElement('button');
            boton.textContent = letra;
            boton.addEventListener('click', () => manejar_Adivinanza(letra, boton));
            teclado_Letras.appendChild(boton);
        }
    }

    function manejar_Adivinanza(letra, boton) {
        boton.disabled = true;
        let acierto = false;
        let nueva_Palabra = '';

        for (let i = 0; i < objeto_Seleccionado.palabra.length; i++) {
            if (objeto_Seleccionado.palabra[i] === letra) {
                nueva_Palabra += letra;
                acierto = true;
            } else {
                nueva_Palabra += palabra_Mostrada[i];
            }
        }

        palabra_Mostrada = nueva_Palabra;
        palabra_Oculta.textContent = palabra_Mostrada.split('').join(' ');

        if (!acierto) {
            errores++;
            imagen_Ahorcado.src = `Images/${errores + 1}.png`;
        }

        verificar_Estado_Juego();
    }

    function verificar_Estado_Juego() {
        if (palabra_Mostrada === objeto_Seleccionado.palabra) {
            mensaje.textContent = '¡Ganaste! 🎉';
            desactivar_Teclado();
            clearInterval(temporizador_Intervalo);
        } else if (errores >= max_Errores) {
            mensaje.textContent = `Perdiste 😢. La palabra era: ${objeto_Seleccionado.palabra}`;
            desactivar_Teclado();
            clearInterval(temporizador_Intervalo);
        }
    }

    function iniciar_Temporizador() {
        clearInterval(temporizador_Intervalo);
        tiempo_Restante = 120;
        temporizador_Elemento.textContent = `Tiempo: 02:00`;

        temporizador_Intervalo = setInterval(() => {
            tiempo_Restante--;
            const min = Math.floor(tiempo_Restante / 60).toString().padStart(2, '0');
            const seg = (tiempo_Restante % 60).toString().padStart(2, '0');
            temporizador_Elemento.textContent = `Tiempo: ${min}:${seg}`;

            if (tiempo_Restante <= 0) {
                clearInterval(temporizador_Intervalo);
                mensaje.textContent = `Se acabó el tiempo. La palabra era: ${objeto_Seleccionado.palabra}`;
                desactivar_Teclado();
            }
        }, 1000);
    }

    function desactivar_Teclado() {
        Array.from(teclado_Letras.children).forEach(b => b.disabled = true);
    }

    btn_Iniciar.addEventListener('click', iniciar_Juego);
    btn_Reiniciar.addEventListener('click', iniciar_Juego);

    btn_Pausar.addEventListener('click', () => {
        if (juego_En_Pausa) {
            iniciar_Temporizador();
            manejar_Teclado(true);
            btn_Pausar.textContent = "Pausar";
            juego_En_Pausa = false;
        } else {
            clearInterval(temporizador_Intervalo);
            manejar_Teclado(false);
            btn_Pausar.textContent = "Reanudar";
            juego_En_Pausa = true;
        }
    });

    btn_Salir.addEventListener('click', (e) => {
        e.preventDefault();
        location.reload();
    });
});
