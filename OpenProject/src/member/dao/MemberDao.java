package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jdbc.ConnectionProvider;
import model.Member;
import model.MemberListView;
import oracle.net.aso.c;
import oracle.net.aso.g;

public class MemberDao {
	private MemberDao() {
	}

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	public int insertMember(Connection conn, Member member) throws SQLException {
		int resultCnt = 0;
		String sql = "INSERT INTO project.member (uid, upw, uname, uphoto) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUid());
			pstmt.setString(2, member.getUpw());
			pstmt.setString(3, member.getUname());
			pstmt.setString(4, member.getUphoto());
			resultCnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 쿼리 에러 발생.");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return resultCnt;
	}

	public int selectById(Connection conn, HttpServletRequest request, String id) {
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM project.member WHERE uid=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				resultCnt = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultCnt;
	}

	public int cntTotalMembers(Connection conn, HttpServletRequest request) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM project.member";
		int resultCnt = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				resultCnt = rs.getInt(1);
				System.out.println("resultCnt 값 : " + resultCnt);
			}
		} catch (SQLException e) {
			System.out.println("결과 가져오는중 SQL 에러 발생, 쿼리문 확인 필요함.");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println("PreparedStatement 종료 시도중 오류 발생.");
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("ResultSet 종료 시도중 오류 발생.");
					e.printStackTrace();
				}
			}
		}
		return resultCnt;
	}

	public List<Member> getMemberList(Connection conn, MemberListView memberListView) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		String sql = "SELECT * FROM project.member limit ?, ?";
		int endRow = memberListView.getEndRow();
		int pagePerCount = memberListView.getmPagePerCount();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, pagePerCount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();

				member.setIdx(rs.getInt("idx"));
				member.setUid(rs.getString("uid"));
				member.setUpw(rs.getString("upw"));
				member.setUname(rs.getString("uname"));
				member.setUphoto(rs.getString("uphoto"));
				member.setRegdate(rs.getDate("regdate"));

				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println("PreparedStatement 종료중 예외 발생.");
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("ResultSet 종료중 예외 발생.");
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public Boolean withdrawMember(Connection conn, int manageIdx) {
		boolean result = false;
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM project.member WHERE idx=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, manageIdx);
			resultCnt = pstmt.executeUpdate();

			if (resultCnt > 0) {
				result = true;
			}else {
				result = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println("PreparedStatement 종료중 예외 발생.");
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public Member getMemberInfo(Connection conn, int manageIdx) {
		Member getMember = new Member();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM project.member WHERE idx=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, manageIdx);
			rs = pstmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					getMember.setIdx(rs.getInt("idx"));
					getMember.setUid(rs.getString("uid"));
					getMember.setUpw(rs.getString("upw"));
					getMember.setUname(rs.getString("uname"));
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 발생! 로그 확인하세요");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("기타 에러 발생! 로그 확인하세요");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println("PreparedStatement 종료중 예외 발생.");
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("ResultSet 종료중 예외 발생.");
					e.printStackTrace();
				}
			}
		}
		return getMember;
	}

}
