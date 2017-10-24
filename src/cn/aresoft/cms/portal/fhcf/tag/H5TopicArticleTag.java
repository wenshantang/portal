package cn.aresoft.cms.portal.fhcf.tag;

import cm.aresoft.cms.portal.plugin.htmltag.BaseTag;
import cn.aresoft.cms.common.cache.CmsTopicArticleCache;
import cn.aresoft.cms.common.model.CmsArticle;
import cn.aresoft.common.model.CommonParam;

import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;

public class H5TopicArticleTag extends BaseTag {
	private CmsTopicArticleCache cmsTopicArticleCache = BeanFactory.getBean("cmsTopicArticleCache");

	@Override
	public void render() {
		String topicCode = getAttr("topicCode");
		String nowpage = getAttr("page", "1");//当前页
		String row = getAttr("row","3");//当前行数
		CommonParam param=new CommonParam();
		if(Integer.parseInt(nowpage)>1){
    		param.setRows(Integer.parseInt(nowpage)*Integer.parseInt(row));
    	}else{
    		param.setRows(Integer.parseInt(row));
    	}
		param.setPage(1);
//		PageRecord<CmsArticle> articles = cmsTopicArticleCache.get("h5articles_"+topicCode+param.getPage()+param.getRows());
//		if(articles == null){
//			articles = cmsTopicArticleCache.getArticleByTopic(topicCode, param.getPage(), param.getRows());
//			if(articles.getDataList().size()>0){
//				cmsTopicArticleCache.cache("h5articles_"+topicCode+param.getPage()+param.getRows(), articles);
//			}
//		}
		PageRecord<CmsArticle> articles = cmsTopicArticleCache.getArticleByTopic(topicCode, param.getPage(), param.getRows());
		
		int nowtotalpage=0;
    	if (articles.getTotalCount()%Integer.parseInt(row) == 0) {
    		nowtotalpage = articles.getTotalCount()/Integer.parseInt(row);
		} else {
			nowtotalpage = articles.getTotalCount()/Integer.parseInt(row) + 1;
		}
    	this.binds(articles,nowpage,nowtotalpage);
    	this.doBodyRender();
	}

}
