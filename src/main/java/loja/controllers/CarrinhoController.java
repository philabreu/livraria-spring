package loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import loja.daos.ProdutoDAO;
import loja.models.CarrinhoCompras;
import loja.models.CarrinhoItem;
import loja.models.Produto;
import loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

	@Autowired
	private CarrinhoCompras carrinhoCompras;

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping("/adicionar")
	public ModelAndView adicionar(Integer produtoId, TipoPreco tipo) {

		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");

		CarrinhoItem item = criarItem(produtoId, tipo);
		carrinhoCompras.adicionar(item);

		return modelAndView;
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipo) {
		carrinhoCompras.remover(produtoId, tipo);
		return new ModelAndView("redirect:/carrinho");
	}

	private CarrinhoItem criarItem(Integer produtoId, TipoPreco tipo) {

		Produto produto = produtoDAO.find(produtoId);

		CarrinhoItem item = new CarrinhoItem(produto, tipo);

		return item;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("/carrinho/itens");
	}

}
