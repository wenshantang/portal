package cn.aresoft.cms.portal.fhcf.tag;
import cn.aresoft.cms.common.model.CmsArticle;
import cn.aresoft.cms.common.service.CmsArticleService;
import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;
public class ProductArticleTag extends BaseTag {
	private CmsArticleService cmsArticleService = BeanFactory.getBean("cmsArticleService");
	@Override
	public void render() {
		String type = getAttr("type", "");//文章类型
		String topicId = getAttr("topicId", "888888888888888888");//所有栏目下的文章
    	CmsArticle  article = new CmsArticle();
    	article.setType_id(type);
    	PageRecord<CmsArticle> articles = cmsArticleService.queryTopicArticle(topicId, article, getCommonParam());
    	this.binds(articles);
    	this.doBodyRender();
    	}
}