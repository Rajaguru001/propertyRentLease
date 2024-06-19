package com.propertyrent.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.model.SellerPropertyForm;

@WebServlet("/PropertyRentSellerForm")
@MultipartConfig
public class PropertyRentSellerForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SellerPropertyForm spf = new SellerPropertyForm();

	public PropertyRentSellerForm() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PropertyRentLeaseDAO prl = new PropertyRentLeaseDAO();
		int ownerid = Integer.parseInt(request.getParameter("id"));
		String propertyType = request.getParameter("property_type");
		int sqft = Integer.parseInt(request.getParameter("sqft"));
		String furnishing = request.getParameter("furnishing");
		String availableFromStr = request.getParameter("available_from");
		Date availableFrom = null;
		try {
			availableFrom = new SimpleDateFormat("yyyy-MM-dd").parse(availableFromStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int rentPrice = Integer.parseInt(request.getParameter("rent_price"));
		String address = request.getParameter("address");
		String postedDateStr = request.getParameter("posted_on_date");
		Date postedOnDate = null;
		try {
			postedOnDate = new SimpleDateFormat("yyyy-MM-dd").parse(postedDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String location=request.getParameter("location");

		InputStream ebBillInputStream = null;
		Part ebBillPart = request.getPart("EB_Bill");
		if (ebBillPart != null) {
			ebBillInputStream = ebBillPart.getInputStream();
		}

		List<InputStream> propertyImagesInputStreamList = new ArrayList<>();
		Collection<Part> propertyImagesParts = request.getParts().stream()
				.filter(part -> "property_images".equals(part.getName()) && part.getSize() > 0)
				.collect(Collectors.toList());
		for (Part propertyImagesPart : propertyImagesParts) {
			propertyImagesInputStreamList.add(propertyImagesPart.getInputStream());
		}
		boolean isapproval = Boolean.parseBoolean(request.getParameter("isapproval"));

		try {
			prl.sellerdetailsinsert(location,ownerid, propertyType, sqft, furnishing, availableFrom, rentPrice, address,
					postedOnDate, ebBillInputStream, propertyImagesInputStreamList);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("subscription.jsp").forward(request, response);
	}

}
