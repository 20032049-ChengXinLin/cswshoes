/**
*
* I declare that this code was written by me, 20032049.
* I will not copy or allow others to copy my code.
* I understand that copying code is considered as plagiarism.
*
* Student Name: Cheng Xin Lin (20032049), Koh Siew Gek (20008303), Chen Wan Ting (20009334)
* Team ID: SOI-2022-0012
* Team Project ID: SOI-2022-2210-0049
* Project: [IP] Digital Wallet
* Date created: 2022-May-05 4:08:37 pm
*
*/
package FYP.SOI20220012.ECommerce;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 20032049
 *
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	//Select all the cart items that belongs to a member
		public List<ShoppingCart> findByMember_MemberId(int id);
		
		public ShoppingCart findByMember_MemberIdAndProduct_ProductsId(int memberId, int Productid);
}
