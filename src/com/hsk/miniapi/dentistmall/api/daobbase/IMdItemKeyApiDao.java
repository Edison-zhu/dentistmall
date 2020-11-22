package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.hsk.dentistmall.api.persistence.MdItemKey;
import com.hsk.dentistmall.api.persistence.MdItemMxView;
import com.hsk.exception.HSKDBException;

/**
 * 库存合并信息DAO
 * @author Administrator
 *
 */
public interface IMdItemKeyApiDao {
	/**
	 * 增加信息
	 * @param mdItemKey
	 * @return
	 * @throws HSKDBException
	 */
	public MdItemMxView newMdItemKey(MdItemMxView mdItemMxView) throws HSKDBException, BadHanyuPinyinOutputFormatCombination;
	/**
	 * 获取MdItemKey对象
	 * @param mdItemKey
	 * @return
	 * @throws HSKDBException
	 */
	public MdItemMxView getMdItemKeyView(MdItemMxView mdItemMxView) throws HSKDBException;
}
