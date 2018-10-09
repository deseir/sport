package com.moerlong.carloan.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 应用参数配置
 *
 * @author liuzg
 * @date 2017-05-24 20:37
 */
@Configuration
@ConfigurationProperties(prefix = SystemProperties.CONF_PREFIX)
public class SystemProperties {

	public static final String CONF_PREFIX = "system";

	/** 支付请求参数 */
	public static class Paycenter {
		/**代扣请求路径*/
		private String costUrl;
		/**代扣查询路径*/
		private String costQueryUrl;
		/**代付请求路径*/
		private String payUrl;
		/**代付查询路径*/
		private String payQueryUrl;
		/**单笔代扣查询路径*/
		private String singleCostQueryUrl;
		/**单笔代付查询路径*/
		private String singlePayQueryUrl;

		public String getCostUrl() {
			return costUrl;
		}

		public void setCostUrl(String costUrl) {
			this.costUrl = costUrl;
		}

		public String getCostQueryUrl() {
			return costQueryUrl;
		}

		public void setCostQueryUrl(String costQueryUrl) {
			this.costQueryUrl = costQueryUrl;
		}

		public String getPayUrl() {
			return payUrl;
		}

		public void setPayUrl(String payUrl) {
			this.payUrl = payUrl;
		}

		public String getPayQueryUrl() {
			return payQueryUrl;
		}

		public void setPayQueryUrl(String payQueryUrl) {
			this.payQueryUrl = payQueryUrl;
		}

		public String getSingleCostQueryUrl() {
			return singleCostQueryUrl;
		}

		public void setSingleCostQueryUrl(String singleCostQueryUrl) {
			this.singleCostQueryUrl = singleCostQueryUrl;
		}

		public String getSinglePayQueryUrl() {
			return singlePayQueryUrl;
		}

		public void setSinglePayQueryUrl(String singlePayQueryUrl) {
			this.singlePayQueryUrl = singlePayQueryUrl;
		}
	}


	private Paycenter paycenter;

	public Paycenter getPaycenter() {
		return paycenter;
	}

	public void setPaycenter(Paycenter paycenter) {
		this.paycenter = paycenter;
	}
}
