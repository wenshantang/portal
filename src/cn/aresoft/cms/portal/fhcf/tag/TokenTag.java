package cn.aresoft.cms.portal.fhcf.tag;

import java.io.IOException;

import com.puff.framework.utils.IdentityUtil;

public class TokenTag extends BaseTag {

	@Override
	public void render() {
		String token = IdentityUtil.uuid32();
		getRequest().getSession().setAttribute("server_token", token);
		String html = "<input type=\"hidden\" id=\"puff_beetl_client_token\" name=\"puff_beetl_client_token\" autocomplete=\"off\" value=\"" + token + "\" />";
		try {
			ctx.byteWriter.writeString(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
