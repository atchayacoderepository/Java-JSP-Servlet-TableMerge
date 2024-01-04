package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class information
 */
@WebServlet("/information")
public class information extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public information() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		PrintWriter out=response.getWriter();
		ArrayList<UserDetails> user= new ArrayList<UserDetails>();
		ArrayList<ProductDetails> product= new ArrayList<ProductDetails>();
		HashMap<UserDetails,ProductDetails>userproduct = new HashMap<UserDetails,ProductDetails>();
		
		
		try
		{
		Connection con=connection.connection();
		PreparedStatement st=con.prepareStatement("Select * from user");
		ResultSet rs=st.executeQuery();
		
		while(rs.next())
		{
			UserDetails s=new UserDetails();
			s.setUserName(rs.getString("UserName"));
			s.setUserId(rs.getString("UserId"));
			user.add(s);
		}
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
		Connection con1=connection.connection();
		PreparedStatement st=con1.prepareStatement("Select * from product");
		ResultSet rs=st.executeQuery();
		
		while(rs.next())
		{
			ProductDetails s1=new ProductDetails();
			s1.setProductName(rs.getString("ProductName"));
			s1.setProductId(rs.getString("ProductId"));
			product.add(s1);
		}
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
		
		for(int i=0;i<user.size();i++)
		{
			userproduct.put(user.get(i),product.get(i));
		}
		
		for(Map.Entry<UserDetails,ProductDetails> s : userproduct.entrySet())
		{
			UserDetails key=s.getKey();
			ProductDetails value=s.getValue();
			
			out.println("User Details : "+key.UserId+" "+key.UserName);
			out.println("Product Details : "+value.ProductId+" "+value.ProductName);
		}
	}


}
