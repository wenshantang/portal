package cn.aresoft.cms.portal.model;

import cn.aresoft.common.model.BaseModel;

import com.puff.framework.annotation.Column;
import com.puff.framework.annotation.PrimaryKey;
import com.puff.framework.annotation.Table;

@Table("tb_product_report")
public class ProductReport extends BaseModel {
	private static final long serialVersionUID = 614175932606410339L;
		/**
		 * 产品报告表
		 */
		@PrimaryKey.IDWORKER
		@Column
		private String i_id;//'主键',
		@Column
		private String pro_code;//产品代码
		@Column
		private String pro_name;//产品名称
		@Column
		private String report_name;//报告名称
		@Column
		private String url;//报告链接
		@Column
		private String d_createtime;//建立时间
		@Column
		private String i_createuser;//建立人
		@Column
		private String d_lastmodtime;//最后修改时间
		@Column
		private String i_moduser;//修改人
		public String getI_id() {
			return i_id;
		}
		public void setI_id(String i_id) {
			this.i_id = i_id;
		}
		public String getPro_code() {
			return pro_code;
		}
		public void setPro_code(String pro_code) {
			this.pro_code = pro_code;
		}
		public String getPro_name() {
			return pro_name;
		}
		public void setPro_name(String pro_name) {
			this.pro_name = pro_name;
		}
		public String getReport_name() {
			return report_name;
		}
		public void setReport_name(String report_name) {
			this.report_name = report_name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getD_createtime() {
			return d_createtime;
		}
		public void setD_createtime(String d_createtime) {
			this.d_createtime = d_createtime;
		}
		public String getI_createuser() {
			return i_createuser;
		}
		public void setI_createuser(String i_createuser) {
			this.i_createuser = i_createuser;
		}
		public String getD_lastmodtime() {
			return d_lastmodtime;
		}
		public void setD_lastmodtime(String d_lastmodtime) {
			this.d_lastmodtime = d_lastmodtime;
		}
		public String getI_moduser() {
			return i_moduser;
		}
		public void setI_moduser(String i_moduser) {
			this.i_moduser = i_moduser;
		}
		
	
}
