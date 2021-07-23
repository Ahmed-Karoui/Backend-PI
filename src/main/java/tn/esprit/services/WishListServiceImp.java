package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Reclamation;
import tn.esprit.entities.Whishlist;
import tn.esprit.repository.WishlistRepository;;

@Service
public class WishListServiceImp implements IWishlistService  {

	
	@Autowired
	WishlistRepository wishlistRepository;
	
	@Override
	public int addWishList(Whishlist wishlist) {
		// TODO Auto-generated method stub
		wishlistRepository.save(wishlist);
		return wishlist.getId();
	}

	@Override
	public Whishlist deleteWishList(int idWhishlist) {
		// TODO Auto-generated method stub
		Whishlist whishlist = wishlistRepository.findById(idWhishlist).orElse(null);
		wishlistRepository.delete(whishlist);
		return whishlist;
	}

	@Override
	public int updateWishList(Whishlist wishlist) {
		// TODO Auto-generated method stub
		Whishlist Oldwhishlist = wishlistRepository.findById(wishlist.getId()).orElse(null);
		Oldwhishlist.setUser(wishlist.getUser());
		Oldwhishlist.setProducts(wishlist.getProducts());
		Oldwhishlist.setAddDate(wishlist.getAddDate());
		wishlistRepository.save(Oldwhishlist);
		return 0;
	}

	@Override
	public Whishlist findWishList(int idWhishlist) {
		// TODO Auto-generated method stub
		return wishlistRepository.findById(idWhishlist).orElse(null);
	}

	@Override
	public List<Whishlist> findAllWishList() {
		// TODO Auto-generated method stub
		return (List<Whishlist>)wishlistRepository.findAll();
	}

	public int getNumberWishList() {
		// TODO Auto-generated method stub
		return wishlistRepository.getNumberWishlist();
	}
	
}
