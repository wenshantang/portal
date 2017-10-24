package cn.aresoft.cms.portal.fhcf.controller.web;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.aresoft.cms.portal.fhcf.util.DoubleUtil;
import cn.aresoft.common.model.RetMsg;
import cn.aresoft.fhcf.common.cache.FundNavCache;
import cn.aresoft.fhcf.common.model.product.FundNav;
import cn.aresoft.fhcf.common.service.FundNavService;

import com.puff.framework.annotation.Controller;
import com.puff.framework.annotation.Inject;
import com.puff.framework.utils.StringUtil;
import com.puff.jdbc.core.Record;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;
@Controller(value = "/fundnav")
public class FundNavController{
	@Inject 
	private FundNavService fundNavService;
	@Inject
	private FundNavCache fundNavCache;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
    public View fundchart() {
			String procode = PuffContext.getParameter("procode"); //基金代码
			String date_condition = PuffContext.getParameter("date_condition"); //时间条件自定义时间
			List<FundNav> list =getFundnavList(procode, date_condition);
			return ViewFactory.json(RetMsg.success(list));
	}

    //单位净值增长率
    public View fundchart_rate() {
		String procode = PuffContext.getParameter("procode"); //基金代码
		String date_condition = PuffContext.getParameter("date_condition"); //时间条件自定义时间
		List<FundNav> list =getFundnavList(procode, date_condition);
		List<Record> rlist=new ArrayList<Record>();
		for(int i=list.size()-1;i>=0;i--){
			if((i-1)>=0){
				Record r=new Record();
				double c=DoubleUtil.mul(100,DoubleUtil.div(DoubleUtil.sub(list.get(i).getNetvalue(), list.get(i-1).getNetvalue()),list.get(i-1).getNetvalue()));
				r.set("rate", c);
				r.set("date", list.get(i).getTradeDate());
				rlist.add(r);
			}
		}
		return ViewFactory.json(RetMsg.success(rlist));
    }
    
	public List<FundNav> getFundnavList(String procode,String date_condition){
		String starttime =""; //开始时间
		String endtime = ""; //结束时间
		//date_condition:1 一月 2 三月 3一年 4今年以来 
		if(StringUtil.empty(date_condition)||date_condition.equals("1")){//默认为近三个月
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);//近一月
			starttime = sdf.format(c.getTime());
		}
		if (date_condition!=null&&date_condition.trim().length()==1) {
			if(date_condition.equals("2"))
			{
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, -3);
				starttime = sdf.format(c.getTime());
			}
			if(date_condition.equals("3"))
			{
				Calendar c = Calendar.getInstance();
				c.add(Calendar.YEAR, -1);
				starttime = sdf.format(c.getTime());
			}
			if(date_condition.equals("4"))
			{
				Calendar currCal=Calendar.getInstance();  
				Calendar c = Calendar.getInstance();
				c.clear();
				c.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
				starttime = sdf.format(c.getTime());
			}
		}
		if(endtime==null||endtime.trim().length()<=0){//默认为昨天
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			endtime = sdf.format(c.getTime());
		}
		//净值列表
		List<FundNav> list =fundNavCache.get("fundnav_"+date_condition+"_"+procode);//获取缓存
		if(list==null){
			list =fundNavService.fundchart(procode, starttime,endtime);
			if(list.size()>0){
				fundNavCache.cache("fundnav_"+date_condition+"_"+procode,list);//存缓存
			}
		}
		return list;
	}
}

