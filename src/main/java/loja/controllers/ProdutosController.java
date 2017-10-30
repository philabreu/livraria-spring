package loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import loja.daos.ProdutoDAO;
import loja.models.Produto;
import loja.models.TipoPreco;

@Controller
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping("produtos/form")
	public ModelAndView form() {
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}

	@RequestMapping("produtos")
	public String gravar(Produto produto) {
		produtoDao.gravar(produto);

		return "produtos/ok";
	}
}
