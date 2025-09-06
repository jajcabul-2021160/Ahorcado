<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Juego del Ahorcado</title>
    <link rel="stylesheet" href="Css/style.css">
</head>
<body>

    <!-- Login -->
    <div id="login-container" class="login-container">
        <h1>Iniciar Sesión</h1>
        
        <form id="login-form">
            <div class="input-group">
                <label for="username">Ingrese el Nombre del Usuario:</label>
                <input type="text" id="username" required>
            </div>
            <div class="input-group">
                <label for="password">Ingrese La Contraseña:</label>
                <input type="password" id="password" required>
            </div>
            <button type="submit">Iniciar El Juego</button>
        </form>
    </div>

    <!-- Manejo del Juego -->
    <div id="main-container" class="main-container" style="display: none;">
        <div class="game-layout">
            <div class="juego-container">
                <h1>El Ahorcado</h1>
                <div class="imagen-container">
                    <img id="imagen-ahorcado" src="Images/1.png" alt="Imagen del Ahorcado">
                </div>
                <div id="palabra-oculta" class="palabra-oculta"></div>
                <div id="temporizador" class="temporizador">Tiempo: 02:00</div>
                <div id="letras-usadas" class="letras-usadas"></div>
                <div id="teclado-letras" class="teclado-letras"></div>
                <div class="mensaje" id="mensaje"></div>
                <div class="controles">
                    <button id="btn-iniciar">Inicio</button>
                    <button id="btn-pausar">Pausar</button>
                    <button id="btn-reiniciar">Reiniciar</button>
                    <button id="btn-salir">Salir</button>
                </div>
            </div>

            <div class="pistas-container">
                <div id="pistas" class="pistas"></div>
            </div>
        </div>
    </div>

    <!-- ruta del Script -->
    <script src="JS/script.js"></script>

    <!-- Script para controlar login -->
    <script>
        const loginForm = document.getElementById("login-form");
        const loginContainer = document.getElementById("login-container");
        const mainContainer = document.getElementById("main-container");

        loginForm.addEventListener("submit", function(event) {
            event.preventDefault(); 

          
            const user = document.getElementById("username").value;
            const pass = document.getElementById("password").value;

            if(user.trim() !== "" && pass.trim() !== "") {
                loginContainer.style.display = "none"; // Oculta el login
                mainContainer.style.display = "block"; // Muestra el juego
            } else {
                alert("Por favor, ingresa tus credenciales.");
            }
        });
    </script>
</body>
</html>