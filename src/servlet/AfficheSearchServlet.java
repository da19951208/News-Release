package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;
import util.SQLServerConnection;

/**
 * Servlet implementation class AfficheSearchServlet
 */
@WebServlet(description = "AfficheSearchServlet", urlPatterns = { "/AfficheSearchServlet" })
public class AfficheSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficheSearchServlet() {
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
		String sql1 = "select * from affiche where 1=1 "; // 查询语句
		// 获取序号ID
		String Id = request.getParameter("Id");
		if (Id != null && !Id.equals("")) {
			sql1 += " and ID_=?";
		}

		// 获取提交的关键词
		String title = request.getParameter("title");
		if (title != null && !title.equals("")) {
			sql1 += " and TITLE_ like ? ";
		}

		// 获取范围时间
		String date1 = request.getParameter("date1");
		if (date1 != null && !date1.equals("")) {
			sql1 += " and TIME_ > ? ";
		}

		String date2 = request.getParameter("date2");
		if (date2 != null && !date2.equals("")) {
			sql1 += " and TIME_ < ? ";
		}

		Connection conn = null; // 定义属性
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;

		try {
			conn = SQLServerConnection.getConnrction();
			ps1 = conn.prepareStatement(sql1);
			System.out.println(sql1);
			int i = 1;
			if (Id != null && !Id.equals("")) { // 为Id赋值，是查询数据后可以显示查询要求
				ps1.setString(i, Id);
				i++;
				System.out.println("Id" + Id);
			}
			if (title != null && !title.equals("")) { // 为name赋值
				ps1.setString(i, "%" + title + "%");
				i++;
				System.out.println("name" + title);
			}
			if (date1 != null && !date1.equals("")) { // 为date1赋值
				ps1.setString(i, date1);
				// ps1.setTimestamp(i, new Timestamp(d.parse(date1).getTime()));
				i++;
				System.out.println("date1" + date1);
			}
			if (date2 != null && !date2.equals("")) { // 为date2赋值
				ps1.setString(i, date2);
				// ps1.setTimestamp(i, new Timestamp(d.parse(date2).getTime()));
				i++;
				System.out.println("date2" + date2);
			}

			rs1 = ps1.executeQuery();
			ArrayList<Blog> list = new ArrayList<Blog>();
			while (rs1.next()) { // 循环显示数据表中数据
				Blog blog = new Blog();
				blog.setId(rs1.getInt("ID_"));
				blog.setTitle(rs1.getString("TITLE_"));
				blog.setContent(rs1.getString("CONTENT_"));
				blog.setTime(rs1.getString("TIME_"));
				list.add(blog);
			}
			request.setAttribute("list", list);
			request.setAttribute("id", Id);
			request.setAttribute("title", title);
			request.setAttribute("dat1", date1);
			request.setAttribute("dat2", date2);
			request.getRequestDispatcher("affichelist.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
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
