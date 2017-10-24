package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.puff.framework.annotation.BeanScope;
import com.puff.framework.annotation.Controller;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;

import cn.aresoft.common.model.RetMsg;
import sun.misc.BASE64Encoder;

/**
 * 发送验证码短信
 * 
 * @author DC
 *
 */


@Controller(value = "/pd", scope = BeanScope.SINGLETON)
public class demoController {
	
	
	public View procudt() {
		return ViewFactory.file("理财Vip预约列表.xls");
		 
	}
	
	public View liulan(){
		String pdid=PuffContext.getParameter("pdid");
		Map<String,MyObject> db=DB.getAll();
		MyObject mob=db.get(pdid);
		PuffContext.setSessionAttribute("myproduct", mob);
		String cookike=buildCookie(pdid);
		PuffContext.addCookie("historyCookie", cookike, 24*3600*365*10);
		return ViewFactory.redirect("/demo/product");
	}
	 
	private String buildCookie(String id) {
		
		String historyCookie = null;
		HttpServletRequest request=PuffContext.getRequest();
		//得到请求中带来的cookie值
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++){
			if (cookies[i].getName().equals("historyCookie") ){
				historyCookie = cookies[i].getValue();
			}
		}
		
		//如果为空返回当前商品的id
		if (historyCookie == null){
			return id;
		}
		
		LinkedList<String> list = new LinkedList<String>( Arrays.asList((historyCookie.split("\\,"))));
		
		//对不同的情况进行分析返回id的值
		if (list.contains(id)){
			list.remove(id);
		}else{
			if (list.size() >= 4){
				list.removeLast();
			}
		}
		list.addFirst(id);
		
		StringBuffer sb = new StringBuffer();
		for (String sid : list){
			sb.append(sid + ",");
		}
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}
	 
	 
	 public View down(){
			HttpServletRequest request=PuffContext.getRequest();
			HttpServletResponse response=PuffContext.getResponse();
	    	  
	    		Map dataMap = new HashMap(); 
	    		if (getData(response,request,dataMap)) { 
	    		File previewFile = new File(request.getSession().getServletContext().getRealPath(TempltUtil.PREVIEW_DOC)); 
	    		InputStream is;
				try {
					is = new FileInputStream(previewFile);
					return ViewFactory.file("wenjian.doc", is);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ViewFactory.json(RetMsg.error("下载失败"));
				} 
	    	
	    		 
	    		} 
	    		 
	    		return ViewFactory.json(RetMsg.error("下载失败"));
	    	
	    	
	 }
	 private boolean getData(HttpServletResponse response,HttpServletRequest request,Map dataMap) {  
   	  
	        dataMap.put("name", "李昌丽");  
	    
	        dataMap.put("pic", getImageStr());  
	        
	        TempltUtil.toPreview(request, TempltUtil.WORD_TEMPLATE, dataMap); 
	        return true;
	    
	     }
	 
	    private String getImageStr() {
	        String imgFile = "d:/11.jpg";
	        InputStream in = null;
	        byte[] data = null;
	        try {
	          in = new FileInputStream(imgFile);
	          data = new byte[in.available()];
	          in.read(data);
	          in.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	        BASE64Encoder encoder = new BASE64Encoder();
	        return encoder.encode(data);
	      }
}
