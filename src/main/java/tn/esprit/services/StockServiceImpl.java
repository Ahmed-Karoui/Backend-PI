package tn.esprit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.OrderDetails;
import tn.esprit.entities.Product;
import tn.esprit.entities.Stock;
import tn.esprit.entities.StockPosition;
import tn.esprit.repository.OrderDetailsRepository;
import tn.esprit.repository.ProductRepository;
import tn.esprit.repository.StockRepository;
import tn.esprit.repository.UserRepository;

@Service
public class StockServiceImpl implements IStockService {

	@Autowired
	StockRepository stockRepository;
	@Autowired
	ProductRepository ProductRepository;
	@Autowired
	UserRepository UserRepository;
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Override
	public int addStock(Stock stock) {
		stockRepository.save(stock);
		return stock.getId();
	}

	@Override
	public Stock deleteStock(int idStock) {
		Stock stock = stockRepository.findById(idStock).orElse(null);
		stockRepository.delete(stock);
		// TODO Auto-generated method stub
		return stock;
	}

	@Override
	public int updateStock(Stock stock) {
		Stock oldStock = stockRepository.findById(stock.getId()).orElse(null);
		
		oldStock.setProduct(stock.getProduct());
		oldStock.setQte(stock.getQte());
		
		stockRepository.save(oldStock);
		
		return oldStock.getId();
	}

	@Override
	public Stock findStock(int idStock) {
		
		return stockRepository.findById(idStock).orElse(null);
	}

	@Override
	public List<Stock> findAllStock() {
		return (List<Stock>)stockRepository.findAll();
	}

	@Override
	public boolean decrementFromStock(List<OrderDetails> DetailsOrder) {
		for(OrderDetails od : DetailsOrder) {
			
			int orderDetailsId = od.getId();
			
			OrderDetails orderDetailsInfo = orderDetailsRepository.findById(orderDetailsId).orElse(null);
			
			Product product = orderDetailsInfo.getProduct();
			Stock stockOfProduct = stockRepository.getStockByProduct(product.getId());
			
			if(orderDetailsInfo.getQte() > stockOfProduct.getQte()) {
				return false;
			}
			else {
				stockOfProduct.setQte(stockOfProduct.getQte() - orderDetailsInfo.getQte());
				stockRepository.save(stockOfProduct);
			}
			
		}
		return true;
	}

	@Override
	public List<Stock> checkAlertStock() {
	 List<Stock> stock = (List<Stock>) stockRepository.findAll();
	 ArrayList<Stock> myList = new ArrayList();
	 
	 for (Stock st : stock) {
		 if(st.getQte()<10) {
			 myList.add(st);
		 }
		 
		}
	 
		return myList;
	}

	@Override
	public Stock getStockByProduct(int idProduct) {
		Stock stock = stockRepository.getStockByProduct(idProduct);
		return stock;
	}

	@Override
	public List productsOrderByQte() {
		return stockRepository.getProductOrderByStock();
	}

	@Override
	public List getStockByRow(StockPosition row) {
		return stockRepository.getStockByRow(row);
	}

	@Override
	public List<Product> getAvailableProductsToStock() {
		return stockRepository.getAvailableProductsToStock();
	}

	
	
	
	

}
