package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("joinForm".equals(action)) { // 회원가입 양식
			System.out.println("joinForm");

			WebUtil.forward(request, response, "/WEB-INF/views/user/JoinForm.jsp");
		} else if ("join".equals(action)) { // 회원가입
			System.out.println("join");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo(id, password, name, gender);
			System.out.println(vo.toString());

			UserDao userDao = new UserDao();
			userDao.insert(vo);

			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");

		} else if ("loginForm".equals(action)) { // 로그인 양식
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
		} else if ("login".equals(action)) {
			System.out.println("login");
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			UserDao userDao = new UserDao();
			UserVo authVo = userDao.getUser(id, password);

			if (authVo == null) { // 로그인 실패
				System.out.print("로그인 실패");
				WebUtil.redirect(request, response, "/mysite2/user?action=loginForm&result=fail");

			} else { // 로그인 성공
				// 세션영역에 값을 추가
				// 세션 영역에 값을 추가
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authVo);

				WebUtil.redirect(request, response, "/mysite2/main");

				if ("modifyFrom".equals(action)) {
					WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
					System.out.println("회원정보수정");

					} else if ("modify".equals(action)){
						System.out.println("modify");
						id = request.getParameter("id");
						password = request.getParameter("password");
						String name = request.getParameter("name");
						String gender = request.getParameter("gender");
						
						UserVo vo = new UserVo(id, password, name, gender);
						System.out.println(vo.toString());

						UserDao Dao = new UserDao();
						WebUtil.redirect(request, response, "/mysite2/user?action=modufyForm");
						
					}

				
			}
		} else if ("logout".equals(action)) { // 로그아웃 일때
			System.out.println("logout");
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			WebUtil.redirect(request, response, "/mysite2/main");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
