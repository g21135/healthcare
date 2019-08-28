package kr.or.ddit.serverMain;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.LoginSession.ClientListImpl;
import kr.or.ddit.LoginSession.IClientList;
import kr.or.ddit.service.apply.ApplyServiceImpl;
import kr.or.ddit.service.apply.IApplyService;
import kr.or.ddit.service.card.CardServiceImpl;
import kr.or.ddit.service.card.ICardService;
import kr.or.ddit.service.cart.CartServiceImpl;
import kr.or.ddit.service.cart.ICartService;
import kr.or.ddit.service.challApply.ChallApplyServiceImpl;
import kr.or.ddit.service.challApply.IChallApplyService;
import kr.or.ddit.service.challenge.ChallengeServiceImpl;
import kr.or.ddit.service.challenge.IChallengeService;
import kr.or.ddit.service.chart.ChartServiceImpl;
import kr.or.ddit.service.chart.IChartService;
import kr.or.ddit.service.check.CheckServiceImpl;
import kr.or.ddit.service.check.ICheckService;
import kr.or.ddit.service.dealHistory.DealHistoryServiceImpl;
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.service.diary.DiaryServiceImpl;
import kr.or.ddit.service.diary.IDiaryService;
import kr.or.ddit.service.homeSub.HomeSubServiceImpl;
import kr.or.ddit.service.homeSub.IHomeSubService;
import kr.or.ddit.service.homeTraining.HomeTrainingServiceImpl;
import kr.or.ddit.service.homeTraining.IHomeTrainingService;
import kr.or.ddit.service.hope.HopeServiceImpl;
import kr.or.ddit.service.hope.IHopeService;
import kr.or.ddit.service.kcal.IKcalService;
import kr.or.ddit.service.kcal.KcalServiceImpl;
import kr.or.ddit.service.match.IMatchService;
import kr.or.ddit.service.match.MatchServiceImpl;
import kr.or.ddit.service.mediaUtil.IMdeiaUtil;
import kr.or.ddit.service.mediaUtil.MediaUtilImpl;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.service.member.MemberServiceImpl;
import kr.or.ddit.service.membership.IMemberShipService;
import kr.or.ddit.service.membership.MemberShipServiceImpl;
import kr.or.ddit.service.openTalk.IOpenTalkService;
import kr.or.ddit.service.openTalk.OpenTalkServiceImpl;
import kr.or.ddit.service.post.IPostService;
import kr.or.ddit.service.post.PostServiceImpl;
import kr.or.ddit.service.prod.IProdService;
import kr.or.ddit.service.prod.ProdServiceImpl;
import kr.or.ddit.service.recommend.IRecommendService;
import kr.or.ddit.service.recommend.RecommendServiceImpl;
import kr.or.ddit.service.talkAdmin.ITalkAdminService;
import kr.or.ddit.service.talkAdmin.TalkAdminServiceImpl;
import kr.or.ddit.service.reply.IReplyService;
import kr.or.ddit.service.reply.ReplyServiceImpl;
import kr.or.ddit.service.trainer.ITrainerService;
import kr.or.ddit.service.trainer.TrainerServiceImpl;
import kr.or.ddit.service.trainerTalk.ChatServer;
import kr.or.ddit.service.trainerTalk.ChatServerImpl;
import kr.or.ddit.service.video.IVideoService;
import kr.or.ddit.service.video.VideoServiceImpl;
import kr.or.ddit.vo.ProdVO;

public class ServerMain {

	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(8888);

			IClientList host = ClientListImpl.getInstance();
			reg.rebind("host", host);

			/* 권준수 */
			IMemberService member = MemberServiceImpl.getInstance();
			reg.rebind("member", member);
			ITrainerService trainer = TrainerServiceImpl.getInstance();
			reg.rebind("trainer", trainer);

			ICardService card = CardServiceImpl.getInstance();
			reg.rebind("card", card);

			/* 김승태 */
			/*
			 * ICardService card = CardServiceImpl.getInstance(); reg.rebind("card", card);
			 */

			IProdService prod = ProdServiceImpl.getInstance();
			reg.rebind("prod", prod);

			IMemberShipService memberShip = MemberShipServiceImpl.getInstance();
			reg.rebind("memberShip", memberShip);

			ICartService cart = CartServiceImpl.getInstance();
			reg.rebind("cart", cart);

			IDealHistoryService deal = DealHistoryServiceImpl.getInstance();
			reg.rebind("deal", deal);

			IMatchService macth = MatchServiceImpl.getInstance();
			reg.rebind("match", macth);

			IDiaryService diary = DiaryServiceImpl.getInstance();
			reg.rebind("diary", diary);
			ChatServer server = ChatServerImpl.getInstance();
			reg.rebind("server", server);

			IChallengeService chall = ChallengeServiceImpl.getInstance();
			reg.rebind("chall", chall);
			ICheckService check = CheckServiceImpl.getInstance();
			reg.rebind("check", check);
			IChallApplyService challApply = ChallApplyServiceImpl.getInstance();
			reg.rebind("challApply", challApply);
			IMdeiaUtil media = MediaUtilImpl.getInstance();
			reg.rebind("media", media);
			IVideoService video = VideoServiceImpl.getInstance();
			reg.rebind("video", video);
			IHomeTrainingService home = HomeTrainingServiceImpl.getInstance();
			reg.rebind("home", home);
			IOpenTalkService openTalk = OpenTalkServiceImpl.getInstance();
			reg.rebind("openTalk", openTalk);
			ITalkAdminService talkAdmin = TalkAdminServiceImpl.getInstance();
			reg.rebind("talkAdmin", talkAdmin);
			IHomeSubService sub = HomeSubServiceImpl.getInstance();
			reg.rebind("sub", sub);
			
			IKcalService kcal = KcalServiceImpl.getInstance();
			reg.rebind("kcal", kcal);
			
			/* 전성희 */
			IPostService post = PostServiceImpl.getInstance();
			reg.rebind("post", post);

			IRecommendService recomm = RecommendServiceImpl.getInstance();
			reg.rebind("recomm", recomm);

			IReplyService reply = ReplyServiceImpl.getInstance();
			reg.rebind("reply", reply);

			IChartService chart = ChartServiceImpl.getInstance();
			reg.rebind("chart", chart);

			/* 장서익 */
			IApplyService apply = ApplyServiceImpl.getInstance();
			reg.rebind("apply", apply);

			IHopeService hope = HopeServiceImpl.getInstance();
			reg.rebind("hope", hope);

			System.out.println("서버 구동 하자아아아아아앙!!!!!!!!!!!!!!!!!!!!!");

		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
