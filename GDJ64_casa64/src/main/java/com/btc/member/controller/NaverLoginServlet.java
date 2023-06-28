package com.btc.member.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.booking.model.service.BookingService;
import com.btc.booking.model.vo.Booking;
import com.btc.member.model.dao.MemberDao;
import com.btc.member.model.dto.Member;
import com.btc.member.model.service.MemberService;

/**
 * Servlet implementation class NaverLoginServlet
 */
@WebServlet("/naverLogin.do")
public class NaverLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service=new MemberService();
	Properties sql=new Properties();
	{
		String path=MemberDao.class.getResource("/sql/member/naverLogin.properties").getPath();
		try {
		sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NaverLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String clientId = sql.getProperty("nId");// 애플리케이션 클라이언트 아이디값";
		String clientSecret = sql.getProperty("nPw");// 애플리케이션 클라이언트 시크릿값";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:9090/GDJ64_casa64/index.jsp", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "";
		String refresh_token = "";
		System.out.println("apiURL=" + apiURL);
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
				org.json.simple.parser.JSONParser parsing = new org.json.simple.parser.JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				access_token = (String) jsonObj.get("access_token");
				refresh_token = (String) jsonObj.get("refresh_token");

			}
		} catch (Exception e) {
			System.out.println(e);
		}

		if (access_token != null) {
			try {
				String token=access_token;
				String header="Bearer "+token;
				String apiurl = "https://openapi.naver.com/v1/nid/me";
				URL url = new URL(apiurl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Authorization",header);
				int responseCode = con.getResponseCode();
				BufferedReader br;

				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}

				String inputLine;
				StringBuffer res = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					res.append(inputLine);
				}

				br.close();
				
				org.json.simple.parser.JSONParser parsing = new org.json.simple.parser.JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject)obj;
				JSONObject resObj = (JSONObject)jsonObj.get("response");
				
				String nCode=(String)resObj.get("id");
				String email=(String)resObj.get("email");
				String name=(String)resObj.get("name");
				String nickName=(String)resObj.get("nickname");
				String phone=(String)resObj.get("mobile");
				HttpSession session=request.getSession();
				System.out.println(nCode);
				//회원가입
				Member loginMember=null;
				if(service.selectSNSMember(nCode)==null) {
					int inMemberResult=service.insertMember(name, email, nickName, phone,null);
					if(new AdminMemberService().selectCMember(email)!=null) {
						new AdminMemberService().deleteCMember(email);
					}
					int inSnsMemberResult=service.insertSNSMember(nCode, "naver", name, email, nickName);
					if(inMemberResult>0&&inSnsMemberResult>0) {
						loginMember=service.selectEmail(email);

					}
				}else {//로그인
					loginMember=service.selectEmail(email);

				}
				session.setAttribute("loginMember", loginMember);
				Booking recentBooking = new BookingService().searchBookingByMemberNo(loginMember.getMemberNo());
				session.setAttribute("recentBooking", recentBooking);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
