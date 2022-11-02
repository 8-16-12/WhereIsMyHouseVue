package com.ssafy.controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.dto.House;
import com.ssafy.dto.Interest;
import com.ssafy.dto.User;
import com.ssafy.model.service.AddressService;
import com.ssafy.model.service.AddressServiceImp;
import com.ssafy.model.service.HouseService;
import com.ssafy.model.service.HouseServiceImp;
import com.ssafy.model.service.InterestService;
import com.ssafy.model.service.InterestServiceImpl;
import com.ssafy.model.service.UserService;
import com.ssafy.model.service.UserServiceImpl;

@WebServlet("*.do")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int KEY_SIZE = 512;
	static String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALYfYohaiXY0UDuOG3lXmnPldSti7bkEVkK4DVdvXMPOO1eGjdZeb03O231pGvbOZX8f3tV6SqV9hYg9K7LHgCsCAwEAAQ==";
	static String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAth9iiFqJdjRQO44beVeac+V1K2LtuQRWQrgNV29cw847V4aN1l5vTc7bfWka9s5lfx/e1XpKpX2FiD0rsseAKwIDAQABAkApF43A3ES1wfn0Zgz25Pg5fCViw7CFlV/tL9/6wWq51vYAbp/KVMdrWP9kmsJgl+THN3pX5O3DNmYjrLMdkIg5AiEA3LIQK1TU/rqTjXCX1IceR76aQeeHnsmWJoEvCmvFDj0CIQDTQa39G4EiUgaKddUea8VIcVjv4nKd3jiLHwkqOS7WhwIgBoXYxn8WYIUBMW//fM+VjIdMpeAC+SV6I58z1boDs/0CIG9wRyKsPptnH5IshEau3PvQ5vRrxMBoN/WTYpdGYOHDAiA7IpRxiSmVIwTxOZKDVB9O0VnmgFTg0jds6AsG6N7ZtQ==";
	
	private UserService userService = UserServiceImpl.getinstance();
	HouseService houseService = HouseServiceImp.getInstance();
	AddressService addressService = AddressServiceImp.getInstance();
	InterestService interestservice = InterestServiceImpl.getinstance();
	
	private static List<House> houses;

    public MainServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
		System.out.println("action........." + action);
		String url = "index";
		/****************************/
//		if (publicKey == null) {
//	        HashMap<String, String> rsaKeyPair = createKeypairAsString();
//	        //publicKey = rsaKeyPair.get("publicKey");
//	        //privateKey = rsaKeyPair.get("privateKey");
//	        System.out.println("public" + publicKey);
//	        System.out.println("private" + privateKey);
//	        
//		}
		/****************************/
		
		try {
			switch(action) {
			case "/insert.do":
				url = insert(request, response); break;
			case "/login.do":
				url = login(request, response); break;
			case "/search.do":
				url = search(request, response); break;
			case "/update.do":
				url = update(request, response); break;
			case "/delete.do":
				url = delete(request, response); break;
			case "/logout.do":
				url = logout(request, response); break;

			case "/searchApt.do":
				url = searchApt(request, response);	break;
			case "/selectInterest.do":
				url = selectInterest(request, response); break;
				
			case "/registInterest.do":
				url = registInterest(request, response); break;
			case "/dropdownInterest.do":
				url = dropdownInterest(request, response); break;
			// 유림 ////////////////////////////////////////////////////////////////////////////////////////
			case "/sortingAlgorithm.do":
				url = sortingbyprice(request, response); break;
			//////////////////////////////////////////////////////////////////////////////////////////////
			}
		} catch (Exception e) {
			e.printStackTrace();
			url = "error/error";
		}
		
		try {
			switch(action) {
				case "/searchDong.do":
				url = searchDong(request, response); break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			url = "house/nothing";
		}
		
		if (url.startsWith("redirect:")) {
			System.out.println("redirect.........");
			response.sendRedirect(url.substring(9));
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
	// 유림 ////////////////////////////////////////////////////////////////////////////////////////
	private String sortingbyprice(HttpServletRequest request, HttpServletResponse response) {
		// 아파트 정렬해서 출력하기 - house의 거래매매가격 house.getDealAmount()
		houses.sort(new House());
		request.setAttribute("houses", houses);
		return "house/list";
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	private String dropdownInterest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		List<String> list = interestservice.search(id);
		request.setAttribute("InterestList", list);
		return "Interest/selectInterest";
	}

	// 중복제거 만들면 좋겠다
	private String registInterest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String aptName = request.getParameter("aptName");
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String sidoCode = addressService.Name2Code_Sido(sidoName).getSidoCode();
		String gugunCode = addressService.Name2Code_Gugun(gugunName).getGugunCode();
		String dongCode = addressService.Name2Code_Dong(dongName).getDongCode();
		
		List<House> houses = houseService.searchApt(aptName);
		
		for (House house : houses) {
			int aptCode = house.getAptCode();
			String lat = house.getLat();
			String lng = house.getLng();
			
			Interest interest = new Interest(id,aptCode,aptName,sidoCode,sidoName,gugunCode,gugunName,dongCode,dongName,lat,lng);
			interestservice.insert(interest);
		}
		return "redirect:index";
	}

	private String searchApt(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String aptName = request.getParameter("aptName");
//		List<House> houses = houseService.searchApt(aptName);
		
		// 유림 ////////////////////////////////////////////////////////////////////////////////////////
		houses = houseService.searchApt(aptName);
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		if (houses.size() == 0)
			return "house/nothing_apt";
		request.setAttribute("houses", houses);
		return "house/list";
	}

	private String searchDong(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		
		if (!sido.equals(""))
			sido = addressService.Code2Name_Sido(sido).getSidoName();
		if (!gugun.equals(""))
			gugun = addressService.Code2Name_Gugun(gugun).getGugunName();
		if (!dong.equals(""))
			dong = addressService.Code2Name_Dong(dong).getDongName();
		
		if (sido.equals("서울특별시")) {
//			List<House> houses = houseService.searchDong(sido, gugun, dong);
			
			// 유림 ////////////////////////////////////////////////////////////////////////////////////////
			houses = houseService.searchDong(sido, gugun, dong);
			//////////////////////////////////////////////////////////////////////////////////////////////
			
			if (houses == null)
				return "house/nothing";
			request.setAttribute("houses", houses);
			return "house/list";
		}
		else {
			return "house/nothing";
		}
	}
	
	private String selectInterest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String aptname = request.getParameter("aptName");
		Interest interest = interestservice.searchByapt(aptname);
		request.setAttribute("interest", interest);
		System.out.println(interest);
		
		return "Interest/showInterest";
	}

	private String insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		String addr2 = request.getParameter("addr2");
		
		pass = encode(pass, publicKey);
		User user = new User(id, pass, name, birth, email, addr, addr2);
		userService.insert(user);

		return "redirect:index";
	}
	
	private String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		User user = userService.search(id, pass, privateKey);
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", user);
			return "redirect:index";
		} else {
			request.setAttribute("msg_login", "아이디 또는 비밀번호가 잘못되었습니다.");
			return "user/login";
		}
	}
	
	private String search(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		
		User user = userService.search(id);
		
		request.setAttribute("user", user);
		
		return "user/mypage";
	}

	
	private String update(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		String addr2 = request.getParameter("addr2");

		User user = new User(id, pass, name, birth, email, addr, addr2);
		userService.update(user);
		
		System.out.println(id + "," + pass + "," + name + "," + birth + "," + email + "," + addr + "," + addr2);
		
		request.setAttribute("msg_update", "수정 완료");
		
		return "redirect:search.do?id=" + id;
	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		userService.delete(id);
		
		return "redirect:index";
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:index";
	}
	
	
	/**
	 * 키페어 생성
	 */
	static HashMap<String, String> createKeypairAsString() {
		HashMap<String, String> stringKeypair = new HashMap<>();
		try {
			SecureRandom secureRandom = new SecureRandom();
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(KEY_SIZE, secureRandom);
			KeyPair keyPair = keyPairGenerator.genKeyPair();

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			String stringPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
			String stringPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

			stringKeypair.put("publicKey", stringPublicKey);
			stringKeypair.put("privateKey", stringPrivateKey);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringKeypair;
	}

	/**
	 * 암호화
	 */
	static String encode(String plainData, String stringPublicKey) {
		String encryptedData = null;
		try {
			// 평문으로 전달받은 공개키를 공개키객체로 만드는 과정
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] bytePublicKey = Base64.getDecoder().decode(stringPublicKey.getBytes());
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
			PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

			// 만들어진 공개키객체를 기반으로 암호화모드로 설정하는 과정
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			// 평문을 암호화하는 과정
			byte[] byteEncryptedData = cipher.doFinal(plainData.getBytes());
			encryptedData = Base64.getEncoder().encodeToString(byteEncryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

	/**
	 * 복호화
	 */
	static String decode(String encryptedData, String stringPrivateKey) {
		String decryptedData = null;
		try {
			// 평문으로 전달받은 개인키를 개인키객체로 만드는 과정
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
			PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

			// 만들어진 개인키객체를 기반으로 암호화모드로 설정하는 과정
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			// 암호문을 평문화하는 과정
			byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedData.getBytes());
			byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
			decryptedData = new String(byteDecryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedData;
	}
}
