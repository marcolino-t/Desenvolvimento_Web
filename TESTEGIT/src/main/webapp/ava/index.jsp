<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Calculadora de Média</title>
</head>
<body>
    <h1>Calculadora de Média</h1>

    
    <form action="index.jsp" method="post">
        Nota 1: <input type="text" name="n1" required><br><br>
        Nota 2: <input type="text" name="n2" required><br><br>
        <input type="submit" value="Calcular Média">
    </form>

    <%
        
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                
                String n1Str = request.getParameter("n1");
                String n2Str = request.getParameter("n2");

                
                double n1 = Double.parseDouble(n1Str);
                double n2 = Double.parseDouble(n2Str);

                
                double media = (n1 + n2) / 2;

                
                out.println("<h2>A média das notas é: " + media + "</h2>");
            } catch (NumberFormatException e) {
                out.println("<h2>Erro: Por favor, insira valores numéricos válidos.</h2>");
            } catch (Exception e) {
                out.println("<h2>Ocorreu um erro: " + e.getMessage() + "</h2>");
            }
        }
    %>
</body>
</html>

</body>
</html>