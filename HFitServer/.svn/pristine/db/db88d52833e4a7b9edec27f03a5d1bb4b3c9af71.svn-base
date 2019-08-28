package kr.or.ddit.dao.member;

import java.net.PasswordAuthentication;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static IMemberDao dao;
	private static SqlMapClient smc;

	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMemberDao getInstance() {
		if (dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}

	@Override
	public MemberVO login(String id, String pass) {
		MemberVO mem = new MemberVO();

		try {
			mem.setMem_id(id);
			mem.setMem_pass(pass);

			mem = (MemberVO) smc.queryForObject("member.login", mem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem;
	}

	@Override
	public List<MemberVO> selectId(MemberVO mv) {
		List<MemberVO> memberList = new ArrayList<>();
		try {
			memberList = smc.queryForList("member.selectId", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public List<MemberVO> getMemberInfo(MemberVO id) {
		List<MemberVO> memInfo = new ArrayList<>();

		try {
			memInfo = smc.queryForList("member.getMemberInfo", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memInfo;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember", mv);
		} catch (SQLException e) {
			System.out.println("updateMember 에러");
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("member.insertMember", mv);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			System.out.println("insertMember 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> searchId(MemberVO mv) {
		List<MemberVO> memId = new ArrayList<>();
		try {
			memId = smc.queryForList("member.searchId", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memId;
	}

	@Override
	public List<MemberVO> searchPass(MemberVO mv) {
		List<MemberVO> memPass = new ArrayList<>();
		try {
			memPass = smc.queryForList("member.searchPass", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memPass;
	}

	@Override
	public int updatePass(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updatePass", mv);
		} catch (SQLException e) {
			System.out.println("updatePass 에러");
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("member.getAllMemberList");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int deleteMember(String mem_no) {
		int num = 0;

		try {
			num = smc.delete("member.deleteMem", mem_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return num;
	}

	@Override
	public List<MemberVO> getMember() {
		List<MemberVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("member.getMember");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int updateMember(String mem_id) {
		int cnt = 0;

		try {
			cnt = smc.update("member.updateMem", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMemberAdmin(MemberVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("member.updateMemberAdmin", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
