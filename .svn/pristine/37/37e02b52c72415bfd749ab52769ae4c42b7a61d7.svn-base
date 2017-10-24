package cn.aresoft.cms.portal.fhcf;

import java.util.List;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

import cm.aresoft.cms.portal.plugin.TemplateContentSync;
import cm.aresoft.cms.portal.start.StartPortal;
import cn.aresoft.cms.common.cache.CmsArticleCache;
import cn.aresoft.cms.common.cache.CmsBannerCache;
import cn.aresoft.cms.common.cache.CmsModelCache;
import cn.aresoft.cms.common.cache.CmsPropertiesCache;
import cn.aresoft.cms.common.cache.CmsSiteConfigCache;
import cn.aresoft.cms.common.cache.CmsTemplateCache;
import cn.aresoft.cms.common.cache.CmsTopicCache;
import cn.aresoft.cms.common.model.CmsArticle;
import cn.aresoft.cms.common.model.CmsModel;
import cn.aresoft.cms.common.model.CmsTemplate;
import cn.aresoft.cms.common.model.CmsTopic;
import cn.aresoft.cms.common.model.config.CmsSiteConfig;
import cn.aresoft.cms.common.service.CmsArticleService;
import cn.aresoft.cms.common.service.CmsModelService;
import cn.aresoft.cms.common.service.CmsSiteConfigService;
import cn.aresoft.cms.common.service.CmsTemplateService;
import cn.aresoft.cms.common.service.CmsTopicService;
import cn.aresoft.cms.portal.fhcf.tag.FundNavInfoTag;
import cn.aresoft.cms.portal.fhcf.tag.FundNavTag;
import cn.aresoft.cms.portal.fhcf.tag.H5ProductListTag;
import cn.aresoft.cms.portal.fhcf.tag.H5TopicArticleTag;
import cn.aresoft.cms.portal.fhcf.tag.ProductArticleTag;
import cn.aresoft.cms.portal.fhcf.tag.ProductInfoTag;
import cn.aresoft.cms.portal.fhcf.tag.ProductListTag;
import cn.aresoft.cms.portal.fhcf.tag.ProductReportTag;
import cn.aresoft.cms.portal.fhcf.tag.SysDictTag;
import cn.aresoft.cms.portal.fhcf.tag.TokenTag;

import com.puff.framework.utils.ListUtil;
import com.puff.ioc.BeanFactory;

public class StartCD implements StartPortal {

	@Override
	public void initSiteConfigCache(CmsSiteConfigCache cache, CmsSiteConfigService service) {
		List<CmsSiteConfig> sites = service.queryList();
		for (CmsSiteConfig cmsSiteConfig : sites) {
			cache.cache(cmsSiteConfig.getSite_id(), cmsSiteConfig);
		}
	}

	@Override
	public void initTopicCache(CmsTopicCache cache, CmsTopicService service) {
		List<CmsTopic> topics = service.queryList();
		for (CmsTopic cmsTopic : topics) {
			cache.cache(cmsTopic.getLocation(), cmsTopic);
		}
	}

	@Override
	public void initTemplateCache(final CmsTemplateCache cache, final CmsTemplateService service) {
		final int size = 20;
		long totalCount = service.countTemplate();
		int totalPage = 0;
		if (totalCount % size == 0) {
			totalPage = (int) (totalCount / size);
		} else {
			totalPage = (int) (totalCount / size + 1);
		}
		for (int page = 1; page <= totalPage; page++) {
			final int p = page;
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					List<CmsTemplate> list = service.queryListPage(p, size);
					if (ListUtil.notEmpty(list)) {
						for (CmsTemplate cmsTemplate : list) {
							cache.cache(cmsTemplate.getId(), cmsTemplate);
							cache.cache(cmsTemplate.getPath(), cmsTemplate);
						}
					}
				}
			});
			t.start();
		}

	}

	@Override
	public void initArticleCache(CmsArticleCache cache, CmsArticleService cmsArticleService) {
		List<CmsArticle> list = cmsArticleService.queryList();
		for (CmsArticle cmsArticle : list) {
			cache.cache(cmsArticle.getId(), cmsArticle);
		}
	}

	@Override
	public void initOther() {
		CmsModelService cmsModelService = BeanFactory.getBean("cmsModelService");
		CmsModelCache cmsModelCache = BeanFactory.getBean("cmsModelCache");
		List<CmsModel> models = cmsModelService.queryList();
		if (ListUtil.notEmpty(models)) {
			for (CmsModel cmsModel : models) {
				cmsModelCache.cache(cmsModel.getCode(), cmsModel);
			}
		}
		CmsBannerCache cmsBannerCache = BeanFactory.getBean("cmsBannerCache");
		cmsBannerCache.start();
		TemplateContentSync.getInstance();
	}

	@Override
	public void registerTag(GroupTemplate groupTemplate) {
		// 系统字典列表
		groupTemplate.registerTag("sysdict_list", SysDictTag.class);
		// 表单重复提交校验
		groupTemplate.registerTag("token", TokenTag.class);
		//与产品关联的文章
		groupTemplate.registerTag("pro_articles", ProductArticleTag.class);
		//产品列表
		groupTemplate.registerTag("pro_Lists", ProductListTag.class);
		//h5产品列表
		groupTemplate.registerTag("h5_pro_Lists", H5ProductListTag.class);
		//h5文章
		groupTemplate.registerTag("h5_topic_article", H5TopicArticleTag.class);
		//基金净值列表
		groupTemplate.registerTag("nav_Lists", FundNavTag.class);
		//产品详情页
		groupTemplate.registerTag("product", ProductInfoTag.class);
		//产品净值详情(用于产品详情页的基金净值以及今年以来的净值)
		groupTemplate.registerTag("navs", FundNavInfoTag.class);
		//产品报告列表
		groupTemplate.registerTag("reports", ProductReportTag.class);
	}

	@Override
	public void registerFun(GroupTemplate groupTemplate) {
	}

	@Override
	public void bindCommonParam(Template template) {
		CmsPropertiesCache cmsPropertiesCache = BeanFactory.getBean("cmsPropertiesCache");
		String version = cmsPropertiesCache.findValueFormCache("PORTAL_STATIC_CONFIG", "version");
		template.binding("version", version);
		String static_ctx = cmsPropertiesCache.findValueFormCache("PORTAL_STATIC_CONFIG", "static_ctx");
		template.binding("static_ctx", static_ctx);
	}
}
