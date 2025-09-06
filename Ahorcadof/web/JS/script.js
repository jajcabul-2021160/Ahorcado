document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const loginContainer = document.getElementById('login-container');
    const mainContainer = document.getElementById('main-container');

  
    loginForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        if (username === 'quintom' && password === 'admin') {
            loginContainer.style.display = 'none';
            mainContainer.style.display = 'block';
            iniciarJuego();
        } else {
            alert('Usuario o contraseña incorrectos.');
        }
    });

    const palabraOculta = document.getElementById('palabra-oculta');
    const imagenAhorcado = document.getElementById('imagen-ahorcado');
    const teclado = document.getElementById('teclado-letras');
    const mensaje = document.getElementById('mensaje');
    const pistas = document.getElementById('pistas');
    const temporizadorElemento = document.getElementById('temporizador');

    const btnIniciar = document.getElementById('btn-iniciar');
    const btnPausar = document.getElementById('btn-pausar');
    const btnReiniciar = document.getElementById('btn-reiniciar');
    const btnSalir = document.getElementById('btn-salir');

    let objetoSeleccionado = null; 
    let palabraMostrada = '';
    let errores = 0;
    const maxErrores = 6;
    let juegoEnPausa = false;

    let tiempoRestante = 120;
    let temporizadorIntervalo;
    
    function manejarTeclado(habilitar) {
    const botonesTeclado = Array.from(teclado.children);
    botonesTeclado.forEach(boton => {
        boton.disabled = !habilitar;
    });
}

    async function iniciarJuego() {
        try {
            const respuesta = await fetch('./Controlador?accion=obtenerPalabra');
            if (!respuesta.ok) {
                throw new Error('Error al obtener la palabra del servidor');
            }
            const palabraData = await respuesta.json();

            objetoSeleccionado = {
                palabra: palabraData.palabra.toUpperCase(),
                pistas: [palabraData.pista1, palabraData.pista2, palabraData.pista3]
            };

            palabraMostrada = '_'.repeat(objetoSeleccionado.palabra.length);
            errores = 0;

            palabraOculta.textContent = palabraMostrada.split('').join(' ');
            imagenAhorcado.src = 'Images/1.png';
            mensaje.textContent = '';
            pistas.innerHTML = '<h4>Pistas:</h4>';
            objetoSeleccionado.pistas.forEach(p => {
                const li = document.createElement('p');
                li.textContent = `- ${p}`;
                pistas.appendChild(li);
            });

            crearTeclado();
            manejarTeclado(true);
            iniciarTemporizador();
        } catch (error) {
            console.error(error);
            mensaje.textContent = 'No se pudo conectar con el servidor.';
        }
    }

    function crearTeclado() {
        teclado.innerHTML = '';
        const letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for (let letra of letras) {
            const boton = document.createElement('button');
            boton.textContent = letra;
            boton.addEventListener('click', () => manejarAdivinanza(letra, boton));
            teclado.appendChild(boton);
        }
    }

    function manejarAdivinanza(letra, boton) {
        boton.disabled = true;
        let acierto = false;
        let nuevaPalabra = '';

        for (let i = 0; i < objetoSeleccionado.palabra.length; i++) {
            if (objetoSeleccionado.palabra[i] === letra) {
                nuevaPalabra += letra;
                acierto = true;
            } else {
                nuevaPalabra += palabraMostrada[i];
            }
        }

        palabraMostrada = nuevaPalabra;
        palabraOculta.textContent = palabraMostrada.split('').join(' ');

        if (!acierto) {
            errores++;
            imagenAhorcado.src = `Images/${errores + 1}.png`;
        }

        verificarEstadoJuego();
    }

    function verificarEstadoJuego() {
        if (palabraMostrada === objetoSeleccionado.palabra) {
            mensaje.textContent = '¡Ganaste! 🎉';
            desactivarTeclado();
            clearInterval(temporizadorIntervalo);
        } else if (errores >= maxErrores) {
            mensaje.textContent = `Perdiste 😢. La palabra era: ${objetoSeleccionado.palabra}`;
            desactivarTeclado();
            clearInterval(temporizadorIntervalo);
        }
    }

    function iniciarTemporizador() {
        clearInterval(temporizadorIntervalo);
        tiempoRestante = 120;
        temporizadorElemento.textContent = `Tiempo: 02:00`;

        temporizadorIntervalo = setInterval(() => {
            tiempoRestante--;
            const min = Math.floor(tiempoRestante / 60).toString().padStart(2, '0');
            const seg = (tiempoRestante % 60).toString().padStart(2, '0');
            temporizadorElemento.textContent = `Tiempo: ${min}:${seg}`;

            if (tiempoRestante <= 0) {
                clearInterval(temporizadorIntervalo);
                mensaje.textContent = `Se acabó el tiempo. La palabra era: ${objetoSeleccionado.palabra}`;
                desactivarTeclado();
            }
        }, 1000);
    }

    function desactivarTeclado() {
        Array.from(teclado.children).forEach(b => b.disabled = true);
    }

    btnIniciar.addEventListener('click', iniciarJuego);
    btnReiniciar.addEventListener('click', iniciarJuego);

    btnPausar.addEventListener('click', () => {
        if (juegoEnPausa) {
            iniciarTemporizador();
            manejarTeclado(true);
            btnPausar.textContent = "Pausar";
            juegoEnPausa = false;
        } else {
            clearInterval(temporizadorIntervalo);
            manejarTeclado(false);
            btnPausar.textContent = "Reanudar";
            juegoEnPausa = true;
        }
    });

    btnSalir.addEventListener('click', () => {
        location.reload();
    });
});