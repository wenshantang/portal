package cn.aresoft.cms.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.puff.framework.annotation.Bean;
import com.puff.framework.utils.StringUtil;
import com.puff.jdbc.core.PageRecord;
import com.puff.jdbc.core.SqlBuilder;

import cn.aresoft.cms.portal.model.Recruit;
import cn.aresoft.cms.portal.model.Umsg;
import cn.aresoft.cms.portal.service.RecruitService;
import cn.aresoft.common.model.CommonParam;
import cn.aresoft.common.service.impl.CenterServiceImpl;

@Bean(id = "recruitService")
public class RecruitServiceImpl extends CenterServiceImpl<Recruit>implements RecruitService {
	@Override
	public PageRecord<Recruit> paging(Recruit r, CommonParam param) {
		StringBuffer sql = new StringBuffer(SqlBuilder.buildQuerySQL(Umsg.class));
		List<Object> condition = new ArrayList<Object>();
		sql.append(" where 1=1 ");
		if(StringUtil.empty(param.getSort())){
			sql.append(" order by  create_time desc ");
		}
		return paging(sql.toString(), condition, param);
	}
}

