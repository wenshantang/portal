package cn.aresoft.cms.portal.service.impl;
import java.util.ArrayList;
import java.util.List;

import cn.aresoft.cms.portal.model.ProductInfo;
import cn.aresoft.cms.portal.service.ProductInfoService;
import cn.aresoft.common.model.CommonParam;
import cn.aresoft.common.service.impl.CenterServiceImpl;

import com.puff.framework.annotation.Bean;
import com.puff.framework.utils.StringUtil;
import com.puff.jdbc.core.PageRecord;
import com.puff.jdbc.core.SqlBuilder;
@Bean(id = "productInfoService")
public class ProductInfoServiceImpl extends CenterServiceImpl<ProductInfo>implements ProductInfoService {
	@Override
	public PageRecord<ProductInfo>  paging(ProductInfo p, CommonParam param) {
		StringBuffer sql = new StringBuffer(SqlBuilder.buildQuerySQL(ProductInfo.class));
		List<Object> condition = new ArrayList<Object>();
		sql.append(" where 1=1 ");
		if(StringUtil.notBlank(p.getCode())){
			sql.append(" and  code =? ");
			condition.add(p.getCode());
		}
		if(StringUtil.notBlank(p.getName())){
			sql.append(" and  name=? ");
			condition.add(p.getName());
		}
		if(StringUtil.notBlank(p.getType())){
			sql.append(" and  type =? ");
			condition.add(p.getType());
		}
		if(StringUtil.notBlank(p.getStatus())){
			sql.append(" and  status =? ");
			condition.add(p.getStatus());
		}
		
		if(StringUtil.notBlank(p.getIsHot())){
			sql.append(" and   isHot =? ");
			condition.add(p.getIsHot());
		}
		sql.append("  order by  sort_num desc, public_time desc");
		return paging(sql.toString(), condition,param);
	}

	@Override
	public ProductInfo queryByCode(String code) {
		String sql ="select * from tb_product_info where code=?";
		return query(sql, code);
	}

	@Override
	public List<ProductInfo> products() {
		String sql ="select * from tb_product_info ";
		return queryList(sql);
	}
}

