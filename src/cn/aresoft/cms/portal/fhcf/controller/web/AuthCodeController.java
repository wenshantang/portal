package cn.aresoft.cms.portal.fhcf.controller.web;

import java.util.Random;

import com.puff.framework.annotation.BeanScope;
import com.puff.framework.annotation.Controller;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;
/**
 * 生成图片验证码
 */
@Controller(value = "/portal/authcode", scope = BeanScope.SINGLETON)
public class AuthCodeController {
	private char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private final Random random = new Random();

	private String getRandom(int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}
		return sb.toString();
	}

	public View index() {
		return ViewFactory.authCode(getRandom(4), "authCode");
	}

	/*
	 * public View qrcode(String phone) throws Exception { // 生成二维码 String text
	 * = "https://www.werich.com.cn/wx/register/" + phone; int width = 430; int
	 * height = 430; String format = "gif"; HashMap<EncodeHintType, String>
	 * hints = new HashMap<EncodeHintType, String>();
	 * hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); BitMatrix bitMatrix =
	 * new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width,
	 * height, hints); ServletOutputStream out =
	 * PuffContext.getResponse().getOutputStream();
	 * MatrixToImageWriter.writeToStream(bitMatrix, format, out); out.close();
	 * return ViewFactory.nullView(); }
	 */
}
