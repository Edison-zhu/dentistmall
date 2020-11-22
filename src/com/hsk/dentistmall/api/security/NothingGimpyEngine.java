package com.hsk.dentistmall.api.security;

import java.awt.image.BufferedImage;

import com.google.code.kaptcha.GimpyEngine;

public class NothingGimpyEngine implements GimpyEngine {

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.GimpyEngine#getDistortedImage(java.awt.image.BufferedImage)
	 */
	public BufferedImage getDistortedImage(BufferedImage baseImage) {
		// TODO Auto-generated method stub
		return baseImage;
	}
}