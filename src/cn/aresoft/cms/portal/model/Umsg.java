package cn.aresoft.cms.portal.model;

import cn.aresoft.common.model.BaseModel;

import com.puff.framework.annotation.Column;
import com.puff.framework.annotation.PrimaryKey;
import com.puff.framework.annotation.Table;

@Table("u_msg")
public class Umsg extends BaseModel {
		/**
		 * 客户留言表
		 */
		private static final long serialVersionUID = 614175932606410339L;
		@PrimaryKey.IDWORKER
		@Column
		private String id;//id
		@Column
		private String msg_id;//活动(产品)id
		@Column
		private String name;//姓名(名称)
		@Column
		private String mobile;//手机号
		@Column
		private String email;//邮箱
		@Column
		private String type_id;//配置管理里边可配置
		@Column
		private String type;//'1:活动  2:产品'
		@Column
		private String msg_name;//活动(产品)名称
		@Column
		private String create_time;//创建时间
		@Column
		private String update_time;//更新时间
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMsg_id() {
			return msg_id;
		}
		public void setMsg_id(String msg_id) {
			this.msg_id = msg_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getType_id() {
			return type_id;
		}
		public void setType_id(String type_id) {
			this.type_id = type_id;
		}
		public String getMsg_name() {
			return msg_name;
		}
		public void setMsg_name(String msg_name) {
			this.msg_name = msg_name;
		}
		public String getCreate_time() {
			return create_time;
		}
		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}
		public String getUpdate_time() {
			return update_time;
		}
		public void setUpdate_time(String update_time) {
			this.update_time = update_time;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
}
