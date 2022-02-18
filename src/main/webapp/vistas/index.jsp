<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/recursos/estilos/estilo.css">
        <title>Agenda Spring Web MVC</title>
    </head>
    <body>
        <h1>Agenda de números telefónicos</h1>
        <p>    
            <label>Nombres:</label>
            <select>
                <c:forEach var="nombre" items="${nombres}" >
                    <option>${nombre}</option>
                </c:forEach>
            </select>
        </p>
        <div>
            <table>
                <tr class="rojo">
                    <form method="post" action="/alta">
                        <td>
                            <table>
                                <tr><td>Nombre:</td></tr>
                                <tr><td>Teléfono:</td></tr>
                            </table>
                        </td>
                        <td>
                            <table>
                                <tr><td><input type="text" name="nuevoNombre"></td></tr>
                                <tr><td><input type="text" name="nuevoTel"></td></tr>
                            </table>
                        </td>
                        <td>
                            <input type="submit" value="Alta" class="inputDeAccion">
                        </td>
                    </form>
                </tr>
                <tr class="verde">
                    <form method="post" action="/baja">
                        <td>
                            Nombre:
                        </td>
                        <td>
                            <input type="text" name="nombre">
                        </td>
                        <td>
                            <input type="submit" value="Baja" class="inputDeAccion">
                        </td>
                    </form>
                </tr>
                <tr class="azul">
                    <form method="post" action="/modificacion">
                        <td>
                            <table>
                                <tr><td>Nombre:</td></tr>
                                <tr><td>Nuevo teléfono:</td></tr>
                            </table>
                        </td>
                        <td>
                            <table>
                                <tr><td><input type="text" name="nombre"></td></tr>
                                <tr><td><input type="text" name="nuevoTel"></td></tr>
                            </table>
                        </td>
                        <td>
                            <input type="submit" value="Modificación" class="inputDeAccion">
                        </td>
                    </form>
                </tr>
                <tr class="amarillo">
                    <form method="post" action="/consulta">
                        <td>
                            Nombre:
                        </td>
                        <td>
                            <input type="text" name="nombre" value="${telefonoEncontrado}">
                        </td>
                        <td>
                            <input type="submit" value="Consulta" class="inputDeAccion">
                        </td>
                    </form>
                </tr>
            </table>
        </div>
    </body>
</html>
