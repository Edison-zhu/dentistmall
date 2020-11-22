package com.hsk.miniapi.xframe.web.sysTableInfo.Service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.IFieldInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ITablesInfoApiDao;
import com.hsk.xframe.api.dto.freeMarker.FMFieldInfo;
import com.hsk.xframe.api.dto.freeMarker.FMPojoInfo;
import com.hsk.xframe.api.dto.freeMarker.SysCodeInfo;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysClassColumns;
import com.hsk.xframe.api.persistence.SysClassInfo;
import com.hsk.xframe.api.persistence.SysFieldInfo;
import com.hsk.xframe.api.persistence.SysSystemInfo;
import com.hsk.xframe.api.persistence.SysTableInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.AutoCodeUtils;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.framework.service.ICodeAutoApiService;
import com.hsk.miniapi.xframe.web.sysTableInfo.Service.ITableInfoApiService;
import com.hsk.miniapi.xframe.web.window.RunSrc;

@Service
public class TableInfoApiService extends DSTApiService implements ITableInfoApiService {

	private static final ICodeAutoApiService tableSer = null;

	@Autowired
	private ITablesInfoApiDao tablesInfoDao;

	@Autowired
	private IFieldInfoApiDao fieldInfoDao;

	@Autowired
	private ICodeAutoApiService codeAutoService;

	public List<TreeNode> getlistNodeTable(String sysCode) throws HSKException {
		List<TreeNode> list_tn = new ArrayList<TreeNode>();
		SysTableInfo rowObj = new SysTableInfo();
		rowObj.setSysCode(sysCode);
		if (sysCode == null) {
			// 查询全部系统的表对象列表
			TreeNode root_tn = createNode(null);
			List<TreeNode> root_children = new ArrayList<TreeNode>();
			try {
				SysSystemInfo ssi = new SysSystemInfo();
				List<SysSystemInfo> list_ssi = this.getList(ssi);
				if (list_ssi != null && list_ssi.size() > 0) {
					for (SysSystemInfo did : list_ssi) {
						TreeNode root_children_tn = createNode(did.getSysCode());
						root_children_tn.setChildren(this.createListNode(did
								.getSysCode()));
						root_children.add(root_children_tn);
					}
				}
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
			root_tn.setChildren(root_children);
			list_tn.add(root_tn);
		} else {
			TreeNode root_tn = createNode(sysCode);
			root_tn.setChildren(this.createListNode(sysCode));
			list_tn.add(root_tn);
		}
		return list_tn;
	}

	private List<TreeNode> createListNode(String sysCode) {
		SysTableInfo rowObj = new SysTableInfo();
		rowObj.setSysCode(sysCode);
		List<TreeNode> children = new ArrayList<TreeNode>();
		try {
			List<SysTableInfo> list_sti = this.getList(rowObj);
			if (list_sti != null && list_sti.size() > 0) {
				for (SysTableInfo did : list_sti) {
					TreeNode tn = new TreeNode();
					tn.setId(did.getTableCode());
					tn.setType("2");
					tn.setName(did.getTableName() + "(" + did.getTableNode()
							+ ")");
					tn.setText(did.getTableName() + "(" + did.getTableNode()
							+ ")");
					tn.setAttributes(did);
					children.add(tn);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}

		return children;

	}

	private TreeNode createNode(String sysCode) {
		TreeNode tn = new TreeNode();
		SysSystemInfo ssi = new SysSystemInfo();
		if (sysCode != null) {
			ssi.setSysCode(sysCode);
			Object obj = this.getOne(ssi);
			if (obj != null) {
				ssi = (SysSystemInfo) obj;
			}
			tn.setId(ssi.getSysCode());
			tn.setName(ssi.getProjectName());
			tn.setText(ssi.getProjectName());
			tn.setType("1");
			tn.setOpen(true);
			tn.setAttributes(ssi);
		} else {
			tn.setId("0");
			tn.setType("0");
			tn.setName("表对象列表");
			tn.setText("表对象列表");
			tn.setOpen(true);
			tn.setAttributes(ssi);
		}
		return tn;
	}

	public PagerModel getPagerModelFieldforTable(SysTableInfo stable)
			throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<SysFieldInfo>());
		Integer tableid = null;
		if (stable.getStableId() == null && stable.getSysCode() != null) {
			SysTableInfo rowtable = new SysTableInfo();
			rowtable.setSysCode(stable.getSysCode());
			Object obj = this.getOne(rowtable);
			if (obj != null) {
				rowtable = (SysTableInfo) obj;
			}
			tableid = stable.getStableId();
		} else if (stable.getStableId() == null && stable.getSysCode() == null) {
			return pm;
		} else if (stable.getStableId() != null) {
			tableid = stable.getStableId();
		}
		SysFieldInfo sfi = new SysFieldInfo();
		sfi.setStableId(tableid);
		try {
			pm = this.getPagerModel(sfi);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	@SuppressWarnings("unchecked")
	public SysRetrunMessage delTable(SysTableInfo stable) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		// 删除字段信息
		SysFieldInfo sfi_row = new SysFieldInfo();
		try {
			if (stable.getStableId() == null) {
				srm.setCode(0l);
				srm.setMeg("删除出错:出错原因为找到主键为" + stable.getStableId() + "的记录");
				return srm;
			}
			SysTableInfo trow = new SysTableInfo();
			trow.setStableId(stable.getStableId());
			Object obj = this.getOne(trow);
			if (obj != null) {
				trow = (SysTableInfo) obj;
				this.deleteObjects(trow);
			} else {
				srm.setCode(0l);
				srm.setMeg("删除出错:出错原因为找到主键为" + stable.getStableId() + "的记录");
				return srm;
			}
			sfi_row.setStableId(stable.getStableId());
			@SuppressWarnings("rawtypes")
			List list_sfi = this.getList(sfi_row);
			if (list_sfi != null && list_sfi.size() > 0) {
				this.getHibernateDao().deleteAll(list_sfi);
			}

		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}

		return srm;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public PagerModel getPagerModelTableforDB(String sysCode)
			throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList());
		List<SysTableInfo> list_back = new ArrayList<SysTableInfo>();
		SysSystemInfo ssi = new SysSystemInfo();
		ssi.setSysCode(sysCode);
		Object obj = this.getOne(ssi);
		ssi = (SysSystemInfo) obj;
		List<FMPojoInfo> list_fmpi = tablesInfoDao.queryListTableInfo(ssi
				.getDbuserName());

		SysTableInfo rowObj = new SysTableInfo();
		rowObj.setSysCode(sysCode);
		try {
			// 获取表对象列表
			List<SysTableInfo> list_sti = this.getList(rowObj);
			if (list_fmpi != null && list_fmpi.size() > 0) {
				a: for (FMPojoInfo did : list_fmpi) {
					SysTableInfo row = null;
					if (list_sti != null && list_sti.size() > 0) {
						b: for (SysTableInfo table_did : list_sti) {
							if (did.getTableName().equals(
									table_did.getTableName())) {
								row = null;
								break b;
							}
						}
						row = new SysTableInfo();
						row.setTableName(did.getTableName());
						row.setTableNode(did.getCommentText());
					}
					if (row != null) {
						list_back.add(row);
					}
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		if (list_back != null && list_back.size() > 0) {
			pm = new PagerModel(list_back);
		}
		return pm;
	}

	public SysRetrunMessage getNodeTableforDB(String sysCode, String ifall)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		List<TreeNode> list_back = new ArrayList<TreeNode>();
		SysSystemInfo ssi = new SysSystemInfo();
		ssi.setSysCode(sysCode);
		Object obj = this.getOne(ssi);
		ssi = (SysSystemInfo) obj;
		List<FMPojoInfo> list_fmpi = tablesInfoDao.queryListTableInfo(ssi
				.getDbuserName());

		SysTableInfo rowObj = new SysTableInfo();
		rowObj.setSysCode(sysCode);
		try {
			// 获取表对象列表
			List<SysTableInfo> list_sti = this.getList(rowObj);
			if (list_fmpi != null && list_fmpi.size() > 0) {
				a: for (FMPojoInfo did : list_fmpi) {
					if (did.getTableName().indexOf("_") == -1) {
						continue a;
					}
					TreeNode row = new TreeNode();
					row.setId(did.getTableName());
					row.setName(did.getTableName() + "(" + did.getCommentText()
							+ ")");
					row.setAttributes(did);
					if ("1".equals(ifall)) {
						if (list_sti != null && list_sti.size() > 0) {
							b: for (SysTableInfo table_did : list_sti) {
								if (did.getTableName().equals(
										table_did.getTableName())) {
									row = null;
									break b;
								}
							}
						}
					}
					if (row != null) {
						list_back.add(row);
					}
				}
			}
			srm.setObj(list_back);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
	}

	@SuppressWarnings("deprecation")
	public SysRetrunMessage saveTable(SysTableInfo stable) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Session sessiontt = this.getHibernatesession();

		Transaction tc = sessiontt.beginTransaction();
		try {

			// 添加表对象
			// stable.setTableCode(CreateCodeUtil.getTableNo());
			Integer parent_id = this.newObject(stable);
			// 添加字段对象
			// 获取表字段
			String tableName = stable.getTableName();
			String UserName = stable.getDbuserName();
			List<FMFieldInfo> listfmf = fieldInfoDao.queryListFieldInfo(
					tableName, UserName);

			if (listfmf != null && listfmf.size() > 0) {
				int i = 0;
				for (FMFieldInfo did : listfmf) {
					SysFieldInfo sfi = new SysFieldInfo();
					sfi.setStableId(parent_id);
					sfi.setFieldName(did.getFieldName());
					sfi.setFieldNode(did.getFieldComment());
					sfi.setFieldLength(did.getDataLength());
					sfi.setFieldPrecision(did.getDataPrecision());
					sfi.setFieldCode(CreateCodeUtil.getFieldNo());
					sfi.setDataType(did.getFieldType());
					sfi.setIfNull(did.getIsNullable());
					sfi.setIfPk(did.getColumnKey());
					sfi.setOrderNumber(i);
					i++;
					this.newObject(sfi);
				}
			}

		} catch (Exception e) {
			tc.rollback();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
			e.printStackTrace();

		}
		return srm;
	}

	public SysFieldInfo questField(SysFieldInfo field) throws HSKException {
		SysFieldInfo sfi = new SysFieldInfo();
		if (field.getSfieldId() != null) {
			sfi.setSfieldId(field.getSfieldId());
			Object obj = this.getOne(sfi);
			if (obj != null) {
				sfi = (SysFieldInfo) obj;
			}
		}
		return sfi;
	}

	@Override
	public SysRetrunMessage saveOrUpdateField(SysFieldInfo field)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (field.getSfieldId() != null) {
				this.updateObject(field);
			} else {
				field.setFieldCode(CreateCodeUtil.getFieldNo());
				this.newObject(field);
			}
		} catch (Exception e) {
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
			e.printStackTrace();
		}
		return srm;
	}

	public SysTableInfo questTable(SysTableInfo stable) throws HSKException {
		SysTableInfo sfi = new SysTableInfo();
		if (stable.getStableId() != null) {
			sfi.setStableId(stable.getStableId());
			Object obj = this.getOne(sfi);
			if (obj != null) {
				sfi = (SysTableInfo) obj;
			}
		} else {
			// 初始化
			if (stable.getSysCode() != null) {
				SysSystemInfo ssi = new SysSystemInfo();
				ssi.setSysCode(stable.getSysCode());
				Object obj = this.getOne(ssi);
				if (obj != null) {
					ssi = (SysSystemInfo) obj;
				}
				sfi.setSysCode(stable.getSysCode());
				sfi.setDbuserName(ssi.getDbuserName());
				sfi.setDbName(ssi.getDbName());
				sfi.setDbType(ssi.getDbType());
				sfi.setCreateDatetime(new Date());
				sfi.setSeqstr("seq_id");
				sfi.setTableCode(CreateCodeUtil.getTableNo());
			}
		}
		return sfi;
	}

	public SysRetrunMessage saveOrUpdateTable(SysTableInfo stable)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (stable.getStableId() != null) {
				this.updateObject(stable);
			} else {
				srm = this.saveTable(stable);
			}
		} catch (Exception e) {
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public SysRetrunMessage questNodeTable(SysTableInfo stable)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		List<TreeNode> list_back = new ArrayList<TreeNode>();
		List<SysTableInfo> list_stb;
		try {
			list_stb = this.getList(stable);

			if (list_stb != null && list_stb.size() > 0) {
				for (SysTableInfo did : list_stb) {
					TreeNode row = new TreeNode();
					row.setId(did.getTableName());
					row.setText(AutoCodeUtils.toCapitalizeCamelCase(did.getTableName()));
					row.setName(did.getTableName() + "(" + did.getTableNode()
							+ ")");
					row.setAttributes(did);
					list_back.add(row);
				}
			}
			srm.setObj(list_back);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}

		return srm;
	}

	@Override
	public SysRetrunMessage saveTableList(String sysCode, String ifPojo,
			String ifDao, String ifService, List<FMPojoInfo> fmp)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if (fmp != null && fmp.size() > 0) {
			// 获取系统编号

			SysSystemInfo ssi = new SysSystemInfo();
			List<SysClassInfo> listsci = new ArrayList<SysClassInfo>();
			if (sysCode == null) {
				srm.setCode(0l);
				srm.setMeg("系统编号为空！");
				return srm;
			} else {
				ssi.setSysCode(sysCode);
				Object obj = this.getOne(ssi);
				if (obj != null) {
					ssi = (SysSystemInfo) obj;
				}
				String dbName = ssi.getDbName();
				for (FMPojoInfo did : fmp) {
					SysTableInfo stable = new SysTableInfo();
					stable.setSysCode(sysCode);
					SysTableInfo sti = this.questTable(stable);
					sti.setTableName(did.getTableName());
					sti.setTableNode(did.getCommentText());
					sti.setPkName(did.getPkName());
					sti.setDbName(dbName);
					
					this.saveTable(sti);
					if ("1".equals(ifPojo)) {
						listsci.add(this.createPojo(sti, sysCode));
					}
					if ("1".equals(ifDao)) {
						listsci.add(this.createDao(sti, sysCode));
					}
					if ("1".equals(ifService)) {
						listsci.add(this.createServiceObj(sti, sysCode,
								did.getServiceName()));
					}
				}
				if(listsci!=null&&listsci.size()>0){
					codeAutoService.setRequest(this.getRequest());
				 srm=	codeAutoService.createClassListFile(listsci,null);
				}
			}
		} 
		return srm;
	}

	String axt = "admin";
	String vis = "v1.0";

	private SysClassInfo createPojo(SysTableInfo stable, String sysCode)
			throws HSKException {
		SysClassInfo att_sci = new SysClassInfo();
		att_sci.setSysCode(sysCode);
		att_sci.setSClassType("pojo");

		SysCodeInfo att_code = new SysCodeInfo();

		SysClassInfo sci_init = codeAutoService.getsysClass(att_sci);
		att_code.setSysClassInfo(sci_init);

		List<SysClassColumns> list_sysClassColumns = new ArrayList<SysClassColumns>();
		sci_init.setAuthortext(this.axt);
		sci_init.setVersiontext(this.vis);
		sci_init.setTableCode(stable.getTableCode());
		sci_init.setTableName(stable.getTableName());
		sci_init.setTableNode(stable.getTableNode());
		sci_init.setSClassComment(stable.getTableNode());
		sci_init.setSClassName(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()));
		sci_init.setDbType(stable.getDbType());
		sci_init.setSeqstr(stable.getSeqstr());
		sci_init.setPkName(stable.getPkName());
		SysRetrunMessage srm = codeAutoService.initClassColumns(sci_init);
		if ("1".equals(srm.getCode().toString())) {
			list_sysClassColumns = (List<SysClassColumns>) srm.getObj();
			att_code.setList_sysClassColumns(list_sysClassColumns);
			codeAutoService.saveOrUpdatesysClass(att_code);

		}
		return sci_init;
	}

	private SysClassInfo createDao(SysTableInfo stable, String sysCode)
			throws HSKException {
		SysClassInfo att_sci = new SysClassInfo();
		att_sci.setSysCode(sysCode);
		att_sci.setSClassType("dao");

		SysCodeInfo att_code = new SysCodeInfo();
		SysClassInfo sci_init = codeAutoService.getsysClass(att_sci);
		att_code.setSysClassInfo(sci_init);
		List<SysClassColumns> list_sysClassColumns = new ArrayList<SysClassColumns>();
		sci_init.setAuthortext(this.axt);
		sci_init.setVersiontext(this.vis);
		sci_init.setTableCode(stable.getTableCode());
		sci_init.setTableName(stable.getTableName());
		sci_init.setTableNode(stable.getTableNode());
		sci_init.setSClassComment(stable.getTableNode());
		sci_init.setSClassComment2(stable.getTableNode());
		sci_init.setSClassComment3(stable.getTableNode());

		sci_init.setSClassName(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()) + "Dao");
		sci_init.setSClassName2("I"
				+ AutoCodeUtils.toCapitalizeCamelCase(stable.getTableName())
				+ "Dao");

		sci_init.setSClassName3(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()));
		sci_init.setDbType(stable.getDbType());
		sci_init.setSeqstr(stable.getSeqstr());
		sci_init.setPkName(stable.getPkName());

		SysRetrunMessage srm = codeAutoService.initClassColumns(sci_init);
		if ("1".equals(srm.getCode().toString())) {
			list_sysClassColumns = (List<SysClassColumns>) srm.getObj();
			att_code.setList_sysClassColumns(list_sysClassColumns);
			codeAutoService.saveOrUpdatesysClass(att_code);
		}
		return sci_init;
	}

	public SysClassInfo createServiceObj(SysTableInfo stable, String sysCode,
			String serviceName) throws HSKException {
		SysClassInfo att_sci = new SysClassInfo();
		att_sci.setSysCode(sysCode);
		att_sci.setSClassType("service");

		SysCodeInfo att_code = new SysCodeInfo();
		SysClassInfo sci_init = codeAutoService.getsysClass(att_sci);
		att_code.setSysClassInfo(sci_init);
		List<SysClassColumns> list_sysClassColumns = new ArrayList<SysClassColumns>();

		sci_init.setAuthortext(this.axt);
		sci_init.setVersiontext(this.vis);
		sci_init.setTableCode(stable.getTableCode());
		sci_init.setTableName(stable.getTableName());
		sci_init.setTableNode(stable.getTableNode());

		sci_init.setSClassComment(stable.getTableNode());
		sci_init.setSClassComment2(stable.getTableNode());
		sci_init.setSClassComment3(stable.getTableNode());
		sci_init.setSClassComment4(stable.getTableNode() + "Dao接口");

		sci_init.setSClassName(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()) + "Service");
		sci_init.setSClassName2("I"
				+ AutoCodeUtils.toCapitalizeCamelCase(stable.getTableName())
				+ "Service");

		sci_init.setSClassName3(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()));
		sci_init.setSClassName4("I"
				+ AutoCodeUtils.toCapitalizeCamelCase(stable.getTableName())
				+ "Dao");

		sci_init.setServicename(serviceName);

		sci_init.setDbType(stable.getDbType());
		sci_init.setSeqstr(stable.getSeqstr());
		sci_init.setPkName(stable.getPkName());
		att_code.setList_sysClassColumns(list_sysClassColumns);
		codeAutoService.saveOrUpdatesysClass(att_code);
		return sci_init;
	}

}