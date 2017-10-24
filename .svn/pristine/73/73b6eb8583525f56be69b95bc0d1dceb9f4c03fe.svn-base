package cn.aresoft.cms.portal.fhcf.tag;
import cn.aresoft.common.model.CommonParam;
import cn.aresoft.fhcf.common.cache.ProductListCache;
import cn.aresoft.fhcf.common.model.product.ProductInfo;
import cn.aresoft.fhcf.common.service.ProductInfoService;

import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;
public class H5ProductListTag extends BaseTag {
	private ProductInfoService productInfoService = BeanFactory.getBean("productInfoService");
	private ProductListCache productListCache = BeanFactory.getBean("productListCache");
	@Override
	public void render() {
		String pro_style = getAttr("pro_style", "");//'产品类型：固定、浮动、海外',
		String pro_direction = getAttr("pro_direction", "");//产品风向，是用于钱码头产品的分类
		String i_prostatus = getAttr("i_prostatus", "");//开放状态：1：在售 2：即将开放 3：已售罄 
		String nowpage = getAttr("page", "1");//当前页
		String row = getAttr("row","3");//当前行数
    	ProductInfo  p = new ProductInfo();
    	p.setPro_style(pro_style);
    	p.setPro_direction(pro_direction);
    	p.setI_prostatus(i_prostatus);
    	CommonParam param=new CommonParam();
    	if(Integer.parseInt(nowpage)>1){
    		param.setRows(Integer.parseInt(nowpage)*Integer.parseInt(row));
    	}else{
    		param.setRows(Integer.parseInt(row));
    	}
    	param.setPage(1);
    	PageRecord<ProductInfo> datas = productListCache.get("prolist_"+pro_style+"_"+pro_direction+"_"+i_prostatus+"_"+param.getPage()+"_"+param.getRows());
    	if(datas==null){
    		datas=productInfoService.paging(p,param);
    		if(datas.getDataList().size()>0){
    			productListCache.cache("prolist_"+pro_style+"_"+pro_direction+"_"+i_prostatus+"_"+param.getPage()+"_"+param.getRows(), datas);
    		}
    	}
    	int nowtotalpage=0;
    	if (datas.getTotalCount()%Integer.parseInt(row) == 0) {
    		nowtotalpage = datas.getTotalCount()/Integer.parseInt(row);
		} else {
			nowtotalpage = datas.getTotalCount()/Integer.parseInt(row) + 1;
		}
    	this.binds(datas,nowpage,nowtotalpage);
    	this.doBodyRender();
    	}
}