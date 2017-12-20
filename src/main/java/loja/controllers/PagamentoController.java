package loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import loja.models.CarrinhoCompras;
import loja.models.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinhoCompras;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizar(RedirectAttributes model) {

		try {
			String uri = "http://book-payment.herokuapp.com/payment";
			String resposta = restTemplate.postForObject(uri, new DadosPagamento(carrinhoCompras.getTotal()),
					String.class);
			model.addFlashAttribute("sucesso", resposta);

			return new ModelAndView("redirect:/produtos");
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			model.addFlashAttribute("falha", "Valor maior que o permitido");

			return new ModelAndView("redirect:/produtos");
		}
	}
}
