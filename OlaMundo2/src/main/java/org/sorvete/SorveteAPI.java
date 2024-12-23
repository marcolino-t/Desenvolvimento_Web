package org.sorvete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * Servlet implementation class PessoaAPI
 */
//@WebServlet("/SorveteAPI/*")
public class SorveteAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SorveteAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SorveteDao sdao = new SorveteDao();
		Gson gson = new Gson();
		
		int id = 0;
		try {
			//pega o id passado por parametro 
			id = Integer.parseInt(request.getPathInfo().substring(1));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		String pesquisa = request.getParameter("pesquisa");
		
		String resposta;
		if (id==0) {
			//listar todos
			resposta = gson.toJson(sdao.listar(pesquisa));
		} else {
			//consultar apenas 1
			resposta = gson.toJson(sdao.consultar(id));
		}
		response.setHeader("content-type", "application/json");
		response.getWriter().print(resposta);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pega o body da request
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		//converte o body para um objeto Java
		Gson gson = new Gson();
		Sorvete s = gson.fromJson(body,  Sorvete.class);
		
		//salva a nova pessoa
		SorveteDao sdao = new SorveteDao();
//		pdao.inserir(p);
		
		
		
		//envia a resposta
		String resposta = gson.toJson(sdao.inserir(s));
		response.setHeader("content-type", "application/json");
		response.getWriter().print(resposta);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pega o body da request
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		//converte o body para um objeto Java
		Gson gson = new Gson();
		Sorvete s = gson.fromJson(body,  Sorvete.class);
		
		//pega o id passado por parametro
		int id = 0;
		try {
			id = Integer.parseInt(request.getPathInfo().substring(1));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		s.setIdsorvete(id);
		
		//salva a nova pessoa
		SorveteDao sdao = new SorveteDao();
		sdao.alterar(s);
				
		
		//envia a resposta
		String resposta = gson.toJson(sdao.alterar(s));
		response.setHeader("content-type", "application/json");
		response.getWriter().print(resposta);		
		
		
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pega o id passado por parametro
		int id = 0;
		try {
			id = Integer.parseInt(request.getPathInfo().substring(1));
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//exclui a nova pessoa
		SorveteDao sdao = new SorveteDao();
		Sorvete s = new Sorvete();
		Gson gson = new Gson();
		s.setIdsorvete(id);
		sdao.excluir(s);
		
		//evia resposta
		
		String resposta = gson.toJson(sdao.excluir(s));
		response.setHeader("content-type", "application/json");
		response.getWriter().print(resposta);	
	}

}