package loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import loja.daos.ProdutoDAO;
import loja.models.CarrinhoCompras;
import loja.models.CarrinhoItem;
import loja.models.Produto;
import loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoCompras carrinhoCompras;

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping("/adicionar")
	public ModelAndView adicionar(Integer produtoId, TipoPreco tipo) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
		
		CarrinhoItem item = criarItem(produtoId, tipo);
		carrinhoCompras.adicionar(item);

		return modelAndView;
	}

	private CarrinhoItem criarItem(Integer produtoId, TipoPreco tipo) {

		Produto produto = produtoDAO.find(produtoId);

		CarrinhoItem item = new CarrinhoItem(produto, tipo);

		return item;

	}
}
