package com.hsk.dentistmall.web.shopping.controller.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hsk.dentistmall.api.daobbase.IMdOrderAfterSaleDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.web.transaction.service.IMdOrderAfterSaleService;
import com.hsk.dentistmall.web.transaction.service.imp.MdOrderAfterSaleService;
import com.hsk.miniapi.xframe.web.webSite.service.IMdSiteCommentApiService;
import com.hsk.miniapi.xframe.web.webSite.service.ISysWebsiteColumnsApiService;
import com.hsk.miniapi.xframe.web.webSite.service.ISysWebsiteCommentApiService;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.web.webSite.service.ISysWebsiteHotSearchService;
import org.jbpm.pvm.internal.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hsk.dentistmall.api.security.WebSiteCommonUtil;
import com.hsk.dentistmall.web.mall.service.IMdMaterielInfoService;
import com.hsk.dentistmall.web.materiel.service.IMdMaterielFormatService;
import com.hsk.dentistmall.web.materiel.service.IMdMaterielTypeService;
import com.hsk.dentistmall.web.shopping.controller.IMallController;
import com.hsk.dentistmall.web.shopping.model.MdCommentModel;
import com.hsk.dentistmall.web.transaction.service.IMdOrderInfoService;
import com.hsk.dentistmall.web.transaction.service.IMdOrderMxService;
import com.hsk.dentistmall.web.transaction.service.IMdSupplierService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.xframe.api.controller.imp.DSTController;
import com.hsk.xframe.api.daobbase.ISysUserLoginLogDao;
import com.hsk.xframe.api.persistence.SysFieldInfo;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysNoticeInfo;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteComment;
import com.hsk.xframe.web.sysLogin.service.ISysLoginService;
import com.hsk.xframe.web.sysfile.service.IFileService;
import com.hsk.xframe.web.sysnotice.service.ISysNoticeInfoService;
import com.hsk.xframe.web.sysparam.service.ISysParameterService;
import com.hsk.xframe.web.webSite.service.IMdSiteCommentService;
import com.hsk.xframe.web.webSite.service.ISysWebsiteColumnsService;
import com.hsk.xframe.web.webSite.service.ISysWebsiteCommentService;

@Controller
public class MallController extends DSTController  implements IMallController{
	@Autowired
	private ISysWebsiteCommentService sysWebsiteCommentService;
	@Autowired
	private ISysWebsiteColumnsService sysWebsiteColumnsService;
	@Autowired
	private IMdSiteCommentService mdSiteCommentService;
	@Autowired
	private ISysParameterService sysParameterService;
	@Autowired
	private IMdSupplierService mdSupplierService;
	@Autowired
	private IMdMaterielInfoService mdMaterielInfoService;
	@Autowired
	private IMdMaterielFormatService mdMaterielFormatService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private IMdMaterielTypeService mdMaterielTypeService;
	@Autowired
	private IMdOrderInfoService mdOrderInfoService;
	@Autowired
	private IMdOrderMxService mdOrderMxService;
	@Autowired
	private ISysNoticeInfoService sysNoticeInfoService;
	@Autowired
	private ISysWebsiteHotSearchService sysWebsiteHotSearchService;
	@Autowired
	private ISysLoginService sysLoginService;
	@Autowired
	private IMdOrderAfterSaleService mdOrderAfterSaleService;
	@Autowired
	private ISysWebsiteCommentApiService sysWebsiteCommentApiService;
	@Autowired
	private ISysWebsiteColumnsApiService sysWebsiteColumnsApiService;
	@Autowired
	private IMdSiteCommentApiService mdSiteCommentApiService;
	
	@RequestMapping(value = "/index.htm")
	public ModelAndView toIndex(HttpServletRequest request) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		String url = request.getRequestURL().toString();
//		if (url.indexOf("www.cnyyk.cn/") >= 0 || url.indexOf("www.cnyyk.cn:") >= 0 || url.indexOf("www.cnyyk.cn") >= 0) {
////		if (url.indexOf("www.cnyyk.cn/") >= 0 || url.indexOf("localhost:") >= 0 || url.indexOf("www.cnyyk.cn") >= 0) {
//			//获取轮播图模块
//			modelAndView.addObject("lbList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.SY_LB, null));
//			//获取楼层模块
//			List<SysWebsiteColumns> lcColumnsList = sysWebsiteColumnsService.getSysWebsiteColumnsListBySysSwcId(WebSiteCommonUtil.SY_LC);
//			List<MdCommentModel> lcModelList = new ArrayList<MdCommentModel>();
//			if(lcColumnsList != null && lcColumnsList.size() > 0){
//				Integer idx = 1;
//				for(SysWebsiteColumns columns : lcColumnsList){
//					lcModelList.add(mdSiteCommentApiService.getMdCommentModelBySwcId1(columns, columns.getShowNumber(), idx * 5));
//					idx += 1;
//				}
//			}
//			modelAndView.addObject("selType", "index");
//			modelAndView.addObject("lcModelList", lcModelList);
//			modelAndView.setViewName("/ohmy/ohmy");
//			return modelAndView;
//		}
		//获取轮播图模块
		modelAndView.addObject("lbList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.SY_LB, null));
		//获取楼层模块
		List<SysWebsiteColumns> lcColumnsList = sysWebsiteColumnsService.getSysWebsiteColumnsListBySysSwcId(WebSiteCommonUtil.SY_LC);
		List<MdCommentModel> lcModelList = new ArrayList<MdCommentModel>();
		if(lcColumnsList != null && lcColumnsList.size() > 0){
			for(SysWebsiteColumns columns : lcColumnsList){
				lcModelList.add(mdSiteCommentService.getMdCommentModelBySwcId(columns, columns.getShowNumber()));
			}
		}
		modelAndView.addObject("selType", "index");
		modelAndView.addObject("lcModelList", lcModelList);
		modelAndView.setViewName("/dentistmall/shopping/index");
		/**
		 * yanglei
		 * 当用户访问首页不登录的时候，获取ip地址
		 * ----start----
		 */
		String ip = request.getHeader("x-forwarded-for");
		if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
		}
		sysLoginService.addSysUserLoginShoppingLog(null, ip);
		/*----end----*/
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/liebiao.htm")
	public ModelAndView toLiebiao(HttpServletRequest request,Integer mmtId,String searchName) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//获取品牌列表
		modelAndView.addObject("brandList", sysParameterService.getParameterListByParentCode(WebSiteCommonUtil.BRAD_CODE));
		
		MdMaterielType searchMdMaterielType = new MdMaterielType();
		searchMdMaterielType.setMmtLevel("3");
		if(mmtId!= null)
			searchMdMaterielType.setMmtPath("/"+mmtId+"/");
		modelAndView.addObject("mmtList", mdMaterielTypeService.getListObjectByMatName(searchMdMaterielType,searchName));
		modelAndView.addObject("mmtId", mmtId);
		modelAndView.addObject("searchName", searchName);
		//获取面包屑
		if(mmtId != null){
			MdMaterielType mdMaterielType = mdMaterielTypeService.findObject(mmtId);
			modelAndView.addObject("Lehem", mdMaterielType.getMmtName());
		}else if(searchName != null && !searchName.trim().equals("")){
			modelAndView.addObject("Lehem", searchName.trim());
			modelAndView.addObject("searchType", "mat");
		}
		modelAndView.setViewName("/dentistmall/shopping/liebiao");
		return modelAndView;
	}
	
	@RequestMapping(value = "/hzcj.htm")
	public ModelAndView toHzcj(HttpServletRequest request,String searchName) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		modelAndView.addObject("searchName", searchName);
		//获取面包屑
		if(searchName != null && !searchName.trim().equals("")){
			modelAndView.addObject("Lehem", searchName.trim());
			modelAndView.addObject("searchType", "supplier");
		}else{
			modelAndView.addObject("Lehem", "合作厂家");
			modelAndView.addObject("selType", "hzcj");
		}
		modelAndView.setViewName("/dentistmall/shopping/hzcj");
		return modelAndView;
	}
	
	@RequestMapping(value = "/pcjxiangxi.htm")
	public ModelAndView toPcjxiangxi(HttpServletRequest request,Integer wzId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/dentistmall/shopping/pcjxiangxi");
		this.init(modelAndView);
		MdSupplier mdSupplier = mdSupplierService.findObject(wzId);
		modelAndView.addObject("mdSupplier", mdSupplier);
		if(mdSupplier != null && mdSupplier.getCustoms() != null && !mdSupplier.getCustoms().trim().equals("")){
			String[] cusList = mdSupplier.getCustoms().split(",");
			modelAndView.addObject("cusList", cusList);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/xiangxi.htm")
	public ModelAndView toXiangxi(HttpServletRequest request,Integer wmsMiId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		MdMaterielInfo matInfo = mdMaterielInfoService.findObject(wmsMiId);
		MdSupplier mdSupplier = mdSupplierService.findObject(matInfo.getWzId());
		List<SysFileInfo> list = new ArrayList<SysFileInfo>();
		if(matInfo.getListFilecode() != null && !matInfo.getListFilecode().trim().equals("")){
			list = fileService.GetListFileInfoByCodes(matInfo.getListFilecode().trim());
			if(list != null && list.size() > 0){
				Collections.reverse(list);
				modelAndView.addObject("firstFile", list.get(0));
				modelAndView.addObject("endFile", list.get(list.size()-1));
			}
		}
		modelAndView.addObject("matInfo", matInfo);
		modelAndView.addObject("mdSupplier", mdSupplier);
		if(mdSupplier != null && mdSupplier.getCustoms() != null && !mdSupplier.getCustoms().trim().equals("")){
			String[] cusList = mdSupplier.getCustoms().split(",");
			modelAndView.addObject("cusList", cusList);
		}
		//同类热门
		MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
		att_MdMaterielInfo.setMdWmsMiId(matInfo.getMdWmsMiId());
		att_MdMaterielInfo.setState("1");
		att_MdMaterielInfo.setWzState("1");
		List<SysFileInfo> list_cfcafile=new ArrayList<SysFileInfo>();
		
		
		if(matInfo.getCfca01Filecode()!=null&&!"".equals(matInfo.getCfca01Filecode().trim())){
			SysFileInfo sf01 =new SysFileInfo();
			sf01.setFileName(matInfo.getCfca01Filename());
			sf01.setRootPath( matInfo.getCfca01FilePath());
			sf01.setFileType(matInfo.getCfca01Date());
			list_cfcafile.add(sf01);
		}
		
		if(matInfo.getCfca02Filecode()!=null&&!"".equals(matInfo.getCfca02Filecode().trim())){
			SysFileInfo sf02 =new SysFileInfo();
			sf02.setFileName(matInfo.getCfca02Filename());
			sf02.setFileType(matInfo.getCfca02Date());
			sf02.setRootPath( matInfo.getCfca02FilePath());
			list_cfcafile.add(sf02);
		}
		if(matInfo.getCfca03Filecode()!=null&&!"".equals(matInfo.getCfca03Filecode().trim())){
			SysFileInfo sf03 =new SysFileInfo();
			sf03.setFileName(matInfo.getCfca03Filename());
			sf03.setFileType(null);
			sf03.setRootPath( matInfo.getCfca03FilePath());
			list_cfcafile.add(sf03);
		}
		if(matInfo.getCfca04Filecode()!=null&&!"".equals(matInfo.getCfca04Filecode().trim())){
			SysFileInfo sf04 =new SysFileInfo();
			sf04.setFileName(matInfo.getCfca04Filename());
			sf04.setRootPath( matInfo.getCfca04FilePath());
			sf04.setFileType(null);
			list_cfcafile.add(sf04);
		}
		if(matInfo.getCfca05Filecode()!=null&&!"".equals(matInfo.getCfca05Filecode().trim())){
			SysFileInfo sf05 =new SysFileInfo();
			sf05.setFileName(matInfo.getCfca05Filename());
			sf05.setRootPath( matInfo.getCfca05FilePath());
			sf05.setFileType(null);
			list_cfcafile.add(sf05);
		}
		
		if(matInfo.getCfca06Filecode()!=null&&!"".equals(matInfo.getCfca06Filecode().trim())){
			SysFileInfo sf06 =new SysFileInfo();
			sf06.setFileName(matInfo.getCfca06Filename());
			sf06.setRootPath( matInfo.getCfca06FilePath());
			sf06.setFileType(null);
			list_cfcafile.add(sf06);
		}
		
		modelAndView.addObject("cfcafileList", list_cfcafile);
		
		modelAndView.addObject("fileList", list);
		modelAndView.addObject("hotList", mdMaterielInfoService.getHotMdMaterielInfoListBySize(att_MdMaterielInfo, 5));
		modelAndView.addObject("formatList", mdMaterielFormatService.getFormatListByWmsMiId(wmsMiId));
		String url = request.getRequestURL().toString();
//		if (url.indexOf("www.cnyyk.cn/") >= 0 || url.indexOf("www.cnyyk.cn:") >= 0 || url.indexOf("www.cnyyk.cn") >= 0) {
//			modelAndView.addObject("ohmy", true);
//		}
		modelAndView.setViewName("/dentistmall/shopping/xiangxi");
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/gwc.htm")
	public ModelAndView toGwc(HttpServletRequest request) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		modelAndView.setViewName("/dentistmall/shopping/gwc");
		return modelAndView;
	}
	
	@RequestMapping(value = "/ddqr.htm")
	public ModelAndView toDdqr(HttpServletRequest request,String mmfIds,String shus) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		modelAndView.addObject("mmfIds", mmfIds);
		modelAndView.addObject("shus", shus);
		checkLogin(request,modelAndView,"/dentistmall/shopping/ddqr");
		return modelAndView;
	}
	@RequestMapping(value = "/commInfo.htm")
	public ModelAndView toCommInfo(HttpServletRequest request,Integer swmId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		SysWebsiteComment sysWebsiteComment= new SysWebsiteComment();
		sysWebsiteComment.setSwmId(swmId);
		sysWebsiteComment =sysWebsiteCommentService.GetOneSysWebsitComment(sysWebsiteComment);
		modelAndView.addObject("comment", sysWebsiteComment);
		modelAndView.setViewName("/dentistmall/shopping/commentInfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "/noticeInfo.htm")
	public ModelAndView toNoticeInfo(HttpServletRequest request,Integer sniId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		SysNoticeInfo sysNoticeInfo= new SysNoticeInfo();
		sysNoticeInfo.setSniId(sniId);
		sysNoticeInfo =sysNoticeInfoService.findObject(sniId);
		modelAndView.addObject("noticeInfo", sysNoticeInfo);
		modelAndView.setViewName("/dentistmall/shopping/noticeInfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "/scj.htm")
	public ModelAndView toScj(HttpServletRequest request) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		modelAndView.setViewName("/dentistmall/shopping/scj");
		return modelAndView;
	}
	
	@RequestMapping(value = "/qgc.htm")
	public ModelAndView toQgc(HttpServletRequest request) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		modelAndView.setViewName("/dentistmall/shopping/qgc");
		return modelAndView;
	}
	
	@RequestMapping(value = "/qhtx.htm")
	public ModelAndView toQhtx(HttpServletRequest request) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		modelAndView.setViewName("/dentistmall/shopping/qhtx");
		return modelAndView;
	}
	
	@RequestMapping(value = "/ddlb.htm")
	public ModelAndView toDdlb(HttpServletRequest request) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		checkLogin(request,modelAndView,"/dentistmall/shopping/ddlb");
		return modelAndView;
	}

	@RequestMapping(value = "/ddpay.htm")
	public ModelAndView toDdpay(HttpServletRequest request, String moiId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("moiId", moiId);
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		SysUserInfo account = checkLogin(request,modelAndView,"/dentistmall/shopping/ddxq");
		if(account != null && account.getSuiId()!=null){
//			MdOrderInfo mdOrderInfo = mdOrderInfoService.doFindObject2(moiId,account);
			modelAndView.addObject("obj", null);
		}
		checkLogin(request,modelAndView,"/dentistmall/shopping/ddpay");
		return modelAndView;
	}

	@RequestMapping(value = "/ddpayres.htm")
	public ModelAndView toDdpayres(HttpServletRequest request, String moiId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("moiId", moiId);
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		checkLogin(request,modelAndView,"/dentistmall/shopping/ddpayres");
		return modelAndView;
	}
	
	@RequestMapping(value = "/ddxq.htm")
	public ModelAndView toDdxq(HttpServletRequest request,Integer moiId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		SysUserInfo account = checkLogin(request,modelAndView,"/dentistmall/shopping/ddxq");
		if(account != null && account.getSuiId()!=null){
			MdOrderInfo mdOrderInfo = mdOrderInfoService.doFindObject2(moiId,account);
			modelAndView.addObject("obj", mdOrderInfo);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/orderas.htm")
	public ModelAndView toDdas(HttpServletRequest request, Integer moiId) throws HSKException{
		ModelAndView mv = new ModelAndView();
		this.init(mv);
		checkLogin(request, mv, "/dentistmall/shopping/orderaftersale/orderas");
		mv.addObject("moiId", moiId);
		return mv;
	}

	@RequestMapping(value = "/asapply.htm")
	public ModelAndView toAsapply(HttpServletRequest request, Integer moiId, Integer masId, String masCode) throws HSKException{
        ModelAndView modelAndView = new ModelAndView();
	    this.init(modelAndView);
	    SysUserInfo account = checkLogin(request, modelAndView, "/dentistmall/shopping/orderaftersale/asapply");
	    if(masCode == null || masCode.trim().equals("")) { //如果为null，则是新申请，不为null则是修改
            masCode = sysParameterService.getNewCode("AS").getObj().toString();
            modelAndView.addObject("newAs", true);
        } else {
			modelAndView.addObject("newAs", false);
			SysRetrunMessage sm = mdOrderAfterSaleService.getMdOrderSaleAfterByMasId(masId, account);
			MdOrderAfterSaleEntity obj = (MdOrderAfterSaleEntity)sm.getObj();
			if(obj != null && obj.getAsState() != 5 && obj != null && obj.getAsState() != 4) {
				return toAsmx(request, masId);
			}
			Integer asCount = mdOrderAfterSaleService.getMdOrderSaleAfterMxCountByMasId(masId);
			PagerModel pm = mdOrderAfterSaleService.getMdOrderSaleAfterMxList(masId);
			List<Map<String, Object>> list = pm.getItems();
			String momIds = "";
			for (int i = 0; i < list.size() - 1; i++){
				String momId = list.get(i).get("mom_id").toString();
				momIds += momId + ",";
			}
			Double allMoney = mdOrderAfterSaleService.getMdOrderSaleAfterMxMoneyByMasId(masId);
			modelAndView.addObject("obj", obj);
			modelAndView.addObject("asCount", asCount);
			modelAndView.addObject("allMoney", allMoney);
			modelAndView.addObject("momIds", momIds);
		}
		modelAndView.addObject("masId", masId);
        modelAndView.addObject("masCode", masCode); //售后
        modelAndView.addObject("moiId", moiId);
	    return modelAndView;
    }

    @RequestMapping(value = "/asmx.htm")
    public ModelAndView toAsmx(HttpServletRequest request, Integer masId) throws HSKException{
        ModelAndView modelAndView = new ModelAndView();
        this.init(modelAndView);
        SysUserInfo account = checkLogin(request, modelAndView, "/dentistmall/shopping/orderaftersale/asmx");
        modelAndView.addObject("obj", mdOrderAfterSaleService.getMdOrderSaleAfterByMasId(masId, account).getObj()); //售后
		modelAndView.addObject("supplier", mdOrderAfterSaleService.getMdOrderSaleAfterSupplier(masId, account));
        return modelAndView;
    }
	
	@RequestMapping(value = "/ddsh.htm")
	public ModelAndView toDdsh(HttpServletRequest request,Integer moiId) throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		SysUserInfo account = checkLogin(request,modelAndView,"/dentistmall/shopping/ddsh");
		if(account != null && account.getSuiId()!=null){
			MdOrderInfo mdOrderInfo = mdOrderInfoService.doFindObject2(moiId,account);
			modelAndView.addObject("order", mdOrderInfo);
		}
		return modelAndView;
	}
	@RequestMapping(value = "/zhsz.htm")
	public ModelAndView toZhsz(HttpServletRequest request)throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		checkLogin(request,modelAndView,"/dentistmall/shopping/zhsz");
		return modelAndView;
	}
	
	@RequestMapping(value = "/upass.htm")
	public ModelAndView toUpass(HttpServletRequest request)throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		checkLogin(request,modelAndView,"/dentistmall/shopping/upass");
		return modelAndView;
	}

	@RequestMapping(value = "/upsafetycode.htm")
	public ModelAndView toUpsafetycode(HttpServletRequest request)throws HSKException{
		ModelAndView modelAndView = new ModelAndView();
		this.init(modelAndView);
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		checkLogin(request,modelAndView,"/dentistmall/shopping/upsafetycode");
		return modelAndView;
	}

	private void init(ModelAndView modelAndView) throws HSKException{
		//获取热门搜索模块
		modelAndView.addObject("rmssList", sysWebsiteHotSearchService.getSysWebsiteHotSearchList());
		//获取头部TAB模块 
		modelAndView.addObject("tabList", mdSiteCommentService.getMdCommentInfoListBySwcId(WebSiteCommonUtil.TAB_DH, null));
		//底部公司信息
		modelAndView.addObject("dbProList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.DB_PRO_INFO, null));
		//获取公告信息
		modelAndView.addObject("noticeList", sysNoticeInfoService.getSysNoticeInfoList());
		
	}
	
	private SysUserInfo checkLogin(HttpServletRequest request,ModelAndView modelAndView,String url) throws HSKException{
		SysUserInfo account =new SysUserInfo();
		Object  obj=request.getSession().getAttribute("shoppingAccount");
		if(obj!=null){
			account= (SysUserInfo) obj; 
			modelAndView.addObject("userInfo",account);
			modelAndView.setViewName(url);
		}else{
			initIndexView(request,modelAndView);
		}
		return account;
	}
	
	private void initIndexView(HttpServletRequest request,ModelAndView modelAndView) throws HSKException{
		this.init(modelAndView);
		//获取轮播图模块
		modelAndView.addObject("lbList", sysWebsiteCommentService.getSysWebsiteCommentListBySwcId(WebSiteCommonUtil.SY_LB, null));
		//获取楼层模块
		List<SysWebsiteColumns> lcColumnsList = sysWebsiteColumnsService.getSysWebsiteColumnsListBySysSwcId(WebSiteCommonUtil.SY_LC);
		List<MdCommentModel> lcModelList = new ArrayList<MdCommentModel>();
		if(lcColumnsList != null && lcColumnsList.size() > 0){
			for(SysWebsiteColumns columns : lcColumnsList){
				if(columns.getMszType()==2)
					lcModelList.add(mdSiteCommentService.getMdCommentModelBySwcId(columns, 8));
				else
					if(columns.getMszType()==3)
						lcModelList.add(mdSiteCommentService.getMdCommentModelBySwcId(columns, 10));
			}
		}
		modelAndView.addObject("selType", "index");
		modelAndView.addObject("lcModelList", lcModelList);
		modelAndView.setViewName("/dentistmall/shopping/index");
	}
	
}
