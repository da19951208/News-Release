package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;

/**
 * Servlet implementation class AfficheUpdateServlet
 */
@WebServlet(description = "AfficheUpdateServlet", urlPatterns = { "/AfficheUpdateServlet" })
public class AfficheUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficheUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		String Id = request.getParameter("id");
		int id = Integer.parseInt(Id);
		System.out.println(id);
		String Title = request.getParameter("title");
		String Content = request.getParameter("content");
		String Time = request.getParameter("time");

		// ��װ��������ȥ
		Blog blog = new Blog();
		blog.setId(id);
		blog.setTitle(Title);
		blog.setContent(Content);
		blog.setTime(Time);
		// ����ģ�Ͳ��޸ķ���
		String sql1 = "update affiche set TITLE_=?,CONTENT_=?,TIME_=? where ID_=?"; // ���²���
		PreparedStatement ps1 = null;
		try {
			conn = util.SQLServerConnection.getConnrction(); // �������ݿ�
		} catch (Exception e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("AfficheListServlet").forward(request, response);
			return;
		}

		try {
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(sql1);
			ps1.setInt(4, blog.getId());
			ps1.setString(1, blog.getTitle());
			ps1.setString(2, blog.getContent());
			ps1.setString(3, blog.getTime());
			ps1.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("AfficheListServlet").forward(request, response);
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