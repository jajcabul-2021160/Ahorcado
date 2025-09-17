<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Usuario"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Juego del Ahorcado</title>
    <link rel="stylesheet" href="Css/style.css">
</head>
<body>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("index.jsp"); 
        return;
    }
%>

<div id="main-container" class="main-container">
    <div class="game-layout">
        <div class="juego-container">
            <h1>El Ahorcado</h1>
            <p id="bienvenida">Bienvenido, <%= usuario.getCorreoUsuario() %></p>

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
                <!-- Botón Salir que cierra la sesión -->
                <form action="CerrarSesion" method="post" style="display:inline;">
                    <button type="submit" id="btn-salir">Salir</button>
                </form>
            </div>
        </div>

        <div class="pistas-container">
            <div id="pistas" class="pistas"></div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/JS/script.js"></script>
</body>
</html>
