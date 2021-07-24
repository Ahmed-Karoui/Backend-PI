package tn.esprit.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;

import tn.esprit.dto.CountGroupByOrderStatus;
import tn.esprit.dto.CountOrderByUser;
import tn.esprit.entities.Order;
import tn.esprit.entities.OrderDetails;
import tn.esprit.entities.Product;
import tn.esprit.entities.User;
import tn.esprit.repository.OrderDetailsRepository;
import tn.esprit.services.EmailServiceImpl;
import tn.esprit.services.IProductService;
import tn.esprit.services.OrderServiceImpl;
import tn.esprit.services.UserServiceImpl;
import tn.esprit.util.pdf.PDFGenerator;

@RestController
public class RestControllerOrder {
	
	private static final Logger logger = Logger.getLogger(RestControllerOrder.class);

	@Autowired
	private OrderServiceImpl orderService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	public PDFGenerator pdfGenerator ;
	
	@Autowired
	private EmailServiceImpl emailService ;
	
	@Autowired
	private IProductService productServices;
	
	@Autowired
	private OrderDetailsRepository orderDetailsService ;
	
	@PostMapping("/addOrder")
	@ResponseBody
	public Order addOrder(@RequestBody Order order) {
		//User u = userService.findUser(order.getIdUser());
		//order.setUser(u);
		this.orderService.addOrder(order);
		return order ;
	}
	
	@PostMapping("/addOrderDetails")
	@ResponseBody
	public List<OrderDetails> addOrderDetails(@RequestBody List<OrderDetails> orderDetailsList) {
		List<OrderDetails> orderDetailsListToSave = new ArrayList<>();
		for(OrderDetails orderDetail:orderDetailsList) {
			Product product =  productServices.findProduct(orderDetail.getIdProduct());
			Order order = orderService.findOrder(orderDetail.getIdOrder());
			orderDetail.setOrder(order);
			orderDetail.setProduct(product);
			orderDetailsListToSave.add(orderDetail);
		}
		return (List<OrderDetails>) this.orderDetailsService.saveAll(orderDetailsListToSave) ;
	}
	
	@GetMapping("/ListOrderDetailsByOrderId/{idOrderDetail}")
	public List<OrderDetails> ListOrderDetailsByOrderId(@PathVariable("idOrderDetail") int idOrderDetail) {
		List<OrderDetails> listOrderDetails = (ArrayList<OrderDetails>) this.orderDetailsService.findOrderDetailsByOrderId(idOrderDetail);
		List<OrderDetails> listOrderDetailsToReturn = new ArrayList<>();
		for(OrderDetails orderDetails : listOrderDetails) {
			orderDetails.setIdOrder(orderDetails.getOrder().getId());
			orderDetails.setIdProduct(orderDetails.getProduct().getId());
			listOrderDetailsToReturn.add(orderDetails);
		}
		return listOrderDetailsToReturn;
	}
	
	@PutMapping("/updateOrder")
	@ResponseBody
	public Order updateOrder(@RequestBody Order order) {
		this.orderService.updateOrder(order);
		return order;
	}
	
	@DeleteMapping(value = "/deleteOrder/{idOrder}")
	public String deleteOrder(@PathVariable("idOrder") int idOrder) {
		this.orderService.deleteOrder(idOrder);
		return "Order N° " + idOrder + " deleted" ;
	}
	
	@GetMapping("/ListOrder")
	public List<Order> findAllOrder() {
		return this.orderService.findAllOrder();
	}
	
	@GetMapping("/findOrderById/{idOrder}")
	public Order findOrderById(@PathVariable("idOrder") Integer idOrder) {
		return this.orderService.findOrder(idOrder);
	}
	
	@GetMapping("/findOrderByUser/{idUser}")
	public List<Order> findOrderByUser(@PathVariable("idUser") Integer idUser) {
		return this.orderService.findOrderByUserCriteria(idUser);
	}
	
	@GetMapping("/findOrderNumberForUser")
	public List<CountOrderByUser> findOrderByUser() {
		return this.orderService.findNumberOrderForUser();
	}
	
	@GetMapping("/findGroupByStatus")
	public List<CountGroupByOrderStatus> findGroupByStatus() {
		return this.orderService.findStatusGroupByNumber();
	}
	
	public List<OrderDetails> getOrderDetails(List<OrderDetails> ordDetails){
		List<OrderDetails> ordDetailsReturn = new ArrayList<>();
		for(OrderDetails order:ordDetails) {
			Product product = this.productServices.findProduct(order.getIdProduct());
			Order order2 = this.orderService.findOrder(order.getIdOrder());
			order.setProduct(product);
			order.setOrder(order2);
			ordDetailsReturn.add(order);
		}
		return ordDetailsReturn ;
	}
	
	@RequestMapping("/downloadOrderFile") 
	public String method(HttpServletResponse response,@RequestBody List<OrderDetails> orderDetails){
        try {
        
        orderDetails = this.getOrderDetails(orderDetails);
        this.pdfGenerator.generatePdfReport(orderDetails);
        String fileName = this.pdfGenerator.getPdfNameWithDate(orderDetails.get(0).getOrder().getReference());
		byte [] data = Files.readAllBytes(Paths.get(fileName));
        response.setContentType("application/" + "pdf");
        response.addHeader("content-disposition", "attachment; filename=" + fileName);
        response.addHeader("Content-Length", String.valueOf(data.length));
        
        ServletOutputStream sos = response.getOutputStream();
        sos.write(data);
        sos.flush();
        sos.close();
        
        this.emailService.sendMailWithReport("skander1673@gmail.com", "Facture de la commande " + orderDetails.get(0).getOrder().getReference(), "Bonjour,</br> ci-joint la facture </br> Cordialement;",fileName);
        
        } catch (Exception exception) {
        		exception.printStackTrace();
        }
        
		return "" ;
	}

}
