package cn.aresoft.cms.portal.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.puff.framework.annotation.BeanScope;
import com.puff.framework.annotation.Before;
import com.puff.framework.annotation.Controller;
import com.puff.framework.annotation.Inject;
import com.puff.framework.utils.DateTime;
import com.puff.framework.utils.IdentityUtil;
import com.puff.framework.utils.ListUtil;
import com.puff.framework.utils.PathUtil;
import com.puff.framework.utils.StringUtil;
import com.puff.log.Log;
import com.puff.log.LogFactory;
import com.puff.web.fileupload.FileUpload;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;

import cn.aresof.ftp.FtpClient;
import cn.aresof.ftp.model.FtpServer;
import cn.aresof.ftp.service.FtpServerService;
import cn.aresoft.cms.portal.accessIntercept.FormSubmitTokenInterceptor;
import cn.aresoft.cms.portal.model.Recruit;
import cn.aresoft.cms.portal.service.RecruitService;
import cn.aresoft.cms.portal.service.UmsgService;
import cn.aresoft.common.model.RetCode;
import cn.aresoft.common.model.RetMsg;
import cn.aresoft.manager.model.sys.SysFile;
import cn.aresoft.manager.model.sys.SysManagerConfig;
import cn.aresoft.manager.model.sys.Upload;
import cn.aresoft.manager.model.sys.UploadConfig;
import cn.aresoft.manager.service.sys.SysFileService;
import cn.aresoft.manager.service.sys.SysManagerConfigService;
/**
 * 应聘功能
 */
@Controller(value = "/portal/recruit", scope = BeanScope.SINGLETON)
public class RecruitController {
	@Inject 
	private UmsgService umsgService;
    private static final Log log = LogFactory.get();
    @Inject
    private SysFileService sysFileService;
    @Inject
    private FtpServerService ftpServerService;
    @Inject
    private SysManagerConfigService sysManagerConfigService;	
    @Inject
    private RecruitService recruitService;    
	public View add() {
		String messagesTitle=PuffContext.getParameter("messagesTitle");//应聘岗位
		String content=PuffContext.getParameter("content");//申请描述
		String author=PuffContext.getParameter("author");//申请人
		String phone=PuffContext.getParameter("phone");//联系电话
		String address=PuffContext.getParameter("address");//联系地址
		String sex=PuffContext.getParameter("sex");//性别
		String file_url=PuffContext.getParameter("file_url");//文件附件
		if(StringUtil.empty(messagesTitle)){
			return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM,"应聘岗位不能为空!"));
		}
        if(StringUtil.empty(phone)){
        	return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM, "手机号码不能为空!"));
		}
        if(StringUtil.empty(content)){
            return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM,"申请描述不能为空!"));
        }
        if(StringUtil.empty(author)){
            return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM, "申请人不能为空!"));
        }
        if(StringUtil.empty(address)){
            return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM,"联系地址不能为空!"));
        }
        if(StringUtil.empty(sex)){
            return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM, "性别不能为空!"));
        }
        if(StringUtil.empty(file_url)){
            return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM,"文件附件不能为空!"));
        }
        Recruit u = new Recruit();
        u.setId(IdentityUtil.uuid16());
		u.setMessagesTitle(messagesTitle);
		u.setContent(content);
		u.setAuthor(author);
		u.setPhone(phone);
		u.setAddress(address);
		u.setSex(sex);
		u.setFile_url(file_url);
		u.setCreate_time(DateTime.currentTimeStamp());
		recruitService.insert(u);
		return ViewFactory.json(RetMsg.success("新增成功！"));
	}
	   private Map<String, Object> getError(String message) {
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("error", 1);
	        map.put("message", message);
	        return map;
	    }
	public View upload() {
	        FileUpload file = PuffContext.getFile("file");
	        if (file == null)
	            return ViewFactory.json(getError("请选择文件"));
	        SysManagerConfig managerConfig = sysManagerConfigService.querySysManagerConfig();
	        UploadConfig uploadConfig = managerConfig.getUploadConfig();
	        String dirName = PuffContext.getParameter("dir");
	        if (StringUtil.empty(dirName)) {
	            String suffixName = file.getSuffixName();
	            Map<String, Upload> upload_setting = uploadConfig.getUpload_setting();
	            if (upload_setting != null) {
	                for (Entry<String, Upload> entry : upload_setting.entrySet()) {
	                    Upload upload = entry.getValue();
	                    if (upload != null) {
	                        List<String> suffix = upload.getSuffix();
	                        if (!ListUtil.empty(suffix)) {
	                            if (suffix.contains(suffixName)) {
	                                dirName = entry.getKey();
	                                break;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        if (!uploadConfig.getUpload_setting().containsKey(dirName)) {
	            return ViewFactory.json(getError("不允许上传该类型文件!"));
	        }
	        Upload upload = uploadConfig.getUpload_setting().get(dirName);
	        // 最大文件大小
	        Long maxSize = upload.getMaxSize();
	        // 检查文件大小
	        if (maxSize != null && maxSize != 0L && file.getFile().length() > maxSize * 1024)
	            return ViewFactory.json(getError("上传文件大小超过限制。"));

	        if (!upload.getSuffix().contains(file.getSuffixName()))
	            return ViewFactory.json(getError("上传文件扩展名是不允许的扩展名。\n只允许" + ListUtil.list2Str(upload.getSuffix()) + "格式。"));

	        String savePath = PathUtil.getWebRootPath() + "/" + uploadConfig.getSave_dir();
	        String filePath = "";
	        // 文件保存方式 1/yyyMMdd/file 2yyyyMM/dd/file
	        String save_type = uploadConfig.getSave_type();
	        if ("1".equals(save_type)) {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	            String ymd = sdf.format(new Date());
	            filePath = "/" + ymd;
	        } else if ("2".equals(save_type)) {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM/dd");
	            String ymd = sdf.format(new Date());
	            filePath = "/" + ymd;
	        }

	        String ftpFileUrl = (uploadConfig.getSave_dir() + "/" + dirName + "/" + filePath).replace("//", "/");
	        String ftpFilePath = (uploadConfig.getSave_dir() + "/" + dirName + "/" + filePath).replace("//", "/").replace("/", String.valueOf(File.separatorChar));

	        String fullPath = savePath + "/" + dirName + "/" + filePath;
	        File saveDirFile = new File(fullPath);
	        if (!saveDirFile.exists())
	            saveDirFile.mkdirs();
	        String newFileName = IdentityUtil.uuid16() + "." + file.getSuffixName();
	        fullPath = (fullPath + "/" + newFileName).replace("//", "/").replace("/", String.valueOf(File.separatorChar));
	        String http_url = "";
	        try {
	            File uploadedFile = new File(fullPath);
	            file.save(uploadedFile);
	            List<String> servers = upload.getFtpServers();
	            if (!ListUtil.empty(servers)) {
	                for (String ftpId : servers) {
	                    FtpServer ftpServer = ftpServerService.query(ftpId);
	                    FtpClient client = new FtpClient(ftpServer);
	                    client.upload(uploadedFile, ftpFilePath, newFileName);
	                    if (StringUtil.empty(http_url)) {
	                        http_url = ftpServer.getVisit_url() + ("/" + ftpFileUrl + "/" + newFileName).replace("//", "/");
	                    }
	                }
	            }
	        } catch (Exception e) {
	            log.error("上传文件失败！！！", e);
	            return ViewFactory.json(getError("上传文件失败。"));
	        }
	        if (StringUtil.empty(http_url)) {
	            http_url = ("/" + ftpFileUrl + "/" + newFileName).replace("//", "/");
	        }
	        SysFile f = new SysFile();
	        f.setHttp_url(http_url);
	        f.setName(file.getFileName());
	        f.setPath("/" + ftpFileUrl);
	        f.setFile_size(file.getSize() + "");
	        f.setUpload_time(DateTime.currentTimeStamp());
	        f.setType(dirName);
	        f.setSave_name(newFileName);
	        sysFileService.insert(f);
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("error", 0);
	        map.put("url", http_url);
	        return ViewFactory.json(map);
	    }

}
