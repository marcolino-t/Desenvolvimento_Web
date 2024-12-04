<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado do Planejamento</title>
</head>
<body>
    <h1>Planejamento do Churrasco</h1>
    <%
        // Obtendo parâmetros do formulário
        int homens = Integer.parseInt(request.getParameter("homens"));
        int mulheres = Integer.parseInt(request.getParameter("mulheres"));
        int criancas = Integer.parseInt(request.getParameter("criancas"));
        
        // quantidades de consimo por pessoa
        double carnePorHomem = 300; // em gramas
        double carnePorMulher = 270; // em gramas
        double carnePorCrianca = 250; // em gramas

        double cervejaPorHomem = 2; // em litros
        double cervejaPorMulher = 1.5; // em litros
        double cervejaPorCrianca = 0; // em litros

        double refrigerantePorHomem = 0.3; // em litros
        double refrigerantePorMulher = 0.6; // em litros
        double refrigerantePorCrianca = 1; // em litros
        
        // Calculando as quantidades totais
        double totalCarne = (homens * carnePorHomem) + (mulheres * carnePorMulher) + (criancas * carnePorCrianca);
        double totalCerveja = (homens * cervejaPorHomem) + (mulheres * cervejaPorMulher) + (criancas * cervejaPorCrianca);
        double totalRefrigerante = (homens * refrigerantePorHomem) + (mulheres * refrigerantePorMulher) + (criancas * refrigerantePorCrianca);
        
        if (homens > mulheres){
        	response.sendRedirect("erro.jsp");
        };
    %>

    <p><strong>Total de Carne Necessária:</strong> <%= String.format("%.2f", totalCarne / 1000) %> kg</p>
    <p><strong>Total de Cerveja Necessária:</strong> <%= String.format("%.2f", totalCerveja) %> L</p>
    <p><strong>Total de Refrigerante Necessário:</strong> <%= String.format("%.2f", totalRefrigerante) %> L</p>
</body>
</html>