package loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import loja.daos.ProdutoDAO;
import loja.models.Produto;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping("produtos/form")
	public String form() {
		return "produtos/form";
	}
	
	@RequestMapping("produtos")
	public String gravar(Produto produto) {
		
		System.out.println(produto.getTitulo());
		
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}

}
