package com.hsk.xframe.web.webSite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.hsk.exception.HSKException;

/**
 *  
 * 
 * @author x_nam
 * 
 */
public interface IKindeditorController {
	/**
	 *  
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws HSKException
	 */
	public String file_manager_json(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws HSKException;

	/**
	 *  
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws HSKException
	 */
	public String upload_json(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws HSKException;

}
