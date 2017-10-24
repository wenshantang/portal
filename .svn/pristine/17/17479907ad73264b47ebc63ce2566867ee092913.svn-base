package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import cn.aresoft.cms.portal.fhcf.tag.BaseTag;

public class CookTag extends BaseTag{

	@Override
	public void render() {
		Map<String, MyObject> map = DB.getAll();
		Cookie[] cookies= getRequest().getCookies();
		List<MyObject> objlist=new ArrayList<MyObject>();
		for (int i = 0; cookies != null && i < cookies.length; i++){
			
			//找到我们想要的cookie
			if (cookies[i].getName().equals("historyCookie")){
				String[] ids = cookies[i].getValue().split("\\,");
				 
				for (String id : ids){
					objlist.add(map.get(id));
				}
			}
		}
		this.binds(objlist);
		this.doBodyRender();
	}

}
